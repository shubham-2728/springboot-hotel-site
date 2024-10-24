import "../style/home.css";
import {Link} from "react-router-dom";
import RestClient from "../RestClient.js";
import React, {useEffect, useState} from 'react';
import {Carousel} from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import standardRoomOverviewPicture from '../assets/standard-room/standard-room-overview.jpeg';
import deluxeRoomOverviewPicture from '../assets/deluxe-room/deluxe-room-overview.jpeg';
import suiteRoomOverviewPicture from '../assets/suite-room/suite-room-overview.png';

import standardRoomImage1 from '../assets/standard-room/standard-room-1.jpg';
import standardRoomImage2 from '../assets/standard-room/standard-room-2.jpg';
import standardRoomImage3 from '../assets/standard-room/standard-room-3.jpg';


import deluxeRoomImage1 from '../assets/deluxe-room/deluxe-room-1.jpg';
import deluxeRoomImage2 from '../assets/deluxe-room/deluxe-room-2.jpg';
import deluxeRoomImage3 from '../assets/deluxe-room/deluxe-room-3.jpg';

import suiteRoomImage1 from '../assets/suite-room/suite-room-1.jpg';
import suiteRoomImage2 from '../assets/suite-room/suite-room-2.jpeg';
import suiteRoomImage3 from '../assets/suite-room/suite-room-3.jpeg';

import coatHangerIcon from '../assets/icons/coat-rack.gif';
import cushionIcon from '../assets/icons/cushion.gif';
import tvStandIcon from '../assets/icons/tv-stand.gif';
import safeBoxIcon from '../assets/icons/safe.gif';
import owlIcon from '../assets/icons/owl.gif';
import growingIcon from '../assets/icons/money-plant.gif';
import catIcon from '../assets/icons/cat.gif';
import foodTrolleyIcon from '../assets/icons/food-trolley.gif';
import rocketIcon from '../assets/icons/rocket.gif';


