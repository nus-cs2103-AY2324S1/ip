public class TaskList {
    private Task[] taskList = new Task[100];
    private int index = 0;

    public void newTodo(String input) throws DukeMissingArgumentException {
        try {
            String[] msgArr = input.split("todo ");
            Task newTask = new Todo(msgArr[1]);
            taskList[index] = newTask;
            index ++;
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.index + " task"
                    + (index == 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void newDeadline(String input) throws DukeMissingArgumentException {
        try {
            String[] msgArr = input.split("deadline ");             // {"", "return book /by Sunday"}
            msgArr = msgArr[1].split(" /by ");                      // {"return book", "Sunday"}
            Task newTask = new Deadline(msgArr[0], msgArr[1]);
            taskList[index] = newTask;
            index ++;
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.index + " task"
                    + (index == 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void newEvent(String input) throws DukeMissingArgumentException {
        try {
            String[] msgArr = input.split("event ");       // "event project meeting /from Mon 2pm /to 4pm"
            msgArr = msgArr[1].split(" /from ");           // {"", "project meeting /from Mon 2pm /to 4pm"}
            String description = msgArr[0];                // {"project meeting", "Mon 2pm /to 4pm"}
            msgArr = msgArr[1].split(" /to ");             // {"Mon 2pm", "4pm"}
            Task newTask = new Event(description, msgArr[0], msgArr[1]);
            taskList[index] = newTask;
            index ++;
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.index + " task"
                    + (index == 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
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

    public void markAsDone(String[] msgArr) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(msgArr[1]);
            if (i - 1 >= index) {
                throw new DukeNoTaskFoundException();
            }
            this.taskList[i - 1].markAsDone();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void markAsUndone(String[] msgArr) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(msgArr[1]);
            if (i - 1 >= index) {
                throw new DukeNoTaskFoundException();
            }
            this.taskList[i - 1].markAsUndone();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
}
