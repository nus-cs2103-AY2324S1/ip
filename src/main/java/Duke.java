import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static Task[] taskList = new Task[100];
    public static int taskListIndex = 0;

    public static void unmark(int index) {
        Task target = taskList[index - 1];
        target.setDone(false);
        System.out.println("I've marked this task as not done yet...");
        System.out.println("    " + target.getStatus());
        System.out.println("--------------------------------");
    }
    public static void markAsDone(int index) {
        Task target = taskList[index - 1];
        target.setDone(true);
        System.out.println("I've marked this as done...");
        System.out.println("    " + target.getStatus());
        System.out.println("--------------------------------");
    }
    public static void listTask() {
        for (int i = 1; i <= taskListIndex; i++) {
            Task target = taskList[i - 1];
            System.out.println("    " + i + ". " + target.getStatus());
        }
        System.out.println("--------------------------------");
    }
    public static void addTask(Task newTask) {
        taskList[taskListIndex] = newTask;
        System.out.println("    Got it... I've added this task...");
        System.out.println("      " + newTask.getStatus());
        taskListIndex++;
        System.out.println("Now you have " + (taskListIndex) + " tasks in the list.");
        System.out.println("--------------------------------");
    }
    public static boolean continueOrNot(String[] input) {
        if (input[0].equals("bye")) {
            return false;
        }
        return true;
    }
    public static String combineStringParts(String[] parts) {
        String result = "";
        for (int i = 1; i < parts.length; i++) {
            result += parts[i] + " ";
        }
        return result;
    }
    public static String[] input(Scanner sc) {
        String reply = sc.nextLine();
        System.out.println("--------------------------------");
        return reply.split(" ", 2);
    }
    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        Scanner sc = new Scanner(System.in);
        String[] input = input(sc);
        while (continueOrNot(input)) {
            if (input[0].equals("list")) {
                listTask();
            } else if (input[0].equals("mark")) {
                markAsDone(Integer.parseInt(input[1]));
            } else if (input[0].equals("unmark")) {
                unmark(Integer.parseInt(input[1]));
            } else if (input[0].equals("todo")) {
                addTask(new Todo(input[1]));
            } else if (input[0].equals("deadline")) {
                String newString = input[1];
                String[] splitBy = newString.split(" /by ");
                addTask(new Deadline(splitBy[0].strip(), splitBy[1].strip()));
            } else if (input[0].equals("event")) {
                String newString = input[1].strip();
                String[] splitFrom = newString.split("/from ", 2);
                String[] splitTo = splitFrom[1].split("/to", 2);
                String startDate = splitTo[0].strip();
                String endDate = splitTo[1].strip();
                addTask(new Event(splitFrom[0].strip(), startDate, endDate));
            } else {
                System.out.println("not working.");
            }
            input = input(sc);
        }
        System.out.println("    bye...");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}

