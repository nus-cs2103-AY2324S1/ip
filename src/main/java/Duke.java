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

        String[] lst = new String[100];
        int total = 0;

        boolean running = true;
        while (running) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye!");
                System.out.println(line);
                running = false;
            } else if (command.equals("list")) {
                System.out.println(line);
                for (int count = 1; count <= total; count++) {
                    System.out.println(count + ". " + lst[count - 1]);
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + command);
                System.out.println(line);
                lst[total] = command;
                total++;
            }
        }
    }
}
