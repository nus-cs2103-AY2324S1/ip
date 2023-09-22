package Frenchie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Storage {
    private String filePath;

    public Storage() { this.filePath = ""; }
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public TaskList load() throws FrenchieException {
        TaskList loadedTasks = new TaskList();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String taskType = Character.toString(input.charAt(1));
                String taskStatus = Character.toString(input.charAt(4));
                String taskDetails = input.substring(7);
                if (taskType.equals("T")) {
                    ToDo currentTask = new ToDo(taskDetails);
                    if (taskStatus.equals("X")) {
                        currentTask.setIsCompleted();
                    }
                    loadedTasks.addTask(currentTask);
                } else if (taskType.equals("D")) {
                    String taskName = taskDetails.split("\\(")[0].trim();
                    String deadline = taskDetails.split("\\(by: ")[1].split("\\)")[0];
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime storedDeadline = LocalDateTime.parse(deadline, inputFormatter);
                    DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    String constructorDeadline = storedDeadline.format(desiredFormatter);
                    Deadline currentTask = new Deadline(taskName, constructorDeadline);
                    if (taskStatus.equals("X")) {
                        currentTask.setIsCompleted();
                    }
                    loadedTasks.addTask(currentTask);
                } else if (taskType.equals("E")) {
                    String taskName = taskDetails.split("\\(")[0].trim();
                    String startTime = taskDetails.split("\\(")[1].split("from: ")[1].split(" to")[0];
                    String endTime = taskDetails.split("\\(")[1].split("to: ")[1].split("\\)")[0];
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime storedStartTime = LocalDateTime.parse(startTime, inputFormatter);
                    LocalDateTime storedEndTime = LocalDateTime.parse(endTime, inputFormatter);
                    DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    String constructorStartTime = storedStartTime.format(desiredFormatter);
                    String constructorEndTime = storedEndTime.format(desiredFormatter);
                    Event currentTask = new Event(taskName, constructorStartTime, constructorEndTime);
                    if (taskStatus.equals("X")) {
                        currentTask.setIsCompleted();
                    }
                    loadedTasks.addTask(currentTask);
                }
            }
        } catch(IOException e) {
            throw new FrenchieException("Error loading tasks");
        }
        return loadedTasks;
    }

    public void save(TaskList taskList) {
        try (PrintWriter writer = new PrintWriter("frenchie.txt")) {
            for (Task task : taskList.tasks) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
