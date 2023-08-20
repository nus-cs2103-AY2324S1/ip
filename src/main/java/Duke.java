import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t Hello! I'm Violet");
        System.out.println("\t What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println(line);
            if (command.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t " + (i + 1) + ". " + list.get(i));
                }
            } else {
                list.add(command);
                System.out.println("\t added: " + command);
            }
            System.out.println(line);
            command = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
