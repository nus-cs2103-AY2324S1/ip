package bob;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a parser that deals with interactions with the user.
 */
public class Ui {

    private static Parser parser;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Marks a task as completed according to specified task index.
     *
     * @param list the TaskList containing the tasks.
     * @param markNo index of the task in the list to be marked.
     * @return the string representation of Bob's response.
     */
    public static String markTask(TaskList list, int markNo) {
        if (markNo > 0 && markNo <= list.size()) {
            System.out.println("Nice! I've marked this task as done:");
            list.get(markNo - 1).markAsDone();
            System.out.println(list.get(markNo - 1).toString());
            return "Nice! I've marked this task as done:\n" + list.get(markNo - 1).toString();
        } else {
            System.out.println("Sorry, there is no such task!");
            return "Sorry, there is no such task!";
        }
    }

    /**
     * Deletes a task from the list according to specified task index.
     *
     * @param list the TaskList containing the tasks.
     * @param deleteNo index of the task in the list to be deleted.
     * @return the string representation of Bob's response.
     */
    public static String deleteTask(TaskList list, int deleteNo) {
        String response = "Noted. I've removed this task:\n";
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(deleteNo - 1).toString());
        response += list.get(deleteNo - 1).toString() + "\n";
        list.remove(deleteNo - 1);
        response += "Now you have " + String.valueOf(list.size()) + " tasks in the list.";
        System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
        return response;
    }

    /**
     * Finds task according to the specified keyword and lists them out.
     * 
     * @param list the list containing all tasks.
     * @param keyword the keyword used to filter tasks.
     * @return the string representation of Bob's response.
     */
    public static String findTask(TaskList list, String keyword) {
        ArrayList<Task> matches = new ArrayList<Task>();
        String response;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(keyword)) {
                matches.add(list.get(i));
            }
        }

        System.out.println("Here are the matching tasks in your list:");
        response = "Here are the matching tasks in your list:\n";
        for (int i = 1; i <= matches.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
            response += i + ". " + list.get(i - 1).toString() + "\n";
        }
        return response;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param list the TaskList containing the tasks.
     * @param newTask the new Task object that is to be added into the list.
     * @return the string representation of Bob's response.
     */
    public static String addTask(TaskList list, Task newTask) {
        String response;
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        response = "Got it. I've added this task:\n";
        System.out.println(newTask.toString());
        response += newTask.toString() + "\n";
        System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
        response += "Now you have " + String.valueOf(list.size()) + " tasks in the list.";

        return response;
    }

    /**
     * Checks what type of task is given in the input, Identifies the task name and dates/times (if applicable),
     * then instantiates the Task of the specified type and adds it to list by calling addTask method.
     *
     * @param list the TaskList containing the tasks.
     * @param task the input string given.
     * @return the string representation of Bob's response.
     * @throws BobException if input is invalid.
     */
    public static String checkAndAddTask(TaskList list, String task) throws BobException {
        char[] charArray = task.toCharArray();
        String taskName = "";

        //todo
        if (charArray[0] == 't' && charArray[1] == 'o' && charArray[2] == 'd' && charArray[3] == 'o') {
            for (int i = 5; i < charArray.length; i++) {
                taskName = taskName + charArray[i];
            }

            if (taskName.isBlank()) {
                throw new BobException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                Todo thisTask = new Todo(taskName);
                return addTask(list, thisTask);
            }

        }

        //deadline
        if (charArray[0] == 'd' && charArray[1] == 'e' && charArray[2] == 'a' && charArray[3] == 'd' &&
                charArray[4] == 'l' && charArray[5] == 'i' && charArray[6] == 'n' && charArray[7] == 'e') {
            String by = "";
            int byIndex = charArray.length;

            for (int i = 9; i < charArray.length; i++) {
                if (i + 1 < charArray.length && charArray[i + 1] == '/') {
                    byIndex = i + 1;
                    continue;
                }

                if (i > byIndex + 3) {
                    by = by + charArray[i];
                } else if (i < byIndex - 1) {
                    taskName = taskName + charArray[i];
                }

            }

            if (taskName.isBlank()) {
                throw new BobException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                LocalDate d1 = LocalDate.parse(by);
                Deadline thisTask = new Deadline(taskName, d1);
                return addTask(list, thisTask);
            }

        }

        //event
        if (charArray[0] == 'e' && charArray[1] == 'v' && charArray[2] == 'e' && charArray[3] == 'n' && charArray[4] == 't') {
            String from = "";
            String to = "";
            int fromIndex = charArray.length;
            int toIndex = charArray.length;

            for (int i = 6; i < charArray.length; i++) {
                if (i + 1 < charArray.length && charArray[i + 1] == '/' && fromIndex == charArray.length) {
                    fromIndex = i + 1;
                    continue;
                } else if (i + 1 < charArray.length && charArray[i + 1] == '/' && fromIndex != charArray.length) {
                    toIndex = i + 1;
                    continue;
                }

                if (i > fromIndex + 5 && i < toIndex) {
                    from = from + charArray[i];
                } else if (i > toIndex + 3) {
                    to = to + charArray[i];
                } else if (i < fromIndex - 1) {
                    taskName = taskName + charArray[i];
                }

            }

            if (taskName.isBlank()) {
                throw new BobException("OOPS!!! The description of a event cannot be empty.");
            } else {
                LocalDate d1 = LocalDate.parse(from);
                LocalDate d2 = LocalDate.parse(to);
                Event thisTask = new Event(taskName, d1, d2);
                return addTask(list, thisTask);
            }

        }

        //not a task
        throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param list the TaskList containing the tasks.
     * @return the string representation of Bob's response.
     */
    public static String printTasks(TaskList list) {
        String response;
        System.out.println("Here are the tasks in your list:");
        response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
            response += i + ". " + list.get(i - 1).toString() + "\n";
        }
        return response;
    }

    /**
     * Shows the loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Prints greeting when it is the user's first time running.
     */
    public void printGreeting() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints and returns goodbye message when user says bye.
     *
     * @return goodbye message
     */
    public String printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }
}
