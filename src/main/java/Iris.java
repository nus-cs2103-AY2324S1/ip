
import java.util.Scanner;

public class Iris {
    private final Storage taskStorage;
    private final Parser commandParser;
    private static ToDoList toDoList;

    public Iris(String filePath) {
        commandParser = new Parser();
        taskStorage = new Storage(filePath);
        toDoList = new ToDoList(taskStorage.loadTask());
    }

    public void run() {
        Ui.welcomeMsg();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                commandParser.parseCommand(taskStorage, toDoList, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Out of Bounds Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Iris("iris.txt").run();
    }
}
