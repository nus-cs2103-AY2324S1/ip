import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }

    private static void greet() {
        System.out.println("_______________________________\n" +
                "Hello. I am Luxion. \n" +
                "What can I do for you?\n" +
                "_______________________________\n");
        Scanner scanObj = new Scanner(System.in);
        String command = scanObj.nextLine();
        nextCommand(command);
    }

    private static void exit() {
        System.out.println("_______________________________\n" +
                "Bye. See you soon.\n" +
                "_______________________________\n");
    }

    private static void echo(String command) {
        System.out.println("_______________________________\n" +
                command+
                "\n_______________________________\n");
    }

    private static void nextCommand(String command) {
        String[] commandArraycommand = command.split(" ");
        switch(commandArraycommand[0]) {

            case ("bye"):
                exit();
                break;

            case ("list"):
                //list();
                break;

            default:
                echo(command);
        }
    }
}
