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
     *
     * @param name Name of this chatbot.
     */
    public Duke(String name) {
        this.taskList = new TaskList();
        this.ui = new Ui(name);
    }

    /**
     * Start and set up the chatbot.
     *
     * @param filePath Path of the storage file.
     */
    public void startUp(String filePath) {
        this.ui.greet();
        this.taskList.setHardDiskFilePath(filePath);
        this.taskList.loadData();
    }

    /**
     * Starts the service of interacting with the user.
     *
     * @throws DukeException If there are user input errors.
     */
    public void startService() throws DukeException {
        boolean exceptionOccurs = false;

        Commands cmd = this.ui.getNextUserInput();

        Ui.line();

        Task task = null;

        try {
            switch (cmd) {
            case BYE:
                this.ui.bye();
                return;
            case LIST:
                this.taskList.listOutEverything();
                break;
            case MARK:
                int markIndex = this.ui.mark();
                if (this.taskList.isOutOfRange(markIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be marked.");
                }
                this.taskList.mark(markIndex);
                break;
            case UNMARK:
                int unmarkIndex = this.ui.unmark();
                if (taskList.isOutOfRange(unmarkIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be unmarked.");
                }
                this.taskList.unmark(unmarkIndex);
                break;
            case DELETE:
                int deleteIndex = this.ui.delete();
                if (taskList.isOutOfRange(deleteIndex)) {
                    throw new DukeException(Ui.I5 + "☹ OOPS!!! Please specify a valid task number to be deleted.");
                }
                this.taskList.remove(deleteIndex);
                break;
            case TODO:
                task = this.ui.todo();
                break;
            case DEADLINE:
                task = this.ui.deadline();
                break;
            case EVENT:
                task = this.ui.event();
                break;
            case FIND:
                String toFind = this.ui.find();
                this.taskList.find(toFind);
                break;
            default:
                throw new DukeException(Ui.I5 + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            exceptionOccurs = true;
            System.out.println(e.getMessage());
        }

        if (!exceptionOccurs && task != null) {
            this.taskList.add(task);
        }

        Ui.line();
        startService();
    }

    /**
     * This the main method which uses this chatbot.
     *
     * @param args Unused.
     * @throws DukeException If there are any errors occured.
     */
    public static void main(String[] args) throws DukeException {
        Duke bot = new Duke("Kam_BOT");
        bot.startUp("./data/duke.txt");
        bot.startService();
    }
}
