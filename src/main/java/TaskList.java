import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> task_List;
    private int task_Count;

    public TaskList(){
        task_List = new ArrayList<>();
        task_Count = 0;
    }

    public void addTask(Task task){
        task_List.add(task_Count, task);
        task_Count++;
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
        }
    }
}
