FROM gradle:4.4.1-jdk8
WORKDIR /mobileparking
COPY build.gradle .
COPY settings.gradle .
COPY src ./src/
COPY common-client ./common-client/
COPY common-db ./common-db/
COPY common-util ./common-util/
COPY mobile-api ./mobile-api/
COPY libs ./libs/
USER root
CMD gradle mobile-api:build --console plain \
    && java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar mobile-api/build/libs/mobile-api-1.0.0.jar