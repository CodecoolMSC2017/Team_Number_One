/*
    Database initialization script that runs on every web-application redeployment.
*/


DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS subpages;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    username TEXT NOT NULL,
    userrole TEXT NOT NULL
);

CREATE TABLE questions (
    id SERIAL PRIMARY KEY,
    question TEXT NOT NULL,
    answer TEXT NOT NULL
);

CREATE TABLE subpages (
        id SERIAL PRIMARY KEY,
        title TEXT NOT NULL,
        type TEXT NOT NULL,
        description TEXT,
        questionid INTEGER[],
        maxscore INTEGER,
        published BOOLEAN
);

CREATE TABLE answers (
    userid INTEGER NOT NULL,
    questionid INTEGER NOT NULL,
    useranswer TEXT,
    FOREIGN KEY (questionid) REFERENCES questions(id),
    FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE results (
    pageid INTEGER NOT NULL,
    userid INTEGER NOT NULL,
    timestamp TEXT NOT NULL,
    score INTEGER,
    FOREIGN KEY (pageid) REFERENCES subpages(id),
    FOREIGN KEY (userid) REFERENCES users(id)
);

INSERT INTO users (email, password, username, userrole) VALUES
	('a@a', 'a', 'a', 'Mentor'),
	('b@b', 'b', 'b', 'Student');

INSERT INTO subpages (title, type, description, published) values
    ('TestText1', 'T', 'Test text', true);

INSERT INTO questions (question, answer) VALUES
    ('Are you here?', 'yes'),
    ('Are you not here?','no');

-- syntax error quickfixed, this request only syntatically checked, not necessary the correct data request !
INSERT INTO subpages (title, type, questionid, maxscore, published)
VALUES ('The big questions','A',(SELECT ARRAY_AGG(questions.id) FROM questions GROUP BY questions.id ORDER BY questions.id LIMIT 1),20,true);

INSERT INTO results (pageid, userid, timestamp, score) VALUES
    ((SELECT id FROM subpages WHERE type = 'A' LIMIT 1),
	 (SELECT id FROM users WHERE userrole = 'Student' LIMIT 1), '', 10);

INSERT INTO answers (userid, questionid, useranswer) VALUES
    ((SELECT id FROM subpages WHERE type = 'A' LIMIT 1), (SELECT id FROM questions LIMIT 1), 'no');