import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    public static final String SEPARATOR = " !%&%! ";
    private File folder;
    private File file;
    private String filePath;
    private String folderPath;

    public Storage(String folderPath, String fileName) {
        this.filePath = folderPath + "/" +fileName;
        this.folderPath = folderPath;
        this.file = new File(this.filePath);
        this.folder = new File(this.folderPath);
    }

    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                String[] temp = sc.nextLine().split(SEPARATOR);
                Task task;
                switch (temp[0]) {
                case "T":
                    task = new Todo(temp[2]);
                    break;
                case "D":
                    task = new Deadline(temp[2], Time.parseDateTime(temp[3]));
                    break;
                case "E":
                    task = new Event(temp[2], Time.parseDateTime(temp[3]), Time.parseDateTime(temp[4]));
                    break;
                default:
                    throw new DukeException();
                }

                if (temp[1].equals("1")) {
                    task.mark(true);
                } else if (temp[1].equals("0")) {
                    task.mark(false);
                } else {
                    throw new DukeException();
                }

                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
        return taskList;
    }

    public void createFile() {
        if (!this.folder.exists()) {
            folder.mkdirs();
        }
        try {
            if (!file.exists()) {
                Files.createFile(Paths.get(this.filePath));
            } else {
                clearFile();
            }
        } catch (IOException e) {
            /* Here is reach if something terrible happened.
               It is best to throw a runtime exception. */
            throw new RuntimeException(e);
        }
    }

    private void clearFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write("");
        fw.close();
    }

    public void appendFile(String text) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(text);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    public void changeFile(Keyword key, int index) throws DukeException {
        try {
            String tempPath = this.folderPath + "/temp.txt";
            Files.copy(Paths.get(this.filePath), Paths.get(tempPath));
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(""); // Clear the file
            Scanner sc = new Scanner(new File(tempPath));

            if (key.equals(Keyword.DELETE)) {
                if (index >= 0) {
                    removeLine(index, sc, fw);
                } // else delete all
            } else {
                if (index >= 0) {
                    markLine(index, key.equals(Keyword.MARK), sc, fw);
                } else {
                    markAll(key.equals(Keyword.MARK), sc, fw);
                }
            }

            sc.close();
            fw.close();
            Files.delete(Paths.get(tempPath));
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    private void removeLine(int index, Scanner sc, FileWriter fw) throws IOException {
        int curr = 0;

        while (sc.hasNext()) {
            if (curr != index) {
                fw.write(sc.nextLine());
                fw.write("\n");
            } else {
                sc.nextLine();
            }
            curr++;
        }
    }

    private void markLine(int index, boolean isMark, Scanner sc, FileWriter fw) throws IOException {
        int curr = 0;

        while (sc.hasNext()) {
            if (curr != index) {
                fw.write(sc.nextLine());
            } else {
                String task = sc.nextLine();
                String result = task.substring(0, SEPARATOR.length() + 1) +
                        (isMark ? "1" : "0") +
                        task.substring(SEPARATOR.length() + 2);
                fw.write(result);
            }
            fw.write("\n");
            curr++;
        }
    }

    private void markAll(boolean isMark, Scanner sc, FileWriter fw) throws IOException {

        while (sc.hasNext()) {
            String task = sc.nextLine();
            String result = task.substring(0, SEPARATOR.length() + 1) +
                    (isMark ? "1" : "0") +
                    task.substring(SEPARATOR.length() + 2) + "\n";
            fw.write(result);
        }
    }
}
