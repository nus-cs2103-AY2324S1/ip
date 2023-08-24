import java.io.File;

public class SaveHandler {
    private Task[] tasks;
    private File file;

    public SaveHandler(Task[] tasks, File file) {
        this.tasks = tasks;
        this.file = file;
    }

}
