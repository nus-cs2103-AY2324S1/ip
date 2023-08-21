import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int currIndex = 0;

        String greetings = "Hello! I'm Botty!\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!\n";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);
        // Only exit when user types the command bye
        while (true) {
            String command = sc.nextLine();
            // Split string into words
            String[] words = command.split(" ");
            if (words.length == 1 && command.equals("bye")) {
                System.out.println(farewell);
                sc.close();
                break;
                // Display the stored commands
            } else if (words.length == 1 && command.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < currIndex; i++) {
                    System.out.println(i + 1 + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description + "\n");
                }
                // Mark task as done
            } else if (words.length == 2 && words[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:\n");
                int index = Integer.parseInt(words[1]);
                Task currTask = tasks[index - 1];
                currTask.markAsDone();
                System.out.println(" [X] " + currTask.description + "\n");
                // Mark task as not done
            } else if (words.length == 2 && words[0].equals("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:\n");
                int index = Integer.parseInt(words[1]);
                Task currTask = tasks[index - 1];
                currTask.markAsNotDone();
                System.out.println(" [ ] " + currTask.description + "\n");
                // Echo the command
            } else {
                tasks[currIndex] = new Task(command);
                currIndex++;
                System.out.println("added: " + command + "\n");
            }
        }
    }
}
