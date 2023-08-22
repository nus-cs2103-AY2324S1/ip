import java.util.Scanner;

public class Ekud {
    public static final String GREETING = "Hello! I'm Ekud!\nWhat can I do for you?";
    public static final String FAREWELL = "Bye. Hope to see you again soon!";
    public static final String PROMPT = "> ";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(GREETING);

        String line;
        while (true) {
            System.out.print(PROMPT);
            line = scanner.nextLine().trim();
            if (line.equals("bye")) {
                break;
            } else {
                System.out.println(line);
            }
        }

        System.out.println(FAREWELL);

        scanner.close();
    }
}
