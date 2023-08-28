import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            storage.load();
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();

        boolean isBotRunning = true;
        while (isBotRunning) {
            try {
                String scannerIn = scanner.nextLine();
                isBotRunning = Parser.processCommand(scannerIn, tasks);
                tasks.write();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
