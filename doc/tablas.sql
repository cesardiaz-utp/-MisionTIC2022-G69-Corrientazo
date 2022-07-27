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
