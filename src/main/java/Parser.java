import java.util.Scanner;

public class Parser {

    private void parse() {
        label:
        while (this.inputScanner.hasNext()) {
            String[] input = inputScanner.nextLine().split(" ");
            try {
                switch (input[0]) {
                    case "bye":
                        this.bye();
                        break label;
                    case "list":
                        this.listAllTasks();
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        if (tasks.size() == 0) throw new BongoException("OOPS!!! There are currently no tasks.");
                        if (input.length < 2) throw new BongoException("OOPS!!! Please include the task index.");
                        int taskIndex = Integer.parseInt(input[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size())
                            throw new BongoException("OOPS!!! Task does not exist.");
                        if (input[0].equals("mark")) {
                            this.markTaskDone(taskIndex);
                        } else if (input[0].equals("unmark")) {
                            this.markTaskUndone(taskIndex);
                        } else {
                            this.deleteTask(taskIndex);
                        }
                        continue;
                    case "todo":
                        this.addTodo(input);
                        break;
                    case "deadline": {
                        this.addDeadline(input);
                        break;
                    }
                    case "event": {
                        this.addEvent(input);
                        break;
                    }
                    default:
                        throw new BongoException();
                }
            } catch (BongoException e) {
//                System.out.println(printError(e.getMessage()));
            }
        }
    }
}