export default function Home(){

    // Get today's date
    const today = new Date();

    // Get the date 2 days from now
    const twoDaysFromNow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 3);

    // Convert the dates into string format "dd-mm-yyyy"
    const startDateString = today.toISOString().substr(0, 10);
    const endDateString = twoDaysFromNow.toISOString().substr(0, 10);

    // Declare startDate and endDate using useState hook
    const [startDate, setStartDate] = useState(startDateString);
    const [endDate, setEndDate] = useState(endDateString);
    const [availableRooms, setAvailableRooms] = useState([]);
    const [selectedRoom, setSelectedRoom] = useState(null);
    const [searchClicked, setSearchClicked] = useState(false);


    const roomImages = {
        'standard': [
            standardRoomImage1,
            standardRoomImage2,
            standardRoomImage3
        ],
        'deluxe': [
            deluxeRoomImage1,
            deluxeRoomImage2,
            deluxeRoomImage3
        ],
        'suite':[
            suiteRoomImage1,
            suiteRoomImage2,
            suiteRoomImage3
        ]
    };


    const amenityImages = {
        'coat hanger': coatHangerIcon,
        'soft cushion': cushionIcon,
        'television': tvStandIcon,
        'safe': safeBoxIcon,
        'smart owl': owlIcon,
        'money plant': growingIcon,
        'cute cat': catIcon,
        'room service': foodTrolleyIcon,
        'personal moon rocket': rocketIcon
    };

    const roomReviews = {
        'standard': {
            rating: 4.2, // out of 5
            comments: [
                {name: 'Mark S.', comment: 'Excellent service! Definitely coming back'},
                {name: 'Sophia L.', comment: 'Loved the view!'},
                {name: 'Liam H.', comment: 'Comfortable bed, had a good sleep.'}
            ]
        },
        'deluxe': {
            rating: 4.6,
            comments: [
                {name: 'Oliver P.', comment: 'Fantastic room, felt like home.'},
                {name: 'Emma G.', comment: 'Great amenities, worth every penny.'},
                {name: 'Ava J.', comment: 'The food was delicious!'}
            ]
        },
        'suite': {
            rating: 5.0,
            comments: [
                {name: 'Mr. Toggweiler', comment: 'Best stay ever, will visit again. This project is a clear 6 ;)'}

            ]
        },
    };

    useEffect(() => {
        RestClient.getAvailableRooms(startDate, endDate)
            .then(data => {
                const roomsAvailability = data[0]; // Extract object from array

                // Fetch room types and combine with availability information
                RestClient.getRoomTypes()
                    .then(roomTypes => {
                        const combinedRooms = roomTypes.map(roomType => {

                            return {
                                ...roomType,
                                available: roomsAvailability[roomType.type.toUpperCase()],
                                // Adding amenities property
                                amenities: roomType.type.toLowerCase() === 'standard' ? ['coat hanger', 'television', 'safe'] :
                                    roomType.type.toLowerCase() === 'deluxe' ? ['soft cushion', 'cute cat', 'money plant'] :
                                        roomType.type.toLowerCase() === 'suite' ? ['smart owl', 'room service', 'personal moon rocket'] : []
                            };
                        });
                        console.log(combinedRooms);
                        // Set availableRooms state with the combined data
                        setAvailableRooms(combinedRooms);
                    })
            })
            .catch(err => console.error(err));
    }, [startDate, endDate, searchClicked]);




    function renderRoomDetails(room) {
        const currentRoomImages = roomImages[room.type.toLowerCase()];
        const roomReview = roomReviews[room.type.toLowerCase()];
        const stars = '⭐'.repeat(Math.round(roomReview.rating));


        return (
            <div className="room-details">
                <Carousel className="carousel-component">
                    {currentRoomImages.map((image, index) =>
                        <div key={index}>
                            <img src={image} alt={`room ${room.type} view ${index + 1}`}/>
                            <p className="legend">{`Image ${index + 1}`}</p>
                        </div>
                    )}
                </Carousel>
                <div className="room-text-details">
                    <h2>{room.type} Room Details</h2>
                    <p>{room.longDescription}</p>

                    <div className="amenities">
                        <p><h3>Amenities:</h3></p>
                        {room.amenities.map((amenity, index) => (  /* Use room amenities instead */
                            <div key={index} className="amenity">
                                <img src={amenityImages[amenity]} alt={amenity}/>
                                <p>{amenity}</p>
                            </div>
                        ))}
                    </div>

                    <div className="room-reviews">
                        <div className="room-rating">
                            <p>{stars}</p>
                            <p>{roomReview.rating} out of 5</p>
                        </div>
                        <div className="room-comments">
                            <h3>Guest comments:</h3>
                            {roomReview.comments.map((commentObj, i) => <p key={i}>{commentObj.name + ': ' + commentObj.comment}</p>)}
                        </div>

                    <button onClick={() => setSelectedRoom(null)}>Go Back</button>
                </div>
                </div>
            </div>
        );
            }

    function renderRoomInfo(room) {
        let imagePath;
        switch (room.type.toLowerCase()) {
            case 'standard':
                imagePath = standardRoomOverviewPicture;
                break;
            case 'deluxe':
                imagePath = deluxeRoomOverviewPicture;
                break;
            case 'suite':
                imagePath = suiteRoomOverviewPicture;
                break;
            default:
                imagePath = null; // fallback image
        }
        return (
            <div className="type-room">
                <img src={imagePath}/>
                <div className="room-info">
                    <p><b>{room.type} Room</b></p>
                    <p>Price per night: {room.price}</p>
                    <p>Availability: {room.available || 0}</p>


                    <p>{room.shortDescription}</p>
                    <div className="btn-container">
                        <button onClick={() => setSelectedRoom(room)}>Details</button>
                        <Link to={{
                            pathname: '/Booking',
                            search: `?roomType=${room.type.toLowerCase()}&date_start=${startDate}&date_end=${endDate}`
                        }}>
                            <button disabled={room.available <= 0 || room.available === undefined}>Book room</button>
                        </Link>
                    </div>
                </div>

            </div>
        )
    };

    return (
        <div className="main">

            <div className="hotel-name">
                <h1>HSLU City Hotel</h1>
                <p>The HSLU City Hotel, nestled in the vibrant heart of the city, offers a unique blend of comfort and
                    style, making it an ideal choice for both business and leisure travellers. The hotel features a
                    range of accommodations tailored to suit various preferences and budgets. As one of our special
                    rooms, you can reserve the Toggweiler suite, the epitome of luxury and exclusivity, featuring a
                    coding desk and sailoring details.</p>
            </div>

            <div className="hotel-description">
                <p>Check availability
                    Check-In:
                    <input
                        type="date"
                        name="check-in"
                        value={startDate}
                        onChange={e => setStartDate(e.target.value)}
                    />
                    Check-Out:
                    <input
                        type="date"
                        name="check-out"
                        value={endDate}
                        onChange={e => setEndDate(e.target.value)}
                    />
                    <button onClick={() => {
                        setSelectedRoom(null);
                        setSearchClicked((prev) => !prev);  // Toggle searchClicked state
                    }}>
                        Search
                    </button>
                </p>

            </div>

            <div className="room-type-overview">
                {selectedRoom ? renderRoomDetails(selectedRoom) : availableRooms.map(room => renderRoomInfo(room))}
            </div>

        </div>
    );

}