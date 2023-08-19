import java.util.Scanner;

public class Duke {

    public static Scanner scannerObj = new Scanner(System.in);
    public static String userInput;

    public static void main(String[] args) {
        printIntro();
        promptInput();

        do {
            if (userInput.equals("list")) {
                Task.listTasks();
            } else {
                Task newTask = new Task(userInput);
                Task.addTask(newTask);
            }
            promptInput();
        } while (!userInput.equals("bye"));

        printEnd();
    }
    private static void printIntro() {
        printLine();
        System.out.println("Hello! I'm Roe!\n" + "What can I do for you?\n");
        printLine();
    }

    private static void printEnd() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void echoString(String input) {
        printLine();
        System.out.println(input + "\n");
        printLine();
    }

    private static String promptInput() {
        System.out.println();
        userInput = scannerObj.nextLine();
        System.out.println();
        return userInput;
    }

    public static void printLine() {
        int charCount = 50;
        for (int i = 0; i < charCount; i++) {
            System.out.print("─");
        }
        System.out.print("\n");
    }
}
