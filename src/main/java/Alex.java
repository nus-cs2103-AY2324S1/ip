import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        String greeting = "_____________________________________________________________\n"
                + "Hello! I'm your personal task assistant, Alex\n"
                + "What can I do for you today?\n\n"
                + "_____________________________________________________________\n";

        String bye = "_____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "_____________________________________________________________\n";

        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(bye);
                break;
            } else {
                System.out.println("_____________________________________________________________\n"
                        + userInput
                        + "\n"
                        + "_____________________________________________________________\n"
                );
            }
        }
    }
}
