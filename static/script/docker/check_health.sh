#!/bin/bash
echo "Connected to ${DATABASE_HOST} MySQL at ${DATABASE_PORT}..."
while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
    echo "Trying to connect to MySQL at ${DATABASE_PORT}..."
    sleep 10
done

echo "Connected to ${DATABASE_HOST} MySQL at ${DATABASE_PORT}..."

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar app.jar