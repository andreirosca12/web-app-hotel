import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [hotels, setHotels] = useState([]);
  const [error, setError] = useState(null);
  const [input, setInput] = useState('');
  const [userLocation, setUserLocation] = useState(null);
  const [bookings, setBookings] = useState([]);

  const getUserLocation = () => {
    return new Promise((resolve, reject) => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          position => {
            const location = {
              latitude: position.coords.latitude,
              longitude: position.coords.longitude
            };
            setUserLocation(location);
            console.log('User location:', location);
            resolve(location);
          },
          error => {
            if (error.code === error.PERMISSION_DENIED) {
              setError('User denied access to their location.');
            } else {
              setError('Error getting user location. Please allow location access.');
            }
            reject(error);
          }
        );
      } else {
        setError('Geolocation is not supported by your browser.');
        reject(new Error('Geolocation is not supported'));
      }
    });
  };
const fetchBookingsForRooms = async () => {
      try {
        const respo = await axios.get('http://localhost:8080/book/all');
        setBookings(respo.data);
      } catch (error) {
        console.error('Error fetching bookings:', error);
        setBookings([]);
      }
    };
  useEffect(() => {
    
    fetchBookingsForRooms();
  }, []); // Empty dependency array ensures this effect runs only once, on component mount

  const fetchRoomsForHotel = async (hotel) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/v1/hotels/${hotel.id}/rooms`);
      return response.data.map(room => ({
        ...room,
        hotelName: hotel.name // Attach hotel name to each room
      }));
    } catch (error) {
      console.error('Error fetching rooms:', error);
      return [];
    }
  };

  const sendDataToBackend = async () => {
    try {
      const location = await getUserLocation();
      axios.get('http://localhost:8080/api/v1/hotels/nearby', {
        params: {
          userLatitude: location.latitude,
          userLongitude: location.longitude,
          radius: input
        }
      })
        .then(async response => {
          const nearbyHotels = response.data;
          const hotelsWithRoomsPromises = nearbyHotels.map(hotel => fetchRoomsForHotel(hotel));
          const hotelsWithRooms = await Promise.all(hotelsWithRoomsPromises);
          setHotels(hotelsWithRooms);
          setError(null);
        })
        .catch(error => {
          console.error('Error:', error);
          setError('Failed to fetch nearby hotels.');
        });
    } catch (error) {
      console.error('Error:', error);
      setError('Failed to get user location.');
    }
  };

  const handleInputChange = (event) => {
    setInput(event.target.value);
  };


  const deleteBook = async (bookId) => {
    try {
      await axios.delete(`http://localhost:8080/book/${bookId}/delete`);
      // If deletion is successful (assuming server allows DELETE), fetch updated bookings
      fetchBookingsForRooms(); // Assuming this fetches updated bookings
    } catch (error) {
      console.error('Error deleting booking:', error);
      // More informative error message for user
      if (error.response && error.response.status === 405) {
        setError('Deleting bookings might not be supported on this server.');
      } else {
        setError('Failed to delete booking.');
      }
    }
  };
  return (
    <div className="App">
      <button onClick={sendDataToBackend}>Search Hotels</button>
      <input 
        type="text" 
        placeholder="Enter radius" 
        value={input} 
        onChange={handleInputChange} 
      />
      {error && <p>{error}</p>}
      <div>
        {hotels.map(hotel => (
          <div key={hotel.id}>
            <h2>Hotel: {hotel[0].hotelName}</h2>
            <h3>Rooms:</h3>
            <ul>
              {hotel.map(room => (
                <li key={room.id}>
                  Room: {room.roomNumber} - Type: {room.type} -Price: {room.price} -Available: {room.isAvailable}
                  {room.isAvailable === "true" && (
                    <button>Book</button>
                  )}
                </li>
              ))}
            </ul>
          </div>
        ))}
      </div>
      <div>
        <h2>Bookings</h2>
        <ul>
          {
            bookings.map((booking) => (
              <li key={booking.id}>
                <h3>Booking ID: {booking.bookId}</h3>
                <p>Room Number: {booking.roomNumber}</p>
                <p>Data: {booking.date}</p>
                <button onClick={()=>{deleteBook(booking.bookId)}}>Cancel</button>
              </li>
            ))
          }
        </ul>
      </div>
    </div>
  );
}

export default App;
