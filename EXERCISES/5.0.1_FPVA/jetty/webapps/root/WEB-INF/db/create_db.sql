/*
 * Generate a SQLite database with one table (USERS) and
 * fill table with dummy data.
 */

CREATE TABLE USERS (
	username TEXT PRIMARY KEY,
	password TEXT NOT NULL
);

CREATE TABLE REVIEWS (
       reviewID TEXT PRIMARY KEY NOT NULL,
       reviewer TEXT NOT NULL,
       tstamp timestamp NOT NULL,
       review TEXT NOT NULL
);

INSERT INTO USERS VALUES ("Swily1935", "aiM2za4EiT9ei");
INSERT INTO USERS VALUES ("Whosecter48", "otoo7IDae");
INSERT INTO USERS VALUES ("Sairing", "aaPh0iesoh");
INSERT INTO USERS VALUES ("Mirsteve84", "eu0shahS6aNo");
INSERT INTO USERS VALUES ("Lould1996", "pi3aivai5iB");
INSERT INTO USERS VALUES ("Heyerinty83", "ohX2iiyoa");
INSERT INTO USERS VALUES ("Shant1932", "Mei9ieng9oh");
INSERT INTO USERS VALUES ("Aling1975", "fooca5AiS");
INSERT INTO USERS VALUES ("Horlsonflon1954", "Aed3Vae2nie");
INSERT INTO USERS VALUES ("Areme1964", "eing2Xog5ez");
INSERT INTO USERS VALUES ("Haterequiter", "Eibahz4h");
INSERT INTO USERS VALUES ("Refort", "ieSh2uvieg");
INSERT INTO USERS VALUES ("Thereave", "newaeso3Ohl");
INSERT INTO USERS VALUES ("Riduch", "yah3Quae");
INSERT INTO USERS VALUES ("Onclueed", "Udeic0iecu");
INSERT INTO USERS VALUES ("Thorry", "aev6TizocuB");
INSERT INTO USERS VALUES ("Forkabounce", "se7afue8iTh");
INSERT INTO USERS VALUES ("Wasts1966", "aiPhunoo1wae");
INSERT INTO USERS VALUES ("Crad1966", "ohshiZ1roj");
INSERT INTO USERS VALUES ("Twout1945", "va3poh1jaeYae");
INSERT INTO USERS VALUES ("some_guy", "his_password");

INSERT INTO REVIEWS VALUES ("1", "Swily1935", CURRENT_TIMESTAMP, "I like that boulder. That is a nice boulder.");
INSERT INTO REVIEWS VALUES ("2", "Whosecter48", CURRENT_TIMESTAMP, "You see, Ogres are like onions. They have layers.");
INSERT INTO REVIEWS VALUES ("3", "Sairing", CURRENT_TIMESTAMP, "Ross and Rachel were not on a break.");
INSERT INTO REVIEWS VALUES ("4", "Crad1966", CURRENT_TIMESTAMP, "Jack could have fit on that door.");
INSERT INTO REVIEWS VALUES ("7", "Twout1945", CURRENT_TIMESTAMP, "Calm down Greg, it is just soccer.");
INSERT INTO REVIEWS VALUES ("12", "Forkabounce", CURRENT_TIMESTAMP, "I am the dude, dude, nobody calls me Lebowski");
INSERT INTO REVIEWS VALUES ("15", "Wasts1966", CURRENT_TIMESTAMP, "That carpet really tied the room together. This aggression will not stand.");
INSERT INTO REVIEWS VALUES ("21", "Riduch", CURRENT_TIMESTAMP, "Hot take: emacs is better than vim.");

