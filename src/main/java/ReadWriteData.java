import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadWriteData {
    File dataFile;
    Storage previousCommands;


    public ReadWriteData(File datafile, Storage previousCommands) {
        this.dataFile = datafile;
        this.previousCommands = previousCommands;
    }



    public void onInitialise() {
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String task = fileReader.nextLine();
                if (task.equals("")) {
                    continue;
                }
                int i = task.indexOf('|');
                String taskCode = task.substring(0, i);

                String taskWithoutCode = task.substring(i+1);
                int j = taskWithoutCode.indexOf('|');
                String isCompletedTask = taskWithoutCode.substring(0, j);

                String taskDescription = taskWithoutCode.substring(j+1);

                switch(taskCode) {
                    case "T":
                        initialiseTodo(isCompletedTask.equals("X"), taskDescription);
                        break;

                    case "D":
                        initialiseDeadline(isCompletedTask.equals("X"), taskDescription);
                        break;

                    case "E":
                        initialiseEvent(isCompletedTask.equals("X"), taskDescription);
                        break;
                }

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }


    public void initialiseTodo(boolean isCompletedTask, String taskDescription) {
        String temp = String.format("todo %s", taskDescription);
        Task newTask = new Todo(temp);
        previousCommands.addWithoutPrinting(newTask);

        if (isCompletedTask) {
            newTask.toggleDone();
        }
    }

    public void initialiseDeadline(boolean isCompletedTask, String taskDescription) {
        int i = taskDescription.indexOf('(');
        int j = taskDescription.indexOf(')');

        String actualTask = taskDescription.substring(0, i-1);
        String deadline = taskDescription.substring(i+5, j);



        String temp = String.format("deadline %s /by %s", actualTask, deadline);
        Task newTask = new Deadline(temp);
        previousCommands.addWithoutPrinting(newTask);

        if (isCompletedTask) {
            newTask.toggleDone();
        }
    }

    public void initialiseEvent(boolean isCompletedTask, String taskDescription) {
        int i = taskDescription.indexOf('(');
        int j = taskDescription.indexOf(')');
        int k = taskDescription.indexOf("to:");

        String actualTask = taskDescription.substring(0, i-1);
        String startTime = taskDescription.substring(i+7, k-1);
        String endTime = taskDescription.substring(k+4, j);

        String temp = String.format("event %s /from %s /to %s", actualTask, startTime, endTime);
        Task newTask = new Event(temp);
        previousCommands.addWithoutPrinting(newTask);

        if(isCompletedTask) {
            newTask.toggleDone();
        }
    }

    public void write(Task task) {
        try {
            FileWriter fileWriter= new FileWriter(this.dataFile, true);
            String message = String.format("%s|%s|%s",
                    task.eventCode(), task.getStatusIcon(), task.eventDescription());

            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(message);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occured when writing to file: " + e.getMessage());
        }
    }

    public void updateFile() {
        this.dataFile.delete();

        try {
            if (this.dataFile.createNewFile()) {
                this.previousCommands.updateALl(this);
            }
        } catch (IOException e) {
            System.out.println("Error when updating file: " + e.getMessage());
        }
    }
}
