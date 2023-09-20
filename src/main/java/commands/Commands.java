package commands;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;
import main.Duke;
import storage.Storage;
import actions.TaskList;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class Commands {
    private Duke chad;
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Commands(Duke chad, ArrayList<Task> taskArrayList){
        this.chad = chad;
        this.ui = new Ui();
        this.tasklist = new TaskList(taskArrayList);
        this.storage = new Storage();
    }

    /**
     * Actions for when use input "bye"
     *
     * @return string output to be shown
     */
    public String byeCommand() {
        return ui.displayChadBye();
    }

    /**
     * Actions for when use input "list"
     *
     * @return string output to be shown
     */
    public String listCommand(){
        return ui.displaychadListTask(chad.taskArrayList);
    }

    /**
     * Action for when use input "mark"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String markCommand(String[] inputArray) {
        try {
            Integer index = Integer.valueOf(inputArray[1]);
            tasklist.chadMarkTask(index);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadMarkTaskOutput(chad.taskArrayList.get(index - 1).getName(),
                    chad.taskArrayList.get(index - 1).getMark());


            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                return "The task index is invalid! Try again!";
        }
    }

    /**
     * Action for when use input "unmark"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String unmarkCommand(String[] inputArray) {
        try {
            Integer index = Integer.valueOf(inputArray[1]);
            tasklist.chadUnmarkTask(index);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadUnmarkTaskOutput(chad.taskArrayList.get(index - 1).getName(),
                    chad.taskArrayList.get(index - 1).getMark());

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "The task index is invalid! Try again!";
        }
    }

    /**
     * Action for when use input "find"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String findCommand(String[] inputArray) {
        String name = inputArray[1];
        return tasklist.displayChadFindTask(name);
    }

    /**
     * Action for when use input "todo"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String todoCommand(String[] inputArray) {
        try {
            if (inputArray.length == 1 || inputArray[1].isEmpty()) {
                throw new Duke.DukeException("Hey! You forgot what you needed to do?");
            }
            Todo newTodo = new Todo(inputArray[1]);
            tasklist.chadAddList(newTodo);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadAddListOutput(newTodo.toString());

        } catch (Duke.DukeException e) {
            return  e.getMessage() + "\n";
        }
    }

    /**
     * Action for when use input "deadline"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String deadlineCommand(String[] inputArray) {
        try {
            if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                throw new Duke.DukeException("Hey! You forgot what you needed to do?");
            }

            String[] details = inputArray[1].split(" /by ", 2);

            if (details.length < 2) {
                throw new Duke.DukeException("Umm you forgot the deadline! Remember to use /by before the deadline!");
            }
            Deadline newDeadline = new Deadline(details[0], details[1]);
            tasklist.chadAddList(newDeadline);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadAddListOutput(newDeadline.toString());

        } catch (Duke.DukeException e) {
            return e.getMessage() + "\n";
        } catch (DateTimeParseException e) {
            return "Make sure the date format is: d MMM yyyy";
        }
    }

    /**
     * Action for when use input "event"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String eventCommand(String[] inputArray) {
        try {
            if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                throw new Duke.DukeException("Hey! You forgot what you needed to do?");
            }
            String[] details = inputArray[1].split(" /from ", 2);
            if (details.length < 2) {
                throw new Duke.DukeException("Hey you are missing the start date! Remember to use /from before the deadline!");
            }
            String[] timings = details[1].split(" /to ", 0);
            if (timings.length < 2) {
                throw new Duke.DukeException("The end date is missing! Do better! Use /to!");
            }
            Event newEvent = new Event(details[0], timings[0], timings[1]);
            tasklist.chadAddList(newEvent);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadAddListOutput(newEvent.toString());

        } catch (Duke.DukeException e) {
            return e.getMessage() + "\n";
        } catch (DateTimeParseException e) {
            return "Make sure the date format is: d MMM yyyy";
        }
    }

    /**
     * Action for when use input "delete"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String deleteCommand(String[] inputArray) {
        try {
            Integer index = Integer.valueOf(inputArray[1]);
            String name = tasklist.chadRemoveList(index);
            storage.writeFile(chad.taskArrayList);
            return ui.displayChadRemoveOutput(name, chad.taskArrayList.size());

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "The task index is invalid! Try again!";
        }

    }

    /**
     * Action for when use input "edit"
     *
     * @param inputArray input from user
     * @return output string after running the actions
     */
    public String editCommand(String[] inputArray) {
        try {
            String[] details = inputArray[1].split(" ", 4);
            String s = tasklist.updateTask(details);
            storage.writeFile(chad.taskArrayList);
            return s;

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "Check your command! The command is 'edit (index of task) (type of task: T D E) (command: /name /by /from /to) (update)'";
        }
    }

}
