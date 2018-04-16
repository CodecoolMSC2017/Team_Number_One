/*
    Database initialization script that runs on every web-application redeployment.
*/

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    username TEXT NOT NULL,
    userrole TEXT NOT NULL
);

CREATE TABLE subpages (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    type TEXT NOT NULL,
    description TEXT,
    questionid INTEGER,
    maxscore INTEGER,
    FOREIGN KEY (questionid) REFERENCES questions(id)
);

CREATE TABLE question (
    id SERIAL PRIMARY KEY,
    question TEXT NOT NULL,
    answer TEXT NOT NULL
);

CREATE TABLE answers (
    userid INTEGER NOT NULL,
    questionid INTEGER NOT NULL,
    useranswer TEXT,
    FOREIGN KEY (questionid) REFERENCES questions(id),
    FOREIGN KEY (userid) REFERENCES userss(id)
);

CREATE TABLE results (
    pageid INTEGER NOT NULL,
    userid INTEGER NOT NULL,
    timestamp TEXT NOT NULL,
    score INTEGER,
    FOREIGN KEY (pageid_id) REFERENCES subpages(id),
    FOREIGN KEY (userid) REFERENCES users(id)
);

INSERT INTO users (email, password, username, userrole) VALUES
	('a@a', 'a', 'a', 'Mentor'),
	('b@b', 'b', 'b', 'Student');

INSERT INTO subpages (title, type, description) values
    ('TestText1', 'Text', 'Test text');
