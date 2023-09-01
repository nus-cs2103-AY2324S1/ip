package chatbot.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import chatbot.exceptions.SaveFileNotFound;
import chatbot.task.ToDo;
import chatbot.task.TaskList;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.Deadline;

/**
 * Storage class that handles storing and loading saved lists.
 * 
 * @author Owen Yeo
 */
public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves tasks into a text file.
     * 
     * @param tasks TaskList
     */
    public void saveTasks(TaskList tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter
            (path, false));

            for (int i = 1; i < tasks.getLength() + 1; i++) {
                bw.write(tasks.getTask(i).toSaveString());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error writing file:" + e.getMessage());
        }
    }

    /**
     * Loads the existing list found on the storage file into an ArrayList.
     * 
     * @return ArrayList containing all the tasks parsed from the file.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> loadedList = new ArrayList<>();
            File file = new File(path);
            
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    switch (parts[0].trim()) {
                        case("T"): 
                            loadedList.add(new ToDo(parts[2].trim()));
                            if (Integer.parseInt(parts[1].trim()) == 1) {
                                loadedList.get(loadedList.size() - 1).done();
                            }
                            break;

                        case("D"):
                            loadedList.add(new Deadline(parts[2].trim(), parts[3].trim()));
                            if (Integer.parseInt(parts[1].trim()) == 1) {
                                Task task = loadedList.get(loadedList.size() - 1);
                                task.done();
                            }
                            break;
                        
                        case("E"):
                            loadedList.add(new Event(parts[2].trim(), parts[3].trim(), parts[4].trim()));
                            if (Integer.parseInt(parts[1].trim()) == 1) {
                                loadedList.get(loadedList.size() - 1).done();
                            }
                            break;
                    }
                }
                reader.close();

                return loadedList;
            }
        } catch (IOException e) {
            throw new SaveFileNotFound("You forgot your file path, idiot.");
        }
        return null;
    }
    
}
