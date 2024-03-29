CREATE DATABASE spacegames;

USE spacegames;

CREATE TABLE Accounts (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	Username VARCHAR(30) NOT NULL,
	Password VARCHAR(50) NOT NULL,
	Email VARCHAR(150),
	RegisteredOn DATETIME,
	LastSeen DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Stats (
	ID INT PRIMARY KEY,
	xInRowPlayed INT DEFAULT 0,
	xInRowWins INT DEFAULT 0,
	xInRowLost INT DEFAULT 0,
	xInRowTurns INT DEFAULT 0,
	blackJackPlayed INT DEFAULT 0,
	blackJackWins INT DEFAULT 0,
	blackJackLost INT DEFAULT 0,
	blackJackBJ INT DEFAULT 0,
	blackJackTurns INT DEFAULT 0,
	blackJackHits INT DEFAULT 0,
	brickBreakerPlayed INT DEFAULT 0,
	brickBreakerWins INT DEFAULT 0,
	brickBreakerLost INT DEFAULT 0,
	brickBreakerPoints INT DEFAULT 0,
	tickCrossPlayed INT DEFAULT 0,
	tickCrossWins INT DEFAULT 0,
	tickCrossLost INT DEFAULT 0,
	g2048Played INT DEFAULT 0,
	g2048Wins INT DEFAULT 0,
	g2048Lost INT DEFAULT 0,
	g2048Points INT DEFAULT 0,
	g2048HighestPoints INT DEFAULT 0,
	FOREIGN KEY (ID) REFERENCES Accounts(ID) ON UPDATE CASCADE
);
