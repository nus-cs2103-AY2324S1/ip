import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String chatBotName = "Benedit Cucumber Badge";
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");

        boolean dontTerminate = true;

        while (dontTerminate) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                dontTerminate = false;
            } else {
                System.out.println(input);
            }
        }



    }
}
