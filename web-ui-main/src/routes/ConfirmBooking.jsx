import React from 'react';
import '../style/confirm-booking.css';
import { useParams, useNavigate, useLocation } from 'react-router-dom';

export default function ConfirmBooking() {
    const {bookingId} = useParams();
    const { state } = useLocation();
    const name = state ? state.name : '';

    const formattedBookingId = `HSLU-HOTEL-${bookingId.toString().padStart(4, '0')}`;

    let navigate = useNavigate();

    const handleOnClickBack = () => {
        navigate('/');
    }

    return (
        <div className="booking-confirmation-container">
            <h2>Confirmation</h2>
            <p>Thank you for your booking {name}. Your booking id is {formattedBookingId}</p>
            <button onClick={handleOnClickBack}>Back to homepage</button>
        </div>
    );
}