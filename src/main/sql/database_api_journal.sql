-- Public API for journal entry retrieval


-- Get all journal entrys of a secific date
-- Parameter: Date in the format 'YYYY-MM-DD'

SELECT
    determineTimeOfDay(Journal_entry.date) AS timeOfDay,
    Journal_entry.journal_entry,
    ournal_entry.date AS date
FROM Journal_entry
WHERE DATE(Journal_entry.date) LIKE ? AND Journal_entry.personnummer LIKE ?
ORDER BY Journal_entry.date;


-- Get journal entries for a mood rating range with dates
-- Parameters: Lower boundary rating, upper boundary rating (excl), personnummer

SELECT
    determineTimeOfDay(Journal_entry.date) AS timeOfDay,
    Journal_entry.journal_entry,
    DATE(Journal_entry.date) AS date
FROM Journal_entry
NATURAL JOIN Mood ON personnummer
WHERE Mood.rating >= ? AND Mood.rating < ? AND Journal_entry.personnummer LIKE ?
ORDER BY Journal_entry.date;


-- Get journal entries for a tension rating range with dates
-- Parameters: Lower boundary rating, upper boundary rating (excl), personnummer

SELECT
    determineTimeOfDay(Journal_entry.date) AS timeOfDay,
    Journal_entry.journal_entry,
    DATE(Journal_entry.date) AS date
FROM Journal_entry
NATURAL JOIN Tension ON personnummer
WHERE Tension.rating >= ? AND Tension.rating < ? AND Journal_entry.personnummer LIKE ?
ORDER BY Journal_entry.date;
