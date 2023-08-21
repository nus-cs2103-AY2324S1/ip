import java.util.Scanner;

public class Chatbot {
    private TaskList taskList;
    private final String name;

    public Chatbot(String name) {
        this.taskList = new TaskList();
        this.name = name;
    }

    public void start() {
        giveIntro();
        Scanner scanner = new Scanner(System.in);
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
        switch (input) {
            case "bye":
                giveOutro();
                break;
            case "list":
                taskList.listTasks();
                break;
            default:
                taskList.addTask(input);
                break;
        }
    }
}
