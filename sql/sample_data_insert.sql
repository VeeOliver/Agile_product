USE myrmidon;

-- Create sample user 1
INSERT INTO User
VALUES (
    '111111-1111' , -- Personnummer
    'Test User 1' , -- Name
    'test1@myrmidon.com' , -- Email
    SHA1( '11111' ) -- Password in hashed form
);

-- Mood entries user 1
INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-01 06:00:00' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-01 12:24:35' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-01 15:00:48' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-01 17:45:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 22:35:45' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-02 04:56:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-02 10:18:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-02 13:45:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-02 17:22:44' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-05 08:14:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-05 12:20:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-05 19:30:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    10 , -- Rating
    '2022-03-06 02:15:35' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-06 09:22:15' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-08 10:33:15' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-08 11:54:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-08 13:34:24' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-08 16:45:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-08 20:15:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-09 12:45:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

-- Tension entries user 1

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-01 06:00:00' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-01 12:24:35' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 15:00:48' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-01 17:45:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-01 22:35:45' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-02 04:56:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-02 10:18:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-02 13:45:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-02 17:22:44' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-05 08:14:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-05 12:20:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-05 19:30:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-06 02:15:35' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-06 09:22:15' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-08 10:33:15' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-08 11:54:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-08 13:34:24' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-08 16:45:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-08 20:15:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-09 12:45:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

-- Journal entries user 1

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-01 Afternoon' , -- Text entry
    '2022-03-01 14-15-06' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-01 Afternoon' , -- Text entry
    '2022-03-01 16-18-21' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-02 Noon' , -- Text entry
    '2022-03-02 13:48:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-05 Noon' , -- Text entry
    '2022-03-05 12:25:54' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-05 Evening' , -- Text entry
    '2022-03-05 19:34:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-06 Evening' , -- Text entry
    '2022-03-06 02:32:23' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-06 Morning' , -- Text entry
    '2022-03-06 09:25:34' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-08 Noon' , -- Text entry
    '2022-03-08 10:35:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-08 Noon' , -- Text entry
    '2022-03-08 13:35:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-08 Afternoon' , -- Text entry
    '2022-03-08 16:50:12' , -- Date and time at insertion
    '111111-1111' -- Personnummer of current user
);


-- Create sample user 2

INSERT INTO User
VALUES (
    '222222-2222' , -- Personnummer
    'Test User 2' , -- Name
    'test2@myrmidon.com' , -- Email
    SHA1( '22222' ) -- Password in hashed form
);

-- Mood entries user 2
INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-01 05:33:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-01 08:22:34' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 12:25:34' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-01 20:25:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-03 11:34:23' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-03 15:33:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-03 17:22:45' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-03 21:23:23' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-05 06:22:45' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-05 09:13:24' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-05 12:24:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-05 16:22:45' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-05 20:22:12' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-07 08:22:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-07 12:21:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-07 17:25:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-07 21:05:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-09 06:00:00' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    10 , -- Rating
    '2022-03-09 15:22:58' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-09 17:22:09' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

-- Tension entries user 2

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-01 05:33:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 08:22:34' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-01 12:25:34' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-01 20:25:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-03 11:34:23' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-03 15:33:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-03 17:22:45' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-03 21:23:23' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-05 06:22:45' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-05 09:13:24' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-05 12:24:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-05 16:22:45' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-05 20:22:12' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-07 08:22:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-07 12:21:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    10 , -- Rating
    '2022-03-07 17:25:22' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-07 21:05:11' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-09 06:00:00' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-09 15:22:58' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-09 17:22:09' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

