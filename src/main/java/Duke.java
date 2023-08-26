import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jarvis");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String command = userInput.nextLine();

        while (!command.equals("bye")) {
            switch (command) {
                case "list":
                    System.out.println("____________________________________________________________");
                    int counter = 0;
                    while (counter != list.size()) {
                        System.out.println(" " + counter + ". " + list.get(counter));
                        counter++;
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    list.add(command);
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + command);
                    System.out.println("____________________________________________________________");
            }
            command = userInput.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
