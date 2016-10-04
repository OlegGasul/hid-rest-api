CREATE SEQUENCE seq_app_user INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE seq_authentication_token INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE seq_clazz INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE seq_data INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;
CREATE SEQUENCE seq_role INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE TABLE app_user
(
  id bigint NOT NULL,
  version bigint NOT NULL,
  account_expired boolean NOT NULL,
  account_locked boolean NOT NULL,
  enabled boolean NOT NULL,
  password character varying(255) NOT NULL,
  password_expired boolean NOT NULL,
  username character varying(255) NOT NULL,
  CONSTRAINT app_user_pkey PRIMARY KEY (id),
  CONSTRAINT uk_3k4cplvh82srueuttfkwnylq0 UNIQUE (username)
);

CREATE TABLE role
(
  id bigint NOT NULL,
  version bigint NOT NULL,
  authority character varying(255) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id),
  CONSTRAINT uk_irsamgnera6angm0prq1kemt2 UNIQUE (authority)
);

CREATE TABLE app_user_role
(
  app_user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT app_user_role_pkey PRIMARY KEY (app_user_id, role_id),
  CONSTRAINT fk_2j333g1ur8pffgiqts1vmn1vr FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_q8322fca28vuy8ap93aprcjno FOREIGN KEY (app_user_id)
      REFERENCES app_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE authentication_token
(
  id bigint NOT NULL,
  token_value character varying(255) NOT NULL,
  username character varying(255) NOT NULL,
  CONSTRAINT authentication_token_pkey PRIMARY KEY (id)
);

CREATE TABLE clazz
(
  id bigint NOT NULL,
  version bigint NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT clazz_pkey PRIMARY KEY (id)
);

CREATE TABLE data
(
  id bigint NOT NULL,
  version bigint NOT NULL,
  clazz_id bigint NOT NULL,
  content_length bigint NOT NULL,
  content_type character varying(255) NOT NULL,
  file boolean NOT NULL,
  key character varying(255) NOT NULL,
  value text,
  CONSTRAINT data_pkey PRIMARY KEY (id),
  CONSTRAINT fk_6ickyvra8fwno1khle5f5aerj FOREIGN KEY (clazz_id)
      REFERENCES clazz (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);