/*------------------------CREATE TABLES------------------------*/

CREATE TABLE IF NOT EXISTS usuario (
    id BIGSERIAL NOT NULL,
    nome VARCHAR,
sobrenome VARCHAR,
senha VARCHAR,
email VARCHAR,
 
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT usuario_PK PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS venda (
    id BIGSERIAL NOT NULL,
    datavenda DATE,
cliente VARCHAR,
 
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT venda_PK PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS itemvenda (
    id BIGSERIAL NOT NULL,
    quantidade INTEGER,
produto_id BIGINT,

    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT itemvenda_PK PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS produto (
    id BIGSERIAL NOT NULL,
    valor INTEGER,
categoria VARCHAR,
nome VARCHAR,

    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT produto_PK PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS papel (
    id BIGSERIAL NOT NULL,
    nome VARCHAR,
descricao VARCHAR,
  
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT papel_PK PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS permissao (
    id BIGSERIAL NOT NULL,
    nome VARCHAR,
descricao VARCHAR,
 
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT permissao_PK PRIMARY KEY (id)
);


    CREATE TABLE IF NOT EXISTS usuario_papel (
    usuario_id BIGINT NOT NULL,
    papel_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, papel_id),
    CONSTRAINT usuario_papel_papel_FK FOREIGN KEY (papel_id) REFERENCES papel (id),
    CONSTRAINT usuario_papel_usuario_FK FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);          CREATE TABLE IF NOT EXISTS papel_permissao (
    id BIGSERIAL NOT NULL,
    papel_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    CONSTRAINT papel_permissao_PK PRIMARY KEY (id),
    CONSTRAINT papel_permissao_permissao_FK FOREIGN KEY (permissao_id) REFERENCES permissao (id),
    CONSTRAINT papel_permissao_papel_FK FOREIGN KEY (papel_id) REFERENCES papel (id)
);    

/*------------------------ADD CONSTRAINT VALUES------------------------*/

         ALTER TABLE itemvenda
    ADD CONSTRAINT itemvenda_produto_FK FOREIGN KEY (produto_id) REFERENCES produto (id);
    
          

/*------------------------INSERT VALUES------------------------*/

INSERT INTO usuario(email, senha) VALUES ('admin', '4f26aeafdb2367620a393c973eddbe8f8b846ebd');

   INSERT INTO papel(nome, descricao) VALUES ('Admin', 'Role that allows the user to access all items in the system.');

INSERT INTO permissao(nome, descricao) VALUES ('read-*', 'permission.read.all'), ('insert-*', 'permission.insert.all'), ('update-*', 'permission.update.all'), ('delete-*', 'permission.delete.all'), ('associate-*', 'permission.associate.all');



    INSERT INTO usuario_papel (usuario_id, papel_id) VALUES (1, 1);

          INSERT INTO papel_permissao (papel_id, permissao_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);

    


