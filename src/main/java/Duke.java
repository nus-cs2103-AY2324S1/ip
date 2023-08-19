import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static String[] list = new String[100];
    static int count = 0;
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
        String command = scanner.next().toLowerCase();

        if (command.equals("bye")) {
            exit();
        } else if (command.equals("list")) {
            for (int i = 1;i <= count;i++) {
                System.out.println(i + "." + list[i - 1]);
            }
            horiLine();
            askCommand();
        } else {
            horiLine();
            list[count] = command;
            count += 1;
            System.out.println("added: " + command);
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
