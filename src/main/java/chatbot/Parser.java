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
            return endCommand();
        } else if (input.equals("list")) {
            return printStorageList();
        } else if (input.equals("report")) {
            return printExpenseReport();
        } else if (input.toUpperCase().equals("MEDIC!")) {
            return callMedicCommand();
        } else if (input.startsWith("unmark")) {
            return unmarkCommand(input);
        } else if (input.startsWith("mark")) {
            return markCommand(input);
        } else if (input.startsWith("delete")) {
            return deleteCommand(input);
        } else if (input.startsWith("editExpense")) {
            return editExpenseCommand(input);
        } else if (input.startsWith("find")) {
            return findCommand(input);
        } else if (!(input.startsWith("todo") || input.startsWith("event")
                    || input.startsWith("deadline") || input.startsWith("expense"))) {
            return printInvalidInput();
        } else if (input.startsWith("todo")) {
            return todoCommand(input);
        } else if (input.startsWith("deadline")) {
            return deadlineCommand(input);
        } else if (input.startsWith("event")) {
            return eventCommand(input);
        } else if (input.startsWith("expense")) {
            return expenseCommand(input);
        } else {
            assert false : "execution should never reach here";
            return "";
        }
    }

    /**
     * Returns the String message for ending the program.
     */
    public String endCommand() {
        return ui.endProgram();
    }

    /**
     * Returns the String representation of the task list.
     */
    public String printStorageList() {
        storage.write(taskHandler.getTaskList());
        return ui.printStorageList(taskHandler.getTaskList());
    }

    /**
     * Returns the String representation of the report.
     */
    public String printExpenseReport() {
        return ui.printExpenseReport(expenseReport.getExpenseReport());
    }

    public String printInvalidInput() {
        return ui.invalidInput();
    }

    public String callMedicCommand() {
        return ui.printCallMedic();
    }

    public String markCommand(String input) {
        try {
            validateInput(input, 5);
        } catch (UserInputException e) {
            return ui.printIncompleteCommand(e.getMessage());
        }

        int stringLength = input.length();
        int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;
                
        Task task = taskHandler.mark(index);
        return ui.printMarked(task);
    }

    public String unmarkCommand(String input) {
        try {
            validateInput(input, 7);
        } catch (UserInputException e) {
            return ui.printIncompleteCommand(e.getMessage());
        }

        int stringLength = input.length();
        int index = Integer.parseInt(input.substring(stringLength - 1)) - 1;
                
        Task task = taskHandler.unmark(index);
        return ui.printUnmarked(task);
    }

    public String todoCommand(String input) {
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
    }

    public String deadlineCommand(String input) {
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
    }

    public String eventCommand(String input) {
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
    }

    public String deleteCommand(String input) {
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
    }

    public String findCommand(String input) {
        try {
            validateInput(input, 5);
        } catch (UserInputException e) {
            return ui.printIncompleteCommand(e.getMessage());
        }

        String[] keywords = input.split(" ");
        List<Task> filteredList = taskHandler.filterByWord(keywords[1]);

        return ui.printStorageList(filteredList);
    }

    public String expenseCommand(String input) {
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

    /**
     * Retrieves the filtered list according to the keyword.
     * @param input The word to filter the list.
     * @return The output String.
     */
    public String editExpenseCommand(String input) {
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
    }

    /** 
     * Checks for incomplete user inputs.
     * 
     * @param str The user input.
     * @param minimum The minimum length of the user input.
     * @throws UserInputException If the input is deemed incomplete.
     */
    public static void validateInput(String str, int minimum) throws UserInputException {
        if (str.trim().length() <= minimum) {
            throw new UserInputException("OOPS!!! The description of a " + str.trim() + " cannot be empty.");
        }
    }
}