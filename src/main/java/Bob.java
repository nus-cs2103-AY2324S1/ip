import java.util.*;
import java.io.IOException;

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bob(String dataPath) {
        storage = new Storage(dataPath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        new Parser(ui, tasks, storage).parse(sc);
        ui.exit();
    }

    public static void main(String[] args) {
        new Bob("./data/bob.txt").run();
    }
}
