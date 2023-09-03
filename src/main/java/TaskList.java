import java.util.List;
import java.util.ArrayList;
public class TaskList{
    private List<Task> tasks = new ArrayList<>();
    public TaskList(){
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public Task removeTask(int index){
        return this.tasks.remove(index);
    }
    public Task getTask(int index){
        return this.tasks.get(index);
    }

    public void markDone(int index){
        this.tasks.get(index).markAsDone();
    }
    public void markNotDone(int index){
        this.tasks.get(index).markAsNotDone();
    }
    public int getSize() {
        return tasks.size();
    }

}