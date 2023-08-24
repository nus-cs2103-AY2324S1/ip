import java.util.Scanner;


public class Duke {

    //function to retrieve string that the user input
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        //to mimic chatBot
        System.out.print(" ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String horizontalLine = "   ---------------------------------------------------";
        String intro = "    Hello! I'm iPbot \n    What can I do for you?";
        String outro = "    Bye. Hope to see you again soon!";

        System.out.println(horizontalLine);
        System.out.println(intro);
        System.out.println(horizontalLine);

        while (true) {
            String input = getInput();
            if (input.equals("bye")) {
                break;
            }

            System.out.println(horizontalLine);
            System.out.println("     " + input);
            System.out.println(horizontalLine);

        }

        System.out.println(horizontalLine);
        System.out.println(outro);
        System.out.println(horizontalLine);
    }
}