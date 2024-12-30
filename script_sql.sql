CREATE DATABASE `gestincidencias` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

use gestincidencias;
-- TABLA ROLES

CREATE TABLE ROLES (
    idrol BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    PRIMARY KEY (idrol)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- TABLA USUARIOS
CREATE TABLE USUARIOS (
    idusuario BIGINT NOT NULL AUTO_INCREMENT,
    usuario VARCHAR(255) NOT NULL UNIQUE,
    correo VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    idrol BIGINT,
    PRIMARY KEY (idusuario),
    FOREIGN KEY(idrol) REFERENCES ROLES(idrol)
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;


-- TABLA DEPARTAMENTOS
CREATE TABLE DEPARTAMENTOS(
    iddepartamento BIGINT NOT NULL AUTO_INCREMENT,
    coddepartamento VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    PRIMARY KEY(iddepartamento)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;

-- TABLA TÉCNICOS
CREATE TABLE TECNICOS (
    idtecnico BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    iddepartamento BIGINT,
    PRIMARY KEY (idtecnico),
    FOREIGN KEY(iddepartamento) REFERENCES DEPARTAMENTOS(iddepartamento)
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;


-- TABLA PRIORIDADES
CREATE TABLE PRIORIDADES_INCIDENCIA(
   idprioridad BIGINT NOT NULL AUTO_INCREMENT,
   nombre VARCHAR(255) NOT NULL,
   descripcion VARCHAR(255) NOT NULL,
   PRIMARY KEY(idprioridad)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;

-- TABLA TIPO_INCIDENCIA
CREATE TABLE TIPOS_INCIDENCIA(
    idtipoincidencia BIGINT NOT NULL AUTO_INCREMENT,
    codtipoincidencia VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    PRIMARY KEY(idtipoincidencia)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;

-- TABLA ESTADO_INCIDENCIA
CREATE TABLE ESTADOS_INCIDENCIA(
    idestadoincidencia BIGINT NOT NULL AUTO_INCREMENT,
    codestadoincidencia VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    PRIMARY KEY(idestadoincidencia)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;


-- TABLA INCIDENCIAS
CREATE TABLE INCIDENCIAS(
    idincidencia BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fechacreacion DATETIME(6) NOT NULL,
    idestadoincidencia BIGINT NOT NULL,
    idusuario BIGINT NOT NULL,
    idtipoincidencia BIGINT NOT NULL,
    idnota BIGINT NOT NULL,
    idtecnico BIGINT NOT NULL,
    idprioridadincidencia BIGINT NOT NULL,
    PRIMARY KEY(idincidencia),
    FOREIGN KEY(idestadoincidencia) REFERENCES estados_incidencia(idestadoincidencia),
    FOREIGN KEY(idprioridadincidencia) REFERENCES prioridades_incidencia(idprioridad),
    FOREIGN KEY(idtipoincidencia) REFERENCES tipos_incidencia(idtipoincidencia),
    FOREIGN KEY(idtecnico) REFERENCES tecnicos(idtecnico),
    FOREIGN KEY(idusuario) REFERENCES usuarios(idusuario)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;


-- TABLA NOTAS
CREATE TABLE NOTAS_INCIDENCIA(
	idnotaincidencia BIGINT NOT NULL AUTO_INCREMENT,
	descripcion varchar(500) NOT NULL,
    idincidencia BIGINT,
    PRIMARY KEY(idnotaincidencia),
    FOREIGN KEY(idincidencia) REFERENCES incidencias(idincidencia)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci;

