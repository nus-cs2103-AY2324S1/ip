import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();

        String line = "\n_____________________________________________________\n";

        System.out.println(line);

        System.out.println("Hello! I'm Gerald_Bot\n" + "What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i) + "\n");
                }
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + input);
                lst.add(input);
                System.out.println(line);
            }
        }
    }
}