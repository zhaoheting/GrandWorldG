-- Drop table

-- DROP TABLE travel_fly.user_info;

CREATE TABLE travel_fly.user_info (
                                      user_id serial4 NOT NULL,
                                      username text NOT NULL,
                                      "password" text NOT NULL,
                                      create_time timestamp NOT NULL,
                                      last_update_time timestamp NOT NULL,
                                      domain_username text NOT NULL,
                                      social_security_number text NOT NULL,
                                      role_id int8 NOT NULL,
                                      CONSTRAINT user_info_pk PRIMARY KEY (user_id),
                                      CONSTRAINT user_info_ssn_un UNIQUE (social_security_number),
                                      CONSTRAINT user_info_username_un UNIQUE (username),
                                      CONSTRAINT user_info_role_id_fkey FOREIGN KEY (role_id) REFERENCES travel_fly.user_role(role_id)
);

-- Drop table

-- DROP TABLE travel_fly.user_role;

CREATE TABLE travel_fly.user_role (
                                      role_id serial4 NOT NULL,
                                      role_name text NOT NULL,
                                      CONSTRAINT user_role_pk PRIMARY KEY (role_id)
);
