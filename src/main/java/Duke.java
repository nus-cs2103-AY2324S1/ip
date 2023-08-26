import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Johnnythesnake";
        System.out.println("Hello I'm " + name + "\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a command: ");

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                Exit exit = new Exit();
                System.out.println(exit.exitMessage());
                break;
            } else {
                System.out.println(command);

            }
        }
    }
}
