-- Create sample user 1
INSERT INTO User
VALUES (
    '11111111-1111' , -- Personnummer
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
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-01 12:24:35' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-01 15:00:48' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-01 17:45:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 22:35:45' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-02 04:56:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-02 10:18:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-02 13:45:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-02 17:22:44' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-05 08:14:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-05 12:20:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-05 19:30:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    10 , -- Rating
    '2022-03-06 02:15:35' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-06 09:22:15' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-08 10:33:15' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-08 11:54:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-08 13:34:24' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-08 16:45:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-08 20:15:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-09 12:45:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
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
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-01 12:24:35' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 15:00:48' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-01 17:45:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-01 22:35:45' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-02 04:56:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-02 10:18:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-02 13:45:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-02 17:22:44' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-05 08:14:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-05 12:20:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-05 19:30:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-06 02:15:35' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-06 09:22:15' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-08 10:33:15' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-08 11:54:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-08 13:34:24' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-08 16:45:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-08 20:15:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-09 12:45:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
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
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-01 Afternoon' , -- Text entry
    '2022-03-01 16-18-21' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-02 Noon' , -- Text entry
    '2022-03-02 13:48:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-05 Noon' , -- Text entry
    '2022-03-05 12:25:54' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-05 Evening' , -- Text entry
    '2022-03-05 19:34:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-06 Evening' , -- Text entry
    '2022-03-06 02:32:23' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-06 Morning' , -- Text entry
    '2022-03-06 09:25:34' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-08 Noon' , -- Text entry
    '2022-03-08 10:35:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-08 Noon' , -- Text entry
    '2022-03-08 13:35:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 1 on 2022-03-08 Afternoon' , -- Text entry
    '2022-03-08 16:50:12' , -- Date and time at insertion
    '11111111-1111' -- Personnummer of current user
);


-- Create sample user 2

INSERT INTO User
VALUES (
    '22222222-2222' , -- Personnummer
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
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-01 08:22:34' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 12:25:34' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-01 20:25:22' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-03 11:34:23' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-03 15:33:22' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-03 17:22:45' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-03 21:23:23' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-05 06:22:45' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-05 09:13:24' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-05 12:24:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-05 16:22:45' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-05 20:22:12' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-07 08:22:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-07 12:21:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-07 17:25:22' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-07 21:05:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-09 06:00:00' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    10 , -- Rating
    '2022-03-09 15:22:58' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-09 17:22:09' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
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
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-01 08:22:34' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-01 12:25:34' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-01 20:25:22' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-03 11:34:23' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-03 15:33:22' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-03 17:22:45' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-03 21:23:23' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    6 , -- Rating
    '2022-03-05 06:22:45' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    4 , -- Rating
    '2022-03-05 09:13:24' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    2 , -- Rating
    '2022-03-05 12:24:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-05 16:22:45' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-05 20:22:12' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    7 , -- Rating
    '2022-03-07 08:22:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    9 , -- Rating
    '2022-03-07 12:21:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    10 , -- Rating
    '2022-03-07 17:25:22' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    8 , -- Rating
    '2022-03-07 21:05:11' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    5 , -- Rating
    '2022-03-09 06:00:00' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    3 , -- Rating
    '2022-03-09 15:22:58' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    1 , -- Rating
    '2022-03-09 17:22:09' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
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
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-01 Evening' , -- Text entry
    '2022-03-01 20-34-23' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-03 Afternoon' , -- Text entry
    '2022-03-03 15-37-06' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-03 Afternoon' , -- Text entry
    '2022-03-03 17-48-06' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-05 Morning' , -- Text entry
    '2022-03-05 06-25-23' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-05 Noon' , -- Text entry
    '2022-03-05 12-29-06' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-05 Evening' , -- Text entry
    '2022-03-05 20-35-34' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-09 Morning' , -- Text entry
    '2022-03-09 06-12-12' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-09 Afternoon' , -- Text entry
    '2022-03-09 15-32-06' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    'User 2 on 2022-03-09 Afternoon' , -- Text entry
    '2022-03-09 17-35-06' , -- Date and time at insertion
    '22222222-2222' -- Personnummer of current user
);
