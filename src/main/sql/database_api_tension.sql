-- Public API for tension retrieval


-- Get average user tension of specific day by time of day
-- Parameters: Date as 'YYYY-MM-DD', personnummer

SELECT
    determineTimeOfDay(Tension.date) AS timeOfDay,
    AVG(Tension.rating) AS tension,
    MAX(DATE(Tension.date)) AS date
FROM Tension
WHERE DATE(Tension.date) LIKE ? AND Tension.personnummer LIKE ?
GROUP BY timeOfDay;

-- Get average user tension of a time period by time of day
-- Parameters: Start date as 'YYYY-MM-DD', End date +1 as 'YYYY-MM-DD', personnummer

SELECT
    determineTimeOfDay(Tension.date) AS timeOfDay,
    AVG(Tension.rating) AS tension,
    MAX(DATE(Mood.date)) AS endOfPeriod
FROM Tension
WHERE DATE(Tension.date) >= ? AND DATE(Tension.date) < ? AND Tension.personnummer LIKE ?
GROUP BY timeOfDay;


-- Get dates and daytimes according to tension rating
-- Parameters: Lower boundary rating, upper boundary rating (excl), personnummer

SELECT DISTINCT
    determineTimeOfDay(Tension.date) AS timeOfDay,
    Tension.rating AS tension,
    DATE(Tension.date) AS date
FROM Tension
WHERE Tension.rating >= ? AND Tension.rating < ? AND Tension.personnummer LIKE ? ;
