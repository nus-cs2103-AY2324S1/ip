package arona.storage;

import arona.task.DeadlineTask;
import arona.task.EventTask;
import arona.task.Task;
import arona.task.TodoTask;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private Path path;
    private File inFile;

    public Storage(String filePath) {
        this.path = Paths.get(filePath);
        this.inFile = path.toFile();
    }

    public void loadTasks(ArrayList<Task> tasks) {
        try {
            if (!inFile.exists()) {
                inFile.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(path.toString()));

            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("\\|"); //useparser
                switch (strings[0]) {
                case "T":
                    TodoTask todoTask = new TodoTask(strings[2], Integer.parseInt(strings[1]));
                    tasks.add(todoTask);
                    break;
                case "D":
                    DeadlineTask deadlineTask = new DeadlineTask(strings[2], LocalDate.parse(strings[3]), Integer.parseInt(strings[1]));
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    EventTask eventTask = new EventTask(strings[2], strings[3], strings[4], Integer.parseInt(strings[1]));
                    tasks.add(eventTask);
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    public void updateTaskStatusAsMarked(int taskIndex) {
        try {
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine != taskIndex) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    char[] charArray = line.toCharArray();
                    charArray[2] = '1';
                    String modifiedLine = new String(charArray);
                    bw.write(modifiedLine);
                    bw.newLine();
                }
                currentLine++;
            }

            br.close();
            bw.close();

            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTaskStatusAsUnmarked(int taskIndex) {
        try {
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine != taskIndex) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    char[] charArray = line.toCharArray();
                    charArray[2] = '0';
                    String modifiedLine = new String(charArray);
                    bw.write(modifiedLine);
                    bw.newLine();
                }
                currentLine++;
            }

            br.close();
            bw.close();

            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveTask(TodoTask todoTask) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "T|0|" + todoTask.getDescription() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTask(DeadlineTask deadlineTask) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "D|0|" + deadlineTask.getDescription() + "|" + deadlineTask.getDate() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTask(EventTask eventTask) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "E|0|" + eventTask.getDescription() + "|" + eventTask.getFrom() + "|" + eventTask.getTo() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int taskIndex) {
        try {
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line = null;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine != taskIndex) {
                    bw.write(line);
                    bw.newLine();
                }
                currentLine++;
            }

            br.close();
            bw.close();

            inFile.delete();
            tempFile.renameTo(inFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
