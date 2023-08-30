package duke.storage;

import duke.task.*;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        File file = new File(this.filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            ArrayList<Task> taskArr = new ArrayList<>();

            while (sc.hasNext()) {
                String currLine = sc.nextLine();
                String[] wordArr = currLine.split("\\|"); // Escape the | and space characters
                String category = wordArr[0].trim();

                switch (category) {
                case "T":
                    Todo todo = new Todo(wordArr[2].trim());
                    if (wordArr[1].trim().equals("1")) {
                        todo.markAsDone();
                    }
                    taskArr.add(todo);
                    break;
                case "D":
                    Deadline dl = new Deadline(wordArr[2].trim(), wordArr[3].trim());
                    if (wordArr[1].trim().equals("1")) {
                        dl.markAsDone();
                    }
                    taskArr.add(dl);
                    break;
                case "E":
                    String time = wordArr[3].trim();
                    String[] timeline = time.split("to");
                    Event e = new Event(wordArr[2].trim(), timeline[0].trim(), timeline[1].trim());
                    if (wordArr[1].trim().equals("1")) {
                        e.markAsDone();
                    }
                    taskArr.add(e);
                    break;
                }
            }
            return taskArr;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFile(TaskList taskList) {
        try {
            // Open the FileWriter without append mode.
            FileWriter fWriter = new FileWriter(this.filePath);
            // delete whole text.
            fWriter.write("");
            // Close the FileWriter
            fWriter.close();

            // Open the FileWriter in append mode.
            FileWriter fWriter2 = new FileWriter(this.filePath, true);

            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                if (t instanceof Todo) {
                    fWriter2.write("T | " + t.getNumber() + " | " + t.getDescription() + "\n");
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    fWriter2.write("D | " + d.getNumber() + " | " + d.getDescription() + " | " + d.getBy() + "\n");
                } else {
                    //event
                    Event e = (Event) t;
                    fWriter2.write("E | " + e.getNumber() + " | " + e.getDescription() + " | " + e.getFrom() + " to " +
                            e.getTo() + "\n");
                }
            }

            // Close the FileWriter2
            fWriter2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
