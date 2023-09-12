package chatbot;

import java.util.List;

/**
 * Handles the interpretation of user input.
 */
public class Parser {
    /** UI to handle the printing of output. */
    private UI ui;
    /** taskHandler to store the list of Tasks and do operations. */
    private TaskList taskHandler;
    /** Storage to load and write files. */
    private Storage storage;
    private ExpenseReport expenseReport;

    /**
     * Constructor for Parser. Initialises the UI, taskHandler, storage.
     */
    public Parser(UI ui, TaskList taskHandler, Storage storage, ExpenseReport expenseReport) {
        this.ui = ui;
        this.taskHandler = taskHandler;
        this.storage = storage;
        this.expenseReport = expenseReport;
    }

    // Purely for testing purposes.
    public Parser() {}
    /** 
     * Interprets the user string input and
     * returns a value based on the input.
     * @param input The user input to be interpreted.
     * @return The value to determine if the program ends. 
     */
    public String runInput(String input) {
        if (input.equals("bye")) {
            return ui.endProgram();

        } else if (input.equals("list")) {
            storage.write(taskHandler.getTaskList());
            return ui.printStorageList(taskHandler.getTaskList());

        } else if (input.equals("report")) {
            return ui.printExpenseReport(expenseReport.getExpenseReport());

        } else if (input.startsWith("unmark")) {
            try {
                validateInput(input, 7);
            } catch (UserInputException e) {
                return ui.printIncompleteCommand(e.getMessage());
            }

            int stringLength = input.length();
            int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;
                
            Task task = taskHandler.unmark(index);
            return ui.printUnmarked(task);

        } else if (input.startsWith("mark")) {
            try {
                validateInput(input, 5);
            } catch (UserInputException e) {
                return ui.printIncompleteCommand(e.getMessage());
            }

            int stringLength = input.length();
            int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;
                
            Task task = taskHandler.mark(index);
            return ui.printMarked(task);

        } else if (input.startsWith("delete")) {
            try {
                validateInput(input, 7);
            } catch (UserInputException e) {
                return ui.printIncompleteCommand(e.getMessage());
            }

            int stringLength = input.length();
            int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;

            Task task = taskHandler.retrieveTask(index);
            taskHandler.delete(index);

            int size = taskHandler.getSize();

            return ui.deleteTask(task, size);

        } else if (input.startsWith("editExpense")) {
            try {
                validateInput(input, 11);
            } catch (UserInputException e) {
                return ui.printIncompleteCommand(e.getMessage());
            }

            String[] splitInput = input.split(" ", 3);
            int index = Integer.parseInt(splitInput[1]) - 1;
            String updatedCost = splitInput[2];

            expenseReport.getExpense(index).updateCosts(updatedCost);
            Expense expense = expenseReport.getExpense(index);

            return ui.printUpdatedExpense(expense);

        } else if (input.startsWith("find")) {
            try {
                validateInput(input, 5);
            } catch (UserInputException e) {
                return ui.printIncompleteCommand(e.getMessage());
            }

            String[] keywords = input.split(" ");
            List<Task> filteredList = taskHandler.filterByWord(keywords[1]);

            return ui.printStorageList(filteredList);

        } else {
            if (!(input.startsWith("todo") || input.startsWith("event") 
                    || input.startsWith("deadline") || input.startsWith("expense"))) {
                return ui.invalidInput();
            } else if (input.startsWith("todo")) {
                try {
                    validateInput(input, 5);
                } catch (UserInputException e) {
                    return ui.printIncompleteCommand(e.getMessage());

                }

                String[] splitInput = input.split(" ", 2);

                Task todo = new Todo(splitInput[1]);
                taskHandler.add(todo);
                int index = taskHandler.getSize();

                return ui.addTask(todo, index);
            } else if (input.startsWith("deadline")) {
                try {
                    validateInput(input, 9);
                } catch (UserInputException e) {
                    return ui.printIncompleteCommand(e.getMessage());

                }

                String[] splitInput = input.split("/", 2);
                String[] getDescription = splitInput[0].split(" ", 2);

                String returnBy = splitInput[1].substring(3);

                Task deadline = new Deadline(getDescription[1], returnBy);
                taskHandler.add(deadline);
                int index = taskHandler.getSize();
                
                return ui.addTask(deadline, index);
            } else if (input.startsWith("event")) {
                try {
                    validateInput(input, 6);
                } catch (UserInputException e) {
                    return ui.printIncompleteCommand(e.getMessage());

                }

                String[] splitInput = input.split("/");
                String[] toGetDateTime = splitInput[0].split(" ", 2);

                String from = splitInput[1].substring(4);
                String to = splitInput[2].substring(2);

                Task event = new Event(toGetDateTime[1], from, to);
                taskHandler.add(event);
                int index = taskHandler.getSize();

                return ui.addTask(event, index);
            } else if (input.startsWith("expense")) {
                try {
                    validateInput(input, 8);
                } catch (UserInputException e) {
                    return ui.printIncompleteCommand(e.getMessage());

                }

                String[] splitInput = input.split("/");
                String[] getDescription = splitInput[0].split(" ", 2);

                Expense expense = new Expense(getDescription[1], splitInput[1]);
                expenseReport.addExpense(expense);
                int index = expenseReport.getSize();

                return ui.addExpense(expense, index);
            }
        }
        assert false : "execution should never reach here";
        return "";
    }

    /** 
     * Checks for incomplete user inputs.
     * 
     * @param str The user input.
     * @param minimum The minimum length of the user input.
     * @throws UserInputException If the input is deemed incomplete.
     */
    public static void validateInput(String str, int minimum) throws UserInputException {
        if (str.length() <= minimum) {
            throw new UserInputException("OOPS!!! The description of a " + str + " cannot be empty.");
        }
    }
}