package duke;

import java.time.LocalDate;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;


/**
 * Represents a parser that parses user input.
 */
public class Parser {

    /** Represents the list of tasks. */
    private static TaskList tasks = new TaskList();

    /** Represents the type of task. */
    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /** Represents the type of modification to be made. */
    private enum ModifyTask {
        MARK, UNMARK, DELETE
    }

    /**
     * Adds a Task object to the specified list of tasks.
     * @param tsk The type of task to be added.
     * @param input The user input.
     * @throws DukeException If the user input is invalid.
     */
    private static void addTask(TaskType tsk, String input) throws DukeException {
        switch (tsk) {
        case TODO:
            try {
                String todoDescription = input.substring(5);
                TodoTask todoTask = new TodoTask(todoDescription);
                tasks.addTask(todoTask);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            break;
        case DEADLINE:
            try {
                String deadlineDescription = input.substring(9);
                String[] deadlineSplit = deadlineDescription.split(" /by ");
                DeadlineTask deadlineTask =
                        new DeadlineTask(deadlineSplit[0], LocalDate.parse(deadlineSplit[1]));
                tasks.addTask(deadlineTask);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The task of a deadline cannot be empty.");
            } catch (java.time.format.DateTimeParseException e) {
                throw new DukeException("Please enter a valid date in the format yyyy-mm-dd.");
            }
            break;
        case EVENT:
            try {
                String eventDescription = input.substring(6);
                String[] eventSplit = eventDescription.split(" /from ");
                String[] eventSplit2 = eventSplit[1].split(" /to ");
                EventTask eventTask = new EventTask(eventSplit[0], eventSplit2[0], eventSplit2[1]);
                tasks.addTask(eventTask);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The date of an event cannot be empty.");
            }
            break;

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Modifies a Task object in the specified list of tasks.
     * @param mod The type of modification to be made.
     * @param input The user input.
     * @throws DukeException If the user input is invalid.
     */
    private static void modifyTask(ModifyTask mod, String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        switch (mod) {
        case MARK:
            try {
                int taskNumber = Integer.parseInt(inputSplit[1]);
                tasks.markTaskAsDone(taskNumber);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a task number.");
            }
            break;
        case UNMARK:
            try {
                int taskNumber2 = Integer.parseInt(inputSplit[1]);
                tasks.markTaskAsUndone(taskNumber2);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a task number.");
            }
            break;
        case DELETE:
            try {
                int taskNumber3 = Integer.parseInt(inputSplit[1]);
                tasks.deleteTask(taskNumber3);
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please enter a task number.");
            }
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the user input and calls the appropriate method.
     * @param input The user input.
     */
    public static void parseCommands(String input) {
        if (input.equals("list")) {
            tasks.listAllTasks();
        } else {
            String[] inputSplit = input.split(" ");
            try {
                switch (inputSplit[0]) {
                case ("todo"):
                    addTask(TaskType.TODO, input);
                    break;
                case ("deadline"):
                    addTask(TaskType.DEADLINE, input);
                    break;
                case ("event"):
                    addTask(TaskType.EVENT, input);
                    break;
                case ("mark"):
                    modifyTask(ModifyTask.MARK, input);
                    break;
                case ("unmark"):
                    modifyTask(ModifyTask.UNMARK, input);
                    break;
                case ("delete"):
                    modifyTask(ModifyTask.DELETE, input);
                    break;
                case("find"):
                    tasks.findTasks(inputSplit[1]);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                Ui.printError(e);
            }
        }
    }
}
