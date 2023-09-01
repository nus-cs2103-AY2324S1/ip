import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Cupid {

    private static String saveFilePath = "cupid.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList taskList = null;
        Storage storage = null;

        try {
            storage = new Storage(saveFilePath);
            taskList = storage.load();
        } catch (IOException e) {
            ui.fileNotFound();
            return;
        }

        if (taskList == null) {
            ui.fileNotFound();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ui.hello();

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                break;
            }
            Parser parser = new Parser(input, taskList);
            parser.parse();

        }

        try {
            storage.save(taskList);
            System.out.println("Save successful");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Save unsuccessful");
        }

        ui.goodbye();
    }

}
