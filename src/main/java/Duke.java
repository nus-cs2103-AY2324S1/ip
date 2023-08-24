import java.util.Scanner;

public class Duke {

    public static void talk(String str) {
        String line = "\u2500".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        String[] items = new String[100];
        int count = 0;

        Scanner sc = new Scanner(System.in);

        talk(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                String list = "";
                for (int i = 0; i < count; i++) {
                    list += "  " + (i + 1) + ". " + items[i] + "\n";
                }
                talk(list);
            } else {
                items[count] = input;
                count++;
                talk("added: " + input);
            }
        }
        talk(goodbye);
    }
}
