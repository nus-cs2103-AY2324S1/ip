public class TaskList {
    private Task[] array;
    private int nextIndex;

    public TaskList() {
        this.array = new Task[100];
        this.nextIndex = 0;
    }

    public void addTask(Task newTask) {
        // add new task into our array
        array[this.nextIndex] = newTask;
        this.nextIndex += 1;
    }

    public Task getTask(int taskNumber) {
        return this.array[taskNumber-1];
    }

    public int getLength() {
        int count = 0;
        while (this.array[count] != null) {
            count += 1;
        }
        return count;
    }

    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.nextIndex; i++) {
            Task task = this.array[i];
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task.toString() + "\n";
            }
        }
        return list;
    }
}
