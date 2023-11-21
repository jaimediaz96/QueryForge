import axios from "axios";

const API_BASE_URL_WEEK_STATS = "http://localhost:8080/api/bigquery";

const weekStatsService = {
  getWeekStats: (
    term,
    tableName = "top",
    regionName,
    countryName,
    startDate,
    endDate
  ) => {
    const params = {
      term,
      tableName,
      regionName,
      countryName,
      startDate,
      endDate,
    };
    return axios
      .get(`${API_BASE_URL_WEEK_STATS}`, { params })
      .then((response) => response.data);
  },

  getWeekStatsByQueryId: (queryId) => {
    return axios
      .get(`${API_BASE_URL_WEEK_STATS}/query/${queryId}`)
      .then((response) => response.data);
  },
};

export default weekStatsService;
