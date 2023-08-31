package bob;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a parser that deals with interactions with the user
 */
public class Ui {

    private static Parser parser;

    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Marks a task as completed according to specified task index
     *
     * @param markNo index of the task in the list to be marked
     */
    public static void markTask(TaskList list, int markNo) {
        if (markNo > 0 && markNo <= list.size()) {
            System.out.println("Nice! I've marked this task as done:");
            list.get(markNo - 1).markAsDone();
            System.out.println(list.get(markNo - 1).toString());
        } else {
            System.out.println("Sorry, there is no such task!");
        }
    }

    /**
     * Deletes a task from the list according to specified task index
     *
     * @param deleteNo index of the task in the list to be deleted
     */
    public static void deleteTask(TaskList list, int deleteNo) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(deleteNo - 1).toString());
        list.remove(deleteNo - 1);
        System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
    }

    /**
     * Finds task according to the specified keyword and lists them out
     * @param list the list containing all tasks
     * @param keyword the keyword used to filter tasks
     */
    public static void findTask(TaskList list, String keyword) {
        ArrayList<Task> matches = new ArrayList<Task>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(keyword)) {
                matches.add(list.get(i));
            }
        }

        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= matches.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
        }
    }

    /**
     * Adds a task into the list of tasks
     *
     * @param newTask the new Task object that is to be added into the list
     */
    public static void addTask(TaskList list, Task newTask) {
        list.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask.toString());
        System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
    }

    /**
     * Checks what type of task is given in the input, Identifies the task name and dates/times (if applicable),
     * then instantiates the Task of the specified type and adds it to list by calling addTask method.
     *
     * @param task the input string given
     * @throws BobException
     */
    public static void checkAndAddTask(TaskList list, String task) throws BobException {
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
                addTask(list, thisTask);
            }

            return;
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
                addTask(list, thisTask);
            }

            return;
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
                addTask(list, thisTask);
            }

            return;
        }

        //not a task
        throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints all the tasks in the list.
     */
    public static void printTasks(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
        }
    }

    /**
     * Shows the loading error
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Prints greeting when it is the user's first time running
     */
    public void printGreeting() {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints goodbye message when user says bye
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
