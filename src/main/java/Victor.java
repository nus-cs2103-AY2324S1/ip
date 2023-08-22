import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Victor {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner reader = new Scanner(System.in);
        List<Task> list = new ArrayList<>(100);
        System.out.println("Hello! I'm Victor\n" +
                "What can I do for you?\n----------\n");

        while (true) {
            String input = reader.nextLine();
            String[] formattedInput = input.split(" ", 2);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.printf("%d.%s\n", i + 1, task);
                }
                System.out.println("----------\n");
            } else if (formattedInput[0].equals("mark")) {
                Task task = list.get(Integer.parseInt(formattedInput[1]) - 1);
                task.markDone();
                System.out.printf("Nice! I've marked this task as done: \n" +
                        "%s\n" + "----------\n", task);
            } else if (formattedInput[0].equals("unmark")) {
                Task task = list.get(Integer.parseInt(formattedInput[1]) - 1);
                task.unMarkDone();
                System.out.printf("OK, I've marked this task as not done yet: \n" +
                        "%s\n" + "----------\n", task);
            } else if (formattedInput[0].equals("todo")) {
                Task task = new Todo(formattedInput[1]);
                list.add(task);
                System.out.printf("Got it. I've added this task:\n" +
                        "%s\n" + "Now you have %d tasks in the list.\n" +
                        "----------\n", task, list.size());
            } else if (formattedInput[0].equals("deadline")) {
                int indexOfBy = input.indexOf("/by");
                int indexOfDeadline = input.indexOf("deadline");
                String description = input.substring(indexOfDeadline + 9, indexOfBy - 1);
                String deadline = input.substring(indexOfBy + 4);
                Task task = new Deadline(description, deadline);
                list.add(task);
                System.out.printf("Got it. I've added this task:\n" +
                        "%s\n" + "Now you have %d tasks in the list.\n" +
                        "----------\n", task, list.size());
            } else if (formattedInput[0].equals("event")) {
                int indexOfFrom = input.indexOf("/from");
                int indexOfTo = input.indexOf("/to");
                int indexOfEvent = input.indexOf("event");
                String description = input.substring(indexOfEvent + 6, indexOfFrom - 1);
                String from = input.substring(indexOfFrom + 6, indexOfTo - 1);
                String to = input.substring(indexOfTo + 4);
                Task task = new Event(description, from, to);
                list.add(task);
                System.out.printf("Got it. I've added this task:\n" +
                        "%s\n" + "Now you have %d tasks in the list.\n" +
                        "----------\n", task, list.size());
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.printf("added: %s\n----------\n", input);
            }
        }
    }
}
