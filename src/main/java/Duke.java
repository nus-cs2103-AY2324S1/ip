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
        System.out.println("Howdy, I'm + " + NAME + ", your friendly personal assistant!");
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

            switch (prefix) {
                case "bye":
                    IS_RECEIVING_INPUT = false;
                    break;
                case "list":
                    System.out.println(SPACER);
                    printTaskList();
                    System.out.println(SPACER);
                    break;
                case "unmark":
                case "mark":
                    try {
                        int index = SCANNER.nextInt() - 1;
                        if (prefix.equals("mark")) {
                            markTaskInListAsDone(index, true);
                            System.out.println(SPACER);
                            System.out.println("Good job on completing this task! You are an awesome possum!!");
                            System.out.println(TASK_LIST.get(index).toString());
                            System.out.println(SPACER);
                        } else if (prefix.equals("unmark")) {
                            markTaskInListAsDone(index, false);
                            System.out.println(SPACER);
                            System.out.println("Man, you've got this extra task to do now...");
                            System.out.println(TASK_LIST.get(index).toString());
                            System.out.println(SPACER);
                        }
                    } catch (InputMismatchException e) {
                        SCANNER.nextLine();
                        System.out.println(SPACER);
                        System.out.println("HOLD UP! Invalid input for mark command. Input must be a positive non-zero integer.");
                        System.out.println(SPACER);
                    } catch (IndexOutOfBoundsException e) {
                        SCANNER.nextLine();
                        System.out.println(SPACER);
                        System.out.println("HOLD UP! There is no such task in your list!");
                        System.out.println(SPACER);
                    } finally {
                        break;
                    }
                default:
                    String newTaskDescription = prefix + SCANNER.nextLine();
                    addTaskToList(newTaskDescription);
                    System.out.println(SPACER);
                    System.out.println("Added: " + newTaskDescription);
                    System.out.println(SPACER);
                    break;
            }
    }

    private static void addTaskToList(String description) {
        Task newTask = new Task(description);
        TASK_LIST.add(newTask);
    }

    private static void markTaskInListAsDone(int index, boolean done) {
        TASK_LIST.get(index).markTaskCompleted(done);
    }

    private static void printTaskList() {
        System.out.println("Hey buddy, here's your TODO list:");
        int index = 1;
        for (Task task : TASK_LIST) {
            System.out.println(index + "." + task.toString());
            index++;
        }
    }
}
