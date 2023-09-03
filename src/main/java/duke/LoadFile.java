package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoadFile {
    private String filepath;
    private File file;
    public LoadFile(String filepath)  {
        this.filepath = filepath;
    }

    public File getFile() {
        return this.file;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void load() throws FileNotFoundException{

        File f = new File(getFilepath());
        this.file = f;
        Scanner s = new Scanner(getFile());
        while (s.hasNext()) {
            String line = s.nextLine();
            TaskList task = new TaskList(line);
            task.load();
        }
        s.close();
    }
}
