import React, { useEffect, useRef } from "react";
import weekStatsService from "../services/weekStatsService";
import chartsService from "../services/chartsService";

const ChartComponent = ({ term, countryName, regionName, startDate, endDate }) => {
  const chartContainerRef = useRef(null);

  useEffect(() => {
    weekStatsService
      .getWeekStats(term, "top", regionName, countryName, null, null)
      .then((weekStats) => {
        if (weekStats.length === 0) {
          alert("No data available for the given query.");
          return;
        }

        const chart = chartsService.createChart(
          chartContainerRef.current,
          600,
          300
        );

        const weekStatsData = chartsService.prepareWeekStatsData(weekStats);
        chartsService.addLineSeries(chart, weekStatsData, {
          color: "blue",
          lineWidth: 2,
        });

        const avgRankData = chartsService.prepareAvgRankData(weekStats);
        chartsService.addLineSeries(chart, avgRankData, {
          color: "green",
          lineWidth: 2,
          lineStyle: 2,
        });
      })
      .catch((error) => {
        if (error.response && error.response.status === 400) {
          alert("Plese put a Term");
        } else {
          alert("Oops, there was an unexpected error. Please try again");
        }
      });
  }, [term, countryName, regionName, startDate, endDate]);

  return <div ref={chartContainerRef}></div>;
};

export default ChartComponent;
