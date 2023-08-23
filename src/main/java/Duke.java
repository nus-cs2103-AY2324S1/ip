import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "_".repeat(60);
        Scanner scanner = new Scanner(System.in);

        System.out.println(line + "\nHello! I'm Dommi\nWhat can I do for you?\n" + line);
        while (true) {
            String userInput = scanner.nextLine();  // Read user input
            if (userInput.equals("bye")) {
                System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
                break;
            }
            System.out.println(line + "\n" + userInput + "\n" + line);
        }
    }
}
