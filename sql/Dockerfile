FROM mariadb:latest

COPY database_ddl.sql /docker-entrypoint-initdb.d/database_ddl.sql
COPY sample_data_insert.sql /docker-entrypoint-initdb.d/sample_data_insert.sql

ENV MARIADB_ROOT_PASSWORD='root'
ENV MARIADB_ROOT_HOST='localhost'

EXPOSE 3306