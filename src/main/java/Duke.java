import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Personal assistant chatbot that can help you manage a to-do list.
 * @author Wu Jingya
 */
public class Duke {
    private static String NAME = "Moira";
    private static String SPACER = "--------------------------------------------------------------------------";
    private static boolean IS_RECEIVING_INPUT = false;
    private static TaskList TASK_LIST;
    private static Scanner SCANNER;
    private static SaveManager SAVE_MANAGER;

    public static void main(String[] args) {
        SCANNER = new Scanner(System.in);
        TASK_LIST = new TaskList();
        SAVE_MANAGER = new SaveManager(TASK_LIST);
        greet();
        while (IS_RECEIVING_INPUT) {
            getUserInput();
        }
        exit();
        SCANNER.close();
    }

    private static void greet() {
        System.out.println(SPACER);
        System.out.println("Howdy, I'm " + NAME + ", your friendly personal assistant!");
        System.out.println("What can I do for you today?");
        System.out.println(SPACER);
        IS_RECEIVING_INPUT = true;
    }

    private static void exit() {
        IS_RECEIVING_INPUT = false;
        SAVE_MANAGER.saveData();
        System.out.println(SPACER);
        System.out.println("See ya later, alligator! I'm waiting here if you need anything :>");
        System.out.println(SPACER);
    }

    private static void getUserInput() {
        String prefix = SCANNER.next();
        String entireLine = prefix + SCANNER.nextLine();

            switch (prefix) {
                case "bye":
                    handleExit(entireLine);
                    break;
                case "list":
                    handleList(entireLine);
                    break;
                case "unmark":
                case "mark":
                    handleMarkOrUnmark(entireLine);
                        break;
                case "todo":
                    handleAddToDoTask(entireLine);
                    break;
                case "deadline":
                    handleAddDeadlineTask(entireLine);
                    break;
                case "event":
                    handleAddEventTask(entireLine);
                    break;
                case "delete":
                    handleDeleteTask(entireLine);
                    break;
                default:
                    handleInvalidInput();
                    break;
            }
    }

