import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<Task> list = new ArrayList<Task>();

        System.out.println("Greetings, puny mortal. This is \n" + logo
                + "\nThe Lord of Time. \nWhat foolish errand do you seek to accomplish with my immense powers?");
        System.out.println(divider);

        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(divider);
                System.out.println(
                        "You have somehow found the audacity to conjure up this laughable list of inconsequential endeavours:\n");
                System.out.println(divider);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(divider);
            } else if (command.startsWith("mark")) {
                System.out.println(divider);
                int taskNum = Integer.parseInt(command.substring(5));
                list.get(taskNum - 1).changeStatus(true);
                System.out.println(
                        "Astonishingly enough, you have managed to triumph over this mind-bogglingly simple task:\n");
                System.out.println(list.get(taskNum - 1).toString());
                System.out.println(divider);
            } else if (command.startsWith("unmark")) {
                System.out.println(divider);
                int taskNum = Integer.parseInt(command.substring(7));
                list.get(taskNum - 1).changeStatus(false);
                System.out.println(
                        "In a stunning twist, this task remains untouched by the hands of progress:\n");
                System.out.println(list.get(taskNum - 1).toString());
                System.out.println(divider);
            } else {
                System.out.println(divider);
                System.out.println("added: " + command);
                list.add(new Task(command));
                System.out.println(divider);
            }
        }

        System.out.println(divider);
        System.out.println("Is that all? I have better things to do than to listen to lesser beings. Farewell.");
        System.out.println(divider);
        sc.close();
    }
}
