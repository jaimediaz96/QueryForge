import React from "react";
import weekStatsService from "../services/weekStatsService";

const WeekStatsComponent = () => {
  const term = "America";
  const regionName = "Valle del Cauca";
  const countryName = "Colombia";

  const handleGetWeekStats = () => {
    weekStatsService
      .getWeekStats(term, regionName, countryName)
      .then((data) => console.log("Week Stats:", data))
      .catch((error) => console.error(error));
  };

  const handleGetWeekStatsByQueryId = () => {
    const queryId = 2;
    weekStatsService
      .getWeekStatsByQueryId(queryId)
      .then((data) => console.log(`Week Stats for Query ID ${queryId}:`, data))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <button onClick={handleGetWeekStats}>Get Week Stats</button>
      <button onClick={handleGetWeekStatsByQueryId}>
        Get Week Stats By Query ID
      </button>
    </div>
  );
};

export default WeekStatsComponent;
