import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    public static String[] taskList = new String[100];

    public static int taskListIndex = 0;

    public static void markAsDone(int index) {
        String[] taskSplitted = taskList[index - 1].split("");
        taskSplitted[1] = "X";
        taskList[index - 1] = String.join("", taskSplitted);
        System.out.println("I've marked this as done...");
        System.out.println("    " + taskList[index - 1]);
        System.out.println("--------------------------------");
    }
    public static void listTask() {
        for (int i = 1; i <= taskListIndex; i++) {
            System.out.println("    " + i + ". " + taskList[i - 1]);
        }
        System.out.println("--------------------------------");
    }
    public static void addTask(String input) {
        taskList[taskListIndex] = "[ ] " + input;
        System.out.println("    added: " + input);
        taskListIndex += 1;
        System.out.println("--------------------------------");
    }
    public static boolean continueOrNot(String[] input) {
        if (input[0].equals("bye")) {
            return false;
        }
        return true;
    }
    public static String[] input() {
        Scanner myInput = new Scanner(System.in);
        String reply = myInput.nextLine();
        System.out.println("--------------------------------");
        return reply.split(" ");
    }
    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        String[] input = input();
        while (continueOrNot(input)) {
            if (input[0].equals("list")) {
                listTask();
            } else if (input[0].equals("mark")) {
                markAsDone(Integer.parseInt(input[1]));
            } else {
                addTask(String.join(" ", input));
            }
            input = input();
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

