CREATE TABLE request_item (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  quantity int(11) NOT NULL,
  request_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (request_id) REFERENCES requests(id)
);