package duke;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class to store the task result and load it
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for storage
     * @param filePath the relative filepath from the project root
     */
    public Storage(String filePath) {
        //fix hard coded part later
       this.filePath = filePath;
    }

    /**
     * read from file and return an array list of task.
     *
     * @return array list of task
     */
    public ArrayList<Task> load() throws DukeException {
        //print error message if cannot load
        //assume that all file path are ./data/duke.txt for now, change it later
        //if anything happen to the path or to the file, just return a new ArrayList and continue, no exception printed
        Path path = Paths.get("./data/duke.txt");
        ArrayList<Task> loadList = new ArrayList<>();
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    String parsed_str[] = line.split("\\s\\|\\s");
                    if (parsed_str[0].equals("T")) {
                        int size = parsed_str.length;
                        Boolean done = parsed_str[1] == "1" ? true : false;
                        String description = parsed_str[2];
                        Todo curr = new Todo(description);
                        if (done) {
                            curr.markAsDone();
                        }
                        loadList.add(curr);

                    } else if (parsed_str[0].equals("D")) {
                        //reach the date
                        Boolean done = parsed_str[1] == "1" ? true : false;
                        String description = parsed_str[2];
                        String by_date = parsed_str[3];
                        Deadline curr = new Deadline(description, by_date);
                        if (done) {
                            curr.markAsDone();
                        }
                        loadList.add(curr);
                    } else if (parsed_str[0].equals("E")) {
                        Boolean done = parsed_str[1] == "1" ? true : false;
                        String description = parsed_str[2];
                        String fromDate = parsed_str[3];
                        String toDate = parsed_str[4];
                        Event curr = new Event(description, fromDate, toDate);
                        if (done) {
                            curr.markAsDone();
                        }
                        loadList.add(curr);
                    }
                }
            } catch (Exception e) {
                //if anything happen, just return a new one
                throw new DukeException("Error occured in loading", e);
            }

        } else {
            //if the file do not exist, just return a new array list
            return new ArrayList<Task>();
        }
        return loadList;
    }

    /**
     * Store the task in a file.
     * @param tasks An array list of tasks.
     */
    public void store(TaskList tasks) {
        Path path = Paths.get("./data");
        if (Files.notExists(path)) {
            //if the directory not exist, create one instantly
            //duke.txt will be created automatically by default
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                System.out.println("Error occured in creating directory");
            }
        }
        try (FileWriter filewriter = new FileWriter(this.filePath)) {
            //might need a fix for above, todo
            int size = tasks.getSize();
            for (int i = 0; i < size; i++) {
                Task curr = tasks.getTask(i);
                filewriter.write(curr.stringInFile() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error occured in writing the file");
        }
    }
}