-- Journal entries user 2

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-01 Morning' , -- Text entry
    '2022-03-01 08:28:34' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-01 Evening' , -- Text entry
    '2022-03-01 20-34-23' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-03 Afternoon' , -- Text entry
    '2022-03-03 15-37-06' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-03 Afternoon' , -- Text entry
    '2022-03-03 17-48-06' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-05 Morning' , -- Text entry
    '2022-03-05 06-25-23' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-05 Noon' , -- Text entry
    '2022-03-05 12-29-06' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-05 Evening' , -- Text entry
    '2022-03-05 20-35-34' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-09 Morning' , -- Text entry
    '2022-03-09 06-12-12' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-09 Afternoon' , -- Text entry
    '2022-03-09 15-32-06' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-09 Afternoon' , -- Text entry
    '2022-03-09 17-35-06' , -- Date and time at insertion
    '222222-2222' -- Personnummer of current user
);


-- Insert facts into database

INSERT INTO Facts
VALUES (
    'Sea otters hold hands when they sleep to keep from drifting apart.',
    null
);

INSERT INTO Facts
VALUES (
    'Cows have best friends.',
    null
);

INSERT INTO Facts
VALUES (
    'Turtles can breathe through their butts.',
    null
);

INSERT INTO Facts
VALUES (
    'Squirrels plant thousands of new trees each year simply by forgetting where they put their acorns.',
    null
);

INSERT INTO Facts
VALUES (
    'Norway knighted a penguin, his name is Sir Nils Olav.',
    null
);

INSERT INTO Facts
VALUES (
    'Rats laugh when tickled.',
    null
);

INSERT INTO Facts
VALUES (
    'The voices of Mickey and Minnie Mouse were married in real life.',
    null
);

INSERT INTO Facts
VALUES (
    'Rabbit agility jumping, or in Swedish “Kaninhoppning” were started in Sweden in the late 90s, and competitions are held worldwide.',
    null
);

INSERT INTO Facts
VALUES (
    'A study measuring the effects of music found that cows produce more milk when listening to soothing music. They produce the most when listening to R.E.M’s “Everybody Hurts”.',
    null
);

INSERT INTO Facts
VALUES (
    'Google, the periodic table, the structure of our DNA, and “Yesterday” by the Beatles are all ideas that were conceived in dreams.',
    null
);

INSERT INTO Facts
VALUES (
    'Crows like to give presents.',
    null
);

INSERT INTO Facts
VALUES (
    'Astronaut Eugene Cernan wrote his daughter’s initials on the moon.',
    null
);

INSERT INTO Facts
VALUES (
    'There is a basketball court on top of the United States Supreme Court building.',
    null
);

INSERT INTO Facts
VALUES (
    'Squirrels will adopt other squirrel babies if they are in need.',
    null
);

INSERT INTO Facts
VALUES (
    'Just like humans, goats have accents which will depend on the location in which they were raised.',
    null
);

INSERT INTO Facts
VALUES (
    'Dogs feel safe when they smell their humans.',
    null
);

INSERT INTO Facts
VALUES (
    'Dogs sneeze to show that they are play-fighting.',
    null
);

INSERT INTO Facts
VALUES (
    'Honey bee’s can hold hands, they have little hooks which they link together, oftentimes when they are sleeping.',
    null
);

INSERT INTO Facts
VALUES (
    'In the Netherlands, the town of Weesp has a small village called Hogeweyk specifically designed to help those suffering from dementia.',
    null
);

INSERT INTO Facts
VALUES (
    'In Japan, there are a series of ‘animal islands’, including Ookuno (Rabbit) Island which is home to over 900 wild rabbits.',
    null
);

INSERT INTO Facts
VALUES (
    'Ducks shake their tails when they are happy.',
    null
);

INSERT INTO Facts
VALUES (
    'A town in Alaska had a cat as a mayor named Mr. Stubbs who not only served as honorary mayor for 17 years, but lived until he was 20.',
    null
);

INSERT INTO Facts
VALUES (
    'Positive thoughts can change the structure of the brain to disrupt negative thinking patterns.',
    null
);

INSERT INTO Facts
VALUES (
    'The “Broaden and Build” theory suggests that positive thoughts can actually improve your ability to do your job.',
    null
);

INSERT INTO Facts
VALUES (
    'Positive thinking can help you learn things faster, as it broadens your perspective and allows you to be more receptive to things.',
    null
);

