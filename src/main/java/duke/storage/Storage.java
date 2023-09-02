package duke.storage;

import duke.exception.ChatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {
    private final String pathName;
    private TaskList tasks = new TaskList();

    public Storage(String pathName) {
        this.pathName = pathName;
    }
    public TaskList loadFile() throws ChatException {
        try {
            File f = new File(this.pathName);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String pastTaskLine = sc.nextLine();
                String[] pastTaskDetails = pastTaskLine.split(" \\| ");
                Task pastTask;
                switch (pastTaskDetails[0]) {
                    case "T":
                        pastTask = new Task(pastTaskDetails[2]);
                        tasks.addTask(pastTask);
                        if (pastTaskDetails[1].equals("1")) {
                            pastTask.setTaskState(true);
                        }
                        break;
                    case "D":
                        pastTask = new Deadline(pastTaskDetails[2], LocalDate.parse(pastTaskDetails[3]));
                        tasks.addTask(pastTask);
                        if (pastTaskDetails[1].equals("1")) {
                            pastTask.setTaskState(true);
                        }
                        break;
                    case "E":
                        pastTask = new Event(pastTaskDetails[2], pastTaskDetails[3], pastTaskDetails[4]);
                        tasks.addTask(pastTask);
                        if (pastTaskDetails[1].equals("1")) {
                            pastTask.setTaskState(true);
                        }
                        break;
                    default:
                        throw new ChatException("☹ OOPS!!! The file is corrupted");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new ChatException("☹ OOPS!!! There are errors locating the file.");
        }
    }
    public void saveList(TaskList taskList) throws ChatException {
        try {
            FileWriter fw = new FileWriter(this.pathName);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < taskList.getSize(); i++) {
                bw.write(taskList.getTask(i + 1).fileString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new ChatException("☹ OOPS!!! Tasks could not be saved.");
        }
    }

}
