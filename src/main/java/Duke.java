import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String line = "    ————————————————————————————————————————————————————";
    private String name;
    private List<String> list;

    public Duke(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public void exit() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public void greet() {
        System.out.println(Duke.line);
        System.out.println("     Hello! I'm " + this.name);
        System.out.println("     What can I do for you?");
        System.out.println(Duke.line);
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(Duke.line);
        if (input.equals("bye")) {
            scanner.close();
            exit();
            System.out.println(Duke.line);
            return;
        } else if (input.equals("list")) {
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + this.list.get(i));
            }
        } else {
            this.list.add(input);
            System.out.println("     added: " + input);
        }
        System.out.println(Duke.line);
        echo();
    }

    public static void main(String[] args) {
        Duke bot = new Duke("Kam_BOT");
        bot.greet();
        bot.echo();
    }
}
