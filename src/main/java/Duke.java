import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "_".repeat(60);
        String[] tasks = new String[100];
        int noTasks = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println(line + "\nHello! I'm Dommi\nWhat can I do for you?\n" + line);
        while (true) {
            String userInput = scanner.nextLine();  // Read user input
            System.out.println(line);
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < noTasks; i++)
                    System.out.println((i + 1) + ". " + tasks[i]);
                System.out.println(line);
            } else {
                tasks[noTasks] = userInput;
                noTasks += 1;
                System.out.println("added: " + userInput + "\n" + line);
            }
        }
    }
}
