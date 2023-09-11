package aj;

/**
 * Ui class responsible for User Interface of the programme. Return string instead
 * change java doc
 */
public class Ui {
    TaskList taskList;

    public String horiLine() {
//        System.out.println("---------------------");
        return "---------------------\n";
    }

    /**
     * Greets user.
     */
    public String greet() {
        return horiLine() + "Hello! I'm Aj\n" + "Loading data....\nHere are your saved data:\n" + printList() +
                "\nWhat can i do for you?\n" + horiLine();
    }

    public String getHelpMessage() {
        return "Here is a list of commands you can try:\n\n" + "list - To list your added tasks\n" + "mark - To mark " +
                "a " + "task as completed\n" + "unmark - To unmark a tast\n" + "delete - To delete a task\n" + "find " +
                "- To " + "find any keyword related to the task\n" + "todo - To create a 'Todo' task\n" + "deadline -" +
                " To " + "craete" + " a 'deadline' task\n" + "event - To create an 'event' task\n\n\n\n" + "To get " +
                "more " + "specific help for each command, type the command itself!!!\n";
    }

    /**
     * Throws error with help message if command is valid, throws NoSuchCommandException otherwise
     *
     * @param cmd First part of user input which indicates type of command.
     * @throws EmptyDescriptionException If second part of user input does not exist.
     * @throws NoSuchCommandException    If command from user input does not exist.
     */
    public void checkCommand(String cmd) throws EmptyDescriptionException,
            NoSuchCommandException { // if no arguments, give help message
        String helpMessage;
        switch (cmd) {
        case "todo":
            helpMessage = "todo <task name>";
            break;
        case "deadline":
            helpMessage = "deadline <task name> /by <YYYY-MM-DD>";
            break;
        case "event":
            helpMessage = "event <task name> /from <date/time> /to <date/time>";
            break;
        case "mark":
            helpMessage = "mark <task_no>";
            break;
        case "unmark":
            helpMessage = "unmark <task_no>";
            break;
        case "delete":
            helpMessage = "delete <task_no>";
            break;
        case "find":
            helpMessage = "find <any keyword related to the task>";
            break;
        default:
            throw new NoSuchCommandException();
        }
        throw new EmptyDescriptionException(cmd,
                helpMessage);
    }

    /**
     * Takes in an index 'idx' and checks if it is within range of taskList size.
     *
     * @param idx Index given by user input.
     * @throws IndexOutOfRangeException If user gives an index 'idx' bigger than taskList size.
     */
    public void checkIndex(int idx) throws IndexOutOfRangeException { // throws error if index invalid
        if (idx <= this.taskList.getSize() - 1 && idx >= 0) {
            return;
        } else {
            throw new IndexOutOfRangeException(this.taskList.getSize());
        }
    }

    /**
     * Prints number of Tasks in taskList.
     */
    public String printNumTask() {
//        System.out.printf("Now you have %d tasks in the list.\n",
//                this.taskList.getSize());
        return String.format("Now you have %d tasks in the list.\n",
                this.taskList.getSize());
    }

    /**
     * Prints the Tasks in taskList.
     */
    public String printList() {
        String str = "";
        if (this.taskList.getSize() == 0) {
//            System.out.println("No items yet, add something!!!");
            str = "No items yet, add something!!!\n";
        }
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            assert this.taskList.getSize() != 0 : "Tasklist size should be 0";
//        System.out.println(i + "." + this.lst[i - 1]);
//            System.out.println(i + "." + this.taskList.getTask(i - 1));
            str += i + "." + this.taskList.getTask(i - 1) + "\n";
        }
        return str;
    }

    public String printKeywordTask(String k) {
        String str = "";
        str += "Finding tasks with names matching : \"" + k + "\"\n";
        str += "Here they are!!:\n";
//        System.out.println("Finding tasks with names matching : \"" + k + "\"");
//        System.out.println("Here they are!!:");
        int no = 1;
        for (int i = 0; i < this.taskList.getSize(); i++) {
            Task task = this.taskList.getTask(i);
            if (task.getTaskName().contains(k)) {
//                System.out.println(Integer.toString(no) + "." + task);
                str += Integer.toString(no++) + "." + task + "\n";
            }
        }
        str += horiLine();
        return str;
    }

    /**
     * Prints exit message.
     */
    public String exit() {
//        System.out.println("Bye. Hope to see you again soon!");
        horiLine();
        return "Bye. Hope to see you again soon!" + horiLine() + "\n";
    }

    Ui(TaskList taskList) {
        this.taskList = taskList;
    }
}
