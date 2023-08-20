import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HELLO_MESSAGE = "Hello, it's Spot!";
        final String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
        final String LIST_MESSAGE = "Spot's got a list of your tasks, here!";
        System.out.println(HELLO_MESSAGE);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.equals("bye")) {
                String[] commands = input.split(" ");
                if (commands[0].equals("list")) {
                    System.out.println(LIST_MESSAGE);
                    for (int i = 0; i < tasks.size(); i++) {
                        int position = i + 1;
                        System.out.println(position + ". " + tasks.get(i).toString());
                    }
                } else if (commands[0].equals("mark")) {
                    int position = Integer.parseInt(commands[1]);
                    tasks.get(position - 1).markAsDone();
                } else if (commands[0].equals("unmark")) {
                    int position = Integer.parseInt(commands[1]);
                    tasks.get(position - 1).markAsNotDone();
                } else {
                    if (commands[0].equals("todo")) {
                        ToDo newTask = new ToDo(input.substring(4));
                        tasks.add(newTask);
                        System.out.println("Spot will add this new task to your list: "
                                + "\n" + "  " + newTask.toString());
                        System.out.println("Tasks in list: " + tasks.size());
                    } else if (commands[0].equals("deadline")) {
                        String[] keywords = input.substring(8).split("/by");
                        Deadline newTask = new Deadline(keywords[0], keywords[1]);
                        tasks.add(newTask);
                        System.out.println("Spot will add this new task to your list: "
                                + "\n" + "  " + newTask.toString());
                        System.out.println("Tasks in list: " + tasks.size());
                    } else if (commands[0].equals("event")) {
                        String[] keywords = input.substring(5).split("/from|/to");
                        Event newTask = new Event(keywords[0], keywords[1], keywords[2]);
                        tasks.add(newTask);
                        System.out.println("Spot will add this new task to your list: "
                                + "\n" + "  " + newTask.toString());
                        System.out.println("Tasks in list: " + tasks.size());
                    }
                }
            } else {
                break;
            }
        }
        System.out.println(GOODBYE_MESSAGE);
    }
}
