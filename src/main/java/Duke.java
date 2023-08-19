import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ██████╗██╗  ██╗ █████╗ ████████╗████████╗██╗   ██╗\n"
                + "██╔════╝██║  ██║██╔══██╗╚══██╔══╝╚══██╔══╝╚██╗ ██╔╝\n"
                + "██║     ███████║███████║   ██║      ██║    ╚████╔╝ \n"
                + "██║     ██╔══██║██╔══██║   ██║      ██║     ╚██╔╝  \n"
                + "╚██████╗██║  ██║██║  ██║   ██║      ██║      ██║   \n"
                + " ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝      ╚═╝      ╚═╝   \n";


        System.out.println("------------------------------------------");
        System.out.println("Hi!! I am\n" + logo);
        System.out.println("What brings you here today?");
        System.out.println("------------------------------------------");

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine(); // mark 3    // read book
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
            } else {
                tasks.add(new Task(input));
            }
        }
    }
}
