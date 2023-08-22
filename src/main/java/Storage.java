public class Storage {
    private Task[] tasks;
    private int taskCount;

    public Storage() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        this.tasks[this.taskCount] = task;
        this.taskCount++;
    }

    public String getTasks(){
        String result =  "Here are your tasks!\n";
        for (int i = 0; i < taskCount; i++) {
            result+=(i+1)+" "+tasks[i].getStatusIcon()+" "+tasks[i].getDescription();
            if (i != taskCount-1) result+="\n";
        }
        return result;
    }

    public String markTask(int taskIndex, boolean isMark){
        if (taskIndex - 1 > taskCount){
            return "Oops! Cannot find the task.";
        }
        else {
            return tasks[taskIndex - 1].markAsDone(isMark);
        }
    }
}
