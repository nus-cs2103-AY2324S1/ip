import extensions.Task;
import extensions.TaskList;
import java.util.Scanner;

public class Duke {

    private static TaskList list = new TaskList();

    private static String horizontalLine() {
        return "_____________________________________________________\n";
    }

    private static void intro() {
        String output = horizontalLine() +
                " ____  _   _   ____  _____  ____   _     ____  _____ \n" +
                "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n" +
                "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|  \n\n" +
                "Hello! I'm ChatALot.\n" +
                "What can I do for you?\n" +
                horizontalLine();
        System.out.println(output);
    }

    private static String processInput (String userInput) {
        String[] inputArr = userInput.split(" ");
        int index;
        Task task;
        String output;
        String desc;
        String restOfInput;
        String[] arr;

        if (inputArr.length == 0) {
            return "You have not entered anything. Please re-enter.";
        }

        String command = inputArr[0];
        switch(command) {
            case "list":
                return Duke.list.toString();
            case "mark":
                index = Integer.parseInt(inputArr[1]);
                task = Duke.list.markTaskAsDone(index);
                output = "Nice! I've marked this task as done:\n" +
                        "  " +
                        task;
                return output;
            case "unmark":
                index = Integer.parseInt(inputArr[1]);
                task = Duke.list.unmarkTask(index);
                output = "OK, I've marked this task as not done yet:\n" +
                        "  " +
                        task;
                return output;
            case "todo":
                desc = userInput.substring(5);
                task = Duke.list.addTodo(desc);
                output = "Got it. I've added this task:\n" +
                        "  " +
                        task +
                        "\nNow you have " +
                        Duke.list.getSize() +
                        " tasks in the list.";
                return output;
            case "deadline":
                restOfInput = userInput.substring(9);
                arr = restOfInput.split("/by");
                task = Duke.list.addDeadline(arr[0].trim(), arr[1].trim());
                output = "Got it. I've added this task:\n" +
                        "  " +
                        task +
                        "\nNow you have " +
                        Duke.list.getSize() +
                        " tasks in the list.";
                return output;
            case "event":
                restOfInput = userInput.substring(9);
                arr = restOfInput.split("/from|/to");
                task = Duke.list.addEvent(arr[0].trim(), arr[1].trim(), arr[2].trim());
                output = "Got it. I've added this task:\n" +
                        "  " +
                        task +
                        "\nNow you have " +
                        Duke.list.getSize() +
                        " tasks in the list.";
                return output;
            default:
                return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }

    }

    private static String displayOutput(String userInput) {
        String displayed = horizontalLine() +
                processInput(userInput) +
                "\n" +
                horizontalLine();
        System.out.println(displayed);
        return displayed;
    }

    private static String exit() {
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
            displayOutput(userInput);
            userInput = myObj.nextLine();
        }

        exit();
    }

}
