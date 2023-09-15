package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ContactList class represents a list of contacts.
 */
public class ContactList {
    /** List of contacts. **/
    private ArrayList<Contact> contacts;
    /** File storing information to create the list of contacts. **/
    private File startFile;

    /**
     * Instantiates an instance of ContactList.
     *
     * @param startFile Information of the file saved.
     */
    public ContactList(File startFile) {
        this.startFile = startFile;
        assert this.startFile.exists();
        this.contacts = new ArrayList<Contact>();
        try {
            Scanner fileScan = new Scanner(startFile);
            while (fileScan.hasNext()) {
                String contactString = fileScan.nextLine();
                Contact contact = new Contact(contactString);
                contacts.add(contact);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Returns list of contacts as a string.
     *
     * @return List of contacts to be printed.
     */
    public String showContacts() {
        StringBuilder listOfContacts = new StringBuilder();
        for (int i = 0; i < contacts.size() - 1; i++) {
            Contact contact = contacts.get(i);
            listOfContacts.append("     ").append(i + 1).append(".").append(contact.toString()).append("\r\n");
        }
        if (contacts.size() != 0) {
            Contact contact = contacts.get(contacts.size() - 1);
            listOfContacts.append("     ").append(contacts.size()).append(".").append(contact.toString());
        }
        return listOfContacts.toString();
    }

    /**
     * Adds a contact to the list of contacts.
     *
     * @param contact Contact to be added.
     */
    public void add(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Returns the contact removed from a list of contacts.
     * Removes a contact from the list of contacts.
     *
     * @param contactNumber Number of the contact to be removed.
     * @return Contact removed.
     */
    public Contact delete(int contactNumber) {
        return contacts.remove(contactNumber);
    }

    /**
     * Returns the contact specified.
     *
     * @param contactNumber Number of the contact to be removed.
     * @return Contact obtained.
     */
    public Contact get(int contactNumber) {
        return contacts.get(contactNumber);
    }

    /**
     * Returns the number of contacts in the list of contacts.
     *
     * @return Size of contacts list.
     */
    public int size() {
        return contacts.size();
    }
}
