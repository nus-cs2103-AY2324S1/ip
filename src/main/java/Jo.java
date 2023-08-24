import java.util.Scanner;
public class Jo {
    public static void main(String[] args) {

        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println("> " + input);
            input = scanner.nextLine();
        }
        System.out.println("> Bye. Hope to see you again soon!");

    }
}
