CREATE TABLE news (
   id INT NOT NULL AUTO_INCREMENT,
   title VARCHAR(100),
   link VARCHAR(100),
   image VARCHAR(100),
   likecount INT,
   commentcount INT,
   createddate DATE,
   userId INT,
   PRIMARY KEY ( id )
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user (
                    id INT NOT NULL AUTO_INCREMENT,
                    name VARCHAR(100),
                    password VARCHAR(256),
                    salt VARCHAR(100),
                    head_url VARCHAR(256),
                    PRIMARY KEY ( id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE loginticket (
                      id INT NOT NULL AUTO_INCREMENT,
                      userId INT,
                      expired Date,
                      status INT,
                      ticket VARCHAR(256),
                      PRIMARY KEY ( id )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;