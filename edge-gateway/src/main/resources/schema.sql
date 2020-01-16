DROP TABLE IF EXISTS device;
CREATE TABLE device (
    device_id varchar(256) NOT NULL,
    device_sequence int(11) NOT NULL,
    device_name varchar(256) NOT NULL,
    password varchar(256) NOT NULL,
    PRIMARY KEY(device_id)
);