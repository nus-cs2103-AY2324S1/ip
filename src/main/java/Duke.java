import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Initiate Scanner to handle user input
        Scanner scanner = new Scanner(System.in);

        // Introduction Message
        System.out.println(logo);
        System.out.println("Hello! I'm Chad");
        System.out.println("What can I do for you?");
        System.out.println();

        // Task List Storage
        ArrayList<String> taskList = new ArrayList<String>();

        // Saving User input into a list
        while (true) {
            String userInput = scanner.nextLine();

            // If user types bye, goodbye message is printed.
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again");
                break;
            }
            // If user types list, the list is printed.
            else if (userInput.equalsIgnoreCase("list")){
                for (int i = 0; i < taskList.size(); i++){
                    System.out.println(Integer.toString(i+1) + ". " + taskList.get(i));
                }
            }
            // If user types neither list nor bye, the task is added to the list and displayed.
            else {
                taskList.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
        scanner.close();
    }
}
