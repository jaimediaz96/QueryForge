import React from "react";
import userService from "../services/userService";

const UserComponent = () => {
  const userData = {
    username: "newUser",
  };

  const handleGetAll = () => {
    userService
      .getAll(0, 10, "username", "ASC")
      .then((data) => console.log("Get All:", data))
      .catch((error) => console.error(error));
  };

  const handleGetById = () => {
    const userId = 1;
    userService
      .getById(userId)
      .then((data) => console.log(`Get By ID ${userId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleGetByUsername = () => {
    const username = "valeria";
    userService
      .getByUsername(username)
      .then((data) => console.log(`Get By Username ${username}:`, data))
      .catch((error) => console.error(error));
  };

  const handleAddUser = () => {
    userService
      .add(userData)
      .then((data) => console.log("User Added:", data))
      .catch((error) => console.error(error));
  };

  const handleUpdateLastLogin = () => {
    const userId = 1;
    userService
      .updateLastLogin(userId)
      .then((data) => console.log(`Last Login Updated for ID ${userId}:`, data))
      .catch((error) => console.error(error));
  };

  const handleDeleteUser = () => {
    const userId = 13;
    userService
      .delete(userId)
      .then((data) => console.log(`User Deleted ID ${userId}:`, data))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <button onClick={handleGetAll}>Get All Users</button>
      <button onClick={handleGetById}>Get User By ID</button>
      <button onClick={handleGetByUsername}>Get User By Username</button>
      <button onClick={handleAddUser}>Add User</button>
      <button onClick={handleUpdateLastLogin}>Update Last Login</button>
      <button onClick={handleDeleteUser}>Delete User</button>
    </div>
  );
};

export default UserComponent;
