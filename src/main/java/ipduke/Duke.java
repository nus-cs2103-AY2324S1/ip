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
            try {
                String[] input = sc.nextLine().split(" ", 2);
                String firstWord = input[0];
                String taskDetails = input.length == 1 ? "" : input[1];

                if (firstWord.equals("bye")) {
                    System.out.println(parting);
                    break;
                }

                if (firstWord.equals("list")) {
                    System.out.println(divider);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + taskList.get(i).toString());
                    }
                    System.out.println(divider);
                    continue;
                }

                switch (firstWord) {
                    case "todo":
                        if (taskDetails.equals("")) throw new EmptyTodoException(
                                divider + "    TWEET!!! The description of a todo cannot be empty.\n" + divider
                        );
                        taskList.add(new Todo(taskDetails));
                        System.out.println(divider + "    chirp! I've added this task:\n"
                                + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                                + String.format("    Now you have %d tasks in the list\n", taskList.size())
                                + divider);
                        break;
                    case "deadline":
                        if (taskDetails.equals("")) throw new EmptyDeadlineException(
                                divider + "    TWEET!!! The description of a deadline cannot be empty.\n" + divider
                        );
                        taskList.add(new Deadline(taskDetails));
                        System.out.println(divider + "    chirp! I've added this task:\n"
                                + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                                + String.format("    Now you have %d tasks in the list\n", taskList.size())
                                + divider);
                        break;
                    case "event":
                        if (taskDetails.equals("")) throw new EmptyEventException(
                                divider + "    TWEET!!! The description of an event cannot be empty.\n" + divider
                        );
                        taskList.add(new Event(taskDetails));
                        System.out.println(divider + "    chirp! I've added this task:\n"
                                + String.format("    %s", taskList.get(taskList.size() - 1).toString()) + "\n"
                                + String.format("    Now you have %d tasks in the list\n", taskList.size())
                                + divider);
                        break;
                    case "delete":
                        int deleteIndex = Integer.parseInt(taskDetails) - 1;
                        if (deleteIndex < 0 || deleteIndex >= taskList.size()) throw new InvalidTaskNumberException(
                                divider + "    TWEET!!! I can't find the task you are looking for!\n" + divider
                        );
                        taskList.remove(deleteIndex);
                        System.out.print(divider + "    chirp! chirp! Task right out the window!\n" + divider);
                        break;
                    case "mark":
                        int markIndex = Integer.parseInt(taskDetails) - 1;
                        if (markIndex < 0 || markIndex >= taskList.size()) throw new InvalidTaskNumberException(
                                divider + "    TWEET!!! I can't find the task you are looking for!\n" + divider
                        );
                        System.out.print(divider);
                        taskList.get(markIndex).markTask();
                        System.out.println(divider);
                        break;
                    case "unmark":
                        int unmarkIndex = Integer.parseInt(taskDetails) - 1;
                        if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) throw new InvalidTaskNumberException(
                                divider + "    TWEET!!! I can't find the task you are looking for!\n" + divider
                        );
                        System.out.print(divider);
                        taskList.get(unmarkIndex).unmarkTask();
                        System.out.println(divider);
                        break;
                    default:
                        throw new EmptyEventException(
                                divider + "    TWEET!!! I'm sorry, but I don't know how to bark\n" + divider
                        );
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("    chirp chirp!\n" + logo_bird + greet);

        setTaskList();
    }


}
