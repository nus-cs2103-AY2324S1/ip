import java.util.Scanner;
public class Potato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------\n" +
                "Hello! I'm Potato\n" + "What can I do for you?\n" +
                "-----------------------------------------\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("-----------------------------------------\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "-----------------------------------------\n");
                break;
            } else {
                System.out.println("-----------------------------------------\n" +
                        input + "\n" +
                        "-----------------------------------------\n");
                continue;
            }
        }
    }
}
