import java.util.Scanner;

public class Duke {

    private static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = sc.nextLine();
        }

        sc.close();

        exit();
    }
}
