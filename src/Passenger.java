public class Passenger {
    static public int idProvider = 0;
    int id;
    String name;
    int age;
    char preference;
    String ticketType;
    int seatNumber;

    void setId() {
        this.id = ++idProvider;
    }
    int getId() {
        return this.id;
    }
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
    void setAge(int age) {
        this.age = age;
    }
    int getAge() {
        return this.age;
    }
    void setPreference(char preference) {
        this.preference = preference;
    }
    char getPreference() {
        return this.preference;
    }
    void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    String getTicketType() {
        return this.ticketType;
    }
    void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    int getSeatNumber() {
        return this.seatNumber;
    }
    void decrementId() {
        idProvider--;
    }
}
