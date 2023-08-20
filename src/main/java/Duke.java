import java.util.Scanner;

public class Duke {

    private boolean active = true;

    private void greet() {
        System.out.println("Hello! I'm Aikent\n" + "What can I do for you?");
    }

    private void exit() {
        active = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo(String msg) {
        if (msg.equals("bye")) {
            exit();
        } else {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);

        bot.greet();

        while (bot.active) {
            String input = sc.nextLine();
            bot.echo(input);
        }
    }
}
