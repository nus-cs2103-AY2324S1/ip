public class Storage {
    private String[] tasks;
    private int taskCount;

    public Storage() {
        this.tasks = new String[100];
        this.taskCount = 0;
    }

    public void addTask(String task) {
        this.tasks[this.taskCount] = task;
        this.taskCount++;
    }

    public String getTasks(){
        String result =  "";
        for (int i = 0; i < taskCount; i++) {
            result+=(i+1)+" "+tasks[i];
            if (i != taskCount-1) result+="\n";
        }
        return result;
    }
}
