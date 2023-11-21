import React, { useState } from "react";
import ChartComponent from "./ChartComponent";

const MainComponent = ({ onLogOut }) => {
  const [term, setTerm] = useState("");
  const [countryName, setCountryName] = useState("");
  const [regionName, setRegionName] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [showChart, setShowChart] = useState(false);

  const handleBuildQuery = () => {
    setShowChart(true);
  };

  const handleListQuery = () => {};

  return (
    <div className="main-container">
      <input
        type="text"
        className="main-input"
        placeholder="Term"
        value={term}
        onChange={(e) => setTerm(e.target.value)}
      />
      <input
        type="text"
        className="main-input"
        placeholder="Country Name"
        value={countryName}
        onChange={(e) => setCountryName(e.target.value)}
      />
      <input
        type="text"
        className="main-input"
        placeholder="Region Name"
        value={regionName}
        onChange={(e) => setRegionName(e.target.value)}
      />
      <input
        type="date"
        className="main-input"
        placeholder="Start Date"
        value={startDate}
        onChange={(e) => setStartDate(e.target.value)}
      />
      <input
        type="date"
        className="main-input"
        placeholder="End Date"
        value={endDate}
        onChange={(e) => setEndDate(e.target.value)}
      />
      <button className="main-button" onClick={handleBuildQuery}>
        Build Query
      </button>

      {showChart && (
        <div className="chart-container">
          <ChartComponent
            term={term}
            countryName={countryName}
            regionName={regionName}
            startDate={startDate}
            endDate={endDate}
          />
        </div>
      )}

      <button className="main-button" onClick={handleListQuery}>
        List Queries
      </button>
      <button className="main-button" onClick={onLogOut}>
        Log Out
      </button>
    </div>
  );
};

export default MainComponent;
