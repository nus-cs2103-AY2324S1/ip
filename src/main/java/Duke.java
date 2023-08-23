import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //greeting
        System.out.println("Hello! I'm Sara");
        System.out.println("What can I do for you?");

        //create scanner to read user inputs
        Scanner scan = new Scanner(System.in);

        //create empty list to store stuff to do
        List<String> toDoList = new ArrayList<>();

        //processing user commands
        while(true) {
            //read user input
            String userInput = scan.nextLine();

            //check for exit command first
            if(userInput.equalsIgnoreCase("bye")){
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                //display list
                System.out.println("List:");
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println(((i + 1) + ". " + toDoList.get(i)));
                }
            } else {
                //store input into list
                toDoList.add(userInput);
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
