import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TicketBooking {
    static ArrayList<Passenger> confirmedTickets = new ArrayList<>();
    static Queue<Passenger> racQueue = new PriorityQueue<>((a, b) -> a.getId() - b.getId());
    static Queue<Passenger> waitingListQueue = new PriorityQueue<>((a, b) -> a.getId() - b.getId());

    static ArrayList<Passenger> upperBerth = new ArrayList<>();
    static ArrayList<Passenger> middleBerth = new ArrayList<>();
    static ArrayList<Passenger> lowerBerth = new ArrayList<>();

    static int berthLimit = 3 / 3;
    static int racLimit = 1;
    static int waitingListLimit = 1;
    static int upper = 1, middle = 2, lower = 3;
    void bookTicket(Passenger p) {
        //Not available in confirmed list
        if (upperBerth.size() == berthLimit && middleBerth.size() == berthLimit && lowerBerth.size() == berthLimit) {
            if (updateRacQueue(p)) {
                System.out.println("You are added in RAC Queue");
            } else if (updateWaitinglistQueue(p)) {
                System.out.println("You are added in Waiting list Queue");
            } else {
                System.out.println("Sorry, Tickets not available");
                p.decrementId();
            }
        } else if (preferenceAvailable(p)) {
            updateConfirmedListQueue(p);
            System.out.println("You get confirmed seat with" + "\nSeat Number: " + p.getSeatNumber() + "\nId: " + p.getId() + "\nTicketType: " + p.getTicketType());
        } else {
            System.out.print("check preferences");
            p.decrementId();
        }
    }

    boolean preferenceAvailable(Passenger p) {
        // Map<Character, Queue<Integer>> cancelled = TicketCancelling.cancelledTickets;
        if (!TicketCancelling.cancelledTickets.get(p.getPreference()).isEmpty()) {
            Integer avb_seat = TicketCancelling.cancelledTickets.get(p.getPreference()).poll();
            p.setSeatNumber(avb_seat);
        } else if (p.getPreference() == 'U' && upperBerth.size() < berthLimit) {
            p.setSeatNumber(upper);
            upperBerth.add(p);
            upper += 3;
        } else if (p.getPreference() == 'M' && middleBerth.size() < berthLimit) {
            p.setSeatNumber(middle);
            middleBerth.add(p);
            middle += 3;
        } else if (p.getPreference() == 'L' && lowerBerth.size() < berthLimit){
            p.setSeatNumber(lower);
            lowerBerth.add(p);
            lower += 3;
        } else {
            return false;
        }
        return true;
    }

    boolean updateRacQueue(Passenger p) {
        if (racQueue.size() < racLimit) {
            p.setTicketType("RAC");
            racQueue.add(p);
            return true;
        }
        return false;
    }
    boolean updateWaitinglistQueue(Passenger p) {
        if (waitingListQueue.size() < waitingListLimit) {
            p.setTicketType("Waiting List");
            waitingListQueue.add(p);
            return true;
        }
        return false;
    }
    void updateConfirmedListQueue(Passenger p) {
        p.setTicketType("Confirmed");
        confirmedTickets.add(p);
    }
    void checkAvailablePreferences() {
        System.out.println("Upper Berth: " + (berthLimit - upperBerth.size()));
        System.out.println("Middle Berth: " + (berthLimit - middleBerth.size()));
        System.out.println("Lower Berth: " + (berthLimit - lowerBerth.size()));
    }
    void displayConfirmedSeats() {
        for (Passenger p: confirmedTickets) {
            System.out.println("\nPassenger id: " + p.getId() + "\nName: " + p.getName() + "\nAge: " + p.getAge() + "\nSeatNumber: " + p.getSeatNumber() + "\nPreference: " + p.getPreference());
        }
    }
    void displayWaitingListSeats() {
        for (Passenger p: waitingListQueue) {
            System.out.println("\nPassenger id: " + p.getId() + "\nName: " + p.getName() + "\nAge: " + p.getAge() + "\nSeatNumber: " + p.getSeatNumber() + "\nPreference: " + p.getPreference());
        }
    }
    void displayRacSeats() {
        for (Passenger p: racQueue) {
            System.out.println("\nPassenger id: " + p.getId() + "\nName: " + p.getName() + "\nAge: " + p.getAge() + "\nSeatNumber: " + p.getSeatNumber() + "\nPreference: " + p.getPreference());
        }
    }
}