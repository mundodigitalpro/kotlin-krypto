package dev.josejordan
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

const val BASE_URL = "https://api.coincap.io/v2"

@Serializable
data class CryptoResponse(val data: CryptoInfo)

@Serializable
data class CryptoInfo(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val priceUsd: String,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val changePercent24Hr: String,
    val supply: String,
    val maxSupply: String?
)

@Serializable
data class TopCryptosResponse(val data: List<CryptoInfo>)

val client = HttpClient(CIO) {
    install(ContentNegotiation) {
        gson()
    }
}

suspend fun obtenerInfoCriptomoneda(idCripto: String): CryptoInfo? {
    return try {
        val response: HttpResponse = client.get("$BASE_URL/assets/$idCripto")
        val cryptoResponse: CryptoResponse = response.body()
        cryptoResponse.data
    } catch (e: Exception) {
        println("Error al obtener información: ${e.message}")
        null
    }
}

fun mostrarInfoCriptomoneda(info: CryptoInfo?) {
    if (info == null) {
        println("No se pudo obtener la información.")
        return
    }

    val precio = info.priceUsd.toDoubleOrNull() ?: 0.0
    val capMercado = info.marketCapUsd.toDoubleOrNull() ?: 0.0
    val volumen24h = info.volumeUsd24Hr.toDoubleOrNull() ?: 0.0
    val cambio24h = info.changePercent24Hr.toDoubleOrNull() ?: 0.0

    println("\nInformación de ${info.name} (${info.symbol}):")
    println("Ranking: #${info.rank}")
    println("Precio actual: $${String.format("%.2f", precio)}")
    println("Capitalización de mercado: $${String.format("%,.0f", capMercado)}")
    println("Volumen en 24h: $${String.format("%,.0f", volumen24h)}")
    println("Cambio de precio en 24h: ${String.format("%.2f", cambio24h)}%")
    println("Oferta circulante: ${String.format("%,.0f", info.supply.toDoubleOrNull() ?: 0.0)} ${info.symbol}")
    if (info.maxSupply != null) {
        println("Oferta máxima: ${String.format("%,.0f", info.maxSupply.toDoubleOrNull() ?: 0.0)} ${info.symbol}")
    } else {
        println("Oferta máxima: No especificada")
    }
}

suspend fun obtenerTopCriptomonedas(limite: Int = 10): List<CryptoInfo>? {
    return try {
        val response: HttpResponse = client.get("$BASE_URL/assets") {
            parameter("limit", limite.toString())
        }
        val topCryptosResponse: TopCryptosResponse = response.body()
        topCryptosResponse.data
    } catch (e: Exception) {
        println("Error al obtener top criptomonedas: ${e.message}")
        null
    }
}

fun mostrarTopCriptomonedas(topCriptos: List<CryptoInfo>?) {
    if (topCriptos == null) {
        println("No se pudo obtener la lista de criptomonedas.")
        return
    }

    println("\nTop Criptomonedas por Capitalización de Mercado:")
    topCriptos.forEach { cripto ->
        val precio = cripto.priceUsd.toDoubleOrNull() ?: 0.0
        val capMercado = cripto.marketCapUsd.toDoubleOrNull() ?: 0.0
        println("${cripto.rank}. ${cripto.name} (${cripto.symbol}): $${String.format("%.2f", precio)} - Cap. Mercado: $${String.format("%,.0f", capMercado)}")
    }
}

fun main() = runBlocking {
    println("Bienvenido a la aplicación de información de criptomonedas")

    while (true) {
        println("\nOpciones:")
        println("1. Ver información detallada de una criptomoneda")
        println("2. Ver top 10 criptomonedas")
        println("3. Salir")

        when (readLine()) {
            "1" -> {
                print("Ingrese el ID de una criptomoneda (ej. 'bitcoin', 'ethereum'): ")
                val idCripto = readLine()?.toLowerCase() ?: continue
                val info = obtenerInfoCriptomoneda(idCripto)
                mostrarInfoCriptomoneda(info)
            }
            "2" -> {
                val topCriptos = obtenerTopCriptomonedas()
                mostrarTopCriptomonedas(topCriptos)
            }
            "3" -> {
                println("¡Gracias por usar la aplicación!")
                client.close()
                return@runBlocking
            }
            else -> println("Opción no válida. Por favor, intente de nuevo.")
        }
    }
}