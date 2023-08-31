import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Predicate;

import tasks.Task;
import tasks.TaskList;
import tasks.TaskListIndexOutOfBoundsException;
import tasks.TaskStatusException;
import tasks.ToDo;
import ui.Ui;
import tasks.Deadline;
import tasks.Event;

import commands.CommandType;
import commands.EmptyDescException;
import commands.InvalidCommandException;
import commands.InvalidDescFormatException;

import parsers.TaskParser;

import storage.Storage;

public class Corgi {
    private TaskList tasks;
    private Storage<Task> storage;
    private Ui ui;

    public static void main(String[] args) {
        Corgi bot = new Corgi();
        bot.start();
    }

    /**
     * Constructs new Corgi chatbot with an empty task list.
     */
    public Corgi() {
        this.ui = new Ui();
        this.storage = new Storage<>(new TaskParser(), "./data/tasks.txt");
        this.tasks = new TaskList(storage.load());

        if (tasks.size() > 0) {
            this.ui.showTasksLoaded(tasks.size());
        }
    }

    /**
     * Starts the chatbot - Corgi.
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        this.ui.setScanner(sc);

        this.ui.showIntro();

        while(true) {
            String userInput = this.ui.readCommand();

            if (userInput.equals("")) {
                continue;
            }

            this.ui.showStartLine();

            String[] inputParts = userInput.split(" ", 2);
            String cmdStr = inputParts[0];

            CommandType cmd = null;

            try {
                cmd = CommandType.getCommandType(cmdStr);
            } catch (InvalidCommandException e) {
                this.ui.showError(e.getClass().getSimpleName());
            }

            boolean breakLoop = false;

            if (cmd != null) {
                breakLoop = this.executeCommand(cmd, inputParts);
            }

            this.ui.showEndLine();

            if (breakLoop) break;
        }

        sc.close();
    }

    /**
     * Executes a command based on the provided CommandType and input arguments.
     *
     * @param cmd The CommandType representing the command to execute
     * @param inputs The array of input arguments for the command
     * @return True if the command execution should exit the chatbot, false otherwise
     */
    private boolean executeCommand(CommandType cmd, String[] inputs) {

        try {
            switch (cmd) {
                case BYE:
                    if (inputs.length > 1) throw new InvalidCommandException();
                    this.ui.showExitMsg();
                    return true;
                case LIST:
                    if (inputs.length > 1) throw new InvalidCommandException();
                    this.displayTasks();
                    break;
                case MARK:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.markTaskAsDone(inputs[1]);
                    break;
                case UNMARK:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.markTaskAsNotDone(inputs[1]);
                    break;
                case TODO:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.addToDo(inputs[1]);
                    break;
                case DEADLINE:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.addDeadline(inputs[1]);
                    break;
                case EVENT:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.addEvent(inputs[1]);
                    break;
                case DELETE:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.deleteTask(inputs[1]);
                    break;
                case DATE:
                    if (inputs.length < 2) throw new EmptyDescException();
                    this.getTaskOnDate(inputs[1]);
                    break;
            }
        } catch (InvalidCommandException e) {
            this.ui.showError(e.getClass().getSimpleName());
        } catch (EmptyDescException e) {
            this.ui.showError(e.getClass().getSimpleName());
        } catch (InvalidDescFormatException e) {
            this.ui.showError(e.getClass().getSimpleName(), e.getMessage());
            this.ui.showCommandFormat(cmd);
        } catch (Exception e) {
            this.ui.showError(e.getClass().getSimpleName());
        }

        return false;
    }

    private void getTaskOnDate(String dateStr) throws InvalidDescFormatException {
        LocalDate target = null;

        try {
            target = LocalDate.parse(dateStr, Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDescFormatException("Invalid date format!");
        }

        final LocalDate FINAL_TARGET = target;

        Predicate<Task> isOnDate = t -> {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                return d.isHappeningOnDate(FINAL_TARGET);
            } else if (t instanceof Event) {
                Event e = (Event) t;
                return e.isHappeningOnDate(FINAL_TARGET);
            }
            return false;
        };

        TaskList tasksOnDate = this.tasks.filter(isOnDate);

        String formattedDate = target.format(Task.DATE_OUTPUT_FORMATTER);
        
        if (tasksOnDate.isEmpty()) {
            this.ui.showNoTaskOnDate(formattedDate);;
        } else {
            this.ui.showTasksOnDate(formattedDate, tasksOnDate.toString());
        }
    }

