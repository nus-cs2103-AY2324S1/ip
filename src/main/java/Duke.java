import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner jonBird = new Scanner(System.in);
        String input = "";
        System.out.println("Hello! I'm JonBird.\nWhat can I do for you?\n");
        while (true) {
            input = jonBird.nextLine();
            if (input.equalsIgnoreCase("bye")) break;
            System.out.println("\t" + input);
        }
        jonBird.close();
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
