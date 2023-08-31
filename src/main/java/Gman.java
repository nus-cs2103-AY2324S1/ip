import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class Gman {
    public static String userInput;
    public static ArrayList<Task> taskList;
    private static final String FILE_PATH = "./data/gman.txt"; //hardcoded

    public static ArrayList<Task> readTasks() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> tasksFromFile = new ArrayList<>();
        while (fileScanner.hasNext()) {
            Task taskRead = Task.readFromFile(fileScanner.nextLine());
            tasksFromFile.add(taskRead);
        }
        return tasksFromFile;
    }

    public static void writeTasks(ArrayList<Task> taskList) throws IOException {
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        FileWriter writer = new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            writer.write(task.toWriteString() + "\n");
        }
        writer.close();
    }
    
    public static String noTasksInList() {
        if (taskList.size() == 0) {
            return ("There are no tasks in the list!");
        } else if (taskList.size() == 1) {
            return ("Now you have 1 task in the list.");
        } else {
            return ("Now you have " + taskList.size() + " tasks in the list.");
        }
    }

    public static void main(String[] args) throws GmanIncorrectKeywordException {
        try {
            taskList = readTasks();
        } catch(FileNotFoundException e) {
            taskList = new ArrayList<>();
        }
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Hello! I'm Gman! \nWhat can I do for you?");
        String exitWord = "bye";
        userInput = myScanner.nextLine();

        while (!userInput.equals(exitWord)) {
            if (userInput.equals("list")) {
                try {
                    if (taskList.size() == 0) {
                        throw new NothingInListException("There's nothing to print in the list bozo...");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + taskList.get(i).displayTask());
                    }
                } catch (NothingInListException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.contains("unmark")) {
                String words[] = userInput.split(" ");
                int number = Integer.valueOf(words[1]) - 1;
                taskList.get(number).unmark();

            } else if (userInput.contains("mark")) {
                String words[] = userInput.split(" ");
                int number = Integer.valueOf(words[1]) - 1;
                taskList.get(number).mark();
            } else if (userInput.contains("todo")) {
                try {
                    if (userInput.substring(4).isEmpty()) {
                        throw new GmanEmptyException("OOOOPS! The description of a todo cannot be empty!");
                    }
                    taskList.add(new Todo(userInput.substring(4)));
                    int index = taskList.size() - 1;
                    taskList.get(index).addedTask();
                    System.out.println(noTasksInList());
                } catch (GmanEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.contains("deadline")) {
                String words[] = userInput.substring(8).split("/");
                taskList.add(new Deadline(words[0], words[1].substring(3)));
                int index = taskList.size() - 1;
                taskList.get(index).addedTask();
                System.out.println(noTasksInList());

            } else if (userInput.contains("event")) {
                String words[] = userInput.substring(5).split("/");
                taskList.add(new Event(words[0], words[1].substring(5), words[2].substring(3)));
                int index = taskList.size() - 1;
                taskList.get(index).addedTask();
                System.out.println(noTasksInList());

            } else if (userInput.contains("delete")) {
                try {

                    String words[] = userInput.split(" ");
                    int indexToDelete = Integer.valueOf(words[1]) - 1;
                    if (indexToDelete > taskList.size() || taskList.size() == 0) {
                        throw new NothingInListException("HEYHEYHEY! There's nothing to delete here!");
                    }
                    System.out.println("Noted. I've removed this task: ");
                    taskList.get(indexToDelete).displayTaskMark();
                    taskList.remove(indexToDelete);
                    System.out.println(noTasksInList());
                } catch(NothingInListException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new GmanIncorrectKeywordException("OOPS! I'm sorry, I don't know what that means! Please start " +
                            "with keywords: todo, deadline, or event!");
                } catch (GmanIncorrectKeywordException e) {
                    System.out.println(e.getMessage());
                }
            }
            userInput = myScanner.nextLine();
        }
        System.out.println("    Bye. Hope to see you again soon!");
        try {
            writeTasks(taskList);
        } catch (IOException e) {
            System.out.println("Sorry... I could not save your tasks :C");
        }
    }
}