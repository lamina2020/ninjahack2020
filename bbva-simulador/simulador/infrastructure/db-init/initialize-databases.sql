CREATE ROLE super WITH LOGIN PASSWORD 'super';

CREATE ROLE usuarios WITH LOGIN PASSWORD 'usuarios' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE usuarios_database;
GRANT ALL PRIVILEGES ON DATABASE usuarios_database TO usuarios ;
GRANT ALL PRIVILEGES ON DATABASE usuarios_database TO super;

CREATE ROLE cuentas WITH LOGIN PASSWORD 'cuentas' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE cuentas_database;
GRANT ALL PRIVILEGES ON DATABASE cuentas_database TO cuentas;
GRANT ALL PRIVILEGES ON DATABASE cuentas_database TO super;

CREATE ROLE tarjetas WITH LOGIN PASSWORD 'tarjetas' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE tarjetas_database;
GRANT ALL PRIVILEGES ON DATABASE tarjetas_database TO tarjetas;
GRANT ALL PRIVILEGES ON DATABASE tarjetas_database TO super;

CREATE ROLE creditos WITH LOGIN PASSWORD 'creditos' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE creditos_database;
GRANT ALL PRIVILEGES ON DATABASE creditos_database TO creditos;
GRANT ALL PRIVILEGES ON DATABASE creditos_database TO super;

CREATE ROLE valores WITH LOGIN PASSWORD 'valores' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE valores_database;
GRANT ALL PRIVILEGES ON DATABASE valores_database TO valores;
GRANT ALL PRIVILEGES ON DATABASE valores_database TO super;
