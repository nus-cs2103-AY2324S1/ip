import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HELLO_MESSAGE = "Hello, it's Spot!";
        final String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
        System.out.println(HELLO_MESSAGE);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();
        while (!command.equals("bye")) {
            String[] keywords = command.split(" ");
            if (keywords[0].equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    int position = i + 1;
                    System.out.println(position + ". " + tasks.get(i).toString());
                }
            } else if (keywords[0].equals("mark")) {
                int position = Integer.parseInt(command.substring(5));
                tasks.get(position - 1).markAsDone();
            } else if (keywords[0].equals("unmark")) {
                int position = Integer.parseInt(command.substring(7));
                tasks.get(position - 1).markAsNotDone();
            } else {
                tasks.add(new Task(command));
                System.out.println("added: " + command);
            }
            command = myObj.nextLine();
        }
        System.out.println(GOODBYE_MESSAGE);
    }
}
