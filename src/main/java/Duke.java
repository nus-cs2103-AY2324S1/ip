import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        TaskList tasks= new TaskList();
        Storage storage = new Storage("data/duke.txt", tasks);
        boolean isRunning = true;
        Parser parser = new Parser();

        try {
            storage.saveTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        ui.printWelcomeMessage();
        while (isRunning) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                ui.printGoodByeMessage();
                break;
            }
            parser.addToList(input, storage, tasks);
        }
    }
}