import axios from "axios";

const API_BASE_URL_COMMENT = "http://localhost:8080/api/comment";

const commentService = {
  getAll: (page, elements, sortBy, sortDirection) => {
    return axios
      .get(API_BASE_URL_COMMENT, {
        params: { page, elements, sortBy, sortDirection },
      })
      .then((response) => response.data);
  },

  getById: (id) => {
    return axios
      .get(`${API_BASE_URL_COMMENT}/${id}`)
      .then((response) => response.data);
  },

  getAllByUserId: (userId, page, elements, sortBy, sortDirection) => {
    return axios
      .get(`${API_BASE_URL_COMMENT}/user/${userId}`, {
        params: { page, elements, sortBy, sortDirection },
      })
      .then((response) => response.data);
  },

  getAllByQueryId: (queryId, page, elements, sortBy, sortDirection) => {
    return axios
      .get(`${API_BASE_URL_COMMENT}/query/${queryId}`, {
        params: { page, elements, sortBy, sortDirection },
      })
      .then((response) => response.data);
  },

  add: (comment) => {
    return axios
      .post(API_BASE_URL_COMMENT, comment)
      .then((response) => response.data);
  },

  delete: (id) => {
    return axios
      .delete(`${API_BASE_URL_COMMENT}/${id}`)
      .then((response) => response.data);
  },
};

export default commentService;
