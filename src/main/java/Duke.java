import java.util.Scanner;


public class Duke {
    static boolean exited = false;

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        System.exit(0);
    }
    public static void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        exited = false;
        String welcome = "Hello! I'm Eddie\n" +
                "What can I do for you?";

        System.out.println(welcome);

        while (!exited) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                Duke.exit();
            } else {
                Duke.echo(command);
            }
        }


    }
}
