import axios from "axios";

const API_BASE_URL_USER = "http://localhost:8080/api/user";

const userService = {
  getAll: (page, elements, sortBy, sortDirection) => {
    return axios
      .get(API_BASE_URL_USER, {
        params: { page, elements, sortBy, sortDirection },
      })
      .then((response) => response.data);
  },

  getById: (id) => {
    return axios
      .get(`${API_BASE_URL_USER}/${id}`)
      .then((response) => response.data);
  },

  getByUsername: (username) => {
    return axios
      .get(`${API_BASE_URL_USER}/username/${username}`)
      .then((response) => response.data);
  },

  add: (user) => {
    return axios
      .post(API_BASE_URL_USER, user)
      .then((response) => response.data);
  },

  updateLastLogin: (id) => {
    return axios
      .patch(`${API_BASE_URL_USER}/${id}`)
      .then((response) => response.data);
  },

  delete: (id) => {
    return axios
      .delete(`${API_BASE_URL_USER}/${id}`)
      .then((response) => response.data);
  },
};

export default userService;
