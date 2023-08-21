import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //this is the new name for my chatbot
        String name = "dukey";
        //these are the lines
        String lines = "        ____________________________________________________________";
        //this will be the greeting
        String greeting = "        Hello! I'm " + name + "\n        What can I do for you?\n";
        //this will be goodbye
        String goodbye = "        Bye. Hope to see you again soon! :D\n";
        //scanner to get user input
        Scanner sc = new Scanner(System.in);
        //this is what the user typed in
        String userCommand;
        //fixed size array to store the items
        ArrayList<Task> toDo = new ArrayList<>();

        //initial greeting
        System.out.println(lines + "\n" + greeting + lines);
        //getting user input
        userCommand = sc.nextLine();
        //echoes the user input when the command is not "bye"
        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println(lines);
                for (int i = 0; i < toDo.size(); i++) {
                    System.out.println("        " + Integer.toString(i + 1) + "." + toDo.get(i));
                }
                System.out.println(lines);
            } else {
                System.out.println(lines + "\n        added: " + userCommand + "\n" + lines);
                toDo.add(userCommand);
            }

            userCommand = sc.nextLine();
        }
        //when the command is "bye", exit
        System.out.println(lines + "\n" + goodbye + lines);
    }
}
