package sidtacphi.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import sidtacphi.exception.SidException;
import sidtacphi.exception.SidInvalidFormatException;
import sidtacphi.exception.SidInvalidIndexException;

/**
 * TaskList is the main class for the task list stored by the Sidtacphi bot.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        super();
    }

    /**
     * Parses a string into an integer and returns a default value on failure.
     *
     * @param text String value to be parsed to integer
     * @param defaultVal default value to be returned if text cannot be parsed
     * @return an integer value represented by the string
     */
    private static int tryParseInt(String text, int defaultVal) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    /**
     * Adds the input to the task_list kept track of by the bot.
     *
     * @param taskType type of task to add
     * @param input input to add to the task_list kept by the bot
     */
    public String addTask(TaskType taskType, String input) throws SidException {
        switch (taskType) {
        case TODO:
            addTodo(input);
            break;
        case EVENT:
            addEvent(input);
            break;
        case DEADLINE:

            break;
        default:
            // The code should never reach here.
            throw new SidException(
                "Something went wrong when creating a task and an invalid task type has been specified.");
        }

        String result = "Sidtacphi: I have added \"" + this.get(this.size() - 1) + "\".";

        if (this.size() == 1) {
            result += "\nSidtacphi: You now have 1 task in your list.";
        } else {
            result += "\nSidtacphi: You now have " + this.size() + " tasks in your list.";
        }

        return result;
    }

    /**
     * Adds a Todo task.
     *
     * @param input full user input
     * @throws SidException
     */
    public void addTodo(String input) throws SidException {
        if (input.length() < 5) {
            throw new SidInvalidFormatException("Please input a name for your Todo task.");
        } else if (input.charAt(4) == ' ') {
            this.add(new Todo(input.substring(5)));
        } else {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }
    }

    /**
     * Adds a Event task.
     *
     * @param input full user input
     * @throws SidException
     */
    public void addEvent(String input) throws SidException {
        if (input.length() < 6) {
            throw new SidInvalidFormatException("Please input a name for your Event"
                    + "task, along with a start and end date.");
        } else if (input.charAt(5) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }

        String[] inputArgs = input.substring(6).split("\\s*/from\\s*");
        if (inputArgs.length != 2) {
            throw new SidInvalidFormatException("Please put in the starting and ending date "
                    + "using \"/from <date>\" followed by \"/to <date>\" for Event tasks.");
        }

        String[] startAndEnd = inputArgs[1].split("\\s*/to\\s*");
        if (startAndEnd.length == 2) {
            LocalDate start;
            LocalDate end;
            try {
                start = LocalDate.parse(startAndEnd[0]);
                end = LocalDate.parse(startAndEnd[1]);
            } catch (DateTimeParseException e) {
                throw new SidInvalidFormatException("Please put in your dates in YYYY-MM-DD format.");
            }

            if (end.isBefore(start)) {
                throw new SidInvalidFormatException(
                    "Please make sure your starting date is before your ending date.");
            }
    
            this.add(new Event(inputArgs[0], start, end));
        } else {
            throw new SidInvalidFormatException("Please put in the starting and ending date "
                    + "using \"/from <date>\" followed by \"/to <date>\" for Event tasks.");
        }
    }

    /**
     * Adds a Deadline task.
     *
     * @param input full user input
     * @throws SidException
     */
    public void addDeadLine(String input) throws SidException {
        if (input.length() < 9) {
            throw new SidInvalidFormatException("Please input a name for your Event"
                    + "task, along with a start and end date.");
        } else if (input.charAt(8) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }

        String[] inputArgs = input.substring(9).split("\\s*/by\\s*");
        if (inputArgs.length == 2) {
            LocalDate deadline;
            try {
                deadline = LocalDate.parse(inputArgs[1]);
            } catch (DateTimeParseException e) {
                throw new SidInvalidFormatException("Please put in your dates in YYYY-MM-DD format.");
            }
            this.add(new Deadline(inputArgs[0], deadline));
        } else if (inputArgs.length == 1) {
            throw new SidInvalidFormatException("Please write in the deadline"
                    + "using \"/by <date>\" for Deadline tasks.");
        } else {
            throw new SidInvalidFormatException("Please do not write in more than 1 deadline.");
        }
    }

    /**
     * Marks/Unmarks the task given.
     *
     * @param isToMarkAsCompleted to mark the task as done when true, and to unmark when false
     * @param input
     */
    public String markTaskAs(boolean isToMarkAsCompleted, String input) throws SidException {
        if (!isToMarkAsCompleted) {
            if (input.length() < 7) {
                throw new SidInvalidFormatException("Please input the task ID number to unmark.");
            } else if (input.charAt(6) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            }

            int taskId = tryParseInt(input.substring(7), -1);
            if (taskId > this.size() || taskId < 1) {
                throw new SidInvalidIndexException("Invalid task ID.");
            }

            Task task = this.get(taskId - 1);
            if (!task.isCompleted()) {
                throw new SidInvalidIndexException("\"" + task + "\" is already unmarked!");
            } else {
                task.unmark();
                return "Sidtacphi: Unmarked \"" + task + "\".";
            }
        } else {
            if (input.length() < 5) {
                throw new SidInvalidFormatException("Please input the task ID number to mark.");
            } else if (input.charAt(4) != ' ') {
                throw new SidException("\"" + input + "\" is not a valid command.");
            }

            int taskId = tryParseInt(input.substring(5), -1);
            if (taskId > this.size() || taskId < 1) {
                throw new SidInvalidIndexException("Invalid task ID.");
            }

            Task task = this.get(taskId - 1);
            if (task.isCompleted()) {
                throw new SidInvalidIndexException("\"" + task + "\" is already marked!");
            } else {
                task.mark();
                return "Sidtacphi: Marked \"" + task + "\".";
            }
        }
    }

    /**
     * Prints the task list.
     */
    public String showTaskList() {
        if (this.size() == 0) {
            return "Sidtacphi: There are no tasks in your list.";
        }

        String result = "Sidtacphi: These are the tasks in your list.\n";
        for (int i = 0; i < this.size(); i++) {
            result += "" + (i + 1) + ". " + this.get(i) + "\n";
        }

        return result;
    }

    /**
     * Finds tasks that have input in their names.
     *
     * @param input
     */
    public String deleteTask(String input) throws SidException {
        if (input.length() < 7) {
            throw new SidInvalidFormatException("Please input the task ID number to delete.");
        } else if (input.charAt(6) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }

        int taskId = tryParseInt(input.substring(7), -1);
        if (taskId > this.size() || taskId < 1) {
            throw new SidInvalidIndexException("Invalid task ID.");
        }

        Task task = this.get(taskId - 1);
        this.remove(taskId - 1);
        return "Sidtacphi: Removed \"" + task + "\".\n"
                + "Sidtacphi: You now have " + this.size() + " tasks in your list.";
    }

    /**
     * Checks if obj is equal to to the TaskList object.
     */
    public String findTask(String input) throws SidException {
        if (input.length() < 5) {
            throw new SidInvalidFormatException("Please input the task ID number to delete.");
        } else if (input.charAt(4) != ' ') {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }

        String name = input.substring(5);
        String result = "Sidtacphi: These are the tasks in your list.\n";
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().contains(name)) {
                result += "" + (i + 1) + ". " + this.get(i) + "\n";
            }
        }

        return result;
    }

    /**
     * Checks if obj is equal to to the TaskList object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TaskList)) {
            System.out.println("hi1");
            return false;
        }
        TaskList taskList = (TaskList) obj;

        if (taskList.size() != this.size()) {
            System.out.println("hi2");
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            System.out.println("hi3");
            if (!this.get(i).equals(taskList.get(i))) {
                System.out.println("task1: " + this.get(i));
                System.out.println("task2: " + taskList.get(i));
                return false;
            }
        }

        return true;
    }
}
