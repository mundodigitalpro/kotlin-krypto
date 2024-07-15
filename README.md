# Krypto Kotlin

Krypto Kotlin es una aplicación de línea de comandos desarrollada en Kotlin que proporciona información actualizada sobre criptomonedas utilizando la API de CoinCap.

## Características

- Obtener información detallada de una criptomoneda específica
- Ver las top 10 criptomonedas por capitalización de mercado
- Interfaz de línea de comandos interactiva
- Utiliza la API de CoinCap para obtener datos en tiempo real
- Implementación con Kotlin y Ktor para peticiones HTTP asíncronas

## Requisitos previos

- JDK 17 o superior
- Gradle 7.0 o superior
- Docker (opcional, para ejecutar en contenedor)

## Instalación

1. Clona este repositorio:
   ```
   git clone https://github.com/TU_USUARIO/krypto-kotlin.git
   cd krypto-kotlin
   ```

2. Compila el proyecto:
   ```
   ./gradlew build
   ```

## Uso

Para ejecutar la aplicación directamente:

```
./gradlew run
```

Sigue las instrucciones en pantalla para:
1. Ver información detallada de una criptomoneda específica
2. Ver las top 10 criptomonedas
3. Salir de la aplicación

## Ejecutar con Docker

1. Construye la imagen Docker:
   ```
   docker build -t app-crypto-kotlin .
   ```

2. Ejecuta el contenedor:
   ```
   docker run -it app-crypto-kotlin
   ```

## Estructura del Proyecto

```
krypto-kotlin/
│
├── src/
│   └── main/
│       └── kotlin/
│           └── Main.kt
│
├── build.gradle.kts
├── Dockerfile
└── README.md
```

- `src/main/kotlin/Main.kt`: Contiene el código fuente principal de la aplicación
- `build.gradle.kts`: Archivo de configuración de Gradle con las dependencias y configuraciones del proyecto
- `Dockerfile`: Configuración para crear un contenedor Docker de la aplicación

## Dependencias principales

- Ktor Client: Para realizar peticiones HTTP a la API de CoinCap
- Kotlinx Serialization: Para la serialización y deserialización de JSON
- Kotlinx Coroutines: Para manejar operaciones asíncronas
- Logback: Para logging (implementación de SLF4J)

## Configuración

El proyecto utiliza la API pública de CoinCap, que no requiere autenticación para las operaciones básicas. Sin embargo, ten en cuenta que puede haber límites de tasa para las solicitudes.

## Contribuir

Las contribuciones son bienvenidas. Por favor, sigue estos pasos para contribuir:

1. Haz un fork del repositorio
2. Crea una nueva rama (`git checkout -b feature/AmazingFeature`)
3. Haz commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Haz push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Problemas conocidos

- La aplicación actualmente no maneja límites de tasa de la API. En uso intensivo, podrías encontrar errores relacionados con esto.

## Futuras mejoras

- Implementar caché para reducir el número de llamadas a la API
- Añadir gráficos de precios históricos
- Implementar alertas de precio

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## Contacto

Jose Jordan - info@josejordan.dev

Link del proyecto: [https://github.com/mundodigitalpro/krypto-kotlin](https://github.com/mundodigitalpro/krypto-kotlin)

---

Desarrollado con ❤️ por Jose Jordan