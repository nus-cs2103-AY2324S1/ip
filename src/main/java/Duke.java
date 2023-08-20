import java.util.Scanner;
public class Duke {



    public static void start() {
        String intro = "Hi! This is your AI assistant LoyBoy!\n";
        String question = "What can I do for you today?";
        System.out.println(intro + question);
        Scanner scanner = new Scanner(System.in);
        String[] items = new String[100];
        int position = 0;

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                String outro = "I wish you a pleasant day ahead, goodbye!\n";
                System.out.println(outro);
                break;
            } else if (command.equalsIgnoreCase("list")) {
                for (int item = 0; items[item] != null; item++)  {
                    System.out.println(item + 1 + ". " + items[item]);
                }
            } else {
                items[position] = command;
                position++;
                System.out.println("You added '" + command + "' to the list!");
            }

        }

        scanner.close();
    }



    public static void main(String[] args) {
        Duke.start();


    }
}

