CREATE TABLE Mesa (
  id INTEGER PRIMARY KEY,
  numero VARCHAR(10) NOT NULL,
  CONSTRAINT Mesa_Numero_UQ UNIQUE (numero)
);

CREATE TABLE Pedido (
  id INTEGER PRIMARY KEY,
  cliente VARCHAR(100) NOT NULL,
  estado VARCHAR(100) NOT NULL,
  id_mesa INTEGER NOT NULL,
  CONSTRAINT Pedido_Mesa_FK FOREIGN KEY (id_mesa) REFERENCES Mesa (id)
);

CREATE TABLE Adicional (
  id INTEGER PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  precio INTEGER NOT NULL,
  CONSTRAINT Adicional_Nombre_UQ UNIQUE (nombre)
);

CREATE TABLE PedidoAdicional (
  id_pedido INTEGER,
  id_adicional INTEGER,
  CONSTRAINT PedidoAdicional_PK PRIMARY KEY (id_pedido, id_adicional),
  CONSTRAINT PedidoAdicional_Pedido_FK FOREIGN KEY (id_pedido) REFERENCES Pedido (id),
  CONSTRAINT PedidoAdicional_Adicional_FK FOREIGN KEY (id_adicional) REFERENCES Adicional (id)
);

CREATE TABLE OpcionSopa (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100),
    CONSTRAINT OpcionSopa_nombre_UQ UNIQUE(nombre)
);

CREATE TABLE OpcionPrincipio (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100),
    CONSTRAINT OpcionPrincipio_nombre_UQ UNIQUE(nombre)
);

CREATE TABLE OpcionCarne (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100),
    CONSTRAINT OpcionCarne_nombre_UQ UNIQUE(nombre)
);

CREATE TABLE OpcionEnsalada (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100),
    CONSTRAINT OpcionEnsalada_nombre_UQ UNIQUE(nombre)
);

CREATE TABLE OpcionJugo (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100),
    CONSTRAINT OpcionJugo_nombre_UQ UNIQUE(nombre)
);

CREATE TABLE Corrientazo (
    id_pedido INTEGER PRIMARY KEY,
    precio INTEGER,
    id_sopa INTEGER,
    id_principio INTEGER,
    id_carne INTEGER,
    id_ensalada INTEGER,
    id_jugo INTEGER,
    CONSTRAINT Corrientazo_id_pedido_FK FOREIGN KEY(id_pedido) REFERENCES Pedido(id),
    CONSTRAINT Corrientazo_id_sopa_pedido_FK FOREIGN KEY(id_sopa) REFERENCES OpcionSopa(id),
    CONSTRAINT Corrientazo_id_principio_pedido_FK FOREIGN KEY(id_principio) REFERENCES OpcionPrincipio(id),
    CONSTRAINT Corrientazo_id_carne_pedido_FK FOREIGN KEY(id_carne) REFERENCES OpcionCarne(id),
    CONSTRAINT Corrientazo_id_Ensalada_pedido_FK FOREIGN KEY(id_ensalada) REFERENCES OpcionEnsalada(id),
    CONSTRAINT Corrientazo_id_jugo_pedido_FK FOREIGN KEY(id_jugo) REFERENCES OpcionJugo(id)
);
