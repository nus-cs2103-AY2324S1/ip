package util;

import task.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    public String filePath = new File("").getAbsolutePath() + "/data/EpochMind.txt";


    /**
     * Saves the task list to a txt file at the path
     *
     * @param taskList The task list to write to file
     */
    public void save(TaskList taskList) {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        try (PrintWriter pw = new PrintWriter(file);) {
            for (Task task : taskList.getTasks()) {
                pw.println(task.toString());
            }
        } catch (Exception e) {
            System.out.println("The Mind has failed to save the tasks");
        }
    }

    /**
     * Saves the task list to a txt file at the path
     *
     * @param taskList The task list to write to file
     */
    public String save(TaskList taskList, String filePath) {
        File file;
        if (filePath.contains(".txt")) {
            file = new File(filePath);
        } else {
            file = new File(filePath + "/EpochMind.txt");
        }
        if (file.exists()) {
            file.delete();
        }
        try (PrintWriter pw = new PrintWriter(file);) {
            File parentDirectory = file.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }
            for (Task task : taskList.getTasks()) {
                pw.println(task.toString());
            }
            return "The Mind has saved the tasks to " + file.getAbsolutePath();
        } catch (Exception e) {
            return "The Mind has failed to save the tasks. Check thy path.";
        }
    }

    public String load(TaskList taskList, String filePath) {
        File file = new File(filePath);
        try (FileReader fileReader = new FileReader(file);){
            BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                switch(line.charAt(1)) {
                case 'T':
                    ToDo todo = new ToDo(line.substring(6, line.length()).trim());
                    if (line.charAt(4) == 'X') {
                        todo.mark();
                    }
                    taskList.add(todo);
                    break;
                case 'D':
                    String[] deadlineSplit = line.split("by:");
                    String date = deadlineSplit[deadlineSplit.length - 1].replace(")", "").trim();

                    String description = deadlineSplit[0].substring(6, deadlineSplit[0].length() - 1).trim();

                    Deadline deadline = new Deadline(description, date);
                    if (line.charAt(4) == 'X') {
                        deadline.mark();
                    }
                    taskList.add(deadline);
                    break;
                case 'E':
                    String[] eventSplit = line.split("from:");
                    String[] fromAndTo = eventSplit[eventSplit.length - 1].split("to:");
                    String from = fromAndTo[0].trim();
                    String to = fromAndTo[1].replace(")", "").trim();

                    String eventDescription = eventSplit[0].substring(6).replace("(","").trim();
                    Event event = new Event(eventDescription, from, to);
                    if (line.charAt(4) == 'X') {
                        event.mark();
                    }
                    taskList.add(event);

                }


            }
            return "The Mind has recalled the stored tasks";
        } catch(Exception e) {
            System.out.println(e);
            return "The Mind has failed to recall the saved tasks. Check thine path.";
        }
    }
}
