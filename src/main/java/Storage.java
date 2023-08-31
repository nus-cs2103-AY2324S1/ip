import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    // Array of Tasks that user has entered
    private FileWriter pw;
    private String currdir;
    private TaskList tasks;
    Ui userUi = new Ui();

    Storage(String dir, TaskList tasks) {
        this.currdir = dir;
        this.tasks = tasks;
    }

    public void load(Parser parser) throws IOException {
        try (Scanner fileScanner = new Scanner(new File("./src/main/java/OUTPUT.txt"))) {
            System.out.println("Your leftover tasks are:");
            int i = 0;
            while (fileScanner.hasNextLine()) {
                i++;
                String taskInfo = fileScanner.nextLine();
                boolean isDone = taskInfo.contains("[X] ");
                if (taskInfo.contains("[T] ")) {
                    String taskName = parser.taskNameFromTextFile(taskInfo, "[T] ");
                    Task newTask = new ToDos(taskName, isDone);
                    tasks.add(newTask);
                    System.out.println(i + ". " + newTask);
                } else if (taskInfo.contains("[E]")) {
                    String taskName = parser.taskNameFromTextFile(taskInfo, "[E] ");
                    String taskFrom = parser.taskFromFromTextFile(taskInfo);
                    String taskTo = parser.taskToFromTextFile(taskInfo);

                    Task newTask = new Events(taskName, taskFrom, taskTo, isDone);
                    tasks.add(newTask);
                    System.out.println(i + ". " + newTask);
                } else if (taskInfo.contains("[D]")) {
                    String taskName = parser.taskNameFromTextFile(taskInfo,"[D] ");
                    String taskBy = parser.taskByFromTextFile(taskInfo);

                    Task newTask = new Deadlines(taskName, taskBy, isDone);
                    tasks.add(newTask);
                    System.out.println(i + ". " + newTask);
                }
            }
        } catch (IOException e) {
            System.out.println("\nERROR: OUTPUT.txt file is not found in directory ./src/main/java/OUTPUT.txt!\n" +
                    "Creating OUTPUT.txt in the given directory now.");
        } finally {
            // Instance of PrintWriter to write new outputs to the file
            pw = new FileWriter("./src/main/java/OUTPUT.txt", true);
            userUi.start();
        }
    }

    public void write(Task task) throws IOException {
        pw.write(task.toString() + "\n");
        pw.flush();
    }

    public void overwrite() throws IOException {
        FileWriter nw = new FileWriter(currdir);
        pw = nw;
        pw.flush();
        for (int i = 0; i < tasks.size(); i++) {
            write(tasks.retrieve(i));
            pw.flush();
        }
    }
}
