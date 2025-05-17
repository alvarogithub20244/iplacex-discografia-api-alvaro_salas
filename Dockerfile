# Etapa de construcción
FROM gradle:jdk21 AS builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

# Corrección del flag: --no-daemon
RUN gradle build --no-daemon

# Etapa final
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copia el JAR generado desde la etapa anterior
COPY --from=builder /app/build/libs/*.jar discografia.jar

EXPOSE 8080

CMD ["java", "-jar", "discografia.jar"]