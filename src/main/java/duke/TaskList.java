package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class to handle the tasklist operations
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(String tasksFromFile) throws DukeException, IOException {
        convertTasksToList(tasksFromFile);
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Converts tasks read from a file to a list of tasks and processes each task.
     * Reads task strings from the provided input, parses them, and handles them based on their types.
     *
     * @param tasksFromFile The string containing tasks read from a file.
     * @throws DukeException If there is an error while processing a task.
     * @throws IOException If there is an I/O error during processing.
     */
    public void convertTasksToList(String tasksFromFile) throws DukeException, IOException {

        int i = 0;
        int j = 0;

        while (i < tasksFromFile.length()) {
            String input = "";
            for (; tasksFromFile.charAt(j) != '\n'; j++) {
                input += tasksFromFile.charAt(j);
            }
            String taskType = input.substring(3, 4);
            String taskDescription = input.substring(9);
            switch (taskType) {
            case "T":
                handleTodoTask("todo " + taskDescription, "file");
                break;
            case "D":
                handleDeadlineTask("deadline " + taskDescription, "file");
                break;
            case "E":
                handleEventTask("event " + taskDescription, "file");
                break;
            default:
                break;
            }

            i = j;
            j++;
            i++;
        }
    }

    /**
     * Retrieves a formatted string showing the number of tasks left in the tasks.
     *
     * @return A string indicating the number of tasks remaining.
     */
    public String getTaskLeft() {
        return "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the tasks.";
    }

    /**
     * Retrieves a formatted string containing all tasks in the tasks.
     *
     * @return A string tasksing all tasks in the task tasks.
     * @throws DukeException If there are no tasks in the tasks.
     */
    public String getAllToDo() throws DukeException {
        StringBuilder res = new StringBuilder();

        if (tasks.size() == 0) {
            throw new DukeException("Oh no! No tasks for now! Add more tasks :)\n");
        }

        res.append("Here are the tasks in your tasks:\n");

        for (int i = 0; i < tasks.size(); i++) {

            res.append(i + 1).append(".")
                    .append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    /**
     * Retrieves a formatted string indicating the success of marking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task tasks or input.
     */
    public String markTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("Specify index to mark task!\n");
        }

        if (parts.length > 2) {
            throw new DukeException("Enter mark command properly!\n");
        }

        //No task to mark
        if (tasks.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to mark!\n");
        }

        if (parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);

                //index entered is more than totalTodos
                if (index > tasks.size() || index <= 0) {
                    throw new DukeException("Enter mark command with positive index lesser than "
                            + (tasks.size() + 1) + "\n");
                }

                res = tasks.get(index - 1).setMarked();

            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your markcommand!\n");
            }
        }
        return res;
    }

    /**
     * Retrieves a formatted string indicating the success of unmarking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task tasks or input.
     */
    public String unmarkTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("Specify index to unmark task!\n");
        }

        if (parts.length > 2) {
            throw new DukeException("Enter unmark command properly!\n");
        }

        //No task to mark
        if (tasks.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to unmark!\n");
        }

        if (parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);

                //index entered is more than totalTodos
                if (index > tasks.size() || index <= 0) {
                    throw new DukeException("Enter umark command with positive index lesser than "
                            + (tasks.size() + 1) + "\n");
                }
                res = tasks.get(index - 1).setUnmarked();

            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your unmark command!\n");
            }
        }
        return res;
    }


    /**
     * Deletes a task from the task tasks based on the provided index.
     *
     * @param input The input of the task to be deleted.
     * @return A message indicating the success of the deletion.
     * @throws DukeException If there's an issue with the task tasks or input.
     */
    public String deleteTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to delete
        if (input.equals("delete") && parts.length == 1) {
            throw new DukeException("Specify index to delete task!\n");
        }

        //No task to delete
        if (tasks.size() == 0) {
            throw new DukeException("No tasks to delete! Add more tasks to delete!\n");
        }

        if ((parts[0].equals("delete")) && parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);
                String removedTask = tasks.get(index - 1).toString();
                tasks.remove(index - 1);
                res = "Noted. I've removed this task: \n " + "  " + removedTask + "\n" + getTaskLeft();
            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your mark/unmark command!\n");
            }
        }

        return res;
    }

    /**
     * Adds a duke.ToDo task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description.
     * @return A message indicating the success of adding the duke.ToDo task.
     * @throws DukeException If there's an issue with the input or task description.
     */
    public String handleTodoTask(String input, String from) throws DukeException, IOException {
        String task = "";

        String[] parts = input.split(" ");

        for (int i = 1; i < parts.length; i++) {
            task += parts[i] + " ";
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said?! "
                    + "Enter again!\n");
        }

        tasks.add(new ToDo(task, TaskType.TODO));

        String str = tasks.get(tasks.size() - 1).toString();
        String res = "Got it. I've added this task :\n" + str + "\n";
        res += getTaskLeft();


        return res;
    }

    /**
     * Adds a duke.Deadline task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description and deadline.
     * @return A message indicating the success of adding the duke.Deadline task.
     * @throws DukeException If there's an issue with the input, task description, or deadline.
     */
    public String handleDeadlineTask(String input, String from) throws DukeException, IOException {
        String task = "";
        String by = "";
        String endTime = "";

        if (from.equals("user")) {
            String[] parts = input.split("/by ");
            String[] taskArray = parts[0].split(" ");
            if (parts.length != 2) {
                throw new DukeException("Specify by date and time!");
            }
            String[] deadlineInfo = parts[1].split(" ");

            if (deadlineInfo.length != 2) {
                throw new DukeException("Specify both date and time in the following manner : yyyy-mm-dd hh:mm");
            }
            by = deadlineInfo[0];
            endTime = deadlineInfo[1];

            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }
        } else if (from.equals("file")) {
            String [] parts = input.split("by: ");
            String[] taskArray = parts[0].split(" ");
            String[] deadlineInfo = parts[1].split(" ");

            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }

            for (int i = 0; i < 3; i++) {
                by += deadlineInfo[i] + " ";
            }

            by = by.substring(0, 11);

            endTime = deadlineInfo[3];
            endTime = endTime.substring(0, endTime.length() - 1);

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(by, inputFormatter);
            by = localDate.format(outputFormatter);
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! "
                    + "Enter again!\n");
        }

        if (by.isEmpty()) {
            throw new DukeException("deadline task must have /by date and time\n");
        }

        tasks.add(new Deadline(task, by, endTime + ":00", TaskType.DEADLINE));

        String str = tasks.get(tasks.size() - 1).toString();
        String res = "Got it. I've added this task :\n" + str + "\n";
        res += getTaskLeft();


        return res;
    }

    /**
     * Adds an duke.Event task to the task tasks based on the provided input.
     *
     * @param input The user input containing the task description and event timings.
     * @return A message indicating the success of adding the duke.Event task.
     * @throws DukeException If there's an issue with the input, task description, or event timings.
     */
    public String handleEventTask(String input, String from) throws DukeException, IOException {
        String task = "";
        String startDate = "";
        String endDate = "";
        String startTime = "";
        String endTime = "";

        if (from.equals("user")) {
            String[] parts = input.split("/from ");
            if (parts.length != 2) {
                throw new DukeException("Specify from and to date and time!");
            }
            String[] taskArray = parts[0].split(" ");
            String[] taskInfo = parts[1].split("/to ");

            if (taskInfo.length != 2) {
                throw new DukeException("Specify both date and time for /from and /to in the following manner "
                        + ": yyyy-mm-dd hh:mm");
            }

            String[] fromInfo = taskInfo[0].split(" ");
            String[] toInfo = taskInfo[1].split(" ");

            if (fromInfo.length != 2) {
                throw new DukeException("Specify both date and time for /from in the following manner "
                        + ": yyyy-mm-dd hh:mm");
            }

            if (toInfo.length != 2) {
                throw new DukeException("Specify both date and time for /to in the following manner "
                        + ": yyyy-mm-dd hh:mm");
            }

            startDate = fromInfo[0];
            startTime = fromInfo[1];

            endDate = toInfo[0];
            endTime = toInfo[1];


            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }
        }

        if (from.equals("file")) {

            String[] parts = input.split("from: ");
            String[] taskArray = parts[0].split(" ");
            String[] taskInfo = parts[1].split("to: ");

            String[] fromInfo = taskInfo[0].split(" ");
            String[] toInfo = taskInfo[1].split(" ");

            for (int i = 1; i < taskArray.length; i++) {
                task += taskArray[i] + " ";
            }

            for (int i = 0; i < 3; i++) {
                startDate += fromInfo[i] + " ";
            }

            for (int i = 0; i < 3; i++) {
                endDate += toInfo[i] + " ";
            }

            startDate = startDate.substring(0, 11);
            endDate = endDate.substring(0, 11);
            startTime = fromInfo[3];
            endTime = toInfo[3];
            endTime = endTime.substring(0, endTime.length() - 1);

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDateStart = LocalDate.parse(startDate, inputFormatter);
            LocalDate localDateEnd = LocalDate.parse(endDate, inputFormatter);
            startDate = localDateStart.format(outputFormatter);
            endDate = localDateEnd.format(outputFormatter);
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! "
                    + "Enter again!\n");
        }

        if (startDate.isEmpty() || endDate.isEmpty()) {
            throw new DukeException("event task must have both /from and /to times\n");
        }

        tasks.add(new Event(task, startDate, endDate, startTime + ":00", endTime + ":00", TaskType.EVENT));

        String str = tasks.get(tasks.size() - 1).toString();
        String res = "Got it. I've added this task :\n" + str + "\n";
        res += getTaskLeft();

        return res;
    }

    /**
     * method to handle find operation
     * @param input user's input
     * @return string representation of operayion
     * @throws DukeException
     */

    public String handleFindTask(String input) throws DukeException {
        String results = "";
        String[] parts = input.split(" ");

        if (parts.length == 1) {
            throw new DukeException("Specify keyword to search for!\n");
        }

        if (parts.length > 2) {
            throw new DukeException("Enter only one keyword to search!\n");
        }

        int resultCounter = 1;
        String keyword = " " + parts[1] + " ";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                results += resultCounter + "." + tasks.get(i).toString() + "\n";
                resultCounter++;
            }
        }

        if (results.equals("")) {
            return "No results found!";
        }
        return results;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
