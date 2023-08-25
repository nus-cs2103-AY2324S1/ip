public class TaskList {
    private Task[] array;
    private int nextIndex;

    public TaskList() {
        this.array = new Task[100];
        this.nextIndex = 0;
    }

    public String addTask(Task newTask) {
        // add new task into our array
        array[this.nextIndex] = newTask;
        this.nextIndex += 1;
        return "added: " + newTask.getName();
    }

    public String markDone(int taskNumber) {
        Task currentTask = this.array[taskNumber - 1];
        currentTask.changeStatus();
        return "Well done! Your task has now been updated to: \n"
                + currentTask.getStatus() + " " + currentTask.getName();
    }

    public String unMarkDone(int taskNumber) {
        Task currentTask = this.array[taskNumber - 1];
        currentTask.changeStatus();
        return "OK! Your task has now been updated to: \n"
                + currentTask.getStatus() + " " + currentTask.getName();
    }

    @Override
    public String toString() {
        // display in numerical pointers
        String list = "Things you need to do: \n";
        for (int i = 0; i < this.nextIndex; i++) {
            Task task = this.array[i];
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task.getStatus() + " " + task.getName() + "\n";
            }
        }
        return list;
    }
}