    private static void handleExit(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            handleInvalidInput();
        } else {
            IS_RECEIVING_INPUT = false;
        }
    }

    private static void handleList(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 1) {
            handleInvalidInput();
        } else {
            System.out.println(SPACER);
            printTaskList();
            System.out.println(SPACER);
        }
    }

    private static void handleMarkOrUnmark(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            handleInvalidInput();
        } else {
            try {
                int index = Integer.parseInt(wordsInInput[1]) - 1;
                if (wordsInInput[0].equals("mark")) {
                    markTaskInListAsDone(index, true);
                    System.out.println(SPACER);
                    System.out.println("Good job on completing this task! You are an awesome possum!!");
                    System.out.println(TASK_LIST.getTaskAsString(index));
                    System.out.println(SPACER);
                } else if (wordsInInput[0].equals("unmark")) {
                    markTaskInListAsDone(index, false);
                    System.out.println(SPACER);
                    System.out.println("Man, you've got this extra thing to do now...");
                    System.out.println(TASK_LIST.getTaskAsString(index));
                    System.out.println(SPACER);
                }
            } catch (NumberFormatException e) {
                System.out.println(SPACER);
                System.out.println("HOLD UP! Invalid input for mark/unmark command. Input must be a positive non-zero integer.");
                System.out.println(SPACER);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(SPACER);
                System.out.println("HOLD UP! There is no such task in your list!");
                System.out.println(SPACER);
            }
        }
    }

    private static void handleAddToDoTask(String userInput) {
        if (!userInput.startsWith("todo ")) {
            handleInvalidInput();
            return;
        }

        String taskDescription = userInput.substring(5);
        if (!taskDescription.isBlank()) {
            ToDoTask newToDoTask = new ToDoTask(taskDescription);
            addTaskToList(newToDoTask);
        } else {
            System.out.println(SPACER);
            System.out.println("HOLD UP! So... what is this task about??");
            System.out.println(SPACER);
        }
    }
    private static void handleAddDeadlineTask(String userInput) {
        if (!userInput.startsWith("deadline ")) {
            handleInvalidInput();
            return;
        }

        String taskDescriptionAndDeadline = userInput.substring(9);
        if (!taskDescriptionAndDeadline.isBlank()) {
            String[] taskDescriptionSections = taskDescriptionAndDeadline.split(" /");
            if (taskDescriptionSections.length != 2) {
                handleInvalidInput();
            } else {
                String taskDescription = taskDescriptionSections[0];
                if (taskDescription.isBlank()) {
                    System.out.println(SPACER);
                    System.out.println("HOLD UP! So... what is this task about??");
                    System.out.println(SPACER);
                    return;
                }
                String taskDeadlineSegment = taskDescriptionSections[1];
                if (taskDeadlineSegment.startsWith("by ")) {
                    String taskDeadline = taskDeadlineSegment.substring(3);
                    if (!taskDeadline.isBlank()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime deadline = LocalDateTime.parse(taskDeadline, formatter);
                            DeadlineTask newDeadlineTask = new DeadlineTask(taskDescription, deadline);
                            addTaskToList(newDeadlineTask);
                        } catch (DateTimeParseException e){
                            System.out.println(SPACER);
                            System.out.println("HOLD UP! You are not formatting your dates right! Use \"yyyy-MM-dd HH:mm\" >:(");
                            System.out.println(SPACER);
                        }
                    } else {
                        System.out.println(SPACER);
                        System.out.println("HOLD UP! C'mon, what's the deadline?");
                        System.out.println(SPACER);
                    }
                } else {
                    handleInvalidInput();
                }
            }
        } else {
            handleInvalidInput();
        }
    }

    private static void handleAddEventTask(String userInput) {
        if (!userInput.startsWith("event ")) {
            handleInvalidInput();
            return;
        }

        String taskDescriptionAndDuration = userInput.substring(6);
        if (!taskDescriptionAndDuration.isBlank()) {
            String[] taskDescriptionSections = taskDescriptionAndDuration.split(" /");
            if (taskDescriptionSections.length != 3) {
                handleInvalidInput();
            } else {
                String taskDescription = taskDescriptionSections[0];
                if (taskDescription.isBlank()) {
                    System.out.println(SPACER);
                    System.out.println("HOLD UP! So... what is this task about??");
                    System.out.println(SPACER);
                    return;
                }
                String taskFromSegment = taskDescriptionSections[1];
                String taskToSegment = taskDescriptionSections[2];
                if (taskFromSegment.startsWith("from ") && taskToSegment.startsWith("to ")) {
                    String taskFrom = taskFromSegment.substring(5);
                    String taskTo = taskToSegment.substring(3);
                    if (!taskFrom.isBlank() && !taskTo.isBlank()) {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            LocalDateTime from = LocalDateTime.parse(taskFrom, formatter);
                            LocalDateTime to = LocalDateTime.parse(taskTo, formatter);
                            EventTask newEventTask = new EventTask(taskDescription, from, to);
                            addTaskToList(newEventTask);
                        } catch (DateTimeParseException e){
                            System.out.println(SPACER);
                            System.out.println("HOLD UP! You are not formatting your dates right! Use \"yyyy-MM-dd HH:mm\" >:(");
                            System.out.println(SPACER);
                        }
                    } else {
                        System.out.println(SPACER);
                        System.out.println("HOLD UP! C'mon, when does it start and end?");
                        System.out.println(SPACER);
                    }
                } else {
                    handleInvalidInput();
                }
            }
        } else {
            handleInvalidInput();
        }
    }

    private static void handleDeleteTask(String userInput) {
        String[] wordsInInput = userInput.split(" ");
        if (wordsInInput.length > 2) {
            handleInvalidInput();
        } else {
            try {
                int index = Integer.parseInt(wordsInInput[1]) - 1;
                if (wordsInInput[0].equals("delete")) {
                    Task deletedTask = TASK_LIST.getTask(index);
                    deleteTaskFromList(index);
                    System.out.println(SPACER);
                    System.out.println("Guess you've got one less thing to do now.");
                    System.out.println(deletedTask.toString());
                    System.out.println("Get going! You have " + TASK_LIST.getTaskCount() + " tasks on record!!");
                    System.out.println(SPACER);
                } else {
                    handleInvalidInput();
                }
            } catch (NumberFormatException e) {
                System.out.println(SPACER);
                System.out.println("HOLD UP! Invalid input for delete command. Input must be a positive non-zero integer.");
                System.out.println(SPACER);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(SPACER);
                System.out.println("HOLD UP! There is no such task in your list!");
                System.out.println(SPACER);
            }
        }
    }

    private static void handleInvalidInput() {
        System.out.println(SPACER);
        System.out.println("HOLD UP! What on earth do you mean??");
        System.out.println(SPACER);
    }

    private static void deleteTaskFromList(int index) {
        TASK_LIST.removeTask(index);
    }

    private static void addTaskToList(Task newTask) {
        TASK_LIST.addTask(newTask);
        System.out.println(SPACER);
        System.out.println("Okay, so here is the new thing to keep you occupied:");
        System.out.println(newTask.toString());
        System.out.println("Get going! You have " + TASK_LIST.getTaskCount() + " tasks on record!!");
        System.out.println(SPACER);
    }

    private static void markTaskInListAsDone(int index, boolean done) {
        TASK_LIST.markTaskAsDone(index, done);
    }

    private static void printTaskList() {
        System.out.println("Hey buddy, here's the stuff you need to do:");
        TASK_LIST.printTasksAsList();
    }
}
