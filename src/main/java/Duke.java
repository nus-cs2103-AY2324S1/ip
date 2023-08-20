import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private boolean active = true;
    private List<String> list = new ArrayList<>();

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

    private void add(String msg) {
        switch (msg) {
            case "bye":
                exit();
                break;
            case "list":
                for (int index = 0; index < this.list.size(); index++) {
                    System.out.println(index + ". " + list.get(index));
                }
                break;
            default:
                this.list.add(msg);
                System.out.println("added: " + msg);
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);

        bot.greet();

        while (bot.active) {
            String input = sc.nextLine();
            bot.add(input);
        }
    }
}
