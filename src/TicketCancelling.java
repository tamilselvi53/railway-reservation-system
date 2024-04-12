import java.util.*;

public class TicketCancelling extends TicketBooking {
    public static Map<Character, Queue<Integer>> cancelledTickets = new HashMap<>();
    static {
        cancelledTickets.put('U', new PriorityQueue<Integer>());
        cancelledTickets.put('M', new PriorityQueue<Integer>());
        cancelledTickets.put('L', new PriorityQueue<Integer>());
    }
    public boolean cancelTicket(int id) {
        for (Passenger p: confirmedTickets) {
            if (p.getId() == id) {
                char cancelledPreference = p.getPreference();
                int cancelledSeatNumber = p.getSeatNumber();
                String currTicketType = p.getTicketType();
                confirmedTickets.remove(p);
                if (!racQueue.isEmpty()) {
                    Passenger curr = racQueue.poll();
                    curr.setTicketType(currTicketType);
                    curr.setPreference(cancelledPreference);
                    curr.setSeatNumber(cancelledSeatNumber);
                    confirmedTickets.add(curr);
                } else {
                    cancelledTickets.get(cancelledPreference).add(p.seatNumber);
                }
                if (!waitingListQueue.isEmpty()) {
                    Passenger curr = waitingListQueue.poll();
                    updateRacQueue(curr);
                }
                System.out.println("Ticket Cancelled Successfully");
                return true;
            }
        }
        for (Passenger p: racQueue) {
            if (p.getId() == id) {
                racQueue.remove(p);
                if (!waitingListQueue.isEmpty()) {
                    Passenger curr = waitingListQueue.poll();
                    updateRacQueue(curr);
                }
                return true;
            }
        }
        for (Passenger p: waitingListQueue) {
            if (p.getId() == id) {
                waitingListQueue.remove(p);
            }
            return true;
        }
        System.out.println("Please, enter correct ID");
        return false;
    }
}

