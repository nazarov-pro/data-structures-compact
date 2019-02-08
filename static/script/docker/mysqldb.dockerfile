FROM mysql/mysql-server

MAINTAINER Shaheen Nazarov "shahin.nazarov@bestsolutions.az"

# Copy the database initialize script:
# Contents of /docker-entrypoint-initdb.d are run on mysqld startup
# ADD  mysql/ /docker-entrypoint-initdb.d/