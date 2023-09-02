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
                    Todo todo = new Todo(strings[2], Integer.parseInt(strings[1]));
                    tasks.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(strings[2], LocalDate.parse(strings[3]), Integer.parseInt(strings[1]));
                    tasks.add(deadline);
                    break;
                case "E":
                    Event event = new Event(strings[2], strings[3], strings[4], Integer.parseInt(strings[1]));
                    tasks.add(event);
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
    public void saveTask(Todo todo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "T|0|" + todo.getDescription() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTask(Deadline deadline) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "D|0|" + deadline.getDescription() + "|" + deadline.getDate() + "\n";

            bw.write(data);
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTask(Event event) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(inFile, true));

            String data = "E|0|" + event.getDescription() + "|" + event.getFrom() + "|" + event.getTo() + "\n";

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
