import React from 'react'
import ReactDOM from 'react-dom/client'
import './style/index.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import ErrorPage from "./routes/ErrorPage.jsx";
import Home from "./routes/Home.jsx";
import Root from "./routes/Root.jsx";
import Booking from "./routes/Booking.jsx";
import ConfirmBooking from "./routes/ConfirmBooking.jsx";



const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
        children: [
            {
                errorElement: <ErrorPage />,
                children:[
                    {
                        index: true,
                        element: <Home />
                    },
                    {
                        path: "/booking",
                        element: <Booking />
                    },
                    {
                        path: "/confirm-booking/:bookingId",
                        element: <ConfirmBooking />
                    }
                ]
            }
        ]
    }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router ={router} />
    </React.StrictMode>,
)
