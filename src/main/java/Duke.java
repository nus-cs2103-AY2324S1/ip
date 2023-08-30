import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage("./data/duke.txt");
        TaskList taskList = new TaskList(storage);

        System.out.println("Hello! I'm Meep.\nWhat can I do for you?");

        while (true) {
            try {
                String userCommand = scanner.nextLine();

                if (userCommand.trim().equalsIgnoreCase("bye")) {
                    System.out.println("Bye! Hope to see you again soon.");
                    break;
                }

                String response = Parser.parseCommand(userCommand, taskList);
                System.out.println(response);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
