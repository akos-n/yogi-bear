DROP TABLE scores;

CREATE TABLE scores (
   playerName varchar (20) NOT NULL,
   playerScores INT NOT NULL
);

INSERT INTO scores (playerName, playerScores)
VALUES 
   ('Aladár',3), 
   ('Béla',2),
   ('Katalin',14),
   ('Aladár',32), 
   ('Béla',21),
   ('Katalin',15);
