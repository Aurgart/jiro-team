CREATE SCHEMA jiro_team;

CREATE TABLE jiro_team.team
(
    id serial NOT NULL,
    name varchar NOT NULL,
    owner_id integer not null,
    PRIMARY KEY (id)
);


CREATE UNIQUE INDEX team_unix01 ON  jiro_team.team(name);

CREATE TABLE jiro_team.team_members
(
    team_id integer NOT NULL REFERENCES jiro_team.team(id),
    user_id integer NOT NULL,
    PRIMARY KEY(team_id,user_id)
);