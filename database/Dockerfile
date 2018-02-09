FROM postgres:10.1-alpine

ADD docker-entrypoint-initdb.d /docker-entrypoint-initdb.d
ADD https://raw.githubusercontent.com/tpolecat/doobie/series/0.5.x/world.sql world.sql
RUN chown -R postgres:postgres /world.sql
