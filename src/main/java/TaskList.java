public class TaskList {
    private Task[] taskList = new Task[100];
    private int index = 0;

    public void add(String description) {
        taskList[index] = new Task(description);
        index ++;
        System.out.println("added: " + description);
    }
    public void list() {
        String msg = "";
        int num = 1;
        for (Task task : taskList) {
            if (task != null) {
                msg = msg + String.valueOf(num) + ": " + task + "\n";
                num ++;
            } else {
                break;
            }
        }
        System.out.println(msg);
    }

    public void markAsDone(int index) {
        this.taskList[index - 1].markAsDone();
    }

    public void markAsUndone(int index) {
        this.taskList[index - 1].markAsUndone();
    }
}
