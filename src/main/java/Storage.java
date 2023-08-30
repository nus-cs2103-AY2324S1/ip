import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    Scanner sc;
    Ui ui;

    public Storage(Ui ui) {
        sc = new Scanner(System.in);
        this.ui = ui;
    }

    public ArrayList<Task> readFile() throws Exception {
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            File taskFile = new File("tasks.txt");
            Scanner taskScanner = new Scanner(taskFile);

            while (taskScanner.hasNextLine()) {
                String task = taskScanner.nextLine();
                String[] taskComponents = task.split(" \\| ");
                String taskType = taskComponents[0];
                boolean taskStatus = taskComponents[1].equals("1");
                String taskDescription = taskComponents[2];
                switch (taskType) {
                    case "T":
                        ToDo todoTask = new ToDo(taskDescription);
                        todoTask.changeStatus(taskStatus);
                        list.add(todoTask);
                        break;
                    case "D":
                        String deadlineDate = taskComponents[3];
                        Deadline deadlineTask = new Deadline(taskDescription, deadlineDate);
                        deadlineTask.changeStatus(taskStatus);
                        list.add(deadlineTask);
                        break;
                    case "E":
                        String[] taskDates = taskComponents[3].split(" - ");
                        Event event = new Event(taskDescription, taskDates[0], taskDates[1]);
                        event.changeStatus(taskStatus);
                        list.add(event);
                        break;
                }
            }

            taskScanner.close();

        } catch (FileNotFoundException e) {
            // File does not exist
            try {
                // Create new file
                File taskFile = new File("tasks.txt");
                taskFile.createNewFile();
            } catch (Exception f) {
                ui.showError("Error creating new file.");
            }
        }
        return list;
    }

    public void writeFile(ArrayList<Task> list) throws Exception {
        FileWriter fw = new FileWriter("tasks.txt");

        try {
            FileWriter taskWriter = new FileWriter("tasks.txt");
            for (Task task : list) {
                taskWriter.write(task.toFileString() + "\n");
            }
            taskWriter.close();
        } catch (Exception e) {
            System.out.println("Error writing to file.");
        }

        fw.close();
    }
}
