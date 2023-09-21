package Duke;

/**
 * Class to parse user input and execute corresponding commands.
 */
public class Parser {
    private boolean isExit;

    /**
     * Default constructor for the Parser class.
     * Initializes the isExit flag to false.
     */
    public Parser() {
        isExit = false;
    }

    /**
     * Parses the user input and performs the corresponding actions.
     *
     * @param str      The user input to be parsed.
     * @param list     The TaskList instance to manage tasks.
     * @param storage  The Storage instance to handle task storage.
     * @param ui       The Ui instance to manage user interface interactions.
     */
    public void parse(String str, TaskList list, Storage storage, Ui ui) {
        try {
            if (str.equals("bye")) {
                isExit = true;
            } else if (str.equals("list")) {
                list.list(ui);
            } else if (str.contains("unmark")) {
                list.mark(false, str, ui, storage);
            } else if (str.contains("mark")) {
                list.mark(true, str, ui, storage);
            } else if (str.startsWith("todo ")) {
                list.todo(str, ui, storage);
            } else if (str.startsWith("deadline ")) {
                list.deadline(str, ui, storage);
            } else if (str.startsWith("event ")) {
                list.event(str, ui, storage);
            } else if (str.startsWith("delete ")) {
                list.delete(str, ui, storage);
            } else if (str.startsWith("find ")) {
                list.find(str, ui);
            } else if (str.startsWith("sort deadline")) {
                list.sortDeadline(ui);
            } else if (str.startsWith("sort event")) {
                list.sortEvent(ui);
            } else {
                ui.handleError(new DukeException("\n____________________________________________________________\n" +
                        "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________"));
            }
        } catch (DukeException e) {
            ui.handleError(e);
        }
    }

    /**
     * Checks if the program should exit.
     *
     * @return true if the program should exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
