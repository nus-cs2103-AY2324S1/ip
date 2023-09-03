package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Storage class that handles reading and writing to the data text file
 */
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public ArrayList<String> load() throws RichieException {
        ArrayList<String> resultStringArray = new ArrayList<>();
        try {
            File textFile = new File(this.filePath);
            if (textFile.createNewFile()) {
                System.out.println("File created: " + textFile.getName());
            } else {
                System.out.println("Data file already exists");
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));
            String taskString;
            while ((taskString = bufferedReader.readLine()) != null) {
                resultStringArray.add(taskString);
            }
        } catch (IOException e) {
            throw new RichieException("Error loading the file");
        }
        return resultStringArray;
    }

    public void saveCurrentTasks(ArrayList<Task> taskList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task : taskList) {
                String doneNum = task.getIsDone() ? "1" : "0";
                if (task instanceof Todo) {
                    bufferedWriter.append("T/" + doneNum + "/" + task.getDescription());
                    bufferedWriter.append("\n");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    bufferedWriter.append("D/" + doneNum + "/" + deadline.getDescription() + "/"
                            + deadline.getBy().toString());
                    bufferedWriter.append("\n");
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    bufferedWriter.append("E/" + doneNum + "/" + event.getDescription() + "/"
                            + event.getFrom().toString() + "/"
                            + event.getTo().toString());
                    bufferedWriter.append("\n");
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
