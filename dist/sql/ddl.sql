CREATE DATABASE beacon DEFAULT CHARACTER SET utf8mb4;
USE beacon;
CREATE TABLE `habit`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT,
    `openId`      varchar(128) NOT NULL DEFAULT '0',
    `title`       varchar(32)  NOT NULL DEFAULT '',
    `content`     text         NOT NULL ,
    `frequency`   smallint(6)  NOT NULL DEFAULT 0,
    `duration`    smallint(6)  NOT NULL DEFAULT 0,
    `create_time` datetime     NOT NULL ,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `habit_daily`
(
    `id`       int(11)      NOT NULL AUTO_INCREMENT,
    `habit_id` int(11)      NOT NULL DEFAULT 0,
    `date`     datetime     NOT NULL ,
    `count`    mediumint(9) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `habit_tag`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `habit_id` int(11) NOT NULL DEFAULT 0,
    `tag_id`   int(11) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `tag`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT,
    `name`        varchar(32) NOT NULL DEFAULT '',
    `description` varchar(32) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_auth`
(
    `id`          int(10) unsigned NOT NULL AUTO_INCREMENT,
    `open_id`     varchar(128)     NOT NULL DEFAULT '',
    `user_name`   varchar(32)      NOT NULL DEFAULT '',
    `session_key` varchar(128)     NOT NULL DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_auth_open_id_uindex` (`open_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;






