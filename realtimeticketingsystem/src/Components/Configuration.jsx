// eslint-disable-next-line no-unused-vars
import React, { useState } from "react"; // Converted to functional component
import PropTypes from "prop-types";

const Configuration = ({ config, onChange }) => {
    const [formState, setFormState] = useState(config);

    const inputHandler = (e) => {
        const { name, value } = e.target;
        const numericValue = parseInt(value, 10);

        // Validate if positive number and at least 1
        if (numericValue >= 1) {
            setFormState((prevState) => ({
                ...prevState, // Spread the previous state
                [name]: numericValue, // set state with validated value
            }));
        }
    };

    const submitHandler = (e) => {
        e.preventDefault();
        if (formState.TotalTickets > formState.maxticketCapacity) {
            alert("The Maximum Ticket Capacity should be greater than total ticket count");
            return;
        }

        if (formState.TicketReleaseRate < formState.CustomerRetrievalRate) {
            alert("Customer Retrieval Rate should be greater than Ticket Release Rate");
            return;
        }

        // Call onChange using update state
        onChange(formState);
        alert("Configuration updated successfully!");
    };

    // Create form structure
    return (
        <form onSubmit={submitHandler}>
            <h3>System Settings</h3>

            <label>
                Enter Total Tickets:
                <input
                    type="number"
                    name="TotalTickets"
                    value={formState.TotalTickets}
                    onChange={inputHandler}
                    min="1" // Ensures minimum value of 1
                    required
                />
            </label>
            <br />

            <label>
                Enter Ticket Release Rate:
                <input
                    type="number"
                    name="TicketReleaseRate"
                    value={formState.TicketReleaseRate}
                    onChange={inputHandler}
                    min="1" // Ensures minimum value of 1
                    required
                />
            </label>
            <br />

            <label>
                Enter Customer Retrieval Rate:
                <input
                    type="number"
                    name="CustomerRetrievalRate"
                    value={formState.CustomerRetrievalRate}
                    onChange={inputHandler}
                    min="1" // Ensures minimum value of 1
                    required
                />
            </label>
            <br />

            <label>
                Enter Maximum Ticket Capacity:
                <input
                    type="number"
                    name="maxticketCapacity"
                    value={formState.maxticketCapacity}
                    onChange={inputHandler}
                    min="1" // Ensures minimum value of 1
                    required
                />
            </label>
            <br />

            <label>
                No.of Tickets a Customer Can Purchase:
                <input
                    type="number"
                    name="quantity"
                    value={formState.quantity}
                    onChange={inputHandler}
                    min="1" // Ensures minimum value of 1
                    required
                />
            </label>
            <br />

            <button type="submit">Update System</button>
        </form>
    );
};

Configuration.propTypes = {
    config: PropTypes.object.isRequired, // Updated type
    onChange: PropTypes.func.isRequired, // Updated type
};

export default Configuration;
