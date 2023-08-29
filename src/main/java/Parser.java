public class Parser {
    enum modifyStatus {
        MARK,
        UNMARK
    }

    enum taskType {
        EVENT,
        DEADLINE,
        TODO
    }

    public static void parseCommands(String nextLine, TaskList tasks, Storage storage) {
        String firstWord = nextLine.split(" ")[0];
        try {
            switch(firstWord) {
                case "bye":
                    Ui.bidFarewell();
                    return;
                case "list":
                    Ui.printItems(tasks);
                    break;
                case "unmark":
                    tasks.modifyStatus(modifyStatus.UNMARK, nextLine);
                    break;
                case "mark":
                    tasks.modifyStatus(modifyStatus.MARK, nextLine);
                    break;
                case "delete":
                    tasks.deleteItem(nextLine);
                    break;
                case "event":
                    tasks.addItem(taskType.EVENT, nextLine);
                    break;
                case "deadline":
                    tasks.addItem(taskType.DEADLINE, nextLine);
                    break;
                case "todo":
                    tasks.addItem(taskType.TODO, nextLine);
                    break;
                default:
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            storage.write(tasks);
        } catch (DukeException e) {
            Ui.printWrapped(e.getMessage());
        }
    }
}
