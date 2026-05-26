import java.util.ArrayList;

public class Admin extends User {

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void displayProfile() {
        System.out.println("+---------------------------------+");
        System.out.println("  ADMIN PROFILE");
        System.out.println("+---------------------------------+");
        System.out.println("  Name  : " + name);
        System.out.println("  Email : " + email);
        System.out.println("  Role  : Administrator");
        System.out.println("+---------------------------------+");
    }

    public void viewAllUsers(ArrayList<User> users) {
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
            return;
        }
        System.out.println("\n====== ALL REGISTERED USERS ======");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("\n[" + (i + 1) + "] Role: " + users.get(i).getRole());
            users.get(i).displayProfile();
        }
        System.out.println("Total users: " + users.size());
    }

    // DSA: Linear Search — find user by email
    public User searchByEmail(ArrayList<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    // DSA: Bubble Sort — sort mentors by experience (descending)
    public ArrayList<Mentor> getMentorsSortedByExperience(ArrayList<User> users) {
        ArrayList<Mentor> mentors = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Mentor) mentors.add((Mentor) u);
        }

        // Bubble sort
        int n = mentors.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mentors.get(j).getExperienceYears() < mentors.get(j + 1).getExperienceYears()) {
                    Mentor temp = mentors.get(j);
                    mentors.set(j, mentors.get(j + 1));
                    mentors.set(j + 1, temp);
                }
            }
        }
        return mentors;
    }

    // Remove user by email
    public boolean removeUser(ArrayList<User> users, String email) {
        User toRemove = searchByEmail(users, email);
        if (toRemove != null) {
            users.remove(toRemove);
            System.out.println("User '" + toRemove.getName() + "' removed successfully.");
            return true;
        }
        System.out.println("User not found.");
        return false;
    }
}
