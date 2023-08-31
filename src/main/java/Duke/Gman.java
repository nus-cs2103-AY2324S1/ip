package Duke;

import java.util.Scanner;

public class Gman {

    private static TaskList taskList = new TaskList();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws GmanException {
        Ui.greet();
        String userInput = scanner.nextLine();
        Parser.readInput(userInput, taskList);
    }
}