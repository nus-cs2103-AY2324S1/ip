import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\tHello! I'm Violet");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println(line);
            System.out.println("\t" + command);
            System.out.println(line);
            command = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
