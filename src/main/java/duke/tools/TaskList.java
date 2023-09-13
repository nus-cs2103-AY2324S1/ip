package duke.tools;

import duke.Duke;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    private static final String line = "___________________________________________";
    private static ArrayList<Task> taskList;
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to return the taskList.
     *
     * @return taskList the task list.
     */
    public ArrayList<Task> getTaskArray() {
        return taskList;
    }

    /**
     * Method to handle TODOs.
     *
     * @param descr the task description
     */
    public String handleTodo(String descr) {
        String res = null;
        try {
            ToDo newTodo = new ToDo(descr);
            newTodo.checkValidity();
            taskList.add(newTodo);
            res = "Okie! I've added this ToDo to your task list!\n";
            res += newTodo.writtenFormat() + "\n";
            res += "Now you've got " + taskList.size() + " tasks in your list.\n";
        } catch (DukeException e) {
            res = e.getMessage();
        }
        return res;
    }

    /**
     * Method to handle Events.
     *
     * @param descr the task description
     */
    public String handleEvent(String descr) {
        String res = null;
        try {
            Event newEvent = new Event(descr);
            newEvent.checkValidity();
            taskList.add(newEvent);
            res = "Okie! I've added this Event to your task list!\n";
            res += newEvent.writtenFormat() + "\n";
            res += "Now you've got " + taskList.size() + " tasks in your list.\n";
        } catch (DukeException e) {
            res = e.getMessage();
        }
        return res;
    }

    /**
     * Method to handle Deadlines.
     *
     * @param descr the task description
     */
    public String handleDeadline(String descr) {
        String res;
        try {
            Deadline newDeadline = new Deadline(descr);
            newDeadline.checkValidity();
            taskList.add(newDeadline);
            res = "Okie! I've added this Deadline to your task list!\n";
            res += newDeadline.writtenFormat() + "\n";
            res += "Now you've got " + taskList.size() + "  tasks in your list.\n";
        } catch (DukeException e) {
            res = e.getMessage();
        }
        return res;
    }

    /**
     * Method to mark task.
     *
     * @param task the task being marked
     * @throws DukeException if input is invalid.
     */
    public String mark(String task) throws DukeException {
        String res = null;
        String[] parts = task.split(" ");
        if (parts.length < 2) {
            throw new DukeException("Which task do you want to mark as done?");
        }

        String index = parts[1];
        int taskIndex = 0;

        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException("Please enter a valid index.");
        }

        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            res = "Please enter a valid index.";
        }

        Task taskChanged = taskList.get(taskIndex);
        String action = parts[0];

        try {
            if (action.equals("mark")) {
                taskChanged.markDone();
                res = "Nice! I've marked this task as done:\n";
            } else if (action.equals("unmark")) {
                taskChanged.markUndone();
                res = "Nice! I've marked this task as undone:\n";
            }
            res += taskChanged.toString() + "\n";
        } catch (DukeException e) {
            res = e.getMessage();
        }

        return res;
    }

    /**
     * Method deletes task from taskList.
     *
     * @param task The instructions containing index of task to be deleted.
     * @throws DukeException if input is invalid.
     */
    public String delete(String task) throws DukeException {
        String res;
        String[] segments = task.split(" ");
        if (segments.length < 2) {
            throw new DukeException("Which task do you want to delete?");
        }
        String index = segments[1];
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(index) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid index."); //e.g. delete hi
        }
        if (taskIndex > taskList.size() || taskIndex < 0) {
            throw new DukeException("Please enter a valid index.");
        }
        Task deletedTask = taskList.get(Integer.parseInt(index) - 1);
        taskList.remove(deletedTask);

        res = "Deleted the following task: \n";
        res += deletedTask.toString() + "\n";
        return res;
    }

    /**
     * Method to find relevant tasks.
     *
     * @param task the input that specifies what to find.
     * @throws DukeException if the input is invalid.
     */
    public String find(String task) throws DukeException {
        String[] parts = task.split("find ");
        if (parts.length < 1) {
            throw new DukeException("What do you want to find?");
        }

        String relevantWord = parts[1].trim();
        ArrayList<Task> resultList = new ArrayList<>();
        String output;

        for (Task existingTask : taskList) {
            if (existingTask.toString().contains(relevantWord)) {
                resultList.add(existingTask);
            }
        }
        try {
            TaskList resultTaskList = new TaskList(resultList);
            output = resultTaskList.printList();
        } catch (DukeException e) {
            output = "There are no relevant tasks";
        }
        return output;
    }

    /**
     * Method to print taskList.
     *
     * @return The list in String form
     * @throws DukeException if the list is empty
     */
    public String printList() throws DukeException {
        String res = "Here are your tasks:\n";
        if (taskList.isEmpty()) {
            throw new DukeException("You have no tasks in your list!");
        } else {
            for (int i = 1; i <= taskList.size(); i++) {
                res += i + "." + taskList.get(i - 1) + "\n";
            }
            return res;
        }
    }

    /**
     * Method to handle inputs.
     *
     * Entry point linking terminal to system.
     *
     * @throws DukeException if input is invalid or double marking/ unmarking
     */
    public String handleInput(String task) {
        KeywordEnum keywordEnum = KeywordEnum.assign(task);

            switch(keywordEnum) {
            case LIST:
                try {
                    return printList();
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case BYE:
                    return this.ui.printOutro();
            case TODO:
                return handleTodo(task);
            case DEADLINE:
                return handleDeadline(task);
            case EVENT:
                return handleEvent(task);
            case DELETE:
                try {
                    return delete(task);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            case MARK:
            case UNMARK:
                try {
                    return mark(task);
                } catch (IndexOutOfBoundsException e) {
                    return e.getMessage();
                } catch (DukeException dukeException) {
                    return dukeException.getMessage();
                }
            case FIND:
                try {
                    return find(task);
                } catch (DukeException e) {
                    return e.getMessage();
                }
            default:
                return "This is not a valid task.";
        }
    }
}
