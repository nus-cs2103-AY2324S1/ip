import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> task_List;
    private int task_Count;
    private String FILE_NAME = "src/main/MYBOT.txt";

    public TaskList(){
        task_List = new ArrayList<>();
        task_Count = 0;
    }

    public void addTask(Task task){
        task_List.add(task_Count, task);
        task_Count++;
        saveTasksToFile(FILE_NAME);
    }

    public List<Task> getTask_List(){
        return task_List;
    }

    public int getTask_Count(){
        return task_List.size();
    }

    public Task getTask(int task_number){
        return task_List.get(task_number - 1);
    }

    public void removeTask(int task_number) {
        if (task_number >= 0 && task_number <= task_List.size()) {
            task_List.remove(task_number - 1);
            saveTasksToFile(FILE_NAME);
        }
    }

    public void saveTasksToFile(String filePath) {
        clearTasksInFile(filePath);
        try (FileWriter writer = new FileWriter(filePath)) {
            List<Task> task_List = getTask_List();
            for (Task task : task_List) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Please create a new file for your tasks!");
        } catch (Exception e) {
            System.out.println("The file to store your tasks entered cannot be accessed.");
        }
    }

    public void loadTaskFromFile(String filePath) throws MYBotExceptions {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String inputLines;

            while((inputLines = reader.readLine()) != null) {
                String [] taskSplit = inputLines.split("\\|");
                String taskType = taskSplit[0];
                String status = taskSplit[1];
                String taskDescription = taskSplit[2];

                if (taskType.equals("T")) {
                    addTask(new Todo(taskDescription, status));
                } else if (taskType.equals("D")) {
                    String by = taskSplit[3];
                    addTask(new Deadline(taskDescription, by, status));
                } else if (taskType.equals("E")) {
                    String from = taskSplit[3];
                    String to =  taskSplit[4];
                    addTask(new Event(taskDescription, from, to, status));
                }
            }
        } catch (IOException e) {
            System.out.println("Please ensure that MYBOT.txt can be accessed");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("You have no task at the moment!");
        } /*catch (Exception e) {
            System.out.println("The file to store your tasks entered cannot be accessed.");
        }*/
    }

    public void clearTasksInFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("The file you entered cannot be accessed. \n Please create a new file for your tasks!");
        } catch (Exception e) {
            System.out.println("The file to store your tasks entered cannot be accessed.");
        }
    }
}
