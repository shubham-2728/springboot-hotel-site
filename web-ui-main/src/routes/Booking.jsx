
import "../style/book-room.css";
import { useLocation, useNavigate } from 'react-router-dom';
import RestClient from "../RestClient.js";
import React, { useState, useEffect } from 'react';
import { Checkbox, FormControlLabel, Dialog, DialogTitle, DialogContent, DialogActions, Button } from '@mui/material';

import standardRoomOverviewPicture from '../assets/standard-room/standard-room-overview.jpeg';
import deluxeRoomOverviewPicture from '../assets/deluxe-room/deluxe-room-overview.jpeg';
import suiteRoomOverviewPicture from '../assets/suite-room/suite-room-overview.png';

export default function Booking(){
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const [checked1, setChecked1] = useState(false);
    const [checked2, setChecked2] = useState(false);
    const [open, setOpen] = useState(false);
    const [price, setPrice] = useState(0);

    let query = new URLSearchParams(useLocation().search);
    let roomType = query.get("roomType");
    let date_start = query.get("date_start");
    let date_end = query.get("date_end");

    let navigate = useNavigate();
    let roomTypeOverviewPicture;

    let dayCount = new Date(date_end) - new Date(date_start);
    dayCount = dayCount / (1000 * 60 * 60 * 24);  // convert milliseconds to days
    let totalPrice = dayCount ? (dayCount * price) : 0;

    useEffect(() => {
        if (roomType === 'suite') {
            setOpen(true);
        }

        RestClient.getRoomTypes()
            .then(roomTypes => {
                const room = roomTypes.find(rt => rt.type.toLowerCase() === roomType);
                if (room) {
                    setPrice(room.price);
                    console.log("price:" + price);
                }
            })
            .catch(err => console.error(err));
    }, []);

    function handleClose(isConfirmed) {
        setOpen(false);
        if(!isConfirmed){
            navigate('/');
        }
    }



    switch(roomType) {
        case 'standard':
            roomTypeOverviewPicture = standardRoomOverviewPicture;
            break;
        case 'deluxe':
            roomTypeOverviewPicture = deluxeRoomOverviewPicture;
            break;
        case 'suite':
            roomTypeOverviewPicture = suiteRoomOverviewPicture;
            break;
        default:
            roomTypeOverviewPicture = standardRoomOverviewPicture; // default image
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        RestClient.addBooking(roomType, date_start, date_end, name, email)
            .then(response => {
                console.log(response);
                navigate(`/confirm-booking/${response.reservationId}`, {
                    state: { name }
                });
            })
            .catch(err => {
                console.error(err);
            });
    }

    return (

        <div className='booking-main'>

            <div className='box-left'>
                <img className='overview-image' src={roomTypeOverviewPicture} alt='Room Type'/>
                <div className='box-left-content'>
                    <p>Room Type: {roomType}</p>
                    <p>Start Date: {date_start}</p>
                    <p>End Date: {date_end}</p>
                    <p>Price: {
                        (() => {
                            let dayCount = new Date(date_end) - new Date(date_start);
                            dayCount = dayCount / (1000 * 60 * 60 * 24);  // convert milliseconds to days
                            let totalPrice = dayCount ? (dayCount * price) : 0;
                            return totalPrice;
                        })()
                    }</p>
                    <p>to be paid on arrival</p>
                </div>
            </div>

            <div className='box-right'>
                <form onSubmit={handleSubmit}>
                    <h3>Personal Details</h3>
                    <label>
                        Name:
                        <input className="input-field"
                               type="text"
                               required
                               value={name}
                               onChange={e => setName(e.target.value)}
                        />
                    </label>
                    <br/>

                    <label>
                        Email:
                        <input className="input-field"
                            type="email"
                            required
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        />
                    </label>
                    <br/>

                    <button id="submit-booking" type="submit">Submit Booking</button>
                </form>
            </div>
            <Dialog open={open} disableEscapeKeyDown>
                <DialogTitle>{"Hold Your Horses!"}</DialogTitle>
                <DialogContent>
                    <p>You're about to book the VIP Toggweiler Suite.</p>
                    <p>To make sure that you are entitled to do so please confirm below:</p>
                    <FormControlLabel
                        control={<Checkbox checked={checked1} onChange={(e) => setChecked1(e.target.checked)}/>}
                        label="I'm Mr. Toggweiler."
                    />
                    <FormControlLabel
                        control={<Checkbox checked={checked2} onChange={(e) => setChecked2(e.target.checked)}/>}
                        label="I am aware that any claim to be Mr. Toggweiler without actually being Mr. Toggweiler can result in legal action."
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => handleClose(false)}>Cancel</Button>
                    <Button onClick={() => handleClose(true)} disabled={!(checked1 && checked2)}>Continue
                        booking</Button>
                </DialogActions>
            </Dialog>

        </div>
    );
}