// eslint-disable-next-line no-unused-vars
import React, { useState } from "react";

const LogDisplay = () => {
    const [logs, setLogs] = useState([]);
    const [Message, setMessage] = useState("");

    const createLog = (message) => {
        const timestamp = new Date().toLocaleString();
        setLogs((prevLogs) => [...prevLogs, `${timestamp}: ${message}`]);
    };

    //handle start process
    const handleStart = () => {
        setMessage("System running...");
        createLog("System started.");
    };

    //handle stop process
    const handleStop = () => {
        setMessage("System stopped.");
        createLog("System stopped.");
    };

    return (
        <div className="log-container">
            <h3 className="log-title">Activity Log</h3>
            <ul className="log-list">
                {logs.map((log, index) => (
                    <li key={index} className="log-entry">
                        {log}
                    </li>
                ))}
            </ul>
            <div className="status-container">
                <h4>Status:</h4>
                <p className="status-message">{Message}</p>
            </div>
            <div className="log-actions">
                <button onClick={handleStart} className="start-button">Start System</button>
                <button onClick={handleStop} className="stop-button">Stop System</button>
            </div>
        </div>
    );
};

export default LogDisplay;
