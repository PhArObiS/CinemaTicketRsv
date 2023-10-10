PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE contacts (name text, phone integer, email text);
INSERT INTO contacts VALUES('Ali',1234,'ali@gmail.com');
INSERT INTO contacts VALUES('Neil',12345,'neillopes@hotmail.com');
INSERT INTO contacts VALUES('Johnny',123456,'johnnyB@gmail.com');
COMMIT;
