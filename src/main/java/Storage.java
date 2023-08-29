import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private static ArrayList<Task> storage = new ArrayList<>();

    public static void loadSave() throws DukeException {
        try {
            String relativePath = "data/anto.txt";
            Path absolutePath = Paths.get(relativePath).toAbsolutePath();
            File antoFile = absolutePath.toFile();

            // If file doesn't exist
            if (!antoFile.exists()) {
                antoFile.createNewFile();
                Printing.printNoSavedFile();
            } else {
                Scanner sc = new Scanner(antoFile);
                while (sc.hasNextLine()) {
                    String currLine = sc.nextLine();
                    String[] currLineArr = currLine.split(Pattern.quote(" | "));
                    if (currLineArr[0].equals("T")) {
                        Task newTask = new Todo(currLineArr[2]);
                        if (currLineArr[1].equals("1")) {
                            newTask.markAsDone();
                        }
                        addToStorageFromFile(newTask);
                    } else if (currLineArr[0].equals("D")) {
                        Task newTask = new Deadline(currLineArr[2], currLineArr[3]);
                        if (currLineArr[1].equals("1")) {
                            newTask.markAsDone();
                        }
                        addToStorageFromFile(newTask);
                    } else if (currLineArr[0].equals("E")) {
                        Task newTask = new Event(currLineArr[2], currLineArr[3], currLineArr[4]);
                        if (currLineArr[1].equals("1")) {
                            newTask.markAsDone();
                        }
                        addToStorageFromFile(newTask);
                    }
                }
                Printing.printSavedFileFound();
            }
        } catch (java.io.IOException e) {
            throw new DukeException("☹ OOPS!!! IOException");
        }
    }

    public static void addToStorage(Task newTask) throws DukeException {
        Storage.storage.add(newTask);

        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        try {
            FileWriter writer = new FileWriter(antoFile, true);
            if (newTask instanceof Todo) {
                writer.write(String.format("\nT | %s | %s", newTask.isDone ? "1" : "0", newTask.description));
            } else if (newTask instanceof Deadline) {
                writer.write(String.format("\nD | %s | %s | %s", newTask.isDone ? "1" : "0", newTask.description,
                        ((Deadline) newTask).by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            } else if (newTask instanceof Event) {
                writer.write(String.format("\nE | %s | %s | %s | %s", newTask.isDone ? "1" : "0", newTask.description,
                        ((Event) newTask).from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                        ((Event) newTask).to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! IOException");
        }
    }

    public static void addToStorageFromFile(Task newTask) {
        Storage.storage.add(newTask);
    }

    public static ArrayList<Task> getStorage() {
        return Storage.storage;
    }

    public static int getLength() {
        return Storage.storage.size();
    }

    public static void markAsDone(int index) throws DukeException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 0 |", "| 1 |") +
                            System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! IOException");
        }

        Storage.storage.get(index).markAsDone();
    }

    public static void unmark(int index) throws DukeException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 1 |", "| 0 |") +
                            System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! IOException");
        }

        Storage.storage.get(index).unmark();
    }

    public static Task delete(int index) throws DukeException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line != index) {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! IOException");
        }
        return Storage.storage.remove(index);
    }
}
