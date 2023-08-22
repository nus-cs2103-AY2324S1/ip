import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> todoList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I am Dukey.\n"
                + "What can I do for you?");

        while (true) {
            String response = scanner.nextLine();

            if (response.isEmpty()) {
                System.out.println("You entered nothing! Try again!");
            } else if (response.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (response.equalsIgnoreCase("list")) {
                int i;
                for (i = 1; i < todoList.size() + 1; i++) {
                    System.out.println(i + ". " + todoList.get(i - 1));
                }
            } else {
                    todoList.add(response);
                    System.out.println("added: " + response);
                }
            }
        scanner.close();
        }
    }


