import java.util.Scanner;
public class Duke {

    public static void start() {
        String intro = "Hi! This is your AI assistant LoyBoy!\n";
        String question = "What can I do for you today?";
        System.out.println(intro + question);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                String outro = "I wish you a pleasant day ahead, goodbye!\n";
                System.out.println(outro);
                break;
            }

            System.out.println("You just said '" + command + "'!");

        }

        scanner.close();
    }



    public static void main(String[] args) {
        Duke.start();


    }
}

