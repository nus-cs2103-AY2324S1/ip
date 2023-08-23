import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ___  __    ________  ________  ________   ________  ________      \r\n" + //
                "|\\  \\|\\  \\ |\\   __  \\|\\   __  \\|\\   ___  \\|\\   __  \\|\\   ____\\     \r\n" + //
                "\\ \\  \\/  /|\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\___|_    \r\n" + //
                " \\ \\   ___  \\ \\   _  _\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ \\_____  \\   \r\n" + //
                "  \\ \\  \\\\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\|____|\\  \\  \r\n" + //
                "   \\ \\__\\\\ \\__\\ \\__\\\\ _\\\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\____\\_\\  \\ \r\n" + //
                "    \\|__| \\|__|\\|__|\\|__|\\|_______|\\|__| \\|__|\\|_______|\\_________\\\r\n" + //
                "                                                       \\|_________|";

        String divider = "\n____________________________________________________________\n";

        Scanner sc = new Scanner(System.in);

        String command = "";

        System.out.println("Greetings, puny mortal. This is \n" + logo
                + "\nThe Lord of Time. \nWhat inconsequential errand do you seek to accomplish with my immense powers?");
        System.out.println(divider);

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                System.out.println(divider);
                System.out.println(command);
                System.out.println(divider);
            }
        }

        System.out.println(divider);
        System.out.println("Is that all? I have better things to do than to listen to lesser beings. Farewell.");
        System.out.println(divider);
        sc.close();
    }
}
