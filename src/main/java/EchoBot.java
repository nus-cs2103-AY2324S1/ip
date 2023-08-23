import java.util.Scanner;
public class EchoBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "   _____________________________________________\n";
        //Array to store the tasks
        String[] tasks = new String[100];
        int numOfTask = 0;

        System.out.println(horizontalLine + "    Hello! I'm EchoBot\n" + logo);
        System.out.println("    What can I do for you?\n" + horizontalLine);

        while(true) {
            // Read the user input
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(horizontalLine + "    Bye. Hope to see you again soon!\n" + horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);

                for (int i = 0; i < numOfTask; i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }

                System.out.println(horizontalLine);
            } else {
                //Store the task inputted by user
                tasks[numOfTask] = userInput;
                numOfTask++;

                System.out.println(horizontalLine + "    added: " + userInput + "\n" + horizontalLine);
            }
        }
    }
}
