// eslint-disable-next-line no-unused-vars
import React from "react";
import * as PropTypes from "prop-types";

const TicketStatus = ({ Tickets, TicketPool }) => {
    return (
        <div className="ticket-status-container">
            <h3 className="ticket-status-title">Current Ticket Status</h3>

            <div className="ticket-status-cards">
                <div className="status-card">
                    <div className="status-card-title">Total Tickets</div>
                    <div className="status-card-value">{Tickets}</div>
                </div>

                <div className="status-card">
                    <div className="status-card-title">Available Tickets</div>
                    <div className="status-card-value">{TicketPool}</div>
                </div>
            </div>
        </div>
    );
};

TicketStatus.propTypes = {
    Tickets: PropTypes.any,
    TicketPool: PropTypes.any
};

export default TicketStatus;
