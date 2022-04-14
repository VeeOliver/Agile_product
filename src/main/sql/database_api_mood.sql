-- Public API for mood retrieval


-- Get average user mood of specific day by daytime
-- Parameters: Date as 'YYYY-MM-DD', personnummer
SELECT
    determineTimeOfDay(Mood.date) AS timeOfDay,
    AVG(Mood.rating) AS mood    
FROM Mood
WHERE DATE(Mood.date) LIKE ? AND Mood.personnummer LIKE ?
GROUP BY timeOfDay;


-- Get average user mood of time period by daytime
-- Parameters: Start date as 'YYYY-MM-DD', End date +1 as 'YYYY-MM-DD', personnummer

SELECT
    determineTimeOfDay(Mood.date) AS timeOfDay,
    AVG(Mood.rating) AS mood    
FROM Mood
WHERE DATE(Mood.date) >= ? AND Mood.date < ? AND Mood.personnummer LIKE ?
GROUP BY timeOfDay;


-- Get dates and daytimes according to mood rating
-- Parameters: Lower boundary rating, upper boundary rating (excl), personnummer

SELECT DISTINCT
    determineTimeOfDay(Mood.date) AS timeOfDay,
    Mood.rating AS mood,
    DATE(Mood.date) AS date
FROM Mood
WHERE Mood.rating >= ? AND Mood.rating < ? AND Mood.personnummer LIKE ? ;
