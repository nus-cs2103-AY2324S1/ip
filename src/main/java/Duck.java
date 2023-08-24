import java.util.Scanner;
public class Duck {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        Duck.greet();
        String input = in.nextLine();
        while (!input.equals("bye")) {
            Duck.echo(input);
            input = in.nextLine();
        }
        Duck.bye();
        in.close();
    }

    private static void line() {
        System.out.println("____________________________________________________________");
    }

    private static void greet() {
        String greeting = "Hello! I'm Duck\n" + "What can I do for you?";

        line();
        System.out.println(greeting);
        line();
    }

    private static void bye() {
        String bye = "Bye. Hope to see you again soon!";

        line();
        System.out.println(bye);
        line();
    }

    private static void echo(String input) {
        line();
        System.out.println(input);
        line();
    }

}
