package aj;

public class Ui {
    TaskList taskList;

    public void horiLine() {
        System.out.println("---------------------");
    }

    public void greet() {
        horiLine();
        System.out.println("Hello! I'm Aj\n");
        System.out.println("Loading data....\nHere are your saved data:\n");
        printList();
        System.out.println("\nWhat can i do for you?");
        horiLine();
    }

    public void checkMessage(String cmd, String msg) throws EmptyDescriptionException { // if no arguments, give help message
        String helpMessage;
        if (msg.length() == 0 || msg.equals(" ")) {
            if (cmd.equals("todo")) {
                helpMessage = "todo <task name>";
            } else if (cmd.equals("deadline")) {
                helpMessage = "deadline <task name> /by <date/time>";
            } else if (cmd.equals("event")) {
                helpMessage = "event <task name> /from <date/time> /to <date/time>";
            } else if (cmd.equals("mark")) {
                helpMessage = "mark <idx>";
            } else if (cmd.equals("unmark")) {
                helpMessage = "unmark <idx>";
            } else if (cmd.equals("delete")) {
                helpMessage = "delete <idx>";
            } else if (cmd.equals("find")) {
                helpMessage = "find <any keyword for task name>";
            } else {
                helpMessage = "";
            }
            throw new EmptyDescriptionException(cmd, helpMessage);
        }
    }

    public void checkIndex(int val) throws IndexOutOfRangeException { // throws error if index invalid
        if (val <= this.taskList.getSize() - 1 && val >= 0) {
            return;
        } else {
            throw new IndexOutOfRangeException(this.taskList.getSize());
        }
    }

    public void printNoTask() {
        System.out.printf("Now you have %d tasks in the list.\n", this.taskList.getSize());
    }

    public void printList() {
        if (this.taskList.getSize() == 0) {
            System.out.println("No items yet, add something!!!");
        }
        for (int i = 1; i <= this.taskList.getSize(); i++) {
//        System.out.println(i + "." + this.lst[i - 1]);
            System.out.println(i + "." + this.taskList.getTask(i - 1));
        }
    }

    public void printKeywordTask(String k) {
        System.out.println("Finding tasks with names matching : \"" + k + "\"");
        System.out.println("Here they are!!:");
        int no = 1;
        for (int i = 0; i < this.taskList.getSize(); i++) {
            Task task = this.taskList.getTask(i);
            if (task.getTaskName().contains(k)) {
                System.out.println(Integer.toString(no) + "." + task);
            }
        }
        horiLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horiLine();
    }

    Ui(TaskList taskList) {
        this.taskList = taskList;
    }
}
