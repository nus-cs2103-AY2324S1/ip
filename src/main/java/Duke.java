public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        Duke robot404 = new Duke("./data", "duke.txt");
        robot404.start();
    }

    public Duke(String foldPath, String fileName) {
        this.storage = new Storage(foldPath, fileName);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            storage.createFile();
            taskList = new TaskList();
        }
    }

    public void start() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                isExit = parseCommand(fullCommand);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
                System.out.println();
            }
        }
    }

    private boolean parseCommand(String text) throws DukeException {
        String[] split = text.split(" ");
        if (text.isEmpty() || split.length == 0) {
            throw new DukeException("OOPS!!! You have not entered anything!");
        }

        Keyword key;
        try {
            key = Keyword.valueOf(split[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.");
        }

        if (split.length == 1) {
            return processOneWordCommand(key);
        }

        String rest = text.substring(split[0].length() + 1);
        return processMultiWordCommand(key, rest);
    }

    private boolean processOneWordCommand(Keyword key) throws DukeException {
        String err = String.format("OOPS!!! The description of a %s cannot be empty.",
                key.getKeyword());
        switch (key){
        case BYE:
            ui.showExit();
            return true;

        case LIST:
            taskList.listTask(ui);
            break;

        case TODO:
            throw new TodoException(err);

        case DEADLINE:
            throw new DeadlineException(err);

        case EVENT:
            throw new EventException(err);

        case MARK:
        case UNMARK:
        case DELETE:
            throw new ManipulateException(err, key.getKeyword());

        case PRINT_DATE:
            throw new PrintDateException(err);
        }

        return false;
    }

    private boolean processMultiWordCommand(Keyword key, String rest) throws DukeException {
        String err = String.format("OOPS!!! The command for %s task is invalid.",
                 key.getKeyword());
        switch (key) {
        case BYE:
            if (rest.equals(Ui.NAME)) {
                ui.showExit();
                return true;
            }
            // fall through
        case LIST:
            String errMessage = Ui.connectTwoLine(
                    String.format("OOPS!!! The command for %s is invalid.", key.getKeyword()),
                    String.format("Enter in the form: \"%s\"", key.getKeyword()));
            throw new DukeException(errMessage);

        case MARK:
        case UNMARK:
        case DELETE:
            processManipulateCommand(key, rest, err);
            break;

        case TODO:
        case DEADLINE:
        case EVENT:
            processAddCommand(key, rest, err);
            break;

        case PRINT_DATE:
            processPrintCommand(key, rest, err);
            break;
        }
        return false;
    }

    private void processManipulateCommand(Keyword key, String rest, String err) throws DukeException {
        int task_num;
        try {
            if (!rest.equals("all")) {
                task_num = Integer.parseInt(rest);
            } else {
                taskList.manipulateAllTask(key, ui);
                storage.changeFile(key, -1);
                return;
            }
        } catch (NumberFormatException e) {
            throw new ManipulateException(err, key.getKeyword());
        }

        if (key.equals(Keyword.DELETE)) {
            taskList.deleteTask(task_num - 1, ui);
            storage.changeFile(Keyword.DELETE, task_num - 1);
        } else {
            taskList.markTask(task_num - 1, key.equals(Keyword.MARK), ui);
            storage.changeFile(key, task_num - 1);
        }
    }

    private void processAddCommand(Keyword key, String rest, String err) throws DukeException {
        Task task;
        switch(key) {
        case TODO:
            task = new Todo(rest);
            break;

        case DEADLINE:
            String[] deadlineTask = rest.split(" /by ");
            if (deadlineTask.length != 2) {
                throw new DeadlineException(err);
            }
            try {
                task = new Deadline(deadlineTask[0], Time.parseDateTime(deadlineTask[1]));
            } catch (DukeException e) {
                throw new DeadlineException(err);
            }
            break;

        default: // equivalent to case EVENT
            String[] eventTask = rest.split(" /from ");
            if (eventTask.length != 2) {
                throw new EventException(err);
            }
            String[] dates = eventTask[1].split(" /to ");
            if (dates.length != 2) {
                throw new EventException(err);
            }
            try {
                task = new Event(eventTask[0], Time.parseDateTime(dates[0]), Time.parseDateTime(dates[1]));
            } catch (DukeException e) {
                throw new EventException(err);
            }
            break;
        }
        taskList.addTask(task, ui);
        storage.appendFile(task.fileFormat());
    }

    private void processPrintCommand(Keyword key, String rest, String err) throws DukeException {
        String[] printTask = rest.split(" /on ");
        if (printTask.length != 2) {
            throw new PrintDateException(err);
        }
        if (!printTask[0].equals("deadline") && !printTask[0].equals("event")) {
            throw new PrintDateException(err);
        }

        try {
            taskList.printDateTask(Keyword.valueOf(printTask[0].toUpperCase()), Time.parseDate(printTask[1]), ui);
        } catch (DukeException e) {
            throw new PrintDateException(err);
        }
    }
}
