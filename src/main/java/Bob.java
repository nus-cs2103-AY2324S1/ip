import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    static ArrayList<Task> list = new ArrayList<Task>();

    /**
     * Marks a task as completed according to specified task index
     *
     * @param markNo index of the task in the list to be marked
     */
    public static void markTask(int markNo) {
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
    public static void deleteTask(int deleteNo) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(deleteNo - 1).toString());
        list.remove(deleteNo - 1);
        System.out.println("Now you have " + String.valueOf(list.size()) + " tasks in the list.");
    }

    /**
     * Adds a task into the list of tasks
     *
     * @param newTask the new Task object that is to be added into the list
     */
    public static void addTask(Task newTask) {
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
    public static void checkAndAddTask(String task) throws BobException {
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
                addTask(thisTask);
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
                Deadline thisTask = new Deadline(taskName, by);
                addTask(thisTask);
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
                Event thisTask = new Event(taskName, from, to);
                addTask(thisTask);
            }

            return;
        }

        //not a task
        throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints all the tasks in the list.
     *
     */
    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        Scanner obj = new Scanner(System.in);

        while (true) {
            String input = obj.nextLine();
            boolean isMark = false;
            int markNo = 0;
            boolean isDelete = false;
            int deleteNo = 0;
            char[] charArray = input.toCharArray();

            if (charArray[0] == 'm' && charArray[1] == 'a' && charArray[2] == 'r' && charArray[3] == 'k'
                    && Character.isWhitespace(charArray[4]) && Character.isDigit(charArray[5])) {
                isMark = true;
                markNo = Character.getNumericValue(charArray[5]);
            }

            if (charArray[0] == 'd' && charArray[1] == 'e' && charArray[2] == 'l' && charArray[3] == 'e' &&
                    charArray[4] == 't' && charArray[5] == 'e' && Character.isWhitespace(charArray[6]) &&
                    Character.isDigit(charArray[7])) {
                isDelete = true;
                deleteNo = Character.getNumericValue(charArray[7]);
            }

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                printTasks();
            } else if (isMark) {
                markTask(markNo);
            } else if (isDelete) {
                deleteTask(deleteNo);
            } else {
                try {
                    checkAndAddTask(input);
                } catch (BobException e) {
                    System.out.println(e.toString());
                }
            }
        }

    }

}
