package duke;

/**
 * The Parser class represents a parser that takes inputs and produces an output.
 */
public class Parser {

    /**
     * Processes and responds to the inputs received.
     *
     * @param input Input typed by user.
     * @param tasks List of tasks.
     * @param storage Stores the file and handles file methods.
     * @return Bot response as a string.
     */
    public static String parseInput(String input, TaskList tasks, ContactList contacts,
        Storage storage, Storage contactStorage) {
        try {
            if (input.equals("list")) {
                return ListCommand.execute(input, tasks, storage);
            } else if (input.startsWith("todo ") || (input.startsWith("todo") && input.length() == 4)) {
                return ToDoCommand.execute(input, tasks, storage);
            } else if (input.startsWith("deadline ") || (input.startsWith("deadline") && input.length() == 8)) {
                return DeadlineCommand.execute(input, tasks, storage);
            } else if (input.startsWith("event ") || (input.startsWith("event") && input.length() == 5)) {
                return EventCommand.execute(input, tasks, storage);
            } else if (input.startsWith("mark ") && input.length() > 5 && input.substring(5).matches("\\d+")) {
                return MarkCommand.execute(input, tasks, storage);
            } else if (input.startsWith("unmark ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                return UnmarkCommand.execute(input, tasks, storage);
            } else if (input.startsWith("delete ") && input.length() > 7 && input.substring(7).matches("\\d+")) {
                return DeleteCommand.execute(input, tasks, storage);
            } else if (input.startsWith("find ") || (input.startsWith("find") && input.length() == 4)) {
                return FindCommand.execute(input, tasks, storage);
            } else if (input.startsWith("contacts") || (input.startsWith("contacts") && input.length() == 13)) {
                return ShowContactsCommand.execute(input, contacts, contactStorage);
            } else if (input.startsWith("contact ") || (input.startsWith("contact") && input.length() == 7)) {
                return ContactCommand.execute(input, contacts, contactStorage);
            } else if (input.startsWith("remove ") || (input.startsWith("remove") && input.length() == 6)) {
                return RemoveContactCommand.execute(input, contacts, contactStorage);
            } else {
                throw new InvalidTextException();
            }
        } catch (InvalidTextException e) {
            return Ui.invalidText();
        }
    }
}
