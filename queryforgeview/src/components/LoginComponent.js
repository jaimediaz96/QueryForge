import React, { useState, useEffect } from "react";
import "../styles/LoginStyles.css";
import userService from "../services/userService";

const LoginComponent = ({ onLoginSuccess }) => {
  const [username, setUsername] = useState("");
  const [previousUsernames, setPreviousUsernames] = useState([]);

  useEffect(() => {
    const storedUsernames = JSON.parse(localStorage.getItem("usernames")) || [];
    setPreviousUsernames(storedUsernames);
  }, []);

  const handleUsernameChange = (e) => {
    setUsername(e.target.value);
  };

  const handleLogin = () => {
    userService
      .getByUsername(username)
      .then((user) => {
        const updatedUsernames = [...previousUsernames, user.username];
        localStorage.setItem("usernames", JSON.stringify(updatedUsernames));
        setPreviousUsernames(updatedUsernames);
        onLoginSuccess(username);
      })
      .catch((error) => {
        if (error.response && error.response.status === 400) {
          alert("User already registered");
        } else {
          alert("Oops, there was an unexpected error. Please try again");
        }
      });
  };

  const handleSignIn = () => {
    const newUser = { username };
    userService
      .add(newUser)
      .then((response) => {
        const updatedUsernames = [...previousUsernames, response.username];
        localStorage.setItem("usernames", JSON.stringify(updatedUsernames));
        setPreviousUsernames(updatedUsernames);
        onLoginSuccess(username);
      })
      .catch((error) => {
        if (error.response && error.response.status === 400) {
          alert("User already registered");
        } else {
          alert("Oops, there was an unexpected error. Please try again");
        }
      });
  };

  const handleCardClick = (selectedUsername) => {
    setUsername(selectedUsername);
    onLoginSuccess(selectedUsername);
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <div className="login-form">
        <input
          type="text"
          value={username}
          onChange={handleUsernameChange}
          placeholder="username"
          className="login-input"
        />
        <button onClick={handleLogin} className="login-button">
          Log In
        </button>
        <button onClick={handleSignIn} className="login-button">
          Sign In
        </button>

        <div>
          {previousUsernames.map((name, index) => (
            <div
              key={index}
              className="username-card"
              onClick={() => handleCardClick(name)}
            >
              {name}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default LoginComponent;
