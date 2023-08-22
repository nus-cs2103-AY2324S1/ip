import java.util.Scanner;
public class Duke {
    private static final String name = "Faiz";
    private static final String line = "__________________________________\n";
    public static void main(String[] args) {

        greet();
        startChat();
    }

    private static void greet() {
        String greeting = line + "Hello! I'm " + name + "\n" + "What can I do for you?" + "\n" + line;
        System.out.println(greeting);
    }

    private static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput= scanner.nextLine();
        String goodbye = line + "Bye. Hope to see you again soon!" + "\n" + line;

        while (!userInput.equals("bye")){
            echo(userInput);
            userInput = scanner.nextLine();
        }
        System.out.println(goodbye);
        scanner.close();
    }

    private static void echo(String command) {
        String echo = line + command + "\n" + line;
        System.out.println(echo);
    }
}
