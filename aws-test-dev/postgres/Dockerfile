FROM postgres:9.4.19

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 1234
ENV POSTGRES_DB aws-test

COPY tables.sql /docker-entrypoint-initdb.d/
