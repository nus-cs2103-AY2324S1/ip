import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        horizontalLine();
        System.out.println("Hello! I'm Snow!");
        System.out.println("What can I do for you?");
        horizontalLine();

        Scanner userInput = new Scanner(System.in);  // Create a Scanner object
        String userOutput = userInput.nextLine();  // Read user input
        ArrayList<Task> inputList = new ArrayList<>();
        while (!userOutput.equals("bye")) {
            String[] splitOutput = userOutput.split(" ");
            if (userOutput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(i+1 + ". " + inputList.get(i).toString());
                }
            } else if (splitOutput[0].equals("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                Task markTask = inputList.get(Integer.parseInt(splitOutput[1])-1);
                markTask.mark();
                System.out.println(markTask.toString());
            } else if (splitOutput[0].equals("unmark")) {
                System.out.println(" OK, I've marked this task as not done yet:");
                Task unmarkTask = inputList.get(Integer.parseInt(splitOutput[1])-1);
                unmarkTask.unmark();
                System.out.println(unmarkTask.toString());
            } else {
                System.out.println("added: " + userOutput);  // Output user input
                inputList.add(new Task (userOutput));
            }
            userOutput = userInput.nextLine();  // Read user input

        }
        printBye();


    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
