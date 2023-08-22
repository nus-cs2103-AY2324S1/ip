public class TaskList {
    private String[] task_List;
    private int task_Count;

    public TaskList(){
        task_List = new String[100];
        task_Count = 0;
    }

    public void addTask(String task){
        task_List[task_Count] = task;
        task_Count++;
    }

    public String[] getTask_List(){
        return task_List;
    }

    public int getTask_Count(){
        return task_Count;
    }
}
