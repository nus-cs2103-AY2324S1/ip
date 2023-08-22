import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " _ \n"
                + "| |\n"
                + "| |\n"
                + "| |___\n"
                + "|_____|\n";
        System.out.println("Greetings from\n" + logo);
        System.out.println("Hi! This is your intelligent friend L.\n" + "What can I do for you?");
        while(scanner.hasNextLine()) {
            String repeat = scanner.nextLine();
            if (repeat.contains("bye") || repeat.contains("Bye")) {
                System.out.println("Bye. See you next time!");
                break;
            } else {
                System.out.println(repeat);
            }
        }
    }
}
