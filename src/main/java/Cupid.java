import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Cupid {

    private static String saveFilePath = "cupid.txt";

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("____________________________________________________________");
        System.out.println("Initializing...");

        ArrayList<Task> taskList = null;

        try {
            Load load = new Load(saveFilePath);
            taskList = load.load();
        } catch (IOException e) {
            System.out.println("File not found. Terminating operation...");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Cupid.");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                break;
            }
            Parser parser = new Parser(input, taskList);
            parser.parse();

        }

        try {
            Save save = new Save(taskList);
            System.out.println("Save successful");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Save unsuccessful");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}
