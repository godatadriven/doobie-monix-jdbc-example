#!/bin/sh
psql -U postgres <<-SQL
  CREATE DATABASE "world";
  CREATE ROLE demo;
  GRANT ALL ON DATABASE "world" TO demo;
  REVOKE CONNECT ON DATABASE "world" FROM PUBLIC;
  GRANT CONNECT ON DATABASE "world" TO demo;
  ALTER DATABASE "world" OWNER TO demo;
  ALTER ROLE demo WITH LOGIN;
SQL

psql -c '\i world.sql' -d world -U demo
