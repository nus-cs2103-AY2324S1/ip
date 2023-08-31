import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    private static final long serialVersionUID = -7108460826726050219L;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    public String getTask() {
        return this.description;
    }

    public String taskString() {
        String str = this.isDone ? "[X] " : "[ ] ";
        String output = str + this.description;
        return output;
    }
    public static void listTasks(List<Task> tasks) {
        int i = 1;
        System.out.print("\tHere are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println();
            System.out.print("\t" + i + "." + task.taskString());
            i++;
        }
    }
    /*public String fileString() {
        String str = this.isDone ? "[X] " : "[ ] ";
        String output = "\n" + str + this.description + "\n";
        return output;
    }*/
     static void saveTasks(String filePath, List<Task> tasks) throws IOException {
         ObjectOutputStream file = null;
         try {
             file = new ObjectOutputStream(new FileOutputStream(filePath));
             /*for(Task task: tasks) {
                 String str = task.fileString();
                 byte[] strToBytes = str.getBytes();
                 file.write(strToBytes);
             }*/
             file.writeObject(tasks);
         } catch(IOException e) {
             System.out.println(e.getMessage());
         } finally {
             if (file!= null) {
                 file.flush();
                 file.close();
             }
         }
     }

    static List<Task> loadTasks(String filePath) {
        List<Task> tasks = new ArrayList<>();
        ObjectInputStream file = null;
        try {
            file = new ObjectInputStream(new FileInputStream(filePath));
            tasks = (List<Task>) file.readObject();
        } catch (IOException e) {
            // Do nothing here or log the exception if needed
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (file!= null) {
                try {
                    file.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tasks;
    }
}