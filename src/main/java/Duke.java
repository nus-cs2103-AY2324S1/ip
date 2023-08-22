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

        Scanner in = new Scanner(System.in);

        while (true) {
            String s = in.nextLine();
            if (s.equals("bye")) break;
            System.out.println(s);
        }

        System.out.println("Bye~ See you again");
    }
}