INSERT INTO Facts
VALUES (
    'One study showed that writing about positivity could lead to better physical health.',
    null
);

INSERT INTO Facts
VALUES (
    'Positivity can lower your blood pressure and increase cardiovascular health.',
    null
);

INSERT INTO Facts
VALUES (
    'The large dust cloud at the center of our Milky Way smells like raspberries.',
    null
);

INSERT INTO Facts
VALUES (
    'Sheep can tell the difference between happy and sad faces, even on humans, and are more likely to approach happy-looking people.',
    null
);

INSERT INTO Facts
VALUES (
    'Nikola Tesla was severely ill while at school and credits Mark Twains books for his recovery. Later, they would become best friends.',
    null
);

INSERT INTO Facts
VALUES (
    'Sometimes polar bears rub noses to communicate, particularly when asking for a favor.',
    null
);

INSERT INTO Facts
VALUES (
    'Octopuses take care of underwater gardens.',
    null
);

INSERT INTO Facts
VALUES (
    'At a cat cafe in brooklyn, rats and rescue cats co-habitat. The rats have been found to care for the sick or orphaned cats.',
    null
);

INSERT INTO Facts
VALUES (
    'Shakespeare used to call hedgehogs “hedgepigs”.',
    null
);

INSERT INTO Facts
VALUES (
    'Bob Ross did the show “The Joy of Painting” completely for free, all his income came from his own company selling how-to videos and paint supplies.',
    null
);

INSERT INTO Facts
VALUES (
    'A wild turkey that terrorized a Wisconsin town became a a beloved figure and was adopted as their unofficial mayor.',
    null
);

INSERT INTO Facts
VALUES (
    'Disney used to have a hotline kids could call if they wanted to hear Mickey, Minnie, Goofy, Donald or Daisy wish them goodnight.',
    null
);

INSERT INTO Facts
VALUES (
    'Looking at cute things actually improves your focus.',
    null
);

INSERT INTO Facts
VALUES (
    'More than half of Sweden is covered in forests.',
    null
);

INSERT INTO Facts
VALUES (
    'Around 90% of Stockholm’s metro stations have been decorated, resembling one large underground art gallery.',
    null
);

INSERT INTO Facts
VALUES (
    'Kiruna, Sweden houses the largest model of the solar system, at a scale of 1:20 million and spanning 300 km.',
    null
);

INSERT INTO Facts
VALUES (
    'The first patients to be treated with a pioneering leukemia therapy are still in remission after 11 years.',
    null
);

INSERT INTO Facts
VALUES (
    'A butterfly that disappeared from England in 1976 has established a stable population in Northamptonshire.',
    null
);

INSERT INTO Facts
VALUES (
    'A blind eel that was found living in the grounds of a school for blind children is among the ‘new’ species that scientists introduced to the world.',
    null
);

INSERT INTO Facts
VALUES (
    'Iceland has now banned commercial whaling.',
    null
);

INSERT INTO Facts
VALUES (
    'Within a few hours of a revolutionary operation, three paralyzed patients could stand, and their mobily improved to walking, kicking their legs and even jumping over the course of the next several months.',
    null
);

INSERT INTO Facts
VALUES (
    'A long-term global study found that a “promising” immunotherapy breast cancer drug called keytruda could save thousands of lives. The drug, which is already used to treat other forms of cancer, helps the immune system recognise and attack cancer cells. In women who have triple-negative breast cancer, a particularly aggressive form of the disease, scientists found that the drug could help to stop it from returning.',
    null
);

INSERT INTO Facts
VALUES (
    'Chimpanzees have been observed helping other wounded chimpanzees in the wild.',
    null
);

INSERT INTO Facts
VALUES (
    'The 532-acre forest was a sacred place for indigenous Americans, and has been returned to the Pomo people. The forest will also revert to its original name Tc’ih-Léh-Dûñ, meaning "Fish Run Place" in the Sinkyone language.',
    null
);
