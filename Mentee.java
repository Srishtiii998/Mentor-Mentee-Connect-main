import java.util.LinkedList;
import java.util.Queue;

public class Mentee extends User {
    private String interest;
    // DSA: Queue to manage mentor connection requests (FIFO)
    private Queue<String> pendingRequests;

    public Mentee(String name, String email, String password, String interest) {
        super(name, email, password);
        this.interest = interest;
        this.pendingRequests = new LinkedList<>();
    }

    // Send a connection request to a mentor
    public void sendRequest(String mentorName) {
        pendingRequests.add(mentorName);
        System.out.println("Request sent to mentor: " + mentorName);
    }

    // Process/view the next pending request
    public void viewNextRequest() {
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            System.out.println("Next request to: " + pendingRequests.peek());
        }
    }

    // Remove a processed request from the queue
    public void processRequest() {
        if (pendingRequests.isEmpty()) {
            System.out.println("No requests to process.");
        } else {
            String processed = pendingRequests.poll();
            System.out.println("Request to '" + processed + "' has been processed.");
        }
    }

    public int getPendingRequestCount() {
        return pendingRequests.size();
    }

    public String getInterest() {
        return interest;
    }

    @Override
    public String getRole() {
        return "Mentee";
    }

    @Override
    public void displayProfile() {
        System.out.println("+---------------------------------+");
        System.out.println("  MENTEE PROFILE");
        System.out.println("+---------------------------------+");
        System.out.println("  Name     : " + name);
        System.out.println("  Email    : " + email);
        System.out.println("  Interest : " + interest);
        System.out.println("  Pending Requests : " + pendingRequests.size());
        System.out.println("+---------------------------------+");
    }
}
