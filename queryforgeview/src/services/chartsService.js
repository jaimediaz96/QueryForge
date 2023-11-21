import { createChart, LineSeriesPartialOptions } from "lightweight-charts";

const chartsService = {
  createChart: (container, width, height) => {
    const chart = createChart(container, {
      width: width,
      height: height,
    });

    return chart;
  },

  addLineSeries: (chart, data, options = {}) => {
    const lineSeries = chart.addLineSeries(options);
    lineSeries.setData(data);
    return lineSeries;
  },

  prepareWeekStatsData: (weekStats) => {
    return weekStats.map((stat) => ({
      time: stat.week.toString(),
      value: stat.avgScore,
    }));
  },

  prepareAvgRankData: (weekStats) => {
    return weekStats.map((stat) => ({
      time: stat.week.toString(), // Asegúrate de que el formato de fecha sea compatible
      value: stat.avgRank,
    }));
  },
};

export default chartsService;
