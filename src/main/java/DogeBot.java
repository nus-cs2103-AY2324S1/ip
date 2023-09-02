import java.io.File;
import java.io.IOException;

public class DogeBot {
    private static final String HOME = System.getProperty("user.home"); // get relative path
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "OneDrive", "Desktop", "iP",
        "src", "main");
    private static File file;

    private Ui ui;
    private Parser userInput;
    private Storage storage;
    protected static TaskList tasks;

    public DogeBot(String filename) {
        file = new File(PATH.toString(), filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ui = new Ui();
        userInput = new Parser();
        storage = new Storage(file);
        tasks = new TaskList(storage.readFromTxtFile());
    }

    public void run() {
        ui.intro();
        boolean isLoop = true;
        while (isLoop) {
            isLoop = userInput.scan();
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new DogeBot("tasklist.txt").run();
    }
}
