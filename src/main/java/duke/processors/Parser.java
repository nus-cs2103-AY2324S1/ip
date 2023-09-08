package duke.processors;

import duke.exception.DukeDateOutOfRange;
import duke.exception.DukeNoDateException;
import duke.exception.DukeNoDescriptionException;
import duke.exception.DukeUnknownInstruction;
import duke.task.*;

/**
 *Handle the command from the user
 */
public class Parser {
    private final TaskList TASKS;
    private final FileHandler DUKE = new FileHandler();
    private final Ui UI = new Ui();
    private boolean terminate;

    /**
     * Constructor for the Parser class
     * @param TASKS to modify or display the tasks
     */
    public Parser(TaskList TASKS) {
        this.TASKS = TASKS;
        terminate = false;
    }

    private void displayInfo(String msg) throws DukeUnknownInstruction,
            DukeNoDescriptionException, DukeNoDateException,
            DukeDateOutOfRange{
        Task task;

        if (msg.startsWith("todo")) {
            task = new ToDo(msg);
            TASKS.addTasks(task);
        } else if (msg.startsWith("deadline")) {
            task = new Deadline(msg);
            TASKS.addTasks(task);
        } else if (msg.startsWith("event")) {
            task = new Event(msg);
            TASKS.addTasks(task);
        } else {
            throw new DukeUnknownInstruction();
        }
        DUKE.writeFile(task.toString());
        UI.printNumOfTasks(TASKS);
    }

    /**
     * display the description of the task if it is a valid task
     * @param msg the msg to be interpreted
     * @throws DukeUnknownInstruction if the msg is not applicable
     * @throws DukeNoDescriptionException if msg has no description
     * @throws DukeNoDateException if the msg given is not
     *                             date specific for some tasks
     * @throws DukeDateOutOfRange if the date in msg is out of range
     */
    public void readInputs(String msg) throws DukeUnknownInstruction,
            DukeNoDescriptionException, DukeNoDateException,
            DukeDateOutOfRange {
        if (msg.equals("list")) {

            TASKS.listTasks();

        } else if (msg.equals("bye")) {
            this.terminate = true;
        } else {
            boolean isKeyword = msg.matches(".*\\040[0-9]");
            if (isKeyword) {
                String[] part = msg.split("\\s+");
                int ind = Integer.parseInt(part[1]) - 1;
                try {
                    if (ind > TASKS.getCount() || ind < 0) {
                        throw new IndexOutOfBoundsException();
                    }
                    switch (part[0]) {
                    case "mark":
                        TASKS.get(ind).MarkAsDone(DUKE);
                        break;
                    case "unmark":
                        TASKS.get(ind).MarkAsUnDone(DUKE);
                        break;
                    case "delete":
                        TASKS.deleteTask(ind, DUKE);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The given index is not in the available range");
                }
            } else {
                displayInfo(msg);
            }
        }
    }

    /**
     * Get the state of Parser
     * @return boolean value
     */
    public boolean getTerminate() {
        return this.terminate;
    }
}
