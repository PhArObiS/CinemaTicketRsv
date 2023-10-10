
-- Create table for Movies
CREATE TABLE Movies (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,duration INTEGER NOT NULL,classification TEXT NOT NULL,synopsis TEXT,director TEXT,releaseYear INTEGER NOT NULL);

-- Create table for Screening Times
CREATE TABLE ScreeningTimes (id INTEGER PRIMARY KEY AUTOINCREMENT,userID INTEGER, startTime TEXT NOT NULL,endTime TEXT NOT NULL,movieID INTEGER,theater TEXT NOT NULL,FOREIGN KEY (movieID) REFERENCES Movies(id));

-- Create table for Reservations
CREATE TABLE Reservations (reservationID INTEGER PRIMARY KEY AUTOINCREMENT,userID INTEGER,screeningID INTEGER,numberOfSeats INTEGER NOT NULL,FOREIGN KEY (screeningID) REFERENCES ScreeningTimes(id), FOREIGN KEY (userID) REFERENCES Users(id));

-- Create table for Users
CREATE TABLE Users (id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT NOT NULL UNIQUE,password TEXT NOT NULL,age INTEGER, email TEXT NOT NULL, loyaltyPoints INTEGER DEFAULT 0 );

-------------------------------------------------------------------------------------------------------
-- Create table for Ratings and Comments
CREATE TABLE RatingsAndComments (id INTEGER PRIMARY KEY AUTOINCREMENT, movieID INTEGER, userID INTEGER, rating INTEGER CHECK(rating BETWEEN 1 AND 5), TEXT,FOREIGN KEY (movieID) REFERENCES Movies(id),FOREIGN KEY (userID) REFERENCES Users(id));

-- Create table for Employees
CREATE TABLE Employees (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, position TEXT NOT NULL, hireDate TEXT NOT NULL, sales INTEGER DEFAULT 0);

-- Create table for Attendance
CREATE TABLE Attendance ( id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT NOT NULL,movieID INTEGER,attendanceCount INTEGER NOT NULL DEFAULT 0,FOREIGN KEY (movieID) REFERENCES Movies(id));

-- If you have additional fields or other tables, you can add them similarly.

/****************************************************************************************************************/
// Movies

INSERT INTO Movies (title, duration, classification, synopsis, director, releaseYear) VALUES ('The Godfather', 175, 'R', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 'Francis Ford Coppola', 1972);

INSERT INTO Movies (title, duration, classification, synopsis, director, releaseYear) VALUES ('Jurassic Park', 127, 'PG-13', 'During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok.', 'Steven Spielberg', 1993);

INSERT INTO Movies (title, duration, classification, synopsis, director, releaseYear) VALUES ('The Grand Budapest Hotel', 99, 'R', 'The adventures of Gustave H, a legendary concierge at a famous hotel from the fictional Republic of Zubrowka between the first and second World Wars, and Zero Moustafa, the lobby boy who becomes his most trusted friend.', 'Wes Anderson', 2014);

INSERT INTO Movies (title, duration, classification, synopsis, director, releaseYear) VALUES ('Inception', 148, 'PG-13', 'A thief who enters the dreams of others to steal secrets from their subconscious is tasked with planting an idea into the mind of a CEO.', 'Christopher Nolan', 2010);

INSERT INTO Movies (title, duration, classification, synopsis, director, releaseYear) VALUES ('The Shawshank Redemption', 142, 'R', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', 'Frank Darabont', 1994);


-- Inception Screenings
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (1, '01:00 PM', '03:28 PM', 'Theater 1');
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (1, '06:00 PM', '08:28 PM', 'Theater 1');


-- The Shawshank Redemption Screenings
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (2, '02:00 PM', '04:22 PM', 'Theater 2');
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (2, '07:00 PM', '09:22 PM', 'Theater 2');

-- The Godfather Screenings
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (3, '01:30 PM', '04:25 PM', 'Theater 3');
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (3, '05:30 PM', '08:25 PM', 'Theater 3');

-- Jurassic Park Screenings
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (4, '12:00 PM', '02:07 PM', 'Theater 4');
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (4, '03:00 PM', '05:07 PM', 'Theater 4');

-- The Grand Budapest Hotel Screenings
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (5, '11:00 AM', '12:39 PM', 'Theater 5');
INSERT INTO ScreeningTimes (movieId, startTime, endTime, theater) VALUES (5, '04:00 PM', '05:39 PM', 'Theater 5');

/*********************************************************************************************************************************/
// USERS 
-- User 1
INSERT INTO Users (username, password, email, age, loyaltyPoints) VALUES ('john_doe', 'password123', 'johndoe@email.com', 28, 100);

-- User 2
INSERT INTO Users (username, password, email, age, loyaltyPoints) VALUES ('mary_smith', 'ilovemovies', 'marysmith@email.com', 35, 200);

-- User 3
INSERT INTO Users (username, password, email, age, loyaltyPoints) VALUES ('alex_jones', 'alexjones123', 'alex.jones@email.com', 22, 50);

/**************************************************************/
Ratings and Comments

INSERT INTO RatingsAndComments (movieID, userID, rating, comment) VALUES (1, 1, 5, 'Amazing movie! A must-watch.');
INSERT INTO RatingsAndComments (movieID, userID, rating, comment) VALUES (2, 2, 4, 'Really enjoyed it. Great storyline.');

/************************************************************/
-- Insert for the first employee
INSERT INTO Employees (name, position, hireDate, sales) VALUES ('Fred Flinstone', 'Manager', '2020-01-01', 1500);

-- Insert for the second employee
INSERT INTO Employees (name, position, hireDate, sales) VALUES ('Jane FLinstone', 'Sales Associate', '2021-05-15', 700);

/***********************************************************/
-- Insert for the first movie's attendance
INSERT INTO Attendance (date, movieID, attendanceCount) VALUES ('2023-08-10', 1, 250);

-- Insert for the second movie's attendance
INSERT INTO Attendance (date, movieID, attendanceCount) VALUES ('2023-08-10', 2, 180);



