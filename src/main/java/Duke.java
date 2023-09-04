import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    private static boolean createFile(File f)  {
        try {
            return f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    private static void makeDataDir() {
        File dataDirectory = new File("./data/");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
    }

    private static void appendToFile(String filePath, Task taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd.toData());
        fw.write("\n");
        fw.close();
    }

    private void loadData(File file) throws DukeException{
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String taskType = line.substring(0, 1);
                switch (taskType) {
                case "T":
                    tasks.add(Todo.dataToTask(line.substring(4)));
                    break;
                case "E":
                    tasks.add(Event.dataToTask(line.substring(4)));
                    break;
                case "D":
                    tasks.add(Deadline.dataToTask(line.substring(4)));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeAllToFile(File f) {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toData());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws DukeException {
        this.ui.greet();
        Scanner input = new Scanner(System.in);

        String filepath = "data/duke.txt";
        makeDataDir();
        File f = new File(filepath);
        if (!createFile(f)) {
            try {
                loadData(f);
            } catch (DukeException e) {
                throw e;
            }
        }

        boolean end = false;
        while (!end) {
            try {
                String userInput = input.nextLine();

                int spaceIndex = userInput.indexOf(" ");
                if (spaceIndex == -1) {
                    switch (userInput) {
                    case "list":
                        ui.printList(tasks);
                        break;
                    case "bye":
                        end = true;
                        break;
                    case "todo":
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    case "event":
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    case "deadline":
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    switch (userInput.substring(0, spaceIndex)) {
                    case "todo":
                        String todoDesc = userInput.substring(spaceIndex + 1);
                        if (todoDesc.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task newTodo = new Todo(todoDesc);
                        tasks.add(newTodo);
                        try {
                            appendToFile(filepath, newTodo);
                        } catch (IOException e) {
                            throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
                        }
                        ui.printTaskAdded(newTodo, tasks.size());
                        break;
                    case "event":
                        int fromIndex = userInput.indexOf("/from");
                        int toIndex = userInput.indexOf("/to");
                        String eventDesc = userInput.substring(spaceIndex + 1, fromIndex - 1);
                        if (eventDesc.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String from = userInput.substring(fromIndex + 6, toIndex - 1);
                        String to = userInput.substring(toIndex + 4);
                        Task newEvent = new Event(eventDesc, from, to);
                        tasks.add(newEvent);
                        try {
                            appendToFile(filepath, newEvent);
                        } catch (IOException e) {
                            throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
                        }
                        ui.printTaskAdded(newEvent, tasks.size());
                        break;
                    case "deadline":
                        int byIndex = userInput.indexOf("/by");
                        String deadlineDesc = userInput.substring(spaceIndex + 1, byIndex - 1);
                        if (deadlineDesc.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String by = userInput.substring(byIndex + 4);
                        Task newDeadline = new Deadline(deadlineDesc, by);
                        tasks.add(newDeadline);
                        try {
                            appendToFile(filepath, newDeadline);
                        } catch (IOException e) {
                            throw new DukeException("☹ OOPS!!! There is something wrong with the description.");
                        }
                        ui.printTaskAdded(newDeadline, tasks.size());
                        break;
                    case "mark":
                        int i = Integer.parseInt(userInput.split(" ", 2)[1]);
                        Task taskToMark = tasks.get(i - 1);
                        taskToMark.markAsDone();
                        writeAllToFile(f);
                        ui.printTaskMarked(taskToMark);
                        break;
                    case "unmark":
                        int j = Integer.parseInt(userInput.split(" ", 2)[1]);
                        Task taskToUnmark = tasks.get(j - 1);
                        taskToUnmark.markAsNotDone();
                        writeAllToFile(f);
                        ui.printTaskUnmarked(taskToUnmark);
                        break;
                    case "delete":
                        int k = Integer.parseInt(userInput.split(" ", 2)[1]);
                        if (k > tasks.size() || k < 0) {
                            throw new DukeException("Integer out of list range");
                        }
                        Task deletedTask = tasks.get(k - 1);
                        tasks.remove(k - 1);
                        writeAllToFile(f);
                        ui.printTaskDeleted(deletedTask, tasks.size());

                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }

        this.ui.sendOff();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}