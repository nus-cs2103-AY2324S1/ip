import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String bye = " Bye. Hope to see you again soon!";
        String message = "____________________________________________________________\n"
                + " Hello! I'm ChatBot\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        String[] tasksList = new String[100];
        int count = 0;

        System.out.println(message);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            System.out.println("----------------------------------------------");
            if(input.equalsIgnoreCase("bye")) {
                System.out.println("----------------------------------------------");
                System.out.println(bye);
                System.out.println("----------------------------------------------");
                break;

            } else if(input.equalsIgnoreCase("list")) {
                System.out.println("----------------------------------------------");
                for(int i = 0; i < count; i++) {
                    if(count == 0) {
                        System.out.println("You currently have no tasks!");
                    } else {
                        System.out.println((i + 1) + ". " + tasksList[i]);
                    }
                }
                System.out.println("----------------------------------------------");

            } else {
                System.out.println("\t added: " + input);
                tasksList[count] = input;
                count++;
                System.out.println("----------------------------------------------");
            }
        }
    }
}
