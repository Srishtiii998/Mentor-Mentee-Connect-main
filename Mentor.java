import java.util.ArrayList;

public class Mentor extends User {
    private String expertise;
    private int experienceYears;
    private ArrayList<Double> ratings; // DSA: ArrayList to store all ratings

    public Mentor(String name, String email, String password, String expertise, int experienceYears) {
        super(name, email, password);
        this.expertise = expertise;
        this.experienceYears = experienceYears;
        this.ratings = new ArrayList<>();
    }

    // Add a rating (1.0 - 5.0)
    public void addRating(double rating) {
        if (rating < 1.0 || rating > 5.0) {
            System.out.println("Invalid rating. Must be between 1.0 and 5.0.");
            return;
        }
        ratings.add(rating);
        System.out.println("Rating added successfully!");
    }

    // Calculate average from the list — demonstrates ArrayList traversal
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        double sum = 0;
        for (double r : ratings) {
            sum += r;
        }
        return sum / ratings.size();
    }

    public int getRatingCount() {
        return ratings.size();
    }

    // Check if mentor's expertise matches mentee's interest (for matching feature)
    public boolean matchesInterest(String interest) {
        return expertise.equalsIgnoreCase(interest);
    }

    public String getExpertise() {
        return expertise;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String getRole() {
        return "Mentor";
    }

    @Override
    public void displayProfile() {
        System.out.println("+---------------------------------+");
        System.out.println("  MENTOR PROFILE");
        System.out.println("+---------------------------------+");
        System.out.println("  Name       : " + name);
        System.out.println("  Email      : " + email);
        System.out.println("  Expertise  : " + expertise);
        System.out.println("  Experience : " + experienceYears + " years");
        System.out.println("  Avg Rating : " + String.format("%.2f", getAverageRating())
                + " (" + ratings.size() + " reviews)");
        System.out.println("+---------------------------------+");
    }
}
