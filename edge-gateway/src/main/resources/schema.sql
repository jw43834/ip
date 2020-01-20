DROP TABLE IF EXISTS device;
CREATE TABLE device (
    device_id varchar(256) NOT NULL,
    device_sequence int(11) NOT NULL,
    device_name varchar(256) NOT NULL,
    password varchar(256) NOT NULL,
    PRIMARY KEY(device_id)
);

CREATE TABLE sensor (
    sensor_id varchar(256) NOT NULL,
    sensor_sequence int(11) NOT NULL,
    sensor_name varchar(256) NOT NULL,
    device_id varchar(256) NOT NULL,
    PRIMARY KEY(sensor_id, device_id)
);

CREATE TABLE collection_data (
    collection_data_sequence int(11) NOT NULL,
    device_id varchar(256) NOT NULL,
    sensor_id varchar(256) NOT NULL,
    occurrence_time timestamp,
    PRIMARY KEY(collection_data_sequence)
);