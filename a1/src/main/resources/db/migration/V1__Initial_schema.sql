CREATE TABLE IF NOT EXISTS question(
  id int NOT NULL AUTO_INCREMENT,
  title VARCHAR(32) NOT NULL,
  body VARCHAR(64),
  username VARCHAR(32) NOT NULL,
  date VARCHAR(64),
  PRIMARY KEY(id)
);
CREATE TABLE IF NOT EXISTS user(
  id int NOT NULL AUTO_INCREMENT,
  username VARCHAR(32) NOT NULL,
  password VARCHAR(32) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS tag(
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(32),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS question_tag(
  id_tag int NOT NULL,
  id_question int NOT NULL,
  FOREIGN KEY (id_tag) REFERENCES tag(id),
  FOREIGN KEY (id_question) REFERENCES question(id)
);

CREATE TABLE IF NOT EXISTS answer(
  id int NOT NULL AUTO_INCREMENT,
  body VARCHAR(100),
  username VARCHAR(32) NOT NULL,
  date VARCHAR(64),
  question_id int NOT NULL,
  PRIMARY KEY(id)
);