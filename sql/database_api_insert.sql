-- Public API containing ? as a placeholder

-- Create a new user entry
INSERT INTO User
VALUES (
    ? , -- Personnummer
    ? , -- Name
    ? , -- Email
    SHA1( ? ) -- Password in hashed form
);

-- Create a mood rating
INSERT INTO Mood (
    rating,
    date,
    personnummer
)
VALUES (
    ? , -- Rating
    ? , -- Date and time at insertion
    ? -- Personnummer of current user
);

-- Create a tension rating
INSERT INTO Tension (
    rating,
    date,
    personnummer
)
VALUES (
    ? , -- Rating
    ? , -- Date and time at insertion
    ? -- Personnummer of current user
);

INSERT INTO Journal_entry (
    journal_entry,
    date,
    personnummer
)
VALUES (
    ? , -- Text entry
    ? , -- Date and time at insertion
    ? -- Personnummer of current user
);
