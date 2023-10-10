/**
 * Represents a user with basic personal details.
 */
public class User {

    // User's first name
    private String name;

    // User's last name
    private String lastName;

    // User's age
    private int age;

    // User's email address
    private String email;

    /**
     * Constructs a new User with the given details.
     *
     * @param name     the first name of the user
     * @param lastName the last name of the user
     * @param age      the age of the user
     * @param email    the email address of the user
     */
    public User(String name, String lastName, int age, String email) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    /**
     * Returns the user's first name.
     *
     * @return the first name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Updates the user's first name.
     *
     * @param newName the new first name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Returns the user's last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Updates the user's last name.
     *
     * @param newLastName the new last name
     */
    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    /**
     * Returns the user's age.
     *
     * @return the age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Updates the user's age.
     *
     * @param newAge the new age
     */
    public void setAge(int newAge) {
        this.age = newAge;
    }

    /**
     * Returns the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Updates the user's email address.
     *
     * @param newEmail the new email address
     */
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    /**
     * Returns a string representation of the user details.
     *
     * @return a formatted string with user's name, age, and email
     */
    @Override
    public String toString() {
        return "Name: " + this.name + " " + this.lastName + "\nAge: " + this.age + "\nEmail: " + this.email;
    }
}
