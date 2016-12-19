############################################################
#                                                          #
############################################################
#                                                          #


#                                                          #
#                                                          #
############################################################

############################################################


#                                                          #


CREATE TABLE person (
  id          VARCHAR(36)  NOT NULL,
  person_desc VARCHAR(256) NOT NULL DEFAULT '',
  birthdate   DATETIME,
  PRIMARY KEY (id)
);

# for example, set gender_cd = 'M' for the birth event given by event_id
CREATE TABLE person_gender (
  id        VARCHAR(36) NOT NULL,
  gender_cd VARCHAR(3),
  person_id VARCHAR(36) NOT NULL,
  event_id  VARCHAR(36) NOT NULL,
  INDEX person_ind_p1 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

CREATE TABLE person_race (
  id        VARCHAR(36) NOT NULL,
  race_cd   VARCHAR(3),
  person_id VARCHAR(36) NOT NULL,
  event_id  VARCHAR(36) NOT NULL,
  INDEX person_ind_p2 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

CREATE TABLE person_religion (
  id          VARCHAR(36) NOT NULL,
  religion_cd VARCHAR(3),
  person_id   VARCHAR(36) NOT NULL,
  event_id    VARCHAR(36) NOT NULL,
  INDEX person_ind_p3 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);

#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

CREATE TABLE person_contact (
  id                VARCHAR(36)  NOT NULL,
  contact_cd        VARCHAR(3),
  person_id         VARCHAR(36)  NOT NULL,
  contact_person_id VARCHAR(36)  NOT NULL,
  start_event_id    VARCHAR(36)  NOT NULL,
  end_event_id      VARCHAR(36)  NOT NULL,
  contact_desc      VARCHAR(256) NOT NULL DEFAULT '',
  INDEX person_ind_p4 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

CREATE TABLE person_personality (
  id               VARCHAR(36)  NOT NULL,
  personality_cd   VARCHAR(3),
  person_id        VARCHAR(36)  NOT NULL,
  event_id         VARCHAR(36)  NOT NULL,
  personality_desc VARCHAR(256) NOT NULL DEFAULT '',
  INDEX person_ind_p5 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

SELECT *
FROM person_name;
DROP TABLE IF EXISTS person_name;

CREATE TABLE person_name (
  id        VARCHAR(36) NOT NULL,
  person_id VARCHAR(36),
  name_cd   VARCHAR(3),
  prefix    VARCHAR(50),
  first     VARCHAR(100),
  middle    VARCHAR(100),
  last      VARCHAR(100),
  suffix    VARCHAR(50),
  alternate VARCHAR(100),
  name_desc VARCHAR(256),
  title     VARCHAR(100),
  INDEX person_ind_p6 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#
INSERT INTO person
(id, person_desc, birthdate)
VALUES
  (uuid(), 'p3', current_timestamp());

#

SELECT *
FROM person;
############################################################
############################################################
#                                                          #
############################################################
#                                                          #
#                                                          #

INSERT INTO person_contact
(id, contact_cd, person_id, contact_person_id, start_event_id, end_event_id, contact_desc)
VALUES
  (uuid(), 'FRD', 'bcd800ca-c193-11e6-92e8-d017c218b3b1', 'c4f4af33-c193-11e6-92e8-d017c218b3b1', current_timestamp(),
   current_timestamp(), 'a friend from college');

CREATE TABLE person_group (
  id         VARCHAR(36)  NOT NULL,
  group_name VARCHAR(100) NOT NULL,
  group_desc VARCHAR(256) NOT NULL,
  group_cd   VARCHAR(3)   NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE person_group_map (
  id              VARCHAR(36) NOT NULL,
  person_group_id VARCHAR(36) NOT NULL,
  person_id       VARCHAR(36) NOT NULL,
  INDEX person_ind_p10 (person_id),
  INDEX person_ind_p11 (person_group_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  FOREIGN KEY (person_group_id)
  REFERENCES person_group (id),
  PRIMARY KEY (id),
  UNIQUE KEY (person_group_id, person_id)
);


CREATE TABLE name_group (
  id         VARCHAR(36)  NOT NULL,
  group_name VARCHAR(100) NOT NULL,
  group_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE name_group_map (
  person_group_id VARCHAR(36) NOT NULL,
  name_group_id   VARCHAR(36) NOT NULL
);


CREATE TABLE project (
  id              VARCHAR(36)  NOT NULL,
  project_type_id VARCHAR(36)  NOT NULL,
  project_name    VARCHAR(100) NOT NULL,
  project_desc    VARCHAR(256) NOT NULL,
  create_dt       DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by      VARCHAR(40)  NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE project_name_group_map (
  project_id    VARCHAR(36) NOT NULL,
  name_group_id VARCHAR(36) NOT NULL
);


CREATE TABLE creature (
  id            VARCHAR(36)  NOT NULL,
  creature_name VARCHAR(100) NOT NULL,
  creature_desc VARCHAR(256) NOT NULL,
  creature_cd   VARCHAR(3)   NOT NULL,
  PRIMARY KEY (id)
);

# want it so "creature" can have attributes that are defaults for it's kind
# but then specific attributes can be over-ridden for non NPC types

CREATE TABLE creature_attribute_map (
  creature_id VARCHAR(36) NOT NULL,
  attr_id     VARCHAR(36) NOT NULL
);


CREATE TABLE attribute (
  id         VARCHAR(36)  NOT NULL,
  attr_name  VARCHAR(100) NOT NULL,
  attr_desc  VARCHAR(256) NOT NULL,
  attr_short VARCHAR(3)   NOT NULL,
  attr_cd    VARCHAR(3)   NOT NULL,
  UNIQUE (attr_name),
  UNIQUE (attr_short),
  PRIMARY KEY (id)
);


CREATE TABLE creature_attr_defaults (
  id          VARCHAR(36) NOT NULL,
  creature_id VARCHAR(36) NOT NULL,
  attr_id     VARCHAR(36) NOT NULL,
  attr_val    INT DEFAULT 1,
  PRIMARY KEY (id)
);

CREATE TABLE pc_creature (
  id          VARCHAR(36) NOT NULL,
  creature_id VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
);

# these are the over-ridable attribues
CREATE TABLE pc_creature_attribute_map (
  pc_creature_id VARCHAR(36) NOT NULL,
  attr_id        VARCHAR(36) NOT NULL
);

# these are the values of the over-ridden attributes
# event_id gives the start date/time or change date/time for the given attribute
CREATE TABLE pc_creature_attr_value (
  id             VARCHAR(36) NOT NULL,
  pc_creature_id VARCHAR(36) NOT NULL,
  attr_id        VARCHAR(36) NOT NULL,
  event_id       VARCHAR(36),
  attr_val       INT DEFAULT 1,
  PRIMARY KEY (id)
);


CREATE TABLE pc_creature_event_map (
  pc_creature_id VARCHAR(36) NOT NULL,
  event_id       VARCHAR(36) NOT NULL
);

#CREATE TABLE name_creature_map (
#    id VARCHAR(36) NOT NULL,
#    person_group_id VARCHAR(36) NOT NULL,
#    name_group_id VARCHAR(36) NOT NULL,
#    INDEX person_ind_p12 (name_group_id),
#    INDEX person_ind_p13 (person_group_id),
#    FOREIGN KEY (name_group_id)
#        REFERENCES name_group (id),
#    FOREIGN KEY (person_group_id)
#        REFERENCES person_group (id),
#    PRIMARY KEY (id),
#    UNIQUE KEY (person_group_id , name_group_id)
#);


CREATE TABLE person_relative (
  id          VARCHAR(36) NOT NULL,
  person_id   VARCHAR(36),
  relative_id VARCHAR(36),
  relative_cd VARCHAR(3),
  INDEX person_ind_p7 (relative_id),
  FOREIGN KEY (relative_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################


############################################################
#                                                          #
#                                                          #

CREATE TABLE image (
  id       VARCHAR(36)  NOT NULL,
  img_cd   VARCHAR(25)  NOT NULL DEFAULT '',
  img      BLOB         NOT NULL,
  img_size VARCHAR(25)  NOT NULL DEFAULT '',
  img_name VARCHAR(50)  NOT NULL DEFAULT '',
  img_desc VARCHAR(256) NOT NULL DEFAULT '',
  img_tip  VARCHAR(50)  NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);

#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #


CREATE TABLE person_image_map (
  person_id VARCHAR(36) NOT NULL,
  img_id    VARCHAR(36) NOT NULL,
  INDEX person_ind_p8 (person_id),
  INDEX person_ind_p8b (img_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  FOREIGN KEY (img_id)
  REFERENCES image (id)
);

#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

CREATE TABLE person_event (
  id        VARCHAR(36) NOT NULL,
  person_id VARCHAR(36) NOT NULL,
  event_id  VARCHAR(36) NOT NULL,
  INDEX person_ind_p9 (person_id),
  FOREIGN KEY (person_id)
  REFERENCES person (id),
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

CREATE TABLE event_item (
  id         VARCHAR(36)  NOT NULL,
  event_dt   DATETIME     NOT NULL,
  event_cd   VARCHAR(3)   NOT NULL,
  event_desc VARCHAR(256) NOT NULL DEFAULT '',
  event_tip  VARCHAR(50)  NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);

SHOW COLUMNS FROM event_item;
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #

# a location
#
# location_cd:  general, lat/long, address
# G:  use loc_desc
# L:  use latitude/longitude
# A:  use address_id
CREATE TABLE location (
  id          VARCHAR(36)  NOT NULL,
  latitude    LONG,
  longitude   LONG,
  name        VARCHAR(100) NOT NULL,
  loc_desc    VARCHAR(256) NOT NULL DEFAULT '',
  location_cd VARCHAR(3)   NOT NULL,
  address_id  VARCHAR(36)  NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);
#                                                          #
#                                                          #
############################################################

############################################################
#                                                          #
#                                                          #


# a given event can be at multiple locations
CREATE TABLE event_location (
  id          VARCHAR(36) NOT NULL,
  event_id    VARCHAR(36) NOT NULL,
  location_id VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE project_type (
  id              VARCHAR(36)  NOT NULL,
  project_type_cd VARCHAR(3),
  type_desc       VARCHAR(256) NOT NULL DEFAULT '',
  type_tip        VARCHAR(50)  NOT NULL DEFAULT '',
  PRIMARY KEY (id),
  UNIQUE KEY (id)
);


DROP TABLE IF EXISTS usable_base;

CREATE TABLE usable_base (
  id              VARCHAR(36) NOT NULL,
  in_use_cd       VARCHAR(3)  NOT NULL,
  in_use_start_dt DATETIME    NOT NULL,
  in_use_end_dt   DATETIME    NULL DEFAULT nullproject
);


DROP TABLE IF EXISTS basic;

CREATE TABLE basic (
  id         VARCHAR(36) NOT NULL,

  create_dt  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(40)
);

SELECT *
FROM basic;

#alter table usable_base add column create_dt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;
#alter table usable_base add column created_by varchar(40);
DROP TABLE IF EXISTS video_item;

CREATE TABLE video_item (
  video_id VARCHAR(36)  NOT NULL,

  title    VARCHAR(100) NOT NULL
);

SELECT *
FROM video_item
WHERE id = 'c3d48262-255c-4726-ab96-0ba0a0a81702';

SELECT *
FROM usable_base;


CREATE TABLE process_control (
  id           VARCHAR(36) NOT NULL,
  process_name VARCHAR(256),

  UNIQUE KEY (process_name),
  PRIMARY KEY (id)
);


SELECT *
FROM movie;
DROP TABLE IF EXISTS movie;

CREATE TABLE movie (
  id         VARCHAR(36) NOT NULL,
  title      VARCHAR(256),

  movie_desc VARCHAR(256),

  PRIMARY KEY (id)
);


SELECT *
FROM trailer;
DROP TABLE IF EXISTS trailer;

CREATE TABLE trailer (
  id           VARCHAR(36) NOT NULL,
  title        VARCHAR(256),

  trailer_desc VARCHAR(256),

  PRIMARY KEY (id)
);


SELECT *
FROM plot_type;
DROP TABLE IF EXISTS plot_type;

CREATE TABLE plot_type (
  id             VARCHAR(36)  NOT NULL,
  plot_type_cd   VARCHAR(3)   NOT NULL,
  plot_type_tip  VARCHAR(50)  NOT NULL,
  plot_type_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY (id)
);

SELECT *
FROM name_group_map;


CREATE TABLE video_format (


);


CREATE TABLE described (
  id          VARCHAR(36)  NOT NULL,
  description VARCHAR(256) NOT NULL,
  tip         VARCHAR(50)  NOT NULL,
  ref_url     VARCHAR(256)
);


CREATE TABLE movie (
  id VARCHAR(36) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE video_rating (
  rating_id   VARCHAR(36) NOT NULL,
  rating      VARCHAR(3)  NOT NULL,
  rating_desc VARCHAR(256),
  rating_tip  VARCHAR(50),
  PRIMARY KEY (rating_id)
);

DROP TABLE IF EXISTS video_rating;

CREATE TABLE video_rating_map (
  video_id  VARCHAR(36),
  rating_id VARCHAR(36)
);


CREATE TABLE student (
  student_id   VARCHAR(36) NOT NULL,
  student_name VARCHAR(256)
);


CREATE TABLE course (
  course_id   VARCHAR(36) NOT NULL,
  course_name VARCHAR(256),
  course_desc VARCHAR(256),
  course_tip  VARCHAR(100),
  PRIMARY KEY (course_id)
);

ALTER TABLE course
  ADD COLUMN course_desc VARCHAR(256);
ALTER TABLE course
  ADD COLUMN course_tip VARCHAR(100);

CREATE TABLE student_course (
  student_id VARCHAR(36),
  course_id  VARCHAR(36)
);

SELECT *
FROM student;

SELECT *
FROM course;

SELECT *
FROM student_course;


DELETE FROM student;
DELETE FROM course;
DELETE FROM student_course;


DROP TABLE student;
DROP TABLE course;
DROP TABLE student_course;
