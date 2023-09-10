import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Frenchie {
    public ArrayList<Task> tasks;

    //constructor
    public Frenchie() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task NEW_TASK) {
        tasks.add(NEW_TASK);
    }

    public void listTasks() {
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(counter + ". " + task.toString());
            counter += 1;
        }
    }

    public void completeTask(int index) {
        tasks.get(index).mark_as_completed();
    }

    public void uncompleteTask(int index) {
        tasks.get(index).mark_as_incomplete();
    }

    public int getNumOfTasks() {
        return this.tasks.size();
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter("frenchie.txt")) {
            for (Task task : this.tasks) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readTasksFromFile() {
        String filepath = "./frenchie.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String taskType = Character.toString(input.charAt(1));
                String taskStatus = Character.toString(input.charAt(4));
                String taskDetails = input.substring(7);
                if (taskType.equals("T")) {
                    ToDo currentTask = new ToDo(taskDetails);
                    if (taskStatus.equals("X")) {
                        currentTask.mark_as_completed();
                    }
                    this.addTask(currentTask);
                } else if (taskType.equals("D")) {
                    String taskName = taskDetails.split("\\(")[0].trim();
                    String deadline = taskDetails.split("\\(by: ")[1].split("\\)")[0];
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime storedDeadline = LocalDateTime.parse(deadline, inputFormatter);
                    DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    String constructorDeadline = storedDeadline.format(desiredFormatter);
                    Deadline currentTask = new Deadline(taskName, constructorDeadline);
                    if (taskStatus.equals("X")) {
                        currentTask.mark_as_completed();
                    }
                    this.addTask(currentTask);
                } else if (taskType.equals("E")) {
                    String taskName = taskDetails.split("\\(")[0].trim();
                    String startTime = taskDetails.split("\\(")[1].split("from: ")[1].split(" to")[0];
                    String endTime = taskDetails.split("\\(")[1].split("to: ")[1].split("\\)")[0];
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime storedStartTime = LocalDateTime.parse(startTime, inputFormatter);
                    LocalDateTime storedEndTime = LocalDateTime.parse(endTime, inputFormatter);
                    DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    String constructorStartTime = storedStartTime.format(desiredFormatter);
                    String constructorEndTime = storedEndTime.format(desiredFormatter);
                    Event currentTask = new Event(taskName, constructorStartTime, constructorEndTime);
                    if (taskStatus.equals("X")) {
                        currentTask.mark_as_completed();
                    }
                    this.addTask(currentTask);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        Frenchie frenchie = new Frenchie();
        frenchie.readTasksFromFile();
        String skeleton = "____________________________________________________________\n" +
                " Hello! I'm Frenchie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________"
                /* " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n" */;
        System.out.println(skeleton);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________\n" +
                            " Bye. Hope to see you again soon!\n" +
                            "____________________________________________________________");
                    break;
                } else if (input.equals("list")) { //Checking if user is looking to list all tasks
                    frenchie.listTasks();
                } else if (input.contains("mark")) { //Checking if user input is to mark/unmark tasks
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task target_task = frenchie.tasks.get(index);
                    //Checking if user is looking to mark task as done or incomplete
                    if (parts[0].equals("mark")) {
                        frenchie.completeTask(index);
                        System.out.println("____________________________________________________________\n" +
                                " Nice! I've marked this task as done: \n" +
                                target_task.toString() + "\n" +
                                "____________________________________________________________");
                    } else {
                        frenchie.uncompleteTask(index);
                        System.out.println("____________________________________________________________\n" +
                                " OK, I've marked this task as not done yet: \n" +
                                target_task.toString() + "\n" +
                                "____________________________________________________________");
                    }
                } else if (input.contains("delete")){
                    String[] parts = input.split(" ");
                    int index = Integer.parseInt(parts[1]) - 1;
                    Task target_task = frenchie.tasks.get(index);
                    frenchie.deleteTask(index);
                    System.out.println("____________________________________________________________\n" +
                            "Noted. I've removed this task: \n" +
                            target_task.toString()   + "\n" +
                            "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                            "____________________________________________________________");
                } else if (input.contains("event") || input.contains("todo") || input.contains("deadline")) {
                    String[] parts = input.split(" ");
                    String taskType = parts[0];
                    if (parts.length <= 1) {
                        throw new FrenchieException("____________________________________________________________\n" +
                                "OOPS!!! The description of a " + taskType + " cannot be empty.\n" +
                                "____________________________________________________________");
                    } else {
                        if (taskType.equals("todo")) {
                            String taskName = input.split("todo")[1].trim();
                            ToDo currentTask = new ToDo(taskName);
                            frenchie.addTask(currentTask);
                            System.out.println("____________________________________________________________\n" +
                                    " Got it! I've added this task: \n" +
                                    currentTask + "\n" +
                                    "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                                    "____________________________________________________________");
                        } else if (taskType.equals("deadline")) {
                            String taskName = input.split("/by")[0].split("deadline")[1].trim();
                            String deadline = input.split("/by")[1].trim();
                            Deadline currentTask = new Deadline(taskName, deadline);
                            frenchie.addTask(currentTask);
                            System.out.println("____________________________________________________________\n" +
                                    " Got it! I've added this task: \n" +
                                    currentTask + "\n" +
                                    "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                                    "____________________________________________________________");
                        } else {
                            String taskName = input.split("/")[0].split("event")[1].trim();
                            String startTime = input.split("/from")[1].split("/to")[0].trim();
                            String endTime = input.split("/from")[1].split("/to")[1].trim();
                            Event currentTask = new Event(taskName, startTime, endTime);
                            frenchie.addTask(currentTask);
                            System.out.println("____________________________________________________________\n" +
                                    " Got it! I've added this task: \n" +
                                    currentTask + "\n" +
                                    "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                                    "____________________________________________________________");
                        }
                        }
                    } else {
                        throw new FrenchieException("____________________________________________________________\n" +
                                "OOPS!!! I'm sorry but I don't know what that means! :-(\n" +
                                "____________________________________________________________");
                    }
                frenchie.saveTasksToFile();
                } catch (FrenchieException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
