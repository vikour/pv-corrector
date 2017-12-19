

CREATE TABLE modulos (
   nombre VARCHAR(50),
   alpha  NUMERIC DEFAULT 0,
   beta   NUMERIC DEFAULT 0,
   gamma  NUMERIC DEFAULT 0,
   kappa  NUMERIC DEFAULT 0,
   tecnologia VARCHAR(100),
   isc NUMERIC DEFAULT 0,
   pmax NUMERIC DEFAULT 0,
   voc NUMERIC DEFAULT 0,
   ipmax NUMERIC DEFAULT 0,
   vpmax NUMERIC DEFAULT 0,
   iscn NUMERIC DEFAULT 0,
   pmaxn NUMERIC DEFAULT 0,
   noct NUMERIC DEFAULT 0,
   vocn NUMERIC DEFAULT 0,
   ipmaxn NUMERIC DEFAULT 0,
   vpmaxn NUMERIC DEFAULT 0,
   mt1 NUMERIC DEFAULT 0,
   pdv NUMERIC DEFAULT 0,
   pdc NUMERIC DEFAULT 0,
   pdp NUMERIC DEFAULT 0,
   tinicial NUMERIC DEFAULT 0,
   tintermedia NUMERIC DEFAULT 0,
   tfinal NUMERIC DEFAULT 0, 
   ptramo NUMERIC DEFAULT 0,
   stramo NUMERIC DEFAULT 0,
   cmaxp NUMERIC DEFAULT 0,
   cmaxN NUMERIC DEFAULT 0,
   ns NUMERIC DEFAULT 0,
   eta NUMERIC DEFAULT 0,
   m NUMERIC DEFAULT 0,
   rs NUMERIC DEFAULT 0,
   np NUMERIC DEFAULT 0,
   minIsc NUMERIC DEFAULT 0,
   minPmax NUMERIC DEFAULT 0,
   minVoc NUMERIC DEFAULT 0,
   minFF NUMERIC DEFAULT 0,
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
   nombre VARCHAR(50) PRIMARY KEY
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
   imax_v  NUMERIC       NOT NULL,
   imax_m  VARCHAR(10)   NOT NULL,
   vmax_v  NUMERIC       NOT NULL,
   vmax_m  VARCHAR(10)   NOT NULL,
   ff_v    NUMERIC       NOT NULL,
   UNIQUE(fecha,hora)
);

CREATE TABLE curvas_medidas (
   id      INTEGER,
   modulo     VARCHAR(50)   NOT NULL,
   campanya   VARCHAR(50)   NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY (campanya,modulo) REFERENCES campanyas(nombre,modulo)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (id) REFERENCES curvas_iv(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE medidas_canal (
    curva_iv   INTEGER NOT NULL,
    valor      NUMERIC   NOT NULL,
    magnitud   VARCHAR(20)  NOT NULL,
    canal      VARCHAR(50)  NOT NULL,
    PRIMARY KEY (curva_iv,canal),
   FOREIGN KEY (curva_iv) REFERENCES curvas_medidas(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE,
   FOREIGN KEY (canal) REFERENCES canales(nombre)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE medidas_curvas (
    curva_iv    INTEGER,
    valor       NUMERIC  NOT NULL,
    magnitud    VARCHAR(20) NOT NULL,
    orden       INTEGER,
    tipo        INTEGER    NOT NULL,
    PRIMARY KEY(curva_iv,orden, tipo),
   FOREIGN KEY (curva_iv) REFERENCES curvas_iv(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

CREATE TABLE curvas_corregidas (
   id      INTEGER,
   metodo  VARCHAR(50),
   FOREIGN KEY (id) REFERENCES curvas_iv(id)
      ON UPDATE CASCADE
      ON DELETE CASCADE
);

