import extensions.Task;
import extensions.TaskList;
import java.util.Scanner;

public class ChatALot {

    private static TaskList list = new TaskList();

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

    public static String processInput(String userInput) {
        System.out.print(horizontalLine());

        String[] inputArr = userInput.split(" ");
        String output;

        if (inputArr.length == 0) {
            output = "You have not entered anything. Please re-enter.";
        } else if (userInput.toLowerCase().equals("list")) {
            output = ChatALot.list.toString();
        } else if (inputArr.length == 1) {
            Task task = ChatALot.list.addTask(userInput);
            output = "added: " + task;
        } else if (inputArr[0].equals("mark")) {
            int index = Integer.parseInt(inputArr[1]);
            Task task = ChatALot.list.markTaskAsDone(index);
            output = "Nice! I've marked this task as done:\n" +
                    "  " +
                    task;
        } else if (inputArr[0].equals("unmark")) {
            int index = Integer.parseInt(inputArr[1]);
            Task task = ChatALot.list.unmarkTask(index);
            output = "OK, I've marked this task as not done yet:\n" +
                    "  " +
                    task;
        } else {
            Task task = ChatALot.list.addTask(userInput);
            output = "added: " + task;
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
