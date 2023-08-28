package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.TaskList;

public class Storage {

    /*
        file format:
        <tasktype>|<isMarked>|<desc>|<end>|<start>
    */

    public static TaskList retrieveSavedData() {
        try {
            ArrayList<String> arr = new ArrayList<>();
            File f = new File("data/duke.txt");
            if (!f.exists()) {
                return new TaskList();
            }
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String str = s.nextLine();
                arr.add(str);
            }

            s.close();
            return populate(arr);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    private static TaskList populate(ArrayList<String> arr) {
        TaskList taskList = new TaskList();

        for (String str : arr) {
            String[] segmented = str.split("\\|");
            String taskType = segmented[0];
            int isMarked = Integer.parseInt(segmented[1]);
            String desc = segmented[2];
            String end;
            String start;

            switch (taskType) {
                case "T":
                    taskList.addTodo(desc, isMarked);
                    break;
                case "D":
                    end = segmented[3];
                    taskList.addDeadline(desc, end, isMarked);
                    break;
                case "E":
                    end = segmented[3];
                    start = segmented[4];
                    taskList.addEvent(desc, start, end, isMarked);
                    break;
            }
        }

        return taskList;
    }

    public static void saveChanges(TaskList taskList) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fileWriter = new FileWriter("data/duke.txt");
            fileWriter.write("");
            fileWriter.append(taskList.getTextFormattedString());
            fileWriter.close();
        } catch (IOException e) {
            Ui.printError(e.getMessage());
        }
    }

}
