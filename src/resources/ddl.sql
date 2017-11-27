

CREATE TABLE modulos (
   nombre VARCHAR(50),
   alpha  NUMERIC,
   beta   NUMERIC,
   gamma  NUMERIC,
   kappa  NUMERIC,
   PRIMARY KEY(nombre)
);

CREATE TABLE campanyas (
   nombre   VARCHAR(50),
   modulo  VARCHAR(50),
   PRIMARY KEY(nombre, modulo),
   FOREIGN KEY (modulo) REFERENCES modulos(nombre)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE canales (
   nombre VARCHAR(50)
);

CREATE TABLE campanya_tiene_canal (
   modulo     VARCHAR(50),
   campanya   VARCHAR(50),
   canal      VARCHAR(50),
   PRIMARY KEY(modulo,campanya,canal),
   FOREIGN KEY (modulo) REFERENCES campanyas(modulo)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (campanya) REFERENCES campanyas(nombre)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (canal) REFERENCES canal(nombre)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE curvas_iv (
   id      INTEGER   PRIMARY KEY,
   tipo    INTEGER   NOT NULL,
   fecha   VARCHAR(20)   NOT NULL,
   hora    VARCHAR(20)   NOT NULL,
   isc_v   NUMERIC       NOT NULL,
   isc_m   VARCHAR(10)   NOT NULL,
   voc_v   NUMERIC       NOT NULL,
   voc_m   VARCHAR(10)   NOT NULL,
   pmax_v  NUMERIC       NOT NULL,
   pmax_m  VARCHAR(10)   NOT NULL,
   vmax_v  NUMERIC       NOT NULL,
   vmax_m  VARCHAR(10)   NOT NULL,
   ff_v    NUMERIC       NOT NULL,
   ff_m    VARCHAR(10)   NOT NULL
);

CREATE TABLE curvas_medidas (
   id      INTEGER,
   modulo     VARCHAR(50)   NOT NULL,
   campanya   VARCHAR(50)   NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY (modulo) REFERENCES campanyas(modulo)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (campanya) REFERENCES campanyas(nombre)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (id) REFERENCES curvas_iv(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE medidas_canal (
    curva_iv   INTEGER PRIMARY KEY,
    valor      NUMERIC   NOT NULL,
    magnitud   VARCHAR(20)  NOT NULL,
   FOREIGN KEY (curva_iv) REFERENCES curvas_medidas(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE medidas_curvas (
    curva_iv    INTEGER,
    valor       NUMERIC  NOT NULL,
    magnitud    VARCHAR(20) NOT NULL,
    orden       INTEGER,
    tipo        INTEGER    NOT NULL,
    PRIMARY KEY(curva_iv,orden),
   FOREIGN KEY (curva_iv) REFERENCES curvas_iv(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE curvas_corregidas (
   id      INTEGER,
   metodo  VARCHAR(50),
   FOREIGN KEY (id) REFERENCES curvas_iv(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (metodo) REFERENCES metodo_correccion(nombre)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE rol_correcciones (
   curva_iv   INTEGER,
   curva_corr INTEGER,
   rol_temp   INTEGER   NOT NULL,
   rol_irr    INTEGER   NOT NULL,
   FOREIGN KEY (curva_iv) REFERENCES curvas_medidas(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
   FOREIGN KEY (curva_corr) REFERENCES curvas_corregidas(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
   FOREIGN KEY (rol_temp) REFERENCES medidas_canal(curva_iv)
      ON UPDATE CASCADE
      ON DELETE RESTRICT
   FOREIGN KEY (rol_irr) REFERENCES medidas_canal(curva_iv)
      ON UPDATE CASCADE
      ON DELETE RESTRICT
);

CREATE TABLE metodo_correccion (
    nombre  VARCHAR(50) PRIMARY KEY
);