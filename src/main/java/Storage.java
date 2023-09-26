import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    public static void load(TaskList taskList) {
        try {
            String datePattern2 = "MMM-dd-yyyy HH:mm";
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(datePattern2);
            File taskFile = new File("./tasks.txt");
            Scanner taskReader = new Scanner(taskFile);
            while (taskReader.hasNextLine()) {
                String taskFromFile = taskReader.nextLine();
                if (taskFromFile.charAt(0) == 'T') {
                    Todo todo = new Todo(taskFromFile.substring(3));
                    if (taskFromFile.charAt(1) == 't') {
                        todo.markAsDone();
                    }
                    taskList.addTask(todo);
                }
                if (taskFromFile.charAt(0) == 'D') {
                    String deadlineFromFile = taskReader.nextLine();
                    LocalDateTime taskDeadline = LocalDateTime.parse(deadlineFromFile, formatter2);
                    Deadline deadline = new Deadline(taskFromFile.substring(3), taskDeadline);
                    if (taskFromFile.charAt(1) == 't') {
                        deadline.markAsDone();
                    }
                    taskList.addTask(deadline);
                }
                if (taskFromFile.charAt(0) == 'E') {
                    String startFromFile = taskReader.nextLine();
                    LocalDateTime start = LocalDateTime.parse(startFromFile, formatter2);
                    String endFromFile = taskReader.nextLine();
                    LocalDateTime end = LocalDateTime.parse(endFromFile, formatter2);
                    Event event = new Event(taskFromFile.substring(3), start, end);
                    if (taskFromFile.charAt(1) == 't') {
                        event.markAsDone();
                    }
                    taskList.addTask(event);
                }
            }
        } catch (FileNotFoundException e) {
            File taskFile = new File("./tasks.txt");
            try {
                taskFile.createNewFile();
            } catch (IOException e2) {
                System.out.println("Error!");
            }
        }
    }

    public static void saveTasks(TaskList taskList) {
        try {
            FileWriter taskWriter = new FileWriter("./tasks.txt", false);
            for (int taskNumber = 0; taskNumber <= taskList.getCount(); taskNumber++) {
                if (taskList.getTask(taskNumber) instanceof Todo) {
                    taskWriter.write("T");
                    taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                    taskWriter.write(taskList.getTask(taskNumber).getDescription() + "\n");
                } else if (taskList.getTask(taskNumber) instanceof Deadline) {
                    taskWriter.write("D");
                    taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                    taskWriter.write(((Deadline)taskList.getTask(taskNumber)).getDescriptionWithoutTime() + "\n");
                } else if (taskList.getTask(taskNumber) instanceof Event) {
                    taskWriter.write("E");
                    taskWriter.write(taskList.getTask(taskNumber).isDone() ? "t " : "f ");
                    taskWriter.write(((Event)taskList.getTask(taskNumber)).getDescriptionWithoutTime() + "\n");
                }
                if (taskList.getTask(taskNumber) instanceof Deadline) {
                    taskWriter.write(((Deadline) taskList.getTask(taskNumber)).getDeadline() + "\n");
                }
                if (taskList.getTask(taskNumber) instanceof Event) {
                    taskWriter.write(((Event) taskList.getTask(taskNumber)).getStartTime() + "\n");
                    taskWriter.write(((Event) taskList.getTask(taskNumber)).getEndTime() + "\n");
                }
                taskWriter.flush();
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}