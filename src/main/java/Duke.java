import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        String lineSpacer = "________________________________________";
        String logo = "   / \\__\n"
                + "  (    @\\___\n"
                + "  /          O\n"
                + " /   (_____/\n"
                + "/_____/   \n";

        System.out.println(lineSpacer);
        System.out.println("Hello I'm Barkley\n" + logo);
        System.out.println("Howl can I help you?");
        System.out.println(lineSpacer);

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(lineSpacer);
                System.out.println("Goodbye! Have a paw-some day :-)");
                System.out.println(lineSpacer);
                break;
            } else if (userInput.equals("list")) {
                System.out.println(lineSpacer);
                for (int i = 0; i < taskList.size(); i++ ) {
                    int index = i + 1;
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println(lineSpacer);
            } else {
                System.out.println(lineSpacer);
                taskList.add(userInput);
                System.out.println("added: " + userInput);
                System.out.println(lineSpacer);
            }
        }

    }
}
