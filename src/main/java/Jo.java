import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Jo {
    public static void main(String[] args) {

        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");
        List<String> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i+1 + ". " + taskList.get(i));
                }
            } else {
                taskList.add(input);
                System.out.println("> added: " + input);
            }
            input = scanner.nextLine();
        }
        System.out.println("> Bye. Hope to see you again soon!");

    }
}
