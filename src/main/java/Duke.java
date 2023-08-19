import java.util.Scanner;

public class Duke {

    public static void horiLine(){
        System.out.println("---------------------");
    }
    public static void greet() {
        System.out.println("---------------------");
        System.out.println("Hello! I'm AJbot\n" +
                "What can I do for you?");
        horiLine();
    }

    public static void askCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        if (command.toLowerCase().equals("bye")) {
            exit();
        } else {
            horiLine();
            System.out.println(command);
            horiLine();
            askCommand();
        }
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horiLine();
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        greet();
        askCommand();
    }
}
