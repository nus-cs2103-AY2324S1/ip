package sidtacphi.contact;

import java.util.ArrayList;

import sidtacphi.exception.SidException;
import sidtacphi.exception.SidInvalidFormatException;
import sidtacphi.exception.SidInvalidIndexException;

/**
 * ContactList is the main class for the contact list stored by the Sidtacphi bot.
 */
public class ContactList extends ArrayList<Contact> {
    /**
     * Constructs a ContactList object.
     */
    public ContactList() {
        super();
    }

    /**
     * Parses a string into an integer and returns a default value on failure.
     *
     * @param text String value to be parsed to integer
     * @param defaultVal default value to be returned if text cannot be parsed
     * @return an integer value represented by the string
     */
    private static int tryParseInt(String text, int defaultVal) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    /**
     * Adds the input to the task_list kept track of by the bot.
     *
     * @param taskType type of task to add
     * @param input input to add to the task_list kept by the bot
     */
    public String addContact(String input) throws SidException {
        if (input.length() < 11) {
            throw new SidInvalidFormatException("Please input a name for your Contact"
                    + " along with a description of the contact.");
        } else if (input.charAt(10) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }

        String[] inputArgs = input.substring(11).split("\\s*/desc\\s*");
        if (inputArgs.length == 2) {
            this.add(new Contact(inputArgs[0], inputArgs[1]));
        } else if (inputArgs.length == 1) {
            throw new SidInvalidFormatException("Please add \"/desc <description>\""
                    + "to the name of the Contact.");
        } else {
            throw new SidInvalidFormatException("Please do not write in more than 1 description.");
        }

        String result = "Sidtacphi: I have added \"" + this.get(this.size() - 1) + "\".";

        if (this.size() == 1) {
            result += "\nSidtacphi: You now have 1 contact in your list.";
        } else {
            result += "\nSidtacphi: You now have " + this.size() + " contacts in your list.";
        }

        return result;
    }

    /**
     * Prints the contact list.
     */
    public String showContactList() {
        if (this.size() == 0) {
            return "Sidtacphi: There are no contacts in your list.";
        }

        String result = "Sidtacphi: These are the contacts in your list.\n";
        for (int i = 0; i < this.size(); i++) {
            result += "" + (i + 1) + ". " + this.get(i).toString() + "\n";
        }

        return result;
    }

    /**
     * Deletes contact based on the ID in input.
     */
    public String deleteContact(String input) throws SidException {
        if (input.length() < 14) {
            throw new SidInvalidFormatException("Please input the contact ID number to delete.");
        } else if (input.charAt(13) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }

        int contactId = tryParseInt(input.substring(14), -1);
        if (contactId > this.size() || contactId < 1) {
            throw new SidInvalidIndexException("Invalid contact ID.");
        }

        Contact contact = this.get(contactId - 1);
        this.remove(contactId - 1);
        return "Sidtacphi: Removed \"" + contact + "\".\n"
                + "Sidtacphi: You now have " + this.size() + " contacts in your list.";
    }


    /**
     * Checks if obj is equal to to this ContactList object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ContactList)) {
            return false;
        }
        ContactList contactList = (ContactList) obj;

        if (contactList.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(contactList.get(i))) {
                return false;
            }
        }

        return true;
    }
}
