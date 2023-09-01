import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;
    private Storage taskListManager;
    private Ui ui;

    public Duke() throws DukeException, FileNotFoundException {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
        ui = new Ui();
        taskListManager = new Storage("data", "tasks.txt", taskList);
        taskListManager.loadTasks();
    }

    public void start() throws DukeException {
        ui.showWelcome();
        boolean isEcho = true;

        while (isEcho) {
            String strInput = scanner.nextLine();
            ui.showLine();

            String[] words = strInput.split(" ");
            String firstWord = words[0].toLowerCase();

            try {
                TaskType taskType = TaskType.valueOf(firstWord.toUpperCase());

                if (firstWord.equals("add") || firstWord.equals("todo") ||
                        firstWord.equals("deadline") || firstWord.equals("event")) {
                    Task newTasks = Parser.parseTaskInput(strInput);
                    if (newTasks != null) {
                        taskList.addTask(newTasks);
                        taskListManager.saveTask(taskList.getTasks());
                    }
                }

                switch (taskType) {
                case BYE:
                    isEcho = false;
                    System.out.println(" Bye. Hope to see you again soon!");
                    taskListManager.saveTask(taskList.getTasks());
                    break;

                case LIST:
                    ui.showTasks(taskList);
                    break;

                case MARK:
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskList.isMarked(taskIndex)) {
                        throw new DukeException("Task is already marked");
                    } else {
                        taskList.doneAndDusted(taskIndex);
                    }
                    break;

                case UNMARK:
                    taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskList.isMarked(taskIndex)) {
                        taskList.notDoneNotDusted(taskIndex);
                    } else {
                        throw new DukeException("Task is already unmarked");
                    }
                    break;

                case DELETE:
                    if (words.length == 1) {
                        throw new DukeException("Please provide a task number to delete");
                    }
                    taskIndex = Integer.parseInt(words[1]) - 1;
                    taskList.deleteTask(taskIndex);
                    break;

                case ECHO:
                    String[] echoWords = strInput.split(" ", 2);
                    if (echoWords.length < 2) {
                        throw new DukeException(" Nothing to echo!");
                    }
                    String echoedText = echoWords[1].trim();
                    System.out.println(" Echo!! " + echoedText);
                    break;

                case HELP:
                    ui.showCommands();
                    break;

                case UNKNOWN:
                    throw new DukeException("Woops, I don't know this command. I only know: add, todo, deadline, event, list, mark, unmark, delete, bye, echo");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Woops, I don't know this command, sorry :(");
                System.out.println("Pay $100 to unlock more features!");
            } catch (DukeException e) {
                ui.showError(e);
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        try {
            Duke bloopBot = new Duke();
            bloopBot.start();
        } catch (DukeException | FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}