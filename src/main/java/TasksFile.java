
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
public class TasksFile {

    private static final String FILE_PATH = "tasks.txt";

    public static void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            File taskFile = new File(FILE_PATH);
            if (!taskFile.exists()) {
                taskFile.createNewFile();

            }
        }
        catch (IOException e) {
            throw new DukeException("Error creating task file!");
        }

        try {
            FileWriter taskWriter = new FileWriter(FILE_PATH);
            for(Task task : tasks){
                taskWriter.write(task.toString());
                taskWriter.write("\n");
            }
            taskWriter.close();


        } catch (IOException e) {
            throw new DukeException(" Error saving tasks to file");
        }
    }

    public static ArrayList<Task> readTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            File taskFile = new File(FILE_PATH);
            if (!taskFile.exists()){
                return tasks;
            }
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (data.length() > 0){


                    if (data.charAt(1)=='T'){
                        Todo todo = new Todo (data.substring(7));

                        if (data.charAt(4) == 'X'){
                            todo.setAction("mark");
                        }
                        tasks.add(todo);
                    }
                    else if (data.charAt(1)=='D'){
                        String dates = data.split(":")[1];
                        String desc = data.split("\\(")[0];

                        Deadline deadline = new Deadline(desc.substring(7), dates.substring(1,dates.length()-1));
                        if (data.charAt(4) == 'X'){
                            deadline.setAction("mark");
                        }
                        tasks.add(deadline);
                    }
                    else if (data.charAt(1) == 'E'){
                        String[] dates = data.split(":");
                        String desc = data.split("\\(")[0];
                        Event event = new Event(desc.substring(7), dates[1].substring(1,dates[1].length()-2),dates[2].substring(1,dates[2].length()-1));
                        if (data.charAt(4) == 'X'){
                            event.setAction("mark");
                        }
                        tasks.add(event);
                    }
                }
            }

        }
         catch( IOException e){
            throw new DukeException("error reading from file");
        }
        return tasks;
    }

}



