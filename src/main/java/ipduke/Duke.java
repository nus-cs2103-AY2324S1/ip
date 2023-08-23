package ipduke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static List<Task> taskList = new ArrayList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String divider = "    ____________________________________________________________\n";

    static String logo_bird = "    (• >       (• >       (• >       (• >       (• >       (• >\n"
            +  "    /))        /))        /))        /))        /))        /))\n"
            +  "     ``         ``         ``         ``         ``         ``\n"
            + divider;

    static String greet = "    Hello! I'm Birdy\n"
            + "    chirp chirp! How can I help?\n"
            + divider;

    static String parting = divider + "    chirp! See you around!\n"
            + divider;
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(divider + "    " + input + "\n" + divider);
            }
        }
    }

    public static void setTaskList() {
        Scanner sc = new Scanner(System.in);
        boolean inLoop = true;

        while (inLoop) {
            String[] input = sc.nextLine().split(" ", 2);
            String firstWord = input[0];
            String taskDetails = input.length == 1 ? "" : input[1];

            switch (firstWord) {
                case "bye":
                    inLoop = false;
                    break;
                case "list":
                    System.out.println(divider);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + taskList.get(i).toString());
                    }
                    System.out.println(divider);
                    break;
                case "todo":
                    taskList.add(new Todo(taskDetails));
                    System.out.println(divider + "    chirp! I've added this task: \n"
                            + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                            + String.format("    Now you have %d tasks in the list\n", taskList.size())
                            + divider);
                    break;
                case "deadline":
                    taskList.add(new Deadline(taskDetails));
                    System.out.println(divider + "    chirp! I've added this task: \n"
                            + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                            + String.format("    Now you have %d tasks in the list\n", taskList.size())
                            + divider);
                    break;
                case "event":
                    taskList.add(new Event(taskDetails));
                    System.out.println(divider + "    chirp! I've added this task: \n"
                            + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                            + String.format("    Now you have %d tasks in the list\n", taskList.size())
                            + divider);
                    break;
                case "mark":
                    taskList.get(Integer.parseInt(taskDetails) - 1).markTask();
                    break;
                case "unmark":
                    taskList.get(Integer.parseInt(taskDetails) - 1).unmarkTask();
                    break;
                default:
                    System.out.println("<task type> <task taskDetails>");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("    chirp chirp!\n" + logo_bird + greet);

        setTaskList();

        System.out.println(parting);
    }


}
