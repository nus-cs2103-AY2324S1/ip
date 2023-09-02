public class Duke {
    public enum TaskType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        EVENT,
        TODO,
        DEADLINE
    }
    public static void run() {
        Ui.printGreeting();
        String inputString = Ui.readCommand();
        TaskType taskType = null;
        TaskList taskList = new TaskList();

        while (taskType != TaskType.BYE) {
            try {
                taskType = Parser.parseType(inputString);
                taskList.updateFromStorage(); //load tasks from hard drive if it is available, into taskList
                switch (taskType) {
                case LIST: {
                    taskList.printList();
                    break;
                }

                case MARK: {
                    int tasknum = Parser.getMarkIndex(inputString);
                    taskList.markTask(tasknum);
                    break;
                }

                case UNMARK: {
                    int tasknum = Parser.getUnmarkIndex(inputString);
                    taskList.unmarkTask(tasknum);
                    break;
                }

                case DEADLINE:
                case TODO:
                case EVENT:
                    Task nextTask = Parser.parseTask(inputString, taskType);
                    taskList.addTask(nextTask);
                    break;


                case DELETE: {
                    taskList.deleteTask(Parser.getDeleteIndex(inputString));
                    break;
                }

                default: {
                    throw new DukeException("I can't identify your command!");
                }
                }
                taskList.saveList();
                inputString = Ui.readCommand();
                taskType = Parser.parseType(inputString);
            } catch (DukeException e) {
                inputString = Ui.readCommand();
                Ui.print(e.getMessage());
            } catch (TaskParseException e) {
                Ui.print(e.getMessage());
                inputString = Ui.readCommand();
            }
        }
        Ui.print("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
