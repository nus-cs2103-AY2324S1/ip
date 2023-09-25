package thea;

/**
 * Represents an executable user command which loads a file.
 * This class has data of the file name.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class LoadCommand extends Command {

    private final String fileName;

    /**
     * Constructs a new DeleteCommand object.
     *
     * @param fileName the name of file to be loaded.
     */
    public LoadCommand(String fileName) {
        super(false);
        this.fileName = fileName;
    }

    /**
     * Load the specified file.
     *
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        try {
            thea.storage.setFileName(this.fileName);
            thea.tasks = new TaskList(thea.storage.retrieveTasks());
        } catch (FileCorruptedException e) {
            return thea.ui.showError(e.getMessage());
        }
        return Ui.fileLoadedMessage(this.fileName);
    }
}
