import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "          ▄              ▄\n"
                + "        ▌▒█           ▄▀▒▌\n"
                + "        ▌▒▒▀▄       ▄▀▒▒▒▐\n"
                + "      ▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐\n"
                + "     ▄▄▀▒▒▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐\n"
                + "   ▄▀▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀██▀▒▌\n"
                + "  ▐▒▒▒▄▄▄▒▒▒▒▒▒▒▒▒▒▒▒▒▀▄▒▒▌\n"
                + "  ▌▒▒▐▄█▀▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐\n"
                + " ▐▒▒▒▒▒▒▒▒▒▒▒▌██▀▒▒▒▒▒▒▒▒▀▄▌\n"
                + " ▌▒▀▄██▄▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌\n"
                + " ▌▀▐▄█▄█▌▄▒▀▒▒▒▒▒▒░░░░░░▒▒▒▐\n"
                + "▐▒▀▐▀▐▀▒▒▄▄▒▄▒▒▒▒▒░░░░░░▒▒▒▒▌\n"
                + "▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒░░░░░░▒▒▒▐\n"
                + " ▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒▒▒░░░░▒▒▒▒▌\n"
                + " ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▐\n"
                + "  ▀▄▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▄▒▒▒▒▌\n"
                + "    ▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀\n"
                + "   ▐▀▒▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀\n"
                + "  ▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▀▀\n";

        System.out.println(logo + "\nHi ! I'm DogeBot \nHow may I help you today ?\n");

        String[] tasks = new String[100];
        int tasksCounter = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            String s = in.nextLine();
            if (s.equals("bye")) break;

            if (s.equals("list")) {
                int i = 1;
                for (String task : tasks) {
                    if (task == null) break;
                    System.out.println(i++ + ". " + task);
                }
            } else {
                System.out.println("added: " + s); // echo command

                tasks[tasksCounter] = s; // store into 'tasks'
                tasksCounter++;
            }
        }

        System.out.println("Bye~ See you again");
    }
}
