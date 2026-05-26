import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {
    // DSA: HashMap for O(1) email lookup (vs O(n) with ArrayList alone)
    private HashMap<String, User> userMap = new HashMap<>();
    // Keep ArrayList too for ordered iteration (Admin view, sorting)
    private ArrayList<User> userList = new ArrayList<>();

    public boolean registerUser(User user) {
        String email = user.getEmail().toLowerCase();

        // Duplicate email check using HashMap — O(1)
        if (userMap.containsKey(email)) {
            System.out.println(" Registration failed: Email already in use.");
            return false;
        }

        userMap.put(email, user);
        userList.add(user);
        System.out.println("✅ " + user.getRole() + " '" + user.getName() + "' registered successfully!");
        return true;
    }

    // O(1) login lookup using HashMap
    public User login(String email, String password) {
        User user = userMap.get(email.toLowerCase());
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    // Find mentors matching a mentee's interest
    public ArrayList<Mentor> findMatchingMentors(Mentee mentee) {
        ArrayList<Mentor> matches = new ArrayList<>();
        for (User u : userList) {
            if (u instanceof Mentor) {
                Mentor m = (Mentor) u;
                if (m.matchesInterest(mentee.getInterest())) {
                    matches.add(m);
                }
            }
        }
        return matches;
    }

    public ArrayList<User> getAllUsers() {
        return userList;
    }

    public int getTotalUsers() {
        return userList.size();
    }
}
