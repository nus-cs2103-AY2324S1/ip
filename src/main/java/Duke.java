import java.util.Scanner;

public class Duke {
    private String name = "KAM_BOT";
    private String bye = "bye";
    private static final String line = "    ————————————————————————————————————————————————————";

    public Duke(String name) {
        this.name = name;
    }

    public void exit() {
        System.out.println(Duke.line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(Duke.line);
    }

    public void greet() {
        System.out.println(Duke.line);
        System.out.println("    Hello! I'm " + this.name);
        System.out.println("    What can I do for you?");
        System.out.println(Duke.line);
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (input.equals(this.bye)) {
            scanner.close();
            exit();
        } else {
            System.out.println(Duke.line);
            System.out.println("    " + input);
            System.out.println(Duke.line);
            echo();
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke("Kam_BOT");
        bot.greet();
        bot.echo();
    }
}
