import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        int listCount = 1;
        String[] action = new String[100];
        String logo = "____________________________________________________________\n Hello! I'm bob \n What can I do for you?\n ____________________________________________________________";
        System.out.println(logo);
        while (true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon! \n ____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(" " + listCount + ". " + action[i]);
                    listCount ++;
                }
            } else {
                action[counter] = input;
                counter++;
                System.out.println(" added: " + input);
            }

            System.out.println("____________________________________________________________");
        }
    }
}
