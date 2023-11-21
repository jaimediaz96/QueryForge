import axios from "axios";

const API_BASE_URL_QUERY = "http://localhost:8080/api/query";

const queryService = {
  getAll: (page, elements, sortBy, sortDirection) => {
    return axios
      .get(API_BASE_URL_QUERY, {
        params: { page, elements, sortBy, sortDirection },
      })
      .then((response) => response.data);
  },

  getById: (id) => {
    return axios
      .get(`${API_BASE_URL_QUERY}/${id}`)
      .then((response) => response.data);
  },

  getAllByUserId: (userId, page, elements, sortBy, sortDirection) => {
    return axios
      .get(`${API_BASE_URL_QUERY}/user/${userId}`, {
        params: { page, elements, sortBy, sortDirection },
      })
      .then((response) => response.data);
  },

  add: (query) => {
    return axios
      .post(API_BASE_URL_QUERY, query)
      .then((response) => response.data);
  },

  delete: (id) => {
    return axios
      .delete(`${API_BASE_URL_QUERY}/${id}`)
      .then((response) => response.data);
  },
};

export default queryService;
