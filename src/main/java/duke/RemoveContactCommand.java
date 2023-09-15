package duke;

/**
 * The RemoveContactCommand class represents the command to delete a contact.
 */
public class RemoveContactCommand {

    /**
     * Runs the RemoveContactCommand.
     *
     * @param input Input typed by user.
     * @param contacts List of tasks.
     * @param storage Stores the file and handles file methods.
     * @return Bot response when deleting a task.
     */
    public static String execute(String input, ContactList contacts, Storage storage) {
        try {
            int number = Integer.parseInt(input.substring(7));
            boolean isInvalidTask = number > contacts.size() || number <= 0;
            if (isInvalidTask) {
                throw new InvalidTaskException();
            }
            Contact contact = contacts.delete(number - 1);
            storage.rewrite(contacts.showContacts());
            return Ui.removeContact(contact, contacts.size());
        } catch (InvalidTaskException e) {
            return Ui.invalidTask();
        }
    }
}
