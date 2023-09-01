package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

// stores the task list in a text file
public class Storage {
    // create text file
    private final String pathString;

    public Storage() {
        this.pathString = "C:\\Users\\Admin\\ip\\text-ui-test\\data\\task.txt";
    }

    // Update task list in text file
    public void saveTask(Task newTask) {
        try {
            Path path = Paths.get("C:\\Users\\Admin\\ip\\text-ui-test", "data", "task.txt");
            String taskString = newTask.toStoreString() + "\n";
            Files.writeString(path, taskString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTask(Task task, int i) {
        String oldTask = task.toStoreString();
        String newTask = task.toUpdateString(i);

        File fileToUpdate = new File(this.pathString);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToUpdate));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                oldContent += line + System.lineSeparator();
                line = reader.readLine();
            }

            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldTask, newTask);

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToUpdate);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                assert reader != null;
                reader.close();
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Delete task from task list
    public void deleteTask(Task task) {
        String oldTask = task.toStoreString();

        File fileToUpdate = new File(this.pathString);
        String newContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToUpdate));

            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();

            while (line != null) {
                if (!line.equals(oldTask)) {
                    newContent += line + System.lineSeparator();
                }
                line = reader.readLine();
            }

            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToUpdate);

            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                assert reader != null;
                reader.close();
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Retrieve task list from text file and convert to readable codes
    public void handleLoad(TaskList list) {
        Scanner sc = null;
        try {
            File file = new File(this.pathString);

            file.getParentFile().mkdirs();

            // creates new file if it does not exist
            if (!file.exists()) {
                file.createNewFile();
            }

            sc = new Scanner(file);

            while (sc.hasNext()) {
                String[] keyword = sc.nextLine().split("/@/");
                switch (keyword[0]) {
                    case "T":
                        boolean tStatus = keyword[1].equals("1");
                        list.addTask(new ToDo(tStatus, keyword[2]));
                        break;
                    case "D":
                        boolean dStatus = keyword[1].equals("1");
                        list.addTask(new Deadline(
                                dStatus,
                                keyword[2],
                                LocalDateTime.parse(keyword[3])
                        ));
                        break;
                    case "E":
                        boolean eStatus = keyword[1].equals("1");
                        list.addTask(new Event(
                                eStatus,
                                keyword[2],
                                LocalDateTime.parse(keyword[3]),
                                LocalDateTime.parse(keyword[4])
                        ));
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert sc != null;
            sc.close();
        }
    }
}
