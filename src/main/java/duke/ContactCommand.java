package duke;

/**
 * The ContactCommand class represents the command to create a contact.
 */
public class ContactCommand {

    /**
     * Runs the ContactCommand.
     *
     * @param input Input typed by user.
     * @param contacts List of tasks.
     * @param storage Stores the file and handles file methods.
     * @return Bot response when marking a task.
     */
    public static String execute(String input, ContactList contacts, Storage storage) {
        try {
            boolean isEmptyDescription = input.length() <= 8 || input.substring(8).isBlank();
            if (isEmptyDescription) {
                throw new EmptyDescriptionException();
            }
            Contact contact = new Contact(input.substring(8));
            contacts.add(contact);
            storage.rewrite(contacts.showContacts());
            return Ui.addContact(contact.toString(), contacts.size());
        } catch (EmptyDescriptionException e) {
            return Ui.emptyDesc("contact");
        }
    }
}
