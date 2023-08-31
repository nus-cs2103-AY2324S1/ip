package duke;

import duke.task.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Handles all operations of the taskList.
 */

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

    /**
     * Displays all tasks loaded in the user's previous sessions.
     */
    public void load() {
        for (Task task:this.taskList){
            System.out.println(task.toString());
        }
    }

    /**
     * Obtain the task list in arraylist format.
     * @return the ArrayList
     */
    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    /**
     * Get the length of the task list
     * @return an integer of the list's length
     */
    public int getLengthOfTaskList(){
        return this.taskList.size();
    }

    /**
     * Obtain a specific task from the list
     * @param taskNumber the index of the task in the list
     * @return the specified task
     */
    public Task getTask(int taskNumber){
        return this.taskList.get(taskNumber);
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added
     */
    public void addTask(Task task){
        this.taskList.add(task);
    }

    /**
     * Removes the specified task from the task list.
     * @param taskNumber the index of the task to be removed
     */
    public void removeTask(int taskNumber){
        this.taskList.remove(taskNumber);
    }


}
