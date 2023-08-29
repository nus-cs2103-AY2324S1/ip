import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getSaveableList());
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void load(TaskList taskList) {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] line = s.nextLine().trim().split(" \\| ");
                boolean taskIsDone = line[1].equals("1");
                switch (line[0]) {
                    case "T":
                        TodoTask newTodo = new TodoTask(line[2]);
                        if (taskIsDone) {
                            newTodo.complete();
                        }
                        taskList.addTask(newTodo);
                        break;
                    case "D":
                        LocalDate deadline = LocalDate.parse(line[3]);
                        DeadlineTask newDeadline = new DeadlineTask(line[2], deadline);
                        if (taskIsDone) {
                            newDeadline.complete();
                        }
                        taskList.addTask(newDeadline);
                        break;
                    case "E":
                        EventTask newEvent = new EventTask(line[2], line[3], line[4]);
                        if (taskIsDone) {
                            newEvent.complete();
                        }
                        taskList.addTask(newEvent);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Task file not created yet.");
        }
    }
}
