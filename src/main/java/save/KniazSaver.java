package save;

import task.Task;
import task.TaskList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class KniazSaver {

    public final static String DEFAULT_PATH = "./data/Kniaz.dat";

    private File saveFile;
    public KniazSaver(String path) {
        this.saveFile = new File(path);
    }

    public KniazSaver() {
        this(DEFAULT_PATH);
    }

    public void save(TaskList taskList) throws java.io.IOException,java.lang.SecurityException {
        if (!this.saveFile.getParentFile().exists()) {
            this.saveFile.getParentFile().mkdirs();
        }
        if (!this.saveFile.exists()) {
            this.saveFile.createNewFile(); //thrown from here
        }
        if (!this.saveFile.canWrite()) {
            this.saveFile.setWritable(true);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(this.saveFile);
        ObjectOutputStream taskListOutputStream = new ObjectOutputStream(fileOutputStream);

        taskListOutputStream.writeObject(taskList);


    }

}
