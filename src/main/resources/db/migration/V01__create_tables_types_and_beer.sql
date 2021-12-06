CREATE TABLE beer_type (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE beer (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	sku VARCHAR(50) NOT NULL,
	name VARCHAR(80) NOT NULL,
	description TEXT NOT NULL,
	beer_value DECIMAL(10, 2) NOT NULL,
	alcohol_percentage DECIMAL(10, 2) NOT NULL,
	commission DECIMAL(10, 2) NOT NULL,
	flavor VARCHAR(50) NOT NULL,
	origin VARCHAR(50) NOT NULL,
	type_code BIGINT(20) NOT NULL,
	FOREIGN KEY(type_code) REFERENCES beer_type(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO beer_type VALUES(0, "Amber Lager");
INSERT INTO beer_type VALUES(0, "Dark Lager");
INSERT INTO beer_type VALUES(0, "Pale Lager");
INSERT INTO beer_type VALUES(0, "Pilsner");