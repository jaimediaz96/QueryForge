import React, { useState, useEffect } from "react";
import "./App.css";
import LoginComponent from "./components/LoginComponent";
import MainComponent from "./components/MainComponent";

function App() {
  const [isLoggedin, setIsLoggedin] = useState(false);
  const [loggedInUser, setLoggedInUser] = useState("");

  const handleLoginSuccess = (username) => {
    setIsLoggedin(true);
    setLoggedInUser(username);
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>QueryForge</h1>
      </header>
      <main className="App-content">
        {!isLoggedin ? (
          <LoginComponent onLoginSuccess={handleLoginSuccess} />
        ) : (
          <MainComponent username={loggedInUser} />
        )}
      </main>
      <footer className="App-footer">
        <p>© 2023 QueryForge App By jaimediaz96</p>
      </footer>
    </div>
  );
}

export default App;
