package pardiyem.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import pardiyem.task.TaskList;
import pardiyem.task.Task;
import pardiyem.task.Deadline;
import pardiyem.task.Todo;
import pardiyem.task.Event;

public class Storage {
    static final String DEFAULT_PATH = "./data/storagefile.txt";
    File f;

    public Storage() throws IOException{
        f = new File(DEFAULT_PATH);
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    public void save(TaskList tl) throws IOException {
        ArrayList<Task> curr = tl.getList();
        FileWriter fw = new FileWriter(f);
        StringBuilder toWrite = new StringBuilder();
        for (int i = 0; i < curr.size(); i++) {
            if (i != 0) {
                toWrite.append("\n");
            }
            toWrite.append(curr.get(i).toString());
        }
        fw.write(toWrite.toString());
        fw.close();
    }

    public TaskList load() throws FileNotFoundException, IOException{
        ArrayList<Task> out = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = br.readLine();

        while (line != null) {
            out.add(parseLine(line));
            line = br.readLine();
        }

        br.close();

        return new TaskList(out);
    }

    private Task parseLine(String line) throws IllegalArgumentException {
        
        boolean isDone = (line.charAt(4) == 'X');

        switch (line.charAt(1)) {
        case 'T':
            return new Todo(line.substring(7), isDone);
        case 'E':
            int indFrom = line.indexOf("from:");
            int indTo = line.indexOf("to:");
            return new Event(line.substring(7, indFrom - 2), 
                    line.substring(indFrom + 6, indTo - 1),
                    line.substring(indTo + 4, line.length() - 1),
                    isDone);
        case 'D':
            int indBy = line.indexOf("by:");
            return new Deadline(line.substring(7, indBy - 2),
                    line.substring(indBy + 4, line.length() - 1),
                    isDone);
        default:
            throw new IllegalArgumentException("Whoops, I can't read this text file");
        }
    }
}
