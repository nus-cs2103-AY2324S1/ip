import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private File f;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }
    
    public ArrayList<Task> load() throws JoException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split("\\|");
                String taskType = task[0].trim();
                boolean isDone = task[1].trim().equals("1");
                if (taskType.equals("T")) {
                    taskList.add(new Task(task[2].trim(), isDone));
                } else if (taskType.equals("D")) {
                    taskList.add(new Deadline(task[2].trim(), isDone, LocalDate.parse(task[3].trim())));
                } else if (taskType.equals("E")) {
                    taskList.add(
                            new Event(
                                    task[2].trim(),
                                    isDone,
                                    LocalDate.parse(task[3].trim()),
                                    LocalDate.parse(task[4].trim())
                            )
                    );
                }
            }
        } catch (FileNotFoundException e) {
            throw new JoException(e.getMessage());
        }
        return taskList;
    }

    public void update(TaskList taskList) throws JoException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : taskList.tList) {
                fw.write(t.toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new JoException(e.getMessage());
        }
    }
}
