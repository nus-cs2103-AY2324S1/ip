import java.util.Scanner;

public class Duke {
    private static void echo(String input) {
        System.out.println(input);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BaekBot. \nWhat can I do for you?");
        while (true) {
            String echoInput = scanner.nextLine();
            if (echoInput.equals("bye")) {
                break;
            } else {
                echo(echoInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
