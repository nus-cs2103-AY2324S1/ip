import javax.management.InstanceNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private String name = "Lakinta";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    private int count = 1;
    private final String filePath = "./data/duke.txt";

    public Duke() {
        // Load tasks from the file when the chatbot starts up
        loadTasksFromFile();
    }


    public void greeting() {
        System.out.println("Hello! I'm " + name +
                "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo() {
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                exit();
                break;
            } else {
                System.out.println(response);
            }
        }
        scanner.close();
    }

    public void addTask() {
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                exit();
                break;
            } else if (response.equals("list")) {
                tasks.forEach(x -> System.out.println(x));
            } else {
                tasks.add(String.valueOf(count) + ". " + response);
                count++;
                System.out.println("added: " + response);
            }
        }
        scanner.close();
    }

    public void chat() {
        while (true) {
            String response = scanner.nextLine();

            if (response.equals("bye")) {
                exit();
                break;
            } else if (response.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskArrayList.forEach(x -> System.out.println(x.toString()));
            } else if (response.length() > 5 && response.startsWith("mark ")) {
                int id = Integer.valueOf(response.substring(5));
                taskArrayList.get(id - 1).markAsDone();
            } else if (response.length() > 7 && response.startsWith("unmark ")) {
                int id = Integer.valueOf(response.substring(7));
                taskArrayList.get(id - 1).markAsUndone();
            } else {
                taskArrayList.add(new Task(response, count));
                count++;
                System.out.println("added: " + response);
            }
        }
        scanner.close();
    }

    public void setTasks() {
        while (true) {
            String response = scanner.nextLine();
            try {
                if (response.equals("bye")) {
                    exit();
                    break;
                } else if (response.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    taskArrayList.forEach(x -> System.out.println(x.toString()));
                } else if (response.length() > 5 && response.startsWith("mark ")) {
                    int id = Integer.valueOf(response.substring(5));
                    taskArrayList.get(id - 1).markAsDone();
                } else if (response.length() > 7 && response.startsWith("unmark ")) {
                    int id = Integer.valueOf(response.substring(7));
                    taskArrayList.get(id - 1).markAsUndone();
                } else if (response.startsWith("todo ") && response.length() > 5) {
                    String description = response.substring(5);
                    ToDo task = new ToDo(description, count);
                    taskArrayList.add(task);
                    task.addTask(description);
                    count++;
                    System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                } else if (response.startsWith("event ") && response.length() > 6) {
                    String description = response.substring(6, response.indexOf("/from")).trim();
                    String from = response.substring(response.indexOf("/from") + 6, response.indexOf("/to")).trim();
                    String to = response.substring(response.indexOf("/to") + 4).trim();
                    Event task = new Event(description, count, from, to);
                    taskArrayList.add(task);
                    task.addTask(description);
                    count++;
                    System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                } else if (response.startsWith("deadline ") && response.length() > 9) {
                    String description = response.substring(9, response.indexOf("/by")).trim();
                    String by = response.substring(response.indexOf("/by") + 4).trim();
                    Deadline task = new Deadline(description, count, by);
                    taskArrayList.add(task);
                    task.addTask(description);
                    count++;
                    System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                } else if (response.startsWith("delete ") && response.length() > 7) {
                    int id = Integer.valueOf(response.substring(7));
                    Task task = taskArrayList.get(id - 1);
                    taskArrayList.remove(id - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task.toString().substring(2));
                    System.out.println("Now you have " + String.valueOf(taskArrayList.size()) + " tasks in the list.");
                    for (int i = id; i < taskArrayList.size() - 1; i++) {
                        Task temp = taskArrayList.get(i - 1);
                        temp = new Task(temp.description, i - 2);
                    }
                }
                else {
                    //taskArrayList.add(new Task(response, count));

                    CustomException exception = new CustomException();
                    throw exception;
                }
            } catch (CustomException exception) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (StringIndexOutOfBoundsException exception) {
                System.out.println("☹ OOPS!!! The description lacks details.");
            }
        }
        scanner.close();
    }




    private void saveTasksToFile() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        try {
            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // Write the taskArrayList to the file
                oos.writeObject(taskArrayList);
            } catch (IOException e) {
                System.out.println("Error saving tasks to file!");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Error creating the file or directory!");
            e.printStackTrace();
        }
    }

    private void loadTasksFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                // Read the taskArrayList from the file
                taskArrayList = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions, e.g., if the file is corrupted
                taskArrayList = new ArrayList<>();
                e.printStackTrace();
            }
        } else {
            taskArrayList = new ArrayList<>();
        }
    }



    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Duke myBot = new Duke();
        myBot.greeting();
        //myBot.echo();
        //myBot.addTask();
        //myBot.chat();
        myBot.setTasks();
        myBot.saveTasksToFile();
    }
}
