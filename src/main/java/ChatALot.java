import java.util.Scanner;

public class ChatALot {

    private static ItemList list = new ItemList();

    public static String horizontalLine() {
        return "_____________________________________________________\n";
    }

    public static void intro() {
        String output = horizontalLine() +
                " ____  _   _   ____  _____  ____   _     ____  _____ \n" +
                "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n" +
                "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|  \n\n" +
                "Hello! I'm ChatALot.\n" +
                "What can I do for you?\n" +
                horizontalLine();
        System.out.println(output);
    }

    public static String echo(String userInput) {
        String output = "You have entered: " +
                userInput;
        return output;
    }

    public static String processInput(String userInput) {
        System.out.print(horizontalLine());

        String output;
        if (userInput.equals("")) {
            output = "You have not entered anything. Please re-enter.";
        } else if (userInput.toLowerCase().equals("list")) {
            output = ChatALot.list.toString();
        } else {
            Item item = ChatALot.list.addItem(userInput);
            output = "added: " + item;
        }
        System.out.println(output);

        System.out.println(horizontalLine());
        return output;
    }

    public static String exit() {
        String outro = horizontalLine() +
                "Bye. Hope to see you again soon!\n" +
                horizontalLine();
        System.out.println(outro);
        return outro;
    }

    public static void main(String[] args) {
        intro();

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        String bye = "bye";

        while (!userInput.toLowerCase().equals(bye)) {
            processInput(userInput);
            userInput = myObj.nextLine();
        }

        exit();
    }

}
