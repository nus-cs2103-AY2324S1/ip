import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> taskList = new ArrayList<>();

        Storage.loadTasksFromFile(taskList);

        Ui.printIntroMsg();

        input = scanner.nextLine();

        while (!input.equals("bye")) {
            Ui.printSeparator();
            Parser.parse(input, taskList);
            Storage.updateTasksFile(taskList);
            Ui.printSeparator();
            input = scanner.nextLine();
        }

        scanner.close();
        Ui.printOutroMsg();
    }
    
}