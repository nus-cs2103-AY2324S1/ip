public class TaskList {
    private Task[] task_List;
    private int task_Count;

    public TaskList(){
        task_List = new Task[100];
        task_Count = 0;
    }

    public void addTask(Task task){
        task_List[task_Count] = task;
        task_Count++;
    }

    public Task[] getTask_List(){
        return task_List;
    }

    public int getTask_Count(){
        return task_Count;
    }

    public Task getTask(int task_number){
        return task_List[task_number-1];
    }
}
