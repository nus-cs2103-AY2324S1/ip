import exceptions.DukeInvalidFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {
    private File file;
    private File directory;
    private String filePath;
    private boolean hasDirectory = false;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        int lastSlash = filePath.lastIndexOf('/');
        if (lastSlash != -1) {
            String dirPath = filePath.substring(0, lastSlash);
            this.directory = new File(dirPath);
            this.hasDirectory = true;
        }
    }

    public List<Task> load() throws DukeInvalidFileException {
        List<Task> contents = new ArrayList<>();
        if (hasDirectory && !directory.exists()) {
            directory.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeInvalidFileException();
            }
        }
        Scanner s = null;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeInvalidFileException();
        }
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("\\|");

            //removes leading and trailing spaces
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].trim();
            }

            boolean isDone = false;

            if (parts[1] == "1") {
                isDone = true;
            }

            if (parts[0].equals("T")) {
                ToDo todo = new ToDo(parts[2]);
                if (isDone) {
                    todo.markDone();
                }
                contents.add(todo);
            } else if (parts[0].equals("D")) {
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (isDone) {
                    deadline.markDone();
                }
                contents.add(deadline);
            } else if (parts[0].equals("E")) {
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (isDone) {
                    event.markDone();
                }
                contents.add(event);
            }
        }
        return contents;
    }

    //This method overwrites the content of the file.
    public void save(String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

    //This method appends text to the file.
    public void saveAppend(String appendedContent) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(appendedContent);
        fw.close();
    }
}
