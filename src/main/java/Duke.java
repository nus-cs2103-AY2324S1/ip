import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //this is the new name for my chatbot
        String name = "dukey";

        //these are the lines
        String lines = "        ____________________________________________________________";

        //this will be the greeting
        String greeting = "     Hello! I'm " + name + "\nWhat can I do for you?\n";

        //this will be goodbye
        String goodbye = "      Bye. Hope to see you again soon!";

        Scanner sc = new Scanner(System.in);

        //this is what the user typed in
        String userCommand = sc.nextLine();


    }
}
