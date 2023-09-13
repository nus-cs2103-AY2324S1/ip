package geraldbot.util;

import java.util.ArrayList;

import geraldbot.person.Person;

/**
 * Represents a list of people.
 */
public class ContactList extends ListManager<Person> {
    /**
     * Constructs a ContactList with the given list of people.
     *
     * @param contactList The initial list of people.
     */
    public ContactList(ArrayList<Person> contactList) {
        super(contactList);
    }
}
