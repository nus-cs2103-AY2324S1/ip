package save;

import parser.KniazParser;
import task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class KniazLoader {

    public static final String DEFAULT_PATH = KniazSaver.DEFAULT_PATH;

    private File saveFile;

    public KniazLoader(String path) {
        this.saveFile = new File(path);
    }

    public KniazLoader() {
        this(DEFAULT_PATH);
    }

    public TaskList load() throws IOException, SecurityException, ClassNotFoundException {

        if (!this.saveFile.exists()) {
            throw new IOException(String.format("%s does not exist",this.saveFile.getAbsolutePath()));
        }

        if (!this.saveFile.canRead()) {
            this.saveFile.setReadable(true);
        }

        // time to read it
        FileInputStream fileInputStream = new FileInputStream(this.saveFile);
        ObjectInputStream taskInputStream = new ObjectInputStream(fileInputStream);

        return (TaskList) taskInputStream.readObject();

    }

}
