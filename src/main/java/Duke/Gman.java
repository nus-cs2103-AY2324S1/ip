package duke;

import java.util.Scanner;

public class Gman {

    private static TaskList taskList = new TaskList();
    private static Scanner scanner = new Scanner(System.in);
    private static final String exitWord = "bye";
    public static void main(String[] args) {
        Ui.greet();
        String userInput = scanner.nextLine();
        while(!userInput.equals(exitWord)) {
            Parser.readInput(userInput, taskList);
            userInput = scanner.nextLine();
        }
        Parser.readInput(userInput, taskList); //do one last time for bye.

    }

    public String getResponse(String input) {
        return Parser.readInput(input, taskList);
    }

}