import { useState } from "react";
import Configuration from "./Components/Configuration.jsx";
import TicketStatus from "./Components/TicketStatus";
import TicketPage from "./Components/TicketPage";
import LogDisplay from "./Components/LogDisplay.jsx";

import "./App.css";

const App = () => {
    const [config, setConfig] = useState({
        TotalTickets: 1,
        TicketReleaseRate: 0,
        CustomerRetrievalRate: 0,
        maxticketCapacity: 1,
        quantity: 1,
    });
    const [ticketPool, setTicketPool] = useState(config.TotalTickets);

    const handleConfigChange = (newConfig) => {
        setConfig(newConfig);
        setTicketPool(newConfig.TotalTickets);
    };

    return (
        <div className="container">
            <h1>Welcome To Ticket Management System</h1>
            <Configuration config={config} onChange={handleConfigChange} />
            <TicketStatus Tickets={config.TotalTickets} TicketPool={ticketPool} />
            <TicketPage />
            <LogDisplay />
        </div>
    );
};

export default App;
