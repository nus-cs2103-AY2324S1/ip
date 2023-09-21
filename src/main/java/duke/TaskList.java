package duke;

import java.util.ArrayList;

/**
 * TaskList
 */
public class TaskList {
    private static String line = "\t____________________________________________________________\n";
    protected ArrayList<Task> strList;

    /**
     * Constructor to create a new task list
     */
    public TaskList() {
        this.strList = new ArrayList<>();
    }

    /**
     * Prints a tasks to the console when a task is added
     * @param t The task being printed
     */
    private String printAddTask(Task t) {
        String message = "Got it. I've added this task: " + "\n";
        System.out.println(line + "\t" + message);
        message = message + t.toString() + "\n";
        System.out.println("\t\t" + t.toString());
        String now = "Now you have " + strList.size() + " tasks in the list.";
        System.out.println("\t" + now);
        message = message + now;
        System.out.println(line);
        return message;
    }


    /**
     * Creates a new todo task given a description, isDone boolean, and boolean readingFile
     * @param description description of the todo task
     * @param isDone boolean to mark is the task is done or not
     * @param readingFile boolean to check if readingFile or not
     * @throws EmptyDescriptionException
     */
    public String toDoHandler(String description, boolean isDone, boolean readingFile)
            throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException("todo");
        } else {
            Task newToDo = new ToDos(description, isDone);
            strList.add(newToDo);
            if (!readingFile) {
                return printAddTask(newToDo);
            }
            return null;
        }
    }

    /**
     * Creates a new deadline task given a description, isDone boolean, and readingFile boolean
     * @param description description of the todo task
     * @param isDone boolean to mark is the task is done or not
     * @param readingFile boolean to check if readingFile or not
     * @throws EmptyDescriptionException
     */
    public String deadlineHandler(String description, boolean isDone, boolean readingFile) throws
            EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else {
            String[] parts = description.split("/by"); // Split the input string by the delimiter "/"
            String before = parts[0].trim();
            String after = parts[1].trim();
            Task newDeadline = new Deadline(before, after, isDone);
            strList.add(newDeadline);
            if (!readingFile) {
                return printAddTask(newDeadline);
            }
            return null;
        }
    }

    /**
     * creates a event task given a description, isDone boolean, and readingFile boolean
     * @param description description of the todo task
     * @param isDone boolean to mark is the task is done or not
     * @param readingFile boolean to check if readingFile or not
     * @throws EmptyDescriptionException
     */
    public String eventHandler(String description, boolean isDone, boolean readingFile)
            throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException("event");
        } else {
            int fromIndex = description.indexOf("/from"); // Find the index of "/from"
            int toIndex = description.indexOf("/to"); // Find the index of "/to"
            String eventDescription = description.substring(0, fromIndex).trim();
            String from = description.substring(fromIndex + "/from".length(), toIndex).trim();
            String to = description.substring(toIndex + "/to".length()).trim();
            Task newEvent = new Event(eventDescription, from, to, isDone);
            strList.add(newEvent);
            if (!readingFile) {
                return printAddTask(newEvent);
            }
            return null;
        }
    }

    /**
     * PrintTaskList
     */
    public void printTaskList() {
        for (Task element : strList) {
            System.out.println(element.toString());
        }
    }

    /**
     * Prints put the list contents
     */
    public String printListContent() {
        String result = "";
        int arrLength = this.strList.size();
        System.out.println(line);
        result = result + "Here are the tasks in your list:\n";
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < arrLength; i++) {
            int number = i + 1;
            Task t = strList.get(i);
            System.out.println("\t" + number + "." + t.toString());
            String taskToString = number + "." + t.toString();
            result = result + taskToString + "\n";
        }
        System.out.println(line);
        return result;
    }

    /**
     * Marks a task in the list to be done
     * @param number The number of the task in the list
     * @throws NotANumberException
     */

    public String markTaskAsDone(char number) throws NotANumberException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            assert index <= strList.size() : "That index does not exist";
            Task t = strList.get(index);
            t.markTask();
            System.out.println(line);
            String message = "Nice! I've marked this task as done:" + "\n";
            System.out.println("\t" + message);
            System.out.println("\t" + "\t" + t.toString());
            System.out.println(line);
            message = message + t.toString();
            return message;
        }
    }

    /**
     * Unmarks a task in the list
     * @param number The number of the task in the list
     * @throws NotANumberException
     */
    public String unmarkTask(char number) throws NotANumberException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            assert index <= strList.size() : "That index does not exist";
            Task t = strList.get(index);
            t.unmarkTask();
            System.out.println(line);
            String message = "OK, I've marked this task as not done yet:" + "\n";
            System.out.println("\t" + message);
            System.out.println("\t" + "\t" + t.toString());
            System.out.println(line);
            message = message + t.toString();
            return message;
        }
    }

    /**
     * Deletes a task from the list
     * @param number The number of the task inside the list.
     * @throws NotANumberException
     */
    public String deleteTask(char number) throws NotANumberException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            assert index <= strList.size() : "That index does not exist";
            Task t = strList.remove(index);
            System.out.println(line);
            String message = "Noted. I've removed this task: " + "\n";
            System.out.println("\t" + message);
            System.out.println("\t" + "\t" + t.toString());
            message = message + t.toString() + "\n";
            String nowTasks = "Now you have " + strList.size() + " tasks in the list.";
            System.out.println("\t" + nowTasks);
            message = message + nowTasks;
            System.out.println(line);
            return message;
        }
    }

    /**
     * @return an ArrayList of the current Tasks
     */
    public ArrayList<Task> getTaskList() {
        return this.strList;
    }

    /**
     * Prints out all the tasks that match the taskName
     * @param taskName the name of the task that we are finding for
     */
    public String findTask(String taskName) {
        System.out.println(taskName);
        ArrayList<Task> findList = new ArrayList<>();
        for (Task i : this.strList) {
            if ((i.toString().toLowerCase()).contains(taskName.toLowerCase())) {
                findList.add(i);
            }
        }
        System.out.println(line);
        String message = "Here are the matching tasks in your list:" + "\n";
        System.out.println("\t" + message);
        for (int i = 0; i < findList.size(); i++) {
            int number = i + 1;
            String x = number + "." + findList.get(i).toString() + "\n";
            System.out.println("\t" + x);
            message = message + x;
        }
        System.out.println(line);
        return message;
    }

    /**
     * Method to update the time of a task
     * @param description String representation of the task
     * @param number Number of the task inside the TaskList
     * @return Confirmation that the task has been updated
     * @throws NotANumberException
     * @throws InvalidInputException
     */
    public String updateTime(String description, char number) throws NotANumberException, InvalidInputException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            Task t = strList.get(index);
            return t.updateTime(description) + t.toString();
        }
    }

    /**
     * Prints out all the commands
     * @return all commands
     */
    public String help() {
        return "Here is a list of my commands:\n "
                + "1. todo taskDescription. This adds a todo task to your list of tasks.\n"
                + "\tExample Usage:\n"
                + "\ttodo Homework\n\n"
                + "2. deadline taskDescription /by dd-mm-yy HH:MM. This adds a deadline task your list of tasks.\n"
                + "\tExample Usage:\n"
                + "\tdeadline CS2103T assignment /by 21-09-2023 23:59\n\n"
                + "3. event taskDescription /from dd-mm-yy HH:MM /to dd-mm-yy HH:MM. This adds an event to your list "
                + "of tasks.\n"
                + "\tExample Usage: \n"
                + "\tevent CS2103T Team Meeting /from 21-09-2023 21:00 /to 21-09-2023 22:00\n\n"
                + "4. list. This command displays the list of tasks you have.\n\n"
                + "5. mark taskNumber. This command marks the corresponding task in the list as done\n"
                + "\tExample Usage: \n"
                + "\t mark 1\n\n"
                + "6. unmark taskNumber. This command unmarks the corresponding task in the list\n"
                + "\tExample Usage:\n"
                + "\tunmark 1\n\n"
                + "7. delete taskNumber. This command deletes the corresponding task from the list\n"
                + "\tExample Usage:\n"
                + "\tdelete 1\n\n"
                + "8. find taskDescription. This command returns a list of task that matches the taskName given\n"
                + "\tExample Usage:\n"
                + "\tfind CS2103T\n\n"
                + "9.update taskNumber /by dd-mm-yy HH:MM. This command updates the time of an event "
                + "or deadline task.\n"
                + "\tExample Usage: \n"
                + "\tupdate 2 /by 30-09-2023 23:59";
    }

}
