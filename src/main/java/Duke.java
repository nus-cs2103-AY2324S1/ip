import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Obi-wan Kenobi";
        String line = "_____________________________________";
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Hello There! I am " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            command = scanner.nextLine();
            if(command.equals("bye")) {
                break;
            }
            System.out.println("\t" + command);
        }

        System.out.println("Bye. May the force be with you!");
    }

}
