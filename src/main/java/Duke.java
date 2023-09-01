import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String horizontalLine = "___________________________________________________________________";
    static String filePath = "./data/duke.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine
                + "\nHello i'm ChatterBuddy\n"
                + "Is there anything I can assist you with today?\n"
                + horizontalLine);

        ArrayList<Task> tasks = new ArrayList<>();
        String userInput;

        try {
            tasks = load();
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }

        do {
            userInput = scanner.nextLine();
            String[] individualWords = userInput.split(" ");
            String firstWord = individualWords[0];
            String lowerCapsFirstWord = firstWord.toLowerCase();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            try {
                if (lowerCapsFirstWord.equalsIgnoreCase("list")) {
                    if (tasks.size() == 0) {
                        System.out.println("You have 0 task.");
                    }
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + "." + task.toString());
                    }
                    System.out.println(horizontalLine);

                } else if (lowerCapsFirstWord.equalsIgnoreCase("mark")) {
                    if (individualWords.length <= 1) {
                        throw new MissingInputException("Task to mark cannot be empty!");
                    }
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(5)) - 1;
                        Task task = tasks.get(taskNumber);
                        task.updateTaskStatus(true, "Task " + (taskNumber + 1) + " is already done!", "Great job! Task " + (taskNumber + 1) + " is done!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task number.");
                    }

                } else if (lowerCapsFirstWord.equalsIgnoreCase("unmark")) {
                    if (individualWords.length <= 1) {
                        throw new MissingInputException("Task to unmark cannot be empty!");
                    }
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                        Task task = tasks.get(taskNumber);
                        task.updateTaskStatus(false, "Task " + (taskNumber + 1) + " is still incomplete.", "Okay, i've updated Task " + (taskNumber + 1) + " to be incomplete.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task number.");
                    }

                } else if (lowerCapsFirstWord.equalsIgnoreCase("delete")) {
                    if (userInput.length() <= 7 || individualWords.length <= 1) {
                        throw new MissingInputException("Task to be deleted cannot be empty!");
                    }
                    try {
                        int taskNumber = Integer.parseInt(userInput.substring(7)) - 1;
                        Task deletedTask = tasks.remove(taskNumber);
                        System.out.println("This task has been removed\n  " + deletedTask + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task number.");
                    }

                } else if (lowerCapsFirstWord.equalsIgnoreCase("todo")) {
                    if (individualWords.length <= 1) {
                        throw new MissingInputException("The description of a todo cannot be empty!");
                    }
                    String description = userInput.substring(5).trim();
                    ToDo task = new ToDo(description);
                    tasks.add(task);
                    System.out.println("I've added this task:\n  " + task + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);

                } else if (lowerCapsFirstWord.equalsIgnoreCase("deadline")) {
                    if (individualWords.length <= 1) {
                        throw new MissingInputException("The description/ deadline of a deadline cannot be empty!");
                    }
                    try {
                        String fullStr = userInput.substring(9);
                        String[] parts = fullStr.split("/by");
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        Deadline task = new Deadline(description, by);

                        if (task.dateTime != null) {
                            tasks.add(task);
                            System.out.println("I've added this task:\n  " + task + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MissingInputException("You are missing one or some of these inputs - description/ by.");
                    }


                } else if (lowerCapsFirstWord.equalsIgnoreCase("event")) {
                    if (individualWords.length <= 1) {
                        throw new MissingInputException("The description/ time of an event cannot be empty!");
                    }
                    try {
                        String fullStr = userInput.substring(6);
                        String[] partialStr = fullStr.split("/from");
                        String description = partialStr[0].trim();
                        String[] toFrom = partialStr[1].split("/to");
                        String from = toFrom[0].trim();
                        String to = toFrom[1].trim();
                        Event task = new Event(description, from, to);

                        if (task.fromDateTime != null && task.toDateTime != null) {
                            tasks.add(task);
                            System.out.println("I've added this task:\n  " + task + "\nYou have a total of " + tasks.size() + (tasks.size() == 1 ? " task.\n" : " tasks.\n") + horizontalLine);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MissingInputException("You are missing one or some of these inputs - description/ from/ to.");
                    }

                } else {
                    throw new InvalidCommandException();
                }
            } catch (MissingInputException e) {
                e.print();
            } catch (InvalidCommandException e) {
                e.print();
            }

        } while (true);

        try {
            save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }

        System.out.println("Goodbye. Catch you later!" + "\n" + horizontalLine);
        scanner.close();
    }

    private static void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);

        for (Task task: tasks) {
            String taskFileString = task.toFileString();
            writer.write(taskFileString + "\n");
        }

        writer.close();
    }

    private static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.createNewFile();
        } else {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String fileLine;
            while ((fileLine = bufferedReader.readLine()) != null) {
                Task task = createTaskFromFile(fileLine);
                tasks.add(task);
            }

            bufferedReader.close();
        }

        return tasks;
    }

    private static Task createTaskFromFile(String fileLine) {
        String[] parts = fileLine.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        String type = parts[0];
        String description = parts[2];

        Task task;

        if (type.equals("[T]")) {
            task = new ToDo(description);
        } else if (type.equals("[D]")) {
            String by = parts[3];
            task = new Deadline(description, by);
        } else {
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
        }

        task.isDone = parts[1].equals("1");
        return task;
    }
}