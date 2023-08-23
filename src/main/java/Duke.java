import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    private static String NAME = "Moira";
    private static String SPACER = "--------------------------------------------------------------------------";
    private static boolean IS_RECEIVING_INPUT = false;
    private static ArrayList<Task> TASK_LIST;
    private static Scanner SCANNER;

    public static void main(String[] args) {
        SCANNER = new Scanner(System.in);
        TASK_LIST = new ArrayList<>();
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
                    System.out.println(TASK_LIST.get(index).toString());
                    System.out.println(SPACER);
                } else if (wordsInInput[0].equals("unmark")) {
                    markTaskInListAsDone(index, false);
                    System.out.println(SPACER);
                    System.out.println("Man, you've got this extra thing to do now...");
                    System.out.println(TASK_LIST.get(index).toString());
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
                        DeadlineTask newDeadlineTask = new DeadlineTask(taskDescription, taskDeadline);
                        addTaskToList(newDeadlineTask);
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
                        EventTask newEventTask = new EventTask(taskDescription, taskFrom, taskTo);
                        addTaskToList(newEventTask);
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

    private static void handleInvalidInput() {
        System.out.println(SPACER);
        System.out.println("HOLD UP! What on earth do you mean??");
        System.out.println(SPACER);
    }

    private static void addTaskToList(Task newTask) {
        TASK_LIST.add(newTask);
        System.out.println(SPACER);
        System.out.println("Okay, so here is the new thing to keep you occupied:");
        System.out.println(newTask.toString());
        System.out.println("Get going! You have " + TASK_LIST.size() + " tasks on record!!");
        System.out.println(SPACER);
    }

    private static void markTaskInListAsDone(int index, boolean done) {
        TASK_LIST.get(index).markTaskCompleted(done);
    }

    private static void printTaskList() {
        System.out.println("Hey buddy, here's the stuff you need to do:");
        int index = 1;
        for (Task task : TASK_LIST) {
            System.out.println(index + "." + task.toString());
            index++;
        }
    }
}
