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
            String[] formattedInput = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    String taskDescription = task.getDescription();
                    String taskStatusIcon = task.getStatusIcon();
                    System.out.printf("%d.[%s] %s\n", i + 1, taskStatusIcon, taskDescription);
                }
                System.out.println("----------\n");
            } else if (formattedInput[0].equals("mark")) {
                Task task = list.get(Integer.parseInt(formattedInput[1]) - 1);
                task.markDone();
                System.out.printf("Nice! I've marked this task as done: \n" +
                        "[%s] %s\n" +
                        "----------\n", task.getStatusIcon(), task.getDescription());
            } else if (formattedInput[0].equals("unmark")) {
                Task task = list.get(Integer.parseInt(formattedInput[1]) - 1);
                task.unMarkDone();
                System.out.printf("OK, I've marked this task as not done yet: \n" +
                        "[%s] %s\n" +
                        "----------\n", task.getStatusIcon(), task.getDescription());
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.printf("added: %s\n----------\n", input);
            }
        }
    }
}
