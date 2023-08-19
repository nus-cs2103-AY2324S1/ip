import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = new String[100];
        int index = 0;

        String logo =
                ".______     ______   .___________.\n" +
                        "|   _  \\   /  __  \\  |           |\n" +
                        "|  |_)  | |  |  |  | `---|  |----`\n" +
                        "|   _  <  |  |  |  |     |  |     \n" +
                        "|  |_)  | |  `--'  |     |  |     \n" +
                        "|______/   \\______/      |__|     \n";

        System.out.println("_________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");

        while (true) {
            String input = scanner.nextLine();
            System.out.println("_________________________________________");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + " " + data[i]);
                }
            } else {
                data[index] = input;
                index++;
                System.out.println("added: " + input);
            }
            System.out.println("_________________________________________");
        }
    }
}
