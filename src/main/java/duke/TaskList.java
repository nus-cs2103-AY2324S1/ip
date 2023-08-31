package duke;

import duke.task.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<String> tasks){
        this.taskList = new ArrayList<>();
        for (String task: tasks){
            if (task.contains("T")){
                Todo convertedToDoTask = new Todo(task.substring(8));
                this.taskList.add(convertedToDoTask);
            } else if (task.contains("D")){
                String[] deadlineString = task.substring(8).split("\\(");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy ha");
                LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString[1].substring(4, deadlineString[1].length()-1), formatter);
                Deadline deadlineTask = new Deadline(deadlineString[0].trim(), deadlineDate);
                this.taskList.add(deadlineTask);
            } else if (task.contains("E")){
                String[] eventString = task.substring(8).split("\\(");
                String[] timelineString = eventString[1].split("to:");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy ha");
                LocalDateTime startDate = LocalDateTime.parse(timelineString[0].substring(6).trim(), formatter);
                LocalDateTime endDate = LocalDateTime.parse(timelineString[1].substring(0, timelineString[1].length() -1).trim(), formatter);
                Event eventTask = new Event(eventString[0].trim(), startDate, endDate);
                this.taskList.add(eventTask);
            }
        }
    }

    public void load() {
        for (Task task:this.taskList){
            System.out.println(task.toString());
        }
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public int getLengthOfTaskList(){
        return this.taskList.size();
    }

    public Task getTask(int taskNumber){
        return this.taskList.get(taskNumber);
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }

    public void removeTask(int taskNumber){
        this.taskList.remove(taskNumber);
    }


}
