CREATE TABLE `DEPARTMENT` (
    `id` int(11) NOT NULL UNIQUE,
    `name` varchar(20) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `PERSON` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `userid` varchar(50) NOT NULL,
    `name` varchar(20) NOT NULL DEFAULT '',
    `department_id` int(11) DEFAULT NULL,
    `position` varchar(50) DEFAULT NULL,
    `gender` int(2) DEFAULT 1,
    `phone` varchar(50) DEFAULT NULL,
    `email` varchar(50) DEFAULT NULL,
    `weixinid` varchar(50) DEFAULT NULL,
    `avatar` varchar(200) DEFAULT NULL,
    `status` int(2) DEFAULT 0,
    `manager` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`department_id`)
        REFERENCES DEPARTMENT(`id`)
        ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
CREATE TABLE `MESSAGE` (
    `id` bigint(11) unsigned NOT NULL,
    `to_user_name` varchar(50),
    `from_user_name` varchar(50),
    `create_time` bigint(11),
    `msg_type` varchar(20),
    `content` text,
    `media_id` varchar(200),
    `format` varchar(20),
    `thumb_media_id` varchar(200),
    `location_x` double,
    `location_y` double,
    `scale` int(4),
    `label` varchar(30),
    `agent_id` int(2),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `EVENTS` (
    `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `to_user_name` varchar(50),
    `from_user_name` varchar(50),
    `create_time` bigint(11),
    `event` varchar(50),
    `latitude` double,
    `longitude` double,
    `geo_precision` double,
    `event_key` int(2),
    `agent_id` int(2),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
