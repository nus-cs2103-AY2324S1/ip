package task;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import main.UI;

/**
 * Wrapper class that contains ArrayList of type Task.
 */
public class TaskList {

    ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {}

    /**
     * Deletes Task from the Task List.
     *
     * @param input User input specifying the index of the task to be removed.
     */
    public void deleteTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
        taskList.remove(taskIndex);
    }

    /**
     * Adds Event to the Task List.
     *
     * @param input User input specifying the details of the Event to be added.
     */
    public String addEvent(String input) {

        StringBuilder sb = new StringBuilder();

        String[] list = input.split("/");
        String title = list[0].substring(6);
        String start = list[1].substring(5).trim();
        String end = list[2].substring(3).trim();

        // returns 1 if correct datetime
        // returns 0 if random string input

        int startTimeFormat = this.computeDateTimeFormat(start);
        int endTimeFormat = this.computeDateTimeFormat(end);

        if (startTimeFormat != 1 || endTimeFormat != 1) {
            Event event = new Event(title, start, end);
            sb.append(UI.showAddTask(event));
            taskList.add(event);
        } else {
            try {
                DateTimeFormatter inputFormatWithTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime startDateTime = LocalDateTime.parse(start, inputFormatWithTime);
                LocalDateTime endDateTime = LocalDateTime.parse(end, inputFormatWithTime);
                Event event = new Event(title, startDateTime, endDateTime);
                sb.append(UI.showAddTask(event));
                taskList.add(event);
            } catch (DateTimeParseException e) {
                sb.append("Invalid date-time format. Stick to the given format of dd-MM-yyyy HHmm");
                System.out.println("Invalid date-time format. Stick to the given format of dd-MM-yyyy HHmm");
            }
        }
        return String.valueOf(sb);
    }

    /**
     * Adds Deadline to the Task List.
     *
     * @param input User input specifying the details of the Deadline to be added.
     */
    public String addDeadline(String input) {
        StringBuilder sb = new StringBuilder();

        String[] list = input.split("/");
        String title = list[0].substring(9);
        String time = list[1].substring(3).trim();

        // returns 1 if correct datetime
        // returns 0 if random string input

        int timeFormat = this.computeDateTimeFormat(time);

        if (timeFormat == 0) {
            Deadline deadline = new Deadline(title, time);
            sb.append(UI.showAddTask(deadline));
            taskList.add(deadline);
        } else {
            try {
                DateTimeFormatter inputFormatWithTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(time, inputFormatWithTime);
                Deadline deadline = new Deadline(title, dateTime);
                sb.append(UI.showAddTask(deadline));
                taskList.add(deadline);
            } catch (DateTimeParseException e) {
                sb.append("Invalid date-time format. Stick to the given format of dd-MM-yyyy HHmm");
                System.out.println("Invalid date-time format. Stick to the given format of dd-MM-yyyy HHmm");
            }
        }
        return String.valueOf(sb);
    }

    /**
     * Adds ToDoTask to the Task List.
     *
     * @param input User input specifying the details of the ToDoTask to be added.
     */
    public String addToDo(String input) throws DukeException {
        ToDo toDo = new ToDo(input);
        String message = UI.showAddTask(toDo);
        taskList.add(toDo);
        return message;
    }

    /**
     * Generic method to add tasks to the Task List.
     *
     * @param task Task object to be added to the tasks list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the specific task as done.
     *
     * @param taskIndex Index of the task to be marked as done.
     */
    public String mark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.setTaskDone(true);
        return UI.showMarked(currTask);
    }

    /**
     * Unmarks the specific task.
     *
     * @param taskIndex Index of the task to be unmarked.
     */
    public String unmark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.setTaskDone(false);
        return UI.showUnmarked(currTask);
    }

    /**
     * Return string output to be displayed in  GUI when Task Description is updated.
     *
     * @param taskIndex Index of the task to be updated.
     * @param updatedDescription New description to be updated.
     * @return String output to be displayed in  GUI when Task Description is updated.
     */
    public String updateTaskDescription(int taskIndex, String updatedDescription) {
        Task currTask = this.taskList.get(taskIndex - 1);
        // update description returns old description
        String oldDescription = currTask.updateDescription(updatedDescription);

        return UI.showUpdatedDescription(taskIndex, oldDescription, updatedDescription);
    }

    /**
     * getSize() method.
     *
     * @return Number of tasks in the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Checks whether given string is made up of just numbers.
     *
     * @param strNum String to be tested.
     * @return true - is numeric, false - not numeric.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * The method is used to identify dates in the below Local Date Time format :
     * "dd-MM-yyyy HHmm" with HHmm in the 24 hour format. Note: This method only checks for the
     * structure of the input. The validity of the Local Date Time Format is validated/tested later.
     * The method will return 1 if it is in LocalDateTimeFormat, 0 if it is a custom string format
     *
     * @param input string to run the test on.
     * @return 1 or 0.
     */
    int computeDateTimeFormat(String input) {

        // returns 1 if correct datetime
        // returns 2 if correct date
        // returns 0 if random string input

        // Assume that the date input is dd/MM/yyyy 16-08-1977 1800

        String[] blankArray = input.split(" ");

        // Check for datetime input
        if (blankArray.length == 2 && isNumeric(blankArray[1])) {

            String[] slashArray = blankArray[0].split("-");

            boolean isAllNumeric = true;
            for (String s : slashArray) {
                if (!isNumeric(s)) {
                    isAllNumeric = false;
                }
            }
            if (slashArray.length == 3 && isAllNumeric) {
                return 1;
            }

        }
        return 0;
    }

    /**
     * getter method.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskList;
    }
}