    /**
     * Mark task as done.
     * 
     * @param indexStr Target task index.
     */
    private void markTaskAsDone(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            this.tasks.mark(index, true);
            this.storage.save(tasks);
            this.ui.showTaskDone(this.tasks.getTaskInfo(index)); 
        } catch (NumberFormatException e) {
            this.ui.showError(e.getClass().getSimpleName());
        } catch (TaskStatusException e) {
            this.ui.showError(e.getClass().getSimpleName());
        } catch (TaskListIndexOutOfBoundsException e) {
            this.ui.showError(e.getClass().getSimpleName());
        }
    }

    /**
     * Mark task as not done.
     * 
     * @param indexStr Target task index.
     */
    private void markTaskAsNotDone(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            this.tasks.mark(index, false);
            this.storage.save(tasks);
            this.ui.showTaskUndone(this.tasks.getTaskInfo(index));
        } catch (NumberFormatException e) {
            this.ui.showError(e.getClass().getSimpleName());
        } catch (TaskStatusException e) {
            this.ui.showError(e.getClass().getSimpleName());
        } catch (TaskListIndexOutOfBoundsException e) {
            this.ui.showError(e.getClass().getSimpleName());
        }
    }

    /**
     * Display the list of tasks.
     */
    private void displayTasks() {
        if (tasks.isEmpty()) {
            this.ui.showNoTaskInList();
        } else {
            this.ui.showTaskList(this.tasks.toString());
        }
    }

    /**
     * Remove task from list.
     * 
     * @param indexStr Target task index.
     */
    private void deleteTask(String indexStr) {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            String target = this.tasks.getTaskInfo(index);
            this.tasks.remove(index);
            this.storage.save(tasks);
            this.ui.showTaskDeleted(target, this.tasks.size());
        } catch (NumberFormatException e) {
            //todo: when encounter this throw InvalidDescFormat with message of what happening?
            this.ui.showError(e.getClass().getSimpleName());
        } catch (TaskListIndexOutOfBoundsException e) {
            this.ui.showError(e.getClass().getSimpleName());
        }
    }

     /**
      * Add a new ToDo to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the todo
      */
    private void addToDo(String taskInfo) {
        Task newTask = new ToDo(taskInfo);

        this.tasks.add(newTask);

        this.storage.save(tasks);

        this.ui.showTaskAdded("todo", newTask.toString(), this.tasks.size());
    }

    /**
      * Add a new Deadline to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the deadline, including description and date/time details
      */
    private void addDeadline(String taskInfo) throws InvalidDescFormatException{
        String[] deadlineInfos = taskInfo.split(" /by ");

        if (deadlineInfos.length < 2) throw new InvalidDescFormatException("Missing information!");

        String deadlineDesc = deadlineInfos[0];
        LocalDate by = null;

        try {
            by = LocalDate.parse(deadlineInfos[1], Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDescFormatException("Invalid date format!");
        }

        Task newTask = new Deadline(deadlineDesc, by);

        this.tasks.add(newTask);

        this.storage.save(tasks);

        this.ui.showTaskAdded("deadline", newTask.toString(), this.tasks.size());
    }

    /**
      * Add a new Event to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the event, including description and date/time details
      */
    private void addEvent(String taskInfo) throws InvalidDescFormatException{
        String[] eventInfos = taskInfo.split(" /from ");

        if (eventInfos.length < 2) throw new InvalidDescFormatException("Missing information!");

        String eventDesc = eventInfos[0];
        String[] eventDuration = eventInfos[1].split(" /to ");

        if (eventDuration.length < 2) throw new InvalidDescFormatException("Missing information!");

        LocalDate from = null;
        LocalDate to = null;

        try {
            from = LocalDate.parse(eventDuration[0], Task.DATE_INPUT_FORMATTER);
            to = LocalDate.parse(eventDuration[1], Task.DATE_INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDescFormatException("Invalid date format!");
        }

        Task newTask = new Event(eventDesc, from, to);

        this.tasks.add(newTask);

        this.storage.save(tasks);

        this.ui.showTaskAdded("event", newTask.toString(), this.tasks.size());
    }
}
