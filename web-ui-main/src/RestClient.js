

export default class RestClient {

    static baseUrl = 'http://localhost:8082';

    static async getAvailableRooms(start_date, end_date) {
        const url = `${this.baseUrl}/reservations/available-rooms?start_date=${start_date}&end_date=${end_date}`;
        const response = await fetch(url);
        return await response.json();
    }

    static async getRoomTypes() {
        const url = `${this.baseUrl}/room/types`;
        const response = await fetch(url);
        return await response.json();
    }




    static async addBooking(roomType, start_date, end_date, name, email) {
        const url = `${this.baseUrl}/reservations/new`;

        const bookingData = {roomType, start_date, end_date, name, email};

        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingData)
        });

        return await response.json();
    }


}
