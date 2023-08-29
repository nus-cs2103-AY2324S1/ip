import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Parser {

    public String[] words;

    private Ui ui;

    public Parser(String command) {
        this.words = command.split(" ");
        ui = new Ui();
    }

    public void parserChecker(TaskList tasks) throws Exception {

        String action = this.words[0];
        String taskDescription = getTaskDescription(this.words);
        try {
            if (Objects.equals(action, "todo")) {
                if (validToDoCommand(this.words)) {
                    Task newTask = new Todo(taskDescription);
                    tasks.addTask(newTask);
                    ui.taskAdder(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "deadline")) {
                if (validDeadlineCommand(this.words)) {
                    Task newTask = new Deadline(taskDescription, searcher(this.words, "/by"));
                    tasks.addTask(newTask);
                    ui.taskAdder(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "event")) {
                String[] fromTo = fromToDeadline(this.words, "/from", "/to");
                if (validEventCommand(this.words)) {
                    Task newTask = new Event(taskDescription, fromTo[0], fromTo[1]);
                    tasks.addTask(newTask);
                    ui.taskAdder(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "bye")) {
                ui.textGenerator("Bye. Hope to see you again soon!");
                throw new Exception("Termination");
            } else if (Objects.equals(action, "list")) {
                for (int i = 0; i < tasks.userData.size(); i++) {
                    Task currentTask = tasks.userData.get(i);
                    ui.taskInList(i, currentTask);
                }
            } else if (Objects.equals(action, "mark")) {
                int index = Integer.parseInt(this.words[1]);
                Task currentTask = tasks.userData.get(index - 1);
                currentTask.isDone = true;
                ui.afterMT(currentTask);
            } else if (Objects.equals(action, "unmark")) {
                int index = Integer.parseInt(this.words[1]);
                Task currentTask = tasks.userData.get(index - 1);
                currentTask.isDone = false;
                ui.afterUMT(currentTask);
            } else if (Objects.equals(action, "delete")) {
                int index = Integer.parseInt(this.words[1]);
                Task deletedTask = tasks.userData.get(index - 1);
                tasks.deleteTask(index - 1);
                ui.afterDT(deletedTask, tasks.userData.size());
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException DE) {
            System.out.println(DE.getMessage());
        }
    }

    public String getTaskDescription(String[] arrUserCommand) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < arrUserCommand.length; i++) {
            if ("/by".equals(arrUserCommand[i]) || "/from".equals(arrUserCommand[i])) {
                break;
            }
            result.append(" ").append(arrUserCommand[i]);
        }
        return result.toString().trim();
    }

    public boolean validToDoCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            String error = String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]);
            throw new DukeException(error);
        } else {
            return true;
        }
    }

    public String searcher(String[] userCommand, String delimiter) {
        StringBuilder result = new StringBuilder();

        int index = -1;
        for (int i = 0; i < userCommand.length; i++) {
            if (delimiter.equals(userCommand[i])) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index + 1; i < userCommand.length; i++) {
                result.append(userCommand[i]).append(" ");
            }
        }

        return result.toString().trim();
    }

    public String[] fromToDeadline(String[] userCommand, String delimiter, String delimiter_2) {
        StringBuilder firstResult = new StringBuilder();
        StringBuilder secondResult = new StringBuilder();

        int index = -1;
        int index_2 = -1;

        for (int i = 0; i < userCommand.length; i++) {
            if (delimiter.equals(userCommand[i])) {
                index = i;
            } else if (delimiter_2.equals(userCommand[i])) {
                index_2 = i;
            }
        }

        if (index != -1 && index_2 != -1 && index < index_2) {
            for (int i = index + 1; i < index_2; i++) {
                firstResult.append(userCommand[i]).append(" ");
            }
            for (int i = index_2 + 1; i < userCommand.length; i++) {
                secondResult.append(userCommand[i]).append(" ");
            }
        }

        return new String[]{ firstResult.toString().trim(), secondResult.toString().trim() };
    }


    public boolean validDeadlineCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]));
        } else if (userCommand.length <= 2){
            throw new DukeException("For deadlines, please give a gauge of when it is due");
        } else {
            String result = searcher(userCommand, "/by");
            if (Objects.equals(result, "")) {
                throw new DukeException("For deadlines, please give a gauge of when it is due");
            } else {
                String datePattern = "\\d{4}-\\d{2}-\\d{2}";
                String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
                if (!result.matches(datePattern) && !result.matches(dateTimePattern)) {
                    throw new DukeException("Please specify the deadline in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
                }
            }
        }
        return true;
    }

    public boolean validEventCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]));
        } else if (userCommand.length <= 2){
            throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
        } else {
            String[] result = fromToDeadline(userCommand, "/from", "/to");
            if (result[0].equals("") || result[1].equals("")) {
                throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
            }
        }
        return true;
    }
}
