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
