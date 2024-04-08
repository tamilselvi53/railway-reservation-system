import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("Choose any one option" + "\n1. Ticket Booking" + "\n2. Cancel Ticket" + "\n3. Display Available Preference" + "\n4. Display Available Tickets" + "\n5. Display Rac Tickets" + "\n6. Display Waitlinglist Tickets" + "\n7.Exit");
            int option = sc.nextInt();
            TicketBooking tb = new TicketBooking();
            TicketCancelling tc = new TicketCancelling();
            switch(option) {
                case 1:
                    Passenger p = new Passenger();
                    System.out.println("Enter your name\n");
                    String name = sc.next();
                    p.setName(name);
                    System.out.println("Enter your age\n");
                    int age = sc.nextInt();
                    p.setAge(age);
                    System.out.println("Enter your preference\n");
                    char preference = sc.next().charAt(0);
                    p.setPreference(preference);
                    p.setId();
                    tb.bookTicket(p);
                    break;
                case 2:
                    System.out.println("Enter your id to cancel ticket");
                    int id = sc.nextInt();
                    tc.cancelTicket(id);
                    break;
                case 3:
                    tb.checkAvailablePreferences();
                    break;
                case 4:
                    tb.displayConfirmedSeats();
                    break;
                case 5:
                    tb.displayRacSeats();
                    break;
                case 6:
                    tb.displayWaitingListSeats();
                    break;
                case 7:
                    loop = false;
                    break;
            }
        }
    }
}