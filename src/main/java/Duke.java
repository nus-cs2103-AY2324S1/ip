
// fixing DukeException based on my understanding of exceptions 27/8/23
import java.util.Scanner;

public class Duke {
    private UI helper;
    private TaskList tasks;

    public Duke() {
        this.helper = new UI("DukeKing");
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        // welcome message

        helper.welcome();

        // setting up
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        // looping in the program
        while (true) {
            // end the program
            try {
                if (string.equals(Commands.bye.name())) {
                    break;

                    // reading the tasks
                } else if (string.equals(Commands.list.name())) {
                    helper.printLine();
                    tasks.printList();
                    // marking the task to the tasks
                } else if (string.startsWith(Commands.mark.name())) {
                    try {
                        String[] splittedInput = string.split(" ");
                        int taskNumber = Integer.parseInt(splittedInput[1]);
                        Task task = tasks.get(taskNumber - 1);
                        if (!task.isDone) {
                            task.markAsDone();
                            helper.markTask(task);
                        } else if (task.isDone) {
                            helper.printLine();
                            throw new WrongMarkException("This task is already done.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        helper.printLine();
                        System.out.println("OOPS!!! Must choose something to unmark.");
                    } catch (NullPointerException e) {
                        helper.printLine();
                        System.out.println("OOPS!!! You chose air.");
                    }
                    // unmarking the task from the tasks
                } else if (string.startsWith(Commands.unmark.name())) {
                    try {
                        String[] splittedInput = string.split(" ");
                        int taskNumber = Integer.parseInt(splittedInput[1]);
                        Task task = tasks.get(taskNumber - 1);
                        if (task.isDone) {
                            task.markAsUndone();
                            helper.unMarkTask(task);
                        } else {
                            helper.printLine();
                            throw new WrongMarkException("This task is not done yet.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        helper.printLine();
                        System.out.println("OOPS!!! Must choose something to unmark.");
                    } catch (NullPointerException e) {
                        helper.printLine();
                        System.out.println("OOPS!!! You chose air.");
                    }
                    // if task is a todo
                } else if (string.startsWith(Commands.delete.name())) {
                    String[] splittedInput = string.split(" ");
                    int taskNumber = Integer.parseInt(splittedInput[1]);
                    helper.deleteTask(tasks, taskNumber);
                } else {
                    try {
                        Task currentTask = Task.createTask(string);
                        tasks.add(currentTask);
                        helper.addTask(currentTask, tasks);
                    } catch (EmptyDetailsOfTaskError e) {
                        helper.printLine();
                        System.out.println(e.getMessage());
                    } catch (UnknownCommandException e) {
                        helper.printLine();
                        System.out.println(e.getMessage());
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            helper.printLine();
            string = sc.nextLine();
        }
        // end the program
        sc.close();
        helper.bye();
    }
}
