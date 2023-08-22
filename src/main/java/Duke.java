import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "_".repeat(40);
        String[] list = new String[100];
        String name = "DukeKing";
        String welcome = "\nHello! I'm " + name + "\nWhat can I do for you?";
        System.out.println(line + welcome + "\n" + line);
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        int taskInArray = 0;
        while (true) {
            if (string.equals("bye")) {
                break;
            } else if (string.equals("list")) {
                System.out.println(line);
                for (int length = 1; length < taskInArray + 1; length += 1) {
                    System.out.println(length + ". " + list[length]);
                }
            } else {
                System.out.println(line + "\nadded: " + string);
                taskInArray += 1;
                list[taskInArray] = string;
            }
            System.out.println(line);
            string = sc.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!" + "\n" + line);
    }
}
