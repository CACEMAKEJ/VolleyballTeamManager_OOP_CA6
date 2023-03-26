DROP TABLE IF EXISTS players, teams, games;


CREATE TABLE teams (
  team_id INT AUTO_INCREMENT PRIMARY KEY,
  team_name VARCHAR(50) NOT NULL,
  coach_name VARCHAR(100) NOT NULL
);

CREATE TABLE players (
  player_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  birthdate DATE,
  position VARCHAR(20),
  team_id INT,
  FOREIGN KEY (team_id) REFERENCES teams(team_id)
);



CREATE TABLE games (
  game_id INT AUTO_INCREMENT PRIMARY KEY,
  date_played DATE NOT NULL,
  team1_id INT NOT NULL,
  team2_id INT NOT NULL,
  score_team1 INT,
  score_team2 INT,
  FOREIGN KEY (team1_id) REFERENCES teams(team_id),
  FOREIGN KEY (team2_id) REFERENCES teams(team_id)
);

INSERT INTO teams (team_name, coach_name) VALUES
  ('Team A', 'John Smith'),
  ('Team B', 'Sarah Jones'),
  ('Team C', 'David Lee'),
  ('Team D', 'Michelle Chen');

INSERT INTO players (first_name, last_name, birthdate, position, team_id) VALUES
  ('John', 'Smith', '1995-07-15', 'Outside Hitter', 1),
  ('Sarah', 'Jones', '1998-02-22', 'Middle Blocker', 1),
  ('David', 'Lee', '1982-03-08', 'Opposite Hitter', 2),
  ('Michelle', 'Chen', '1993-11-20', 'Setter', 3),
  ('Carlos', 'Ramirez', '1990-05-12', 'Libero', 2),
  ('Mia', 'Garcia', '1996-09-29', 'Middle Blocker', 3),
  ('Daniel', 'Kim', '1997-04-12', 'Outside Hitter', 1),
  ('Natalie', 'Nguyen', '1999-12-31', 'Middle Blocker', 2),
  ('Alex', 'Wong', '1998-08-05', 'Opposite Hitter', 3),
  ('Emily', 'Chung', '1994-02-14', 'Setter', 1);

  INSERT INTO games (date_played, team1_id, team2_id, score_team1, score_team2) VALUES
  ('2022-01-15', 1, 2, 25, 20),
  ('2022-02-01', 3, 4, 25, 21),
  ('2022-02-15', 2, 4, 26, 24),
  ('2022-03-01', 1, 3, 12, 25),
  ('2022-03-15', 2, 3, 28, 26),
  ('2022-04-01', 4, 1, 14, 25),
  ('2022-04-15', 3, 1, 26, 24),
  ('2022-05-01', 4, 2, 27, 25),
  ('2022-05-15', 1, 4, 11, 25),
  ('2022-06-01', 2, 1, 25, 1);