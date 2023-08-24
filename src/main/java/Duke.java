import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String space = "------------------------------------"; // for spacing purposes
        String name = "Adam's Bot"; // name of bot
        String[] toDoList = new String[100]; // list to store the items
        int counter = 0; // counter to keep track of pointer

        System.out.println(space);
        System.out.println("Hello! I'm "+ name);
        System.out.println("What can I do for you?");
        System.out.println(space);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        userInput = scanner.nextLine().trim();// remove trailing spaces and get use input

        //prompt for user input if input is not "bye"
        while(!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {

                System.out.println(space);

                //iterate through list to print items
                for (int i = 0; i < counter; i++ ) {
                    int currentNumber = i + 1;
                    System.out.println(currentNumber + ". "  + toDoList[i]);
                }
                System.out.println(space);

            } else {

                //add item into list
                toDoList[counter] = userInput;
                counter++;

                System.out.println(space);
                System.out.println(userInput);
                System.out.println(space);

            }
            userInput = scanner.nextLine().trim();
        }

        System.out.println(space);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(space);
    }
}
