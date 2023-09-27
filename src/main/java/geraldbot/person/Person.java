package geraldbot.person;

/**
 * Represents a person in the contact list.
 */
public class Person {
    private final String name;
    private final String phone;
    private final String email;
    /**
     * Constructs a Person with the given name, phone number, and email address.
     *
     * @param name  The name of the person.
     * @param phone The phone number of the person.
     * @param email The email address of the person.
     */
    public Person(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        assert this.name != null : "Name cannot be null.";
        assert this.phone != null : "Phone number cannot be null.";
        assert this.email != null : "Email address cannot be null.";
    }
    /**
     * Generates a formatted string for storing the person in the contact storage file.
     *
     * @return A formatted string representing the person for storage.
     */
    public String fileFormat() {
        return name + " | " + phone + " | " + email;
    }
    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    @Override
    public String toString() {
        return "\tName: " + this.name + " | Phone: " + this.phone + " | Email: " + this.email;
    }
}
