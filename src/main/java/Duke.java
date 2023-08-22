import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "DukeKing";
        String welcome = "Hello! I'm " + name + "\nWhat can I do for you?";
        System.out.println(welcome + "\n");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        while (!string.equals("bye")) {
            System.out.println(string);
            string = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
