package pooh;

public class Parser {

    public void parseInput(Storage taskStorage, TaskList taskList, String userInput) throws
            UnrecognizedCommandException, InvalidTaskException, WriteTasksException {
        String userAction = userInput.split(" ")[0];
        if (userAction.equalsIgnoreCase("list")) {
            Ui.printTasksMsg(taskList);
        } else {
            switch (userAction) {
            case "list":
                Ui.printTasksMsg(taskList);
            case "bye":
                Ui.exitMsg();
                System.exit(1);
            case "mark":
                int markIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task markTask = taskList.getTask(markIndex);
                markTask.markAsDone();
                Ui.taskDoneMsg(markTask);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task unmarkTask = taskList.getTask(unmarkIndex);
                unmarkTask.markAsUndone();
                Ui.taskUndoneMsg(unmarkTask);
                break;
            case "todo":
            case "event":
            case "deadline":
                try {
                    TaskList.addTask(taskList, userAction, userInput);
                } catch (EmptyTaskDescriptorsException ex) {
                    Ui.generalRespond(ex.toString());
                }
                break;
            case "delete":
                int delIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                TaskList.deleteTask(taskList, delIndex);
                break;
            default:
                throw new UnrecognizedCommandException();
            }
            taskStorage.writeTask(taskList);
        }
    }

}
