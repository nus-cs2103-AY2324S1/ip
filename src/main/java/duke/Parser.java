package duke;

import java.time.LocalDate;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.task.TodoTask;
import duke.ui.Ui;


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
     * 
     * @param tsk The type of task to be added.
     * @param input The user input.
     * @throws DukeException If the user input is invalid.
     */
    private static String addTask(TaskType tsk, String input) throws DukeException{
        switch (tsk) {
        case TODO:
            try {
                String todoDescription = input.substring(5);
                TodoTask todoTask = new TodoTask(todoDescription);
                return tasks.addTask(todoTask);
            } catch(StringIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("The description of a todo cannot be empty."));
            }
        case DEADLINE:
            try {
                String deadlineDescription = input.substring(9);
                String[] deadlineSplit = deadlineDescription.split(" /by ");
                DeadlineTask deadlineTask = 
                        new DeadlineTask(deadlineSplit[0], LocalDate.parse(deadlineSplit[1]));
                return tasks.addTask(deadlineTask);
            } catch(StringIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("The description of a deadline cannot be empty."));
            } catch(ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("The task of a deadline cannot be empty."));
            } catch (java.time.format.DateTimeParseException e) {
                return Ui.returnErrorString( new DukeException("Please enter a valid date in the format yyyy-mm-dd."));
            }
        case EVENT:
            try {
                String eventDescription = input.substring(6);
                String[] eventSplit = eventDescription.split(" /from ");
                String[] eventSplit2 = eventSplit[1].split(" /to ");
                EventTask eventTask = new EventTask(eventSplit[0], eventSplit2[0], eventSplit2[1]);
                return tasks.addTask(eventTask);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("The description of an event cannot be empty."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("The date of an event cannot be empty."));
            }

        default:
            return Ui.returnErrorString( new DukeException("I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Modifies a Task object in the specified list of tasks.
     * 
     * @param mod The type of modification to be made.
     * @param input The user input.
     * @throws DukeException If the user input is invalid.
     */
    private static String modifyTask(ModifyTask mod, String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        switch (mod) {
        case MARK:
            try {    
                int taskNumber = Integer.parseInt(inputSplit[1]);
                return tasks.markTaskAsDone(taskNumber);
            } catch (NumberFormatException e) {
                return Ui.returnErrorString( new DukeException("Please enter a valid task number."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("Please enter a task number."));
            }
        case UNMARK:
            try {
                int taskNumber2 = Integer.parseInt(inputSplit[1]);
                return tasks.markTaskAsUndone(taskNumber2);
            } catch (NumberFormatException e) {
                return Ui.returnErrorString( new DukeException("Please enter a valid task number."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("Please enter a task number."));
            }
        case DELETE:
            try {
                int taskNumber3 = Integer.parseInt(inputSplit[1]);
                return tasks.deleteTask(taskNumber3);
            } catch (NumberFormatException e) {
                return Ui.returnErrorString( new DukeException("Please enter a valid task number."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.returnErrorString( new DukeException("Please enter a task number."));
            } 
        default:
            return Ui.returnErrorString( new DukeException("I'm sorry, but I don't know what that means :-("));
        }
    }

    /**
     * Parses the user input and calls the appropriate method.
     * 
     * @param input The user input.
     */
    public static String parseCommands(String input) {
        if (input.equals("list")) {
            return tasks.listAllTasks();
        }  else {
            String[] inputSplit = input.split(" ");
            try {
                switch (inputSplit[0]) {
                case ("todo"):
                    return addTask(TaskType.TODO, input);
                case ("deadline"):
                    return addTask(TaskType.DEADLINE, input);
                case ("event"):
                    return addTask(TaskType.EVENT, input);
                case ("mark"):
                    return modifyTask(ModifyTask.MARK, input);
                case ("unmark"):
                    return modifyTask(ModifyTask.UNMARK, input);
                case ("delete"):
                    return modifyTask(ModifyTask.DELETE, input);
                case("find"):
                    return tasks.findTasks(inputSplit[1]);
                default:
                    return Ui.returnErrorString(new DukeException("I'm sorry, but I don't know what that means :-("));
                }
            } 
            catch (DukeException e) {
                return Ui.returnErrorString(e);
            } 
        }
    }
}
