import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String dataPath;

    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    public void writeFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        for (Task t: taskList.getTaskList()) {
            fw.write(t.writeFile());
            fw.write("\n");
        }
        fw.close();
    }

    public void readFile(TaskList taskList) throws FileNotFoundException {
        File file = new File(dataPath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String input = s.nextLine();
            if (input.startsWith("T")) {
                String description = input.substring(8);
                Todo todo = new Todo(description);
                if (input.charAt(4) == '1') todo.markAsDone();
                taskList.addTask(todo);
            }
            else if (input.startsWith("D")) {
                String description = "";
                String by = "";
                int count = 0, slash1 = -1, slash2 = -1;
                for (int i = 0; i < input.length(); ++i) {
                    if (input.charAt(i) == '|') {
                        ++count;
                        if (count == 2) slash1 = i;
                        if (count == 3) slash2 = i;
                    }
                }
                description = input.substring(slash1 + 2, slash2 - 1);
                by = input.substring(slash2 + 2);
                Deadline deadline = new Deadline(description, by);
                if (input.charAt(4) == '1') deadline.markAsDone();
                taskList.addTask(deadline);
            }
            else if (input.startsWith("E")) {
                String description = "";
                String from = "";
                String to = "";
                int count = 0, slash1 = -1, slash2 = -1, slash3 = -1;
                for (int i = 0; i < input.length(); ++i) {
                    if (input.charAt(i) == '|') {
                        ++count;
                        if (count == 2) slash1 = i;
                        if (count == 3) slash2 = i;
                        if (count == 4) slash3 = i;
                    }
                }
                description = input.substring(slash1 + 2, slash2 - 1);
                from = input.substring(slash2 + 2, slash3 - 1);
                to = input.substring(slash3 + 2);
                Event event = new Event(description, from, to);
                if (input.charAt(4) == '1') event.markAsDone();
                taskList.addTask(event);
            }
        }
    }
}
