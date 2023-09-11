package noelPackage.helper;

import noelPackage.Noel;
import noelPackage.exceptions.NoelException;
import noelPackage.tasks.Deadlines;
import noelPackage.tasks.Events;
import noelPackage.tasks.Task;
import noelPackage.tasks.ToDos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Manages a list of tasks with functionalities to add, update, and remove tasks.
 */
public class Tasklist {

    /** The actual list that holds tasks. */
    private final ArrayList<Task> taskList;

    /** Message displayed when a task is added. */
    static String ADDED_MESSAGE_START = "Got it. I've added this task:";

    /** Message displayed when a search is done and tasks are found. */
    String MATCHING_STRING = "Here are the matching tasks in your list:";

    /**
     * Constructs a Tasklist from a serialized list of tasks.
     * @param listOfTasks The serialized list of tasks.
     * @throws NoelException If the list of tasks is null.
     */
    public Tasklist(String listOfTasks) throws NoelException {
        if (listOfTasks == null) {
            throw new NoelException("No content in file!");
        } else {
            taskList = new ArrayList<>();
            updateTaskList(listOfTasks);
        }
    }

    /**
     * Default constructor that initializes an empty task list.
     */
    public Tasklist() {
        taskList = new ArrayList<>();
    }

    /**
     * Checks if the task list is full.
     * @return True if the list is full, otherwise false.
     */
    public boolean checkFull() {
        int maxSize = 100;
        return taskList.size() == maxSize;
    }

    /** Checks if the task list is empty.
     * @return True if the list is empty, otherwise false.
     */
    public boolean checkEmpty() {
        return taskList.size() == 0;
    }

    /**
     * Updates the task list based on a serialized string representation.
     * @param content The serialized string.
     */
    public void updateTaskList(String content) {
        String[] listOfStrings = content.split("\n");

        for (String line : listOfStrings) {
            String[] values = line.split(" \\| ");

            if (values.length != 1) {

                if (Objects.equals(values[0], "E")) {
                    if (values.length == 4) {

                        // (from: 02 02 2023 06:00 to: 03 02 2023 06:00)
                        String[] dates = values[3].split("to:");
                        String[] startDateArray = dates[0].split("\\(from: ")[1].split(" ");
                        String startDate = startDateArray[0] + " " + startDateArray[1];
                        String[] endDateArray = dates[1].split("\\)")[0].split(" ");
                        String endDate = endDateArray[1] + " " + endDateArray[2];

                        if (dates.length == 2) {
                            addEvent(values[2], startDate, endDate);
                        } else {
                            System.out.println("Invalid line! Skipping line...");
                            continue;
                        }
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                        continue;
                    }

                } else if (Objects.equals(values[0], "T")) {
                    if (values.length == 3) {
                        addToDo(values[2]);
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                        continue;
                    }
                } else if (Objects.equals(values[0], "D")) {
                    if (values.length == 4) {
                        addDeadline(values[2], values[3]);
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                        continue;
                    }
                } else {
                    System.out.println("Invalid task! Skipping line...");
                    continue;
                }

                if (Objects.equals(values[1], "1")) {
                    this.taskList.get(this.taskList.size() - 1).markAsDone();
                }
            } else {
                System.out.println("No value in file");
            }
        }
    }

