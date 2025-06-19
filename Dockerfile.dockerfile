# Usa la imagen oficial de Java 21
FROM eclipse-temurin:21-jdk

# Establece el directorio de trabajo
WORKDIR /app

COPY .. .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Empaqueta el proyecto
RUN ./mvnw clean package -DskipTests

# Ejecuta el .jar generado
CMD ["java", "-jar", "target/tubolsillo-backend-0.0.1-SNAPSHOT.jar"]
