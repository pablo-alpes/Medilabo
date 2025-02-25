FROM eclipse-temurin:22-jdk-alpine AS runtime
#we execute in runtime to have access to the volume, from build is not possible
RUN apk add --no-cache maven dos2unix
#maven install and conversion eof between dos to unix, nocache avoids storage of packages in the image

WORKDIR /app
COPY . .

#runs the bash code to install the shareddto dependency and then installs and copies into the libs the generated sharedinterface file
RUN echo '#!/bin/sh' > /entrypoint.sh && \
    echo 'mvn install:install-file -Dfile=/libs/shareddto-1.0-SNAPSHOT.jar -DgroupId=com.medilabo -DartifactId=shareddto -Dversion=1.0-SNAPSHOT -Dpackaging=jar' >> /entrypoint.sh && \
    echo 'mvn clean package -DskipTests' >> /entrypoint.sh && \
    echo 'cp /app/target/sharedinterface-1.0-SNAPSHOT.jar /libs/sharedinterface-1.0-SNAPSHOT.jar' >> /entrypoint.sh && \
    chmod +x /entrypoint.sh && \
    dos2unix /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]