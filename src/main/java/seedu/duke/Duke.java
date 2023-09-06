package seedu.duke;

/**
 * Represents my version of a chatbot.
 * It is mainly used for recording tasks like
 * todos, deadlines and events.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class Duke {
    private TaskList taskList;
    private Ui ui;

    /**
     * The main constructor for this chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui("Kam_BOT");
        this.startUp("./data/duke.txt");
    }

    public static String greet() {
        return Ui.greet();
    }

    /**
     * Start and set up the chatbot.
     *
     * @param filePath Path of the storage file.
     */
    public void startUp(String filePath) {
        this.taskList.setHardDiskFilePath(filePath);
        this.taskList.loadData();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        boolean exceptionOccurs = false;

        String response = "";

        Commands cmd = this.ui.parseUserInput(input);

        Task task = null;

        try {
            switch (cmd) {
            case BYE:
                response = this.ui.bye();
                break;
            case LIST:
                response = this.taskList.listOutEverything();
                break;
            case MARK:
                int markIndex = this.ui.mark(input);
                if (this.taskList.isOutOfRange(markIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }
                response = this.taskList.mark(markIndex);
                break;
            case UNMARK:
                int unmarkIndex = this.ui.unmark(input);
                if (taskList.isOutOfRange(unmarkIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }
                response = this.taskList.unmark(unmarkIndex);
                break;
            case DELETE:
                int deleteIndex = this.ui.delete(input);
                if (taskList.isOutOfRange(deleteIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }
                response = this.taskList.remove(deleteIndex);
                break;
            case TODO:
                task = this.ui.todo(input);
                break;
            case DEADLINE:
                task = this.ui.deadline(input);
                break;
            case EVENT:
                task = this.ui.event(input);
                break;
            case FIND:
                String toFind = this.ui.find(input);
                response = this.taskList.find(toFind);
                break;
            default:
                throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            exceptionOccurs = true;
            response = (e.getMessage());
        }

        if (!exceptionOccurs && task != null) {
            response = this.taskList.add(task);
        }

        return response;
    }
}
