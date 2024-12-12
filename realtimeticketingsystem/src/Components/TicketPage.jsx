// eslint-disable-next-line no-unused-vars
import React, { useState } from "react";

const TicketPage = () => {
    const [customerNames, setCustomerNames] = useState([]);
    const [vendorNames, setVendorNames] = useState([]);
    const [inputCustomerName, setInputCustomerName] = useState("");
    const [inputVendorName, setInputVendorName] = useState("");

    //Adding customers to system
    const handleCustomerAdd = () => {
        const trimmedName = inputCustomerName.trim();
        if (trimmedName !== "" && /^[a-zA-Z\s]+$/.test(trimmedName)) {
            setCustomerNames((prevNames) => [...prevNames, trimmedName]);
            setInputCustomerName("");
        } else {
            alert("Please enter a valid name (only letters and spaces allowed).");
        }
    };
    //Adding vendors to system
    const handleVendorAdd = () => {
        const trimmedName = inputVendorName.trim();
        if (trimmedName !== "" && /^[a-zA-Z\s]+$/.test(trimmedName)) {
            setVendorNames((prevNames) => [...prevNames, trimmedName]);
            setInputVendorName("");
        } else {
            alert("Please enter a valid name (only letters and spaces allowed).");
        }
    };

    return (
        <div className="ticket-page">
            <h1>Vendor-Customer Management</h1>
            <div className="main-section">
                <div className="customer-vendor">
                    {/* Customer Tile */}
                    <div className="tile customer-tile">
                        <h2>Customers</h2>
                        <input
                            type="text"
                            value={inputCustomerName}
                            onChange={(e) => setInputCustomerName(e.target.value)}
                            placeholder="Enter customer name"
                            className="input-field"
                        />
                        <button onClick={handleCustomerAdd} className="count-button">Add Customer</button>
                        <p>Total Customers: {customerNames.length}</p>
                        <ul className="name-list">
                            {customerNames.map((name, index) => (
                                <li key={index} className="name-item">
                                    {`Customer${index + 1} = ${name}`}
                                </li>
                            ))}
                        </ul>
                    </div>

                    {/* Vendor Tile */}
                    <div className="tile vendor-tile">
                        <h2>Vendors</h2>
                        <input
                            type="text"
                            value={inputVendorName}
                            onChange={(e) => setInputVendorName(e.target.value)}
                            placeholder="Enter vendor name"
                            className="input-field"
                        />
                        <button onClick={handleVendorAdd} className="count-button">Add Vendor</button>
                        <p>Total Vendors: {vendorNames.length}</p>
                        <ul className="name-list">
                            {vendorNames.map((name, index) => (
                                <li key={index} className="name-item">
                                    {`Vendor${index + 1} = ${name}`}
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TicketPage;
