package geraldbot.util;

import java.util.ArrayList;

import geraldbot.person.Person;

/**
 * Represents a list of people.
 */
public class ContactList {
    private final ArrayList<Person> contactList;

    /**
     * Constructs a ContactList with the given list of people.
     *
     * @param contactList The initial list of people.
     */
    public ContactList(ArrayList<Person> contactList) {
        this.contactList = contactList;
        assert this.contactList != null : "Task list cannot be null.";
    }

    /**
     * Adds a person to the contact list.
     *
     * @param person The person to be added.
     */
    public void add(Person person) {
        assert person != null : "Task to be added cannot be null.";
        this.contactList.add(person);
    }

    /**
     * Removes and returns the person at the specified index.
     *
     * @param personIdx The index of the person to be removed.
     * @return The removed person.
     */
    public Person remove(int personIdx) {
        assert personIdx >= 0 && personIdx < contactList.size() : "Invalid task index.";
        return this.contactList.remove(personIdx);
    }

    /**
     * Returns the person at the specified index.
     *
     * @param personIdx The index of the person to be retrieved.
     * @return The person at the specified index.
     */
    public Person get(int personIdx) {
        assert personIdx >= 0 && personIdx < contactList.size() : "Invalid task index.";
        return this.contactList.get(personIdx);
    }

    /**
     * Returns the number of people in the list.
     *
     * @return The number of people in the list.
     */
    public int size() {
        return this.contactList.size();
    }
}
