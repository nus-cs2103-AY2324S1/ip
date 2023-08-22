import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chatty\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userinput = scanner.nextLine();
            if (userinput.equals("bye")) {
                break;
            } else {
                System.out.println(userinput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
