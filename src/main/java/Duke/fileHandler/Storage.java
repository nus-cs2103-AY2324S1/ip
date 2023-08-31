package Duke.fileHandler;

import Duke.exceptions.DukeException;
import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.Todo;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Scanner;
public class Storage {

    private static String FILE_PATH;
    public Storage (String file_path){
        FILE_PATH = file_path;
    }

    /***
     * converts string from saved file to Date object
     * @param dateString
     * @return Date
     * @throws DukeException
     */
    public static Date fileDateParser(String dateString) throws DukeException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy h a");

        try {
            Date date = sdf.parse(dateString);
            return date;
        } catch (Exception e) {
            System.out.println(e);
           throw new DukeException("Cannot read time from file");
        }
    }

    /***
     * Saves tasks in memory to file
     * @param tasks
     * @throws DukeException
     */
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
            throw new DukeException(" Error saving Duke.tasks to file");
        }
    }

    /***
     * reads all tasks from saved file
     * @return tasks as an ArrayList
     * @throws DukeException
     */

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
                        String dates = data.split(":",2)[1];
                        String desc = data.split("\\(")[0];
                        Date date = fileDateParser(dates.substring(1,dates.length()-1));
                        Deadline deadline = new Deadline(desc.substring(7), date);
                        if (data.charAt(4) == 'X'){
                            deadline.setAction("mark");
                        }
                        tasks.add(deadline);
                    }
                    else if (data.charAt(1) == 'E'){
                        String[] dates = data.split(":");
                        String desc = data.split("\\(")[0];

                        Date from = fileDateParser(dates[1].substring(1,dates[1].length()-2));
                        Date to = fileDateParser(dates[2].substring(1,dates[2].length()-1));

                        Event event = new Event(desc.substring(7), from,to);

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



