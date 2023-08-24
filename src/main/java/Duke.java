import java.util.Scanner;
public class Duke {
    private static boolean isDone = false;

    public static void main(String[] args) {
        String chatbotName = "Albatross\n";
        System.out.print("Hello! I'm " + chatbotName);
        System.out.println("What can I do for you?");

        // Task list to store user responses
        TaskList taskList = new TaskList();

        // Scanner to read user response
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a command");

        // Scanner reads responses and adds to task list
        String userResponse = scanner.nextLine();

        while (!isDone) {
            // Interpret the commands returned from the Parser
            // and execute the corresponding actions
            try {
                String[] strSegments = Parser.parse(userResponse);
                String command = strSegments[0];
                if (command.equals("bye")) {
                    isDone = false;
                    break;
                } else if (command.equals("list")) {
                    taskList.printTaskList();
                } else if (command.equals("todo")) {
                    Todo todo = new Todo(strSegments[1]);
                    taskList.addTask(todo);
                } else if (command.equals("deadline")) {
                    Deadline deadline = new Deadline(strSegments[1], strSegments[2]);
                    taskList.addTask(deadline);
                } else if (command.equals("event")) {
                    Event event = new Event(strSegments[1], strSegments[2], strSegments[3]);
                    taskList.addTask(event);
                } else if (command.equals("mark")) {
                    taskList.markDone((int) Double.parseDouble(strSegments[1]));
                } else if (command.equals("unmark")) {
                    taskList.markNotDone((int) Double.parseDouble(strSegments[1]));
                } else if (command.equals("delete")) {
                    taskList.delete((int) Double.parseDouble(strSegments[1]));
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            userResponse = scanner.nextLine();
        }

        System.out.println("Bye! Hope to see you again soon!");
        scanner.close();
    }
}
