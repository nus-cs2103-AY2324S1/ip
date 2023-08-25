public class TaskList {
    private String[] array;
    private int nextIndex;

    public TaskList() {
        this.array = new String[100];
        this.nextIndex = 0;
    }

    public String addTask(String newTask) {
        // add new task into our array
        array[this.nextIndex] = newTask;
        this.nextIndex += 1;
        return "added: " + newTask;
    }

    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.nextIndex; i++) {
            String task = this.array[i];
            if (task == null) {
                break;
            } else {
                list += (i + 1) + ". " + task + "\n";
            }
        }
        return list;
    }
}
