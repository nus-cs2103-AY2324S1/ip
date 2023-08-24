import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Remi";
        String line = "______________________";
        System.out.println(line);
        System.out.println("Hey! I'm " + name + "!");
        System.out.println("What can I do for you?");
        System.out.println(line);

        boolean running = true;
        while (running) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye!");
                System.out.println(line);
                running = false;
            } else {
                System.out.println(line);
                System.out.println(command);
                System.out.println(line);
            }
        }
    }
}
