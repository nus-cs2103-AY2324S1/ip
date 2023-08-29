import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskWriter {
    /**
     * Writes task that was most recently added into the system
     * @param fileName directory of the file to be written into
     * @param task the task that was just created by the user
     * @throws IOException catches error when writing into file
     */
    public static void appendTask(String fileName, Task task) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        if(task instanceof ToDo) {
            ToDo todo = (ToDo) task;
            String temp = todo.toSaveFormat() + System.lineSeparator();
            fw.append(temp);
        } else if(task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            String temp = deadline.toSaveFormat() + System.lineSeparator();
            fw.append(temp);
        } else if(task instanceof Event) {
            Event event = (Event) task;
            String temp = event.toSaveFormat() + System.lineSeparator();
            fw.append(temp);
        }
        fw.close();
    }

    /**
     * Rewrites the file as a result of changing its completion status or deletion of
     * task from the tasklist
     * @param fileName directory of the file to be written into
     * @param tsklst the list of tasks that is to be rewritten into the file
     * @throws IOException throws error if writing into file gives an error
     */
    public static void rewriteTask(String fileName, ArrayList<Task> tsklst) throws IOException {
        if(tsklst.isEmpty()){ return; }
        FileWriter fw = new FileWriter(fileName);
        for(Task task : tsklst) {
            if(task instanceof ToDo) {
                ToDo todo = (ToDo) task;
                String temp = todo.toSaveFormat() + System.lineSeparator();
                fw.append(temp);
            } else if(task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String temp = deadline.toSaveFormat() + System.lineSeparator();
                fw.append(temp);
            } else if(task instanceof Event) {
                Event event = (Event) task;
                String temp = event.toSaveFormat() + System.lineSeparator();
                fw.append(temp);
            }
        }
        fw.close();
    }
}
