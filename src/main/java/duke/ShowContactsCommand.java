package duke;

/**
 * The ContactsCommand class represents the command to show the list of contacts.
 */
public class ShowContactsCommand {

    /**
     * Runs the Command.
     *
     * @param input Input typed by user.
     * @param contacts List of tasks.
     * @param storage Stores the file and handles file methods.
     * @return Bot response when asked to show the list of tasks.
     */
    public static String execute(String input, ContactList contacts, Storage storage) {
        return Ui.listOfContacts(contacts.showContacts());
    }
}
