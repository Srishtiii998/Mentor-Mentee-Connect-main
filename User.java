public abstract class User {
    protected String name;
    protected String email;
    private String password; // made private for better encapsulation

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public abstract void displayProfile();

    // New: every subclass must declare its role
    public abstract String getRole();

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
}
