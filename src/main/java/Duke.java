import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String logo =
                "    ,o888888o.    8 8888        8          .8.    8888888 8888888888 8888888 8888888888 `8.`8888.      ,8'\n" +
                        "   8888     `88.  8 8888        8         .888.         8 8888             8 8888        `8.`8888.    ,8'\n" +
                        ",8 8888       `8. 8 8888        8        :88888.        8 8888             8 8888         `8.`8888.  ,8'\n" +
                        "88 8888           8 8888        8       . `88888.       8 8888             8 8888          `8.`8888.,8'\n" +
                        "88 8888           8 8888        8      .8. `88888.      8 8888             8 8888           `8.`88888'\n" +
                        "88 8888           8 8888        8     .8`8. `88888.     8 8888             8 8888            `8. 8888\n" +
                        "88 8888           8 8888888888888    .8' `8. `88888.    8 8888             8 8888             `8 8888\n" +
                        "`8 8888       .8' 8 8888        8   .8'   `8. `88888.   8 8888             8 8888              8 8888\n" +
                        "   8888     ,88'  8 8888        8  .888888888. `88888.  8 8888             8 8888              8 8888\n" +
                        "    `8888888P'    8 8888        8 .8'       `8. `88888. 8 8888             8 8888              8 8888\n";



        System.out.println("------------------------------------------");
        System.out.println("Hi!! I am\n" + logo);
        System.out.println("What brings you here today?");
        System.out.println("------------------------------------------");

        while (true) {
            String input = scanner.nextLine(); // mark 3    // read bookD
            String[] tokens = input.split(" ", 2);

            if (tokens[0].equals("list")) {
                System.out.println("------------------------------------------");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println("------------------------------------------");
            } else if (tokens[0].equals("bye")) {
                System.out.println("Oh.. bye");
                break;
            } else if (tokens[0].equals("mark")) {
                int number = Integer.parseInt(tokens[1]);
                tasks.get(number - 1).setStatus(true);
            } else if (tokens[0].equals("unmark")) {
                int number = Integer.parseInt(tokens[1]);
                tasks.get(number - 1).setStatus(false);
            } else if (tokens[0].equals("todo")) {
                String todoText = input.substring(5);
                addTask(new Todo(todoText));
            } else if (tokens[0].equals("deadline")) {
                String deadlineText = tokens[1];
                String[] parts = deadlineText.split("/by", 2);
                String description = parts[0].trim();
                String date = parts[1].trim();
                addTask(new Deadline(description, date));
            } else if (tokens[0].equals("event")) {
                String eventText = tokens[1];
                String[] eventParts = eventText.split("/from", 2);
                String description = eventParts[0].trim();
                String[] fromToParts = eventParts[1].split("/to", 2);
                String fromDate = fromToParts[0].trim();
                String toTime = fromToParts[1].trim();
                addTask(new Event(description, fromDate, toTime));
            } else {
                tasks.add(new Task(input));
            }
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("------------------------------------------");
        System.out.printf("Got it. I've added this task:\n\t%s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
        System.out.println("------------------------------------------");
    }
}
