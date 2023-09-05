package duke;

import duke.tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    final private String FILEPATH;
    private File file;
    Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    public File load() {
        File openfile = new File(FILEPATH);
        try {
            if (openfile.createNewFile()) {
                System.out.println("New file created" + openfile.getName());
            } else {
                System.out.println("File loaded.");
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("An error occurred.");
        }
        file = openfile;
        return openfile;
    }


    public void updatefile(ArrayList<Task> list) {

        try(FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(list.get(list.size() - 1).dataString());
        } catch (IOException e) {
            System.out.println("nothing");
        }
    }

    public File openfile() {
        return file;
    }
}
