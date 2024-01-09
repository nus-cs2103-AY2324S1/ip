package duke.processors;

import duke.exception.DukeException;
import duke.exception.DukeUnknownInstruction;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Handle the command from the user.
 */
public class Parser {
    private final TaskList TASKS;
    private final FileHandler DUKE = new FileHandler();
    private final Ui UI = new Ui();
    private boolean isTerminated;

    /**
     * Constructor for the Parser class.
     *
     * @param TASKS to modify or display the tasks.
     */
    public Parser(TaskList TASKS) {
        this.TASKS = TASKS;
        this.isTerminated = false;
    }

    private String displayInfo(String msg) throws DukeException {
        Task task;

        if (msg.startsWith("todo")) {
            task = new ToDo(msg);
        } else if (msg.startsWith("deadline")) {
            task = new Deadline(msg);
        } else if (msg.startsWith("event")) {
            task = new Event(msg);
        } else {
            throw new DukeUnknownInstruction();
        }

        TASKS.addTask(task);
        DUKE.writeFile(task.toString());
        return "Got it. I've added this task: \n " +
                "   " + task.toString() + "\n" + UI.NumOfTasks(TASKS);
    }

    /**
     * display the description of the task if it is a valid task.
     *
     * @param msg the msg to be interpreted.
     * @throws DukeException if the msg is not given correctly.
     */
    public String readInputs(String msg) throws DukeException {
        String output = "";
        if (msg.equals("list")) {
            output = TASKS.listTasks();
        } else if (msg.equals("bye")) {
            this.isTerminated = true;
            output = UI.onExit();
        } else {
            boolean isKeyword = msg.matches(".*\\040[0-9]");
            String[] part = msg.split("\\s+");
            if (isKeyword) {
                int ind = Integer.parseInt(part[1]) - 1;
                if (ind >= TASKS.getCount() || ind < 0) {
                    throw new DukeException(
                            "The given index is not in the available range");
                }
                switch (part[0]) {
                case "mark":
                    output = TASKS.get(ind).MarkAsDone(DUKE);
                    break;
                case "unmark":
                    output = TASKS.get(ind).MarkAsUnDone(DUKE);
                    break;
                case "delete":
                    output = TASKS.deleteTask(ind, DUKE);
                    break;
                default:
                    break;
                }
            } else if (msg.startsWith("find")) {
                output = TASKS.findTasks(part[1]);
            } else {
                return displayInfo(msg);
            }
        }
        assert !output.isEmpty();
        return output;
    }

    /**
     * Get the state of Parser.
     *
     * @return boolean value.
     */
    public boolean getTerminate() {
        return this.isTerminated;
    }
}
