FROM eclipse-temurin:22-jdk-alpine AS runtime
ENV SPRING_PROFILES_ACTIVE=docker
#Installation de Maven et dos2unix
RUN apk add --no-cache maven dos2unix

WORKDIR /app
COPY . .

# Création du script d'entrypoint qui installera les dépendances et lancera l'application
RUN echo '#!/bin/sh' > /entrypoint.sh && \
    echo 'mvn install:install-file -Dfile=/libs/shareddto-1.0-SNAPSHOT.jar -DgroupId=com.medilabo -DartifactId=shareddto -Dversion=1.0-SNAPSHOT -Dpackaging=jar' >> /entrypoint.sh && \
    echo 'mvn install:install-file -Dfile=/libs/sharedinterface-1.0-SNAPSHOT.jar -DgroupId=com.medilabo -DartifactId=sharedinterface -Dversion=1.0-SNAPSHOT -Dpackaging=jar' >> /entrypoint.sh && \
    echo 'mvn clean package spring-boot:repackage -DskipTests' >> /entrypoint.sh && \
    echo 'java -jar target/riskservice-1.0-SNAPSHOT.jar' >> /entrypoint.sh && \
    chmod +x /entrypoint.sh && \
    dos2unix /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]