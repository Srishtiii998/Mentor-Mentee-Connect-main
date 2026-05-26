import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        while (true) {
            System.out.println("\n====================================");
            System.out.println("      WELCOME TO MENTOR CONNECT     ");
            System.out.println("====================================");
            System.out.println("1. Register as Mentor");
            System.out.println("2. Register as Mentee");
            System.out.println("3. Register as Admin");
            System.out.println("4. Login");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = getIntInput(scanner);

            switch (choice) {
                case 1: registerMentor(scanner, userManager); break;
                case 2: registerMentee(scanner, userManager); break;
                case 3: registerAdmin(scanner, userManager);  break;
                case 4: login(scanner, userManager);           break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ─── Registration ────────────────────────────────────────────────────────

    private static void registerMentor(Scanner scanner, UserManager userManager) {
        System.out.println("\n=== Mentor Registration ===");
        System.out.print("Name: ");       String name       = scanner.nextLine();
        System.out.print("Email: ");      String email      = scanner.nextLine();
        System.out.print("Password: ");   String password   = scanner.nextLine();
        System.out.print("Expertise: ");  String expertise  = scanner.nextLine();
        System.out.print("Years of Experience: ");
        int experience = getIntInput(scanner);

        userManager.registerUser(new Mentor(name, email, password, expertise, experience));
    }

    private static void registerMentee(Scanner scanner, UserManager userManager) {
        System.out.println("\n=== Mentee Registration ===");
        System.out.print("Name: ");     String name     = scanner.nextLine();
        System.out.print("Email: ");    String email    = scanner.nextLine();
        System.out.print("Password: "); String password = scanner.nextLine();
        System.out.print("Area of Interest: "); String interest = scanner.nextLine();

        userManager.registerUser(new Mentee(name, email, password, interest));
    }

    private static void registerAdmin(Scanner scanner, UserManager userManager) {
        System.out.println("\n=== Admin Registration ===");
        System.out.print("Name: ");     String name     = scanner.nextLine();
        System.out.print("Email: ");    String email    = scanner.nextLine();
        System.out.print("Password: "); String password = scanner.nextLine();

        userManager.registerUser(new Admin(name, email, password));
    }

    // ─── Login ───────────────────────────────────────────────────────────────

    private static void login(Scanner scanner, UserManager userManager) {
        System.out.println("\n=== Login ===");
        System.out.print("Email: ");    String email    = scanner.nextLine();
        System.out.print("Password: "); String password = scanner.nextLine();

        User user = userManager.login(email, password);
        if (user == null) {
            System.out.println("❌ Login failed. Invalid credentials.");
            return;
        }

        System.out.println("\nLogin successful! Welcome, " + user.getName() + "!\n");
        user.displayProfile();

        if      (user instanceof Admin)  adminDashboard(scanner, (Admin) user, userManager);
        else if (user instanceof Mentor) mentorDashboard(scanner, (Mentor) user);
        else if (user instanceof Mentee) menteeDashboard(scanner, (Mentee) user, userManager);
    }

    // ─── Mentor Dashboard ────────────────────────────────────────────────────

    private static void mentorDashboard(Scanner scanner, Mentor mentor) {
        while (true) {
            System.out.println("\n--- Mentor Dashboard ---");
            System.out.println("1. View Profile");
            System.out.println("2. View Average Rating");
            System.out.println("3. Add a Rating (simulate)");
            System.out.println("4. Edit Expertise");
            System.out.println("5. Edit Experience");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            switch (getIntInput(scanner)) {
                case 1: mentor.displayProfile(); break;
                case 2:
                    System.out.printf("Average Rating: %.2f (%d reviews)%n",
                            mentor.getAverageRating(), mentor.getRatingCount());
                    break;
                case 3:
                    System.out.print("Enter rating to add (1.0 - 5.0): ");
                    double r = Double.parseDouble(scanner.nextLine().trim());
                    mentor.addRating(r);
                    break;
                case 4:
                    System.out.print("Enter new expertise: ");
                    mentor.setExpertise(scanner.nextLine());
                    System.out.println("Expertise updated.");
                    break;
                case 5:
                    System.out.print("Enter new years of experience: ");
                    mentor.setExperienceYears(getIntInput(scanner));
                    System.out.println("Experience updated.");
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ─── Mentee Dashboard ────────────────────────────────────────────────────

    private static void menteeDashboard(Scanner scanner, Mentee mentee, UserManager userManager) {
        while (true) {
            System.out.println("\n--- Mentee Dashboard ---");
            System.out.println("1. View Profile");
            System.out.println("2. Find Matching Mentors");
            System.out.println("3. Send Mentor Request");
            System.out.println("4. View Next Pending Request");
            System.out.println("5. Process (Remove) Next Request");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            switch (getIntInput(scanner)) {
                case 1: mentee.displayProfile(); break;
                case 2:
                    ArrayList<Mentor> matches = userManager.findMatchingMentors(mentee);
                    if (matches.isEmpty()) {
                        System.out.println("No mentors found matching your interest: " + mentee.getInterest());
                    } else {
                        System.out.println("\nMatching Mentors for '" + mentee.getInterest() + "':");
                        for (Mentor m : matches) m.displayProfile();
                    }
                    break;
                case 3:
                    System.out.print("Enter mentor name to request: ");
                    mentee.sendRequest(scanner.nextLine());
                    break;
                case 4: mentee.viewNextRequest();  break;
                case 5: mentee.processRequest();   break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ─── Admin Dashboard ─────────────────────────────────────────────────────

    private static void adminDashboard(Scanner scanner, Admin admin, UserManager userManager) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. View All Users");
            System.out.println("2. Search User by Email");
            System.out.println("3. View Mentors Sorted by Experience");
            System.out.println("4. Remove a User");
            System.out.println("5. Total User Count");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            switch (getIntInput(scanner)) {
                case 1: admin.viewAllUsers(userManager.getAllUsers()); break;
                case 2:
                    System.out.print("Enter email to search: ");
                    String searchEmail = scanner.nextLine();
                    User found = admin.searchByEmail(userManager.getAllUsers(), searchEmail);
                    if (found != null) { System.out.println("Found:"); found.displayProfile(); }
                    else System.out.println("No user found with that email.");
                    break;
                case 3:
                    ArrayList<Mentor> sorted = admin.getMentorsSortedByExperience(userManager.getAllUsers());
                    if (sorted.isEmpty()) System.out.println("No mentors registered.");
                    else { System.out.println("\nMentors by Experience (Descending):"); sorted.forEach(Mentor::displayProfile); }
                    break;
                case 4:
                    System.out.print("Enter email of user to remove: ");
                    admin.removeUser(userManager.getAllUsers(), scanner.nextLine());
                    break;
                case 5:
                    System.out.println("Total registered users: " + userManager.getTotalUsers());
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ─── Utility ─────────────────────────────────────────────────────────────

    // Safe integer input — avoids InputMismatchException crashing the app
    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
