import command.Command;
import exception.BobException;
import parser.Parser;
import storage.StorageFile;
import task.*;
import ui.TextUi;

public class Bob {
    private StorageFile storageFile;
    private TaskList taskList;
    private TextUi textUi;

    public Bob(String fileDirectoryPath, String fileName) {
        textUi = new TextUi();
        storageFile = new StorageFile(fileDirectoryPath, fileName);
        try {
            taskList = storageFile.loadTasks();
        } catch (BobException e) {
            textUi.printErrorMessage(e);
            taskList = new TaskList();
        }
    }

    public void run() {
        textUi.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = textUi.readTextInput();
                Command c = Parser.parse(input);
                c.execute(taskList, storageFile, textUi);
                isExit = c.isExit();
            } catch (BobException e) {
                textUi.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/", "Bob.txt").run();
    }
}
