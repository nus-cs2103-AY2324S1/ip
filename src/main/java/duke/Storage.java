package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import duke.parser.FileParser;
import duke.task.Task;
import duke.task.TaskList;
import duke.templates.MessageTemplates;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    private void createFile(File f) throws IOException {
        f.getParentFile().mkdirs();
        f.createNewFile();
    }
    public ArrayList<Task> loadFile() throws IOException {
        File f = new File(this.filePath);
        if (!f.exists()) {
            createFile(f);
            throw new FileNotFoundException(MessageTemplates.MESSAGE_FILE_NOT_FOUND);
        }
        return FileParser.parse(f);
    }
    public void writeToFile(TaskList tl) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(tl.toStringStore());
        fw.close();
    }
}
