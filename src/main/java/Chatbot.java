import java.util.Scanner;

public class Chatbot {
    private TaskList taskList;
    private final String name;

    public Chatbot(String name) {
        this.taskList = new TaskList();
        this.name = name;
    }

    public void start(Scanner scanner) {
        giveIntro();
        String userInput;

        do {
            userInput = scanner.nextLine();
            printHorizontalLine();
            processInput(userInput);
            printHorizontalLine();
        } while (!userInput.equals("bye"));
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void giveIntro() {
        printHorizontalLine();
        System.out.println("Hello " + this.name + "! I'm Sam, your personal AI chatbot, ready to serve you today");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void giveOutro() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("Remember, the universe is vast, but I'm always here for you :D");
    }

    public void processInput(String input) {
        try {
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            switch (command) {
                case "bye":
                    giveOutro();
                    break;
                case "list":
                    taskList.listTasks();
                    break;
                case "mark":
                    int index = Integer.parseInt(parts[1]);
                    taskList.markTaskAsDone(index);
                    break;
                case "unmark":
                    int unmarkIndex = Integer.parseInt(parts[1]);
                    taskList.markTaskAsNotDone(unmarkIndex);
                    break;
                case "todo":
                    if (parts.length <= 1 || parts[1].isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    taskList.addTask(new ToDo(parts[1]));
                    break;
                case "deadline":
                    if (parts.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadlineParts = parts[1].split(" /by ");
                    if (deadlineParts.length != 2) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid deadline description and date.");
                    }
                    taskList.addTask(new Deadline(deadlineParts[0], deadlineParts[1]));
                    break;
                case "event":
                    if (parts.length <= 1) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] eventParts = parts[1].split(" /from | /to ");
                    if (eventParts.length != 3) {
                        throw new DukeException("☹ OOPS!!! Please provide a valid event description, start date, and end date.");
                    }
                    taskList.addTask(new Event(eventParts[0], eventParts[1], eventParts[2]));
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
