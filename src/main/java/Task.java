import java.io.*;
import java.util.ArrayList;
import java.util.List;
abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    private static final long serialVersionUID = -7108460826726050219L;
    //included serialVersionUID indicated as the Task implements Serializable Interface
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
     static void saveTasks(String filePath, List<Task> tasks) {
         ObjectOutputStream file = null;
         try {
             file = new ObjectOutputStream(new FileOutputStream(filePath));
             file.writeObject(tasks);
         } catch (IOException e) {
             System.out.println(e.getMessage());
         } finally {
             if (file!= null) {
                 try {
                     file.close();
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
         }
     }
    static List<Task> loadTasks(String filePath) {
        List<Task> tasks = new ArrayList<>();
        ObjectInputStream file = null;
        try {
            @SuppressWarnings("unchecked")
            //Unchecked warnings are yielded due to an unchecked type conversion
            //readObject() returns an Object, but am casting it to List<Task>
            //The compiler has no way to check if the object is of type List<Task>
            //in compile time, resulting in an unchecked warning being yielded
            File taskFile = new File(filePath);
            if (taskFile.length() > 0) { //check if file is empty before attempting to read the file
                file = new ObjectInputStream(new FileInputStream(taskFile));
                tasks = (List<Task>) file.readObject();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (file != null) {
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