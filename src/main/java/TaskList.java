public class TaskList {
    private Task[] taskList = new Task[100];
    private int index = 0;

    public void add(String input) {
        String[] msgArr = input.split(" ");
        Task newTask = null;
        if (msgArr[0].equals("todo")) {
            msgArr = input.split("todo ");
            newTask = new Todo(msgArr[1]);
            taskList[index] = newTask;
        } else if (msgArr[0].equals("deadline")) {         // "deadline return book /by Sunday"
            msgArr = input.split("deadline ");             // {"", "return book /by Sunday"}
            msgArr = msgArr[1].split(" /by ");             // {"return book", "Sunday"}
            newTask = new Deadline(msgArr[0], msgArr[1]);
            taskList[index] = newTask;
        } else if (msgArr[0].equals("event")) {            // "event project meeting /from Mon 2pm /to 4pm"
            msgArr = input.split("event ");                // {"", "project meeting /from Mon 2pm /to 4pm"}
            msgArr = msgArr[1].split(" /from ");           // {"project meeting", "Mon 2pm /to 4pm"}
            String description = msgArr[0];
            msgArr = msgArr[1].split(" /to ");             // {"Mon 2pm", "4pm"}
            newTask = new Event(description, msgArr[0], msgArr[1]);
            taskList[index] = newTask;
        }
        if (newTask != null) {
            index ++;
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.index + " task"
                    + (index == 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        }
    }
    public void list() {
        String msg = "";
        int num = 1;
        for (Task task : taskList) {
            if (task != null) {
                msg = msg + num + ": " + task + "\n";
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