    /**
     * Parses a date string into a LocalDate object.
     * @param endDate The date string to parse.
     * @return The parsed LocalDate object.
     */
    public LocalDate dateFormat(String endDate) {
        LocalDate date;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            date = LocalDate.parse(endDate, formatter1);
            return date;
        } catch (DateTimeParseException e1) {
            try {
                date = LocalDate.parse(endDate, formatter2);
                return date;
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid date format");
            }
        }
        return null;
    }

    /**
     * Parses a time string into a LocalTime object.
     * @param date The time string to parse.
     * @return The parsed LocalTime object.
     */
    public LocalTime timeFormat(String date) {
        LocalTime time = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            time = LocalTime.parse(date, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                time = LocalTime.parse(date, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid Time Format");
            }
        }
        return time;
    }

    /**
     * Prints a message enclosed in a fixed design.
     * @param message The message to print.
     */
    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    /**
     * Adds an event task to the task list.
     * @param task The task description.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public void addEvent(String task, String startDate, String endDate) {

        LocalDate startDateFormat = dateFormat(startDate);
        LocalDate endDateFormat = dateFormat(endDate);
        LocalTime startTimeFormat = timeFormat(startDate);
        LocalTime endTimeFormat = timeFormat(endDate);

        if (startDateFormat == null || endDateFormat == null) {
            System.out.println("Invalid format for start/end date!");
            return;
        }

        if (startTimeFormat == null || endTimeFormat == null) {
            System.out.println("Invalid format for start/end time!");
            return;
        }

        Task taskToAdd = new Events(task, startDateFormat, startTimeFormat, endDateFormat, endTimeFormat);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = ADDED_MESSAGE_START + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        if (!Noel.updateFromFile) {
            printFunction(updateAdd);
        }
    }

    /**
     * Adds a deadline task to the task list.
     * @param task The task description.
     * @param endDate The end date of the deadline.
     */
    public void addDeadline(String task, String endDate) {

        LocalDate date = dateFormat(endDate);
        if (date == null) {
            System.out.println("Invalid date!");
            return;
        }

        LocalTime time = timeFormat(endDate);
        if (time == null) {
            System.out.println("Invalid date!");
            return;
        }

        Task taskToAdd = new Deadlines(task, date, time);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = ADDED_MESSAGE_START + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        if (!Noel.updateFromFile) {
            printFunction(updateAdd);
        }
    }

    /**
     * Adds a To-Do task to the task list.
     * @param task The task description.
     */
    public void addToDo(String task) {

        Task taskToAdd = new ToDos(task);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = ADDED_MESSAGE_START + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        if (!Noel.updateFromFile) {
            printFunction(updateAdd);
        }
    }

    /**
     * Prints the current task list to the console.
     */
    public void printTaskList(){
        if (checkEmpty()) {
            System.out.println("List is empty!");
        } else {
            String filler = "____________________________________________________________";
            System.out.println(filler);
            int maxLength = taskList.size();
            for (int i = 0; i < maxLength; i++) {
                System.out.println(i + 1 + ". " + taskList.get(i));
            }
            System.out.println(filler);
        }
    }

    /**
     * Returns the task list as a List of Strings suitable for file storage.
     * @return List of strings representing tasks for storage.
     */
    public List<String> getTaskAsList() {
        List<String> linesToAppend = new ArrayList<>();
        for (Task t:taskList) {
            linesToAppend.add(t.toFileString());
        }
        return linesToAppend.subList(0, linesToAppend.size());
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark as done.
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to mark as undone.
     */
    public void unMark(int index) {
        taskList.get(index).markAsUnDone();
    }

    /**
     * Removes a task from the list.
     * @param index The index of the task to remove.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index.
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void searchByKeyword(String keyword) {
        List<String> linesToAppend = new ArrayList<>();

        for (Task t:taskList) {
            if (t.searchTaskName(keyword)) {
                linesToAppend.add(t.toString());
            }
        }
        displayResult(linesToAppend.subList(0, linesToAppend.size()));
    }

    public void displayResult(List<String> listOfTasks) {
        if (listOfTasks.size() == 0) {
            System.out.println("No results with such keyword!");
        } else {
            int lastTask = listOfTasks.size();
            StringBuilder result = new StringBuilder();
            int i = 1;
            for (String t: listOfTasks) {
                if (i == lastTask) {
                    result.append(i).append(".").append(t);
                } else {
                    result.append(i).append(".").append(t).append("\n");
                }
                i++;
            }
            printFunction(MATCHING_STRING + "\n" + result);
        }
    }
}