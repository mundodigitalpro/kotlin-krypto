# Usamos una imagen base de OpenJDK
FROM openjdk:17-slim as build

# Instalamos las herramientas necesarias
RUN apt-get update && apt-get install -y curl unzip

# Copiamos los archivos del proyecto
WORKDIR /app
COPY . .

# Damos permiso de ejecución al gradlew
RUN chmod +x ./gradlew

# Compilamos el proyecto con Gradle
RUN ./gradlew build

# Imagen final
FROM openjdk:17-slim

WORKDIR /app

# Copiamos el jar compilado desde la etapa de build
COPY --from=build /app/build/libs/*.jar ./app.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]