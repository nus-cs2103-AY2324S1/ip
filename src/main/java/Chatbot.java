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
            default:
                taskList.addTask(input);
                break;
        }
    }
}
