import React from "react";
import queryService from "../services/queryService";

const QueryComponent = () => {
  const queryData = {
    userId: 1,
    name: "Cali",
    queryTerm: "America hoy",
    queryTableName: "top",
    queryRegionName: "Valle del Cauca",
    queryCountryName: "Colombia",
    queryStartDate: null,
    queryEndDate: "2023-11-20",
    comment:
      "Busqueda de America hoy en Colombia en el Valle del Cauca entre 2023-03-24 y 2023-11-20",
  };

  const handleGetAll = () => {
    queryService
      .getAll(0, 10, "name", "ASC")
      .then((data) => console.log("Get All Queries:", data))
      .catch((error) => console.error(error));
  };

  const handleGetById = () => {
    const queryId = 1;
    queryService
      .getById(queryId)
      .then((data) => console.log(`Get Query By ID ${queryId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleGetAllByUserId = () => {
    const userId = 1;
    queryService
      .getAllByUserId(userId, 0, 10, "name", "ASC")
      .then((data) => console.log(`Get Queries By User ID ${userId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleAddQuery = () => {
    queryService
      .add(queryData)
      .then((data) => console.log("Query Added:", data))
      .catch((error) => console.error(error));
  };

  const handleDeleteQuery = () => {
    const queryId = 1; // Ejemplo, reemplaza con el ID real
    queryService
      .delete(queryId)
      .then((data) => console.log(`Query Deleted ID ${queryId}:`, data))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <button onClick={handleGetAll}>Get All Queries</button>
      <button onClick={handleGetById}>Get Query By ID</button>
      <button onClick={handleGetAllByUserId}>Get Queries By User ID</button>
      <button onClick={handleAddQuery}>Add Query</button>
      <button onClick={handleDeleteQuery}>Delete Query</button>
    </div>
  );
};

export default QueryComponent;
