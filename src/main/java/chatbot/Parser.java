package chatbot;

public class Parser {
    private UI ui;
    private TaskList taskHandler;
    private Storage storage;

    public Parser(UI ui, TaskList taskHandler, Storage storage) {
        this.ui = ui;
        this.taskHandler = taskHandler;
        this.storage = storage;
    }

    // Purely for testing purposes
    public Parser() {
        
    }

    public int runInput(String input) {
        if (input.equals("bye")) {
            ui.endProgram();
            return 1;

        } else if (input.equals("list")) {
            ui.printStorageList(taskHandler.taskForce);
            storage.write(taskHandler.taskForce);
            return 0;

        } else if (input.startsWith("unmark")) {
            try {
                validateInput(input, 7);
            } catch (UserInputException e) {
                ui.incompleteCommand(e.getMessage());
                return 0;
            }

            int stringLength = input.length();
            int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;
                
            Task task = taskHandler.unmark(index);
            ui.printUnmarked(task);
            return 0;

        } else if (input.startsWith("mark")) {
            try {
                validateInput(input, 5);
            } catch (UserInputException e) {
                ui.incompleteCommand(e.getMessage());
                return 0;
            }

            int stringLength = input.length();
            int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;
                
            Task task = taskHandler.mark(index);
            ui.printMarked(task);
            return 0;

        } else if (input.startsWith("delete")) {
            try {
                validateInput(input, 7);
            } catch (UserInputException e) {
                ui.incompleteCommand(e.getMessage());
                return 0;
            }

            int stringLength = input.length();
            int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;

            Task task = taskHandler.retrieveTask(index);
            taskHandler.delete(index);

            int size = taskHandler.taskForce.size();

            ui.deleteTask(task, size);
            return 0;

        } else {
            if (!(input.startsWith("todo") || input.startsWith("event") 
                        || input.startsWith("deadline"))) {
                ui.invalidInput();
            } else if (input.startsWith("todo")) {
                try {
                    validateInput(input, 5);
                } catch (UserInputException e) {
                    ui.incompleteCommand(e.getMessage());
                    return 0;
                }

                String[] moreStrings = input.split(" ", 2);

                Task todo = new Todo(moreStrings[1]);
                taskHandler.add(todo);
                int index = taskHandler.taskForce.size();

                ui.addTask(todo, index);
            } else if (input.startsWith("deadline")) {
                try {
                    validateInput(input, 9);
                } catch (UserInputException e) {
                    ui.incompleteCommand(e.getMessage());
                    return 0;
                }

                String[] moreStrings = input.split("/", 2);
                String[] getDescription = moreStrings[0].split(" ", 2);

                String returnBy = moreStrings[1].substring(3);

                Task deadline = new Deadline(getDescription[1], returnBy);
                taskHandler.add(deadline);
                int index = taskHandler.taskForce.size();
                
                ui.addTask(deadline, index);
            } else if (input.startsWith("event")) {
                try {
                    validateInput(input, 6);
                } catch (UserInputException e) {
                    ui.incompleteCommand(e.getMessage());
                    return 0;
                }

                String[] moreStrings = input.split("/");
                String[] pullStrings = moreStrings[0].split(" ", 2);

                String from = moreStrings[1].substring(4);
                String to = moreStrings[2].substring(2);

                Task event = new Event(pullStrings[1], from, to);
                taskHandler.add(event);
                int index = taskHandler.taskForce.size();

                ui.addTask(event, index);
            }
            return 0;
        }
    }

    public static void validateInput(String str, int minimum) throws UserInputException {
        if (str.length() <= minimum) {
            throw new UserInputException("OOPS!!! The description of a " + str + " cannot be empty.");
        }
    }
}