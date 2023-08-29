
// fixing DukeException based on my understanding of exceptions 27/8/23
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private UI helper;
    private TaskList tasks;
    private Storage storage;

    public Duke(String filePath) {
        this.helper = new UI("DukeKing");
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
            helper.noFile();
        }
    }

    public static void main(String[] args) {
        new Duke("./dataTasks.txt").run();
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
        // saving the tasks
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("OOPS!!! There is no file to save.");
        }
        // end the program
        sc.close();
        helper.bye();
    }
}
