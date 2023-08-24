import java.util.Scanner;
public class Bareum {
    static boolean active;
    static void reply(String reply) {
        String botName = "Bareum: ";
        String fullReply = botName + reply;
        System.out.println(fullReply);
    }

    public static void main(String[] args) {
        active = true;
        String introduction = "Hello! I'm Bareum! What can I do for you? ^^\n";
        String goodbye = "Bye! Hope to see you again soon!\n";

        reply(introduction);
        Scanner sc = new Scanner(System.in);
        while (active) {
            String command = sc.next();
            if (command.equals("bye")) {
                active = false;
            } else {
                reply(command);
            }
        }

        reply(goodbye);
    }
}
