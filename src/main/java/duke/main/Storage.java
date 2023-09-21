package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandList;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * A class handling the saving and load the taskList and commandList
 * into a file.
 */
public class Storage {

    /**
     * The path of the file where the taskList is saved
     */
    protected String filePath;

    /**
     * The path of the file where the previous commands are saved
     */
    protected String fileCommandPath;

    /**
     * The constructor for the Storage
     */
    public Storage() {
        String home = System.getProperty("user.home");
        Path path1 = Paths.get(home, "data", "duke.txt");
        Path path2 = Paths.get(home, "data", "dukePreviousCommands.txt");
        File file1 = new File(path1.toString());
        File file2 = new File(path2.toString());
        try {
            file1.getParentFile().mkdirs();
            if (!file1.exists()) {
                file1.createNewFile();
            }
            this.filePath = path1.toString();
        } catch (IOException e) {
            System.out.println("Cannot create file for duke!");
        }
        try {
            file2.getParentFile().mkdirs();
            if (!file2.exists()) {
                file2.createNewFile();
            }
            this.fileCommandPath = path2.toString();
        } catch (IOException e) {
            System.out.println("Cannot create file for previous commands!");
        }
    }

    /**
     * Writes the data into the file.
     * @param data The content to be written into the file
     */
    public void writeData(String data) {
        try {
            FileWriter file = new FileWriter(this.filePath, false);
            file.write(data);
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing to duke file!");
        }

    }

    /**
     * Writes the previous commands into the file.
     * @param data The content to be written into the file
     */
    public void previousCommandsWriter(String data) {
        try {
            FileWriter file = new FileWriter(this.fileCommandPath, false);
            file.write(data);
            file.close();
        } catch (IOException e) {
            System.out.println("Error writing to previous commands file!");
        }

    }

    /**
     * Reads the content of the file
     * @return TaskList
     */
    public TaskList loadData() {
        TaskList tempList = new TaskList();
        File file = new File(this.filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tempList.addTask(parseLine(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        return tempList;
    }

    /**
     * Reads the content of the file for previous commands
     * @return list of previous commands
     */
    public CommandList previousCommandsLoader() {
        CommandList tempList = new CommandList();
        File file = new File(this.fileCommandPath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                tempList.addCommand(previousCommandsLineParser(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Command File is not found!");
        }
        return tempList;
    }

    /**
     * Reads the line and convert them into Task objects
     * @param line The line read from the file
     * @return Task
     */
    public Task parseLine(String line) {
        String[] input = line.split(" \\| ");
        if (input[0].equals("T")) {
            Todo task = new Todo(input[2]);
            if (input[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (input[0].equals("D")) {
            Deadline task = new Deadline(input[2], input[3]);
            if (input[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        } else if (input[0].equals("E")) {
            Event task = new Event(input[2], input[3], input[4]);
            if (input[1].equals("1")) {
                task.markAsDone();
            }
            return task;
        }
        return null;
    }

    /**
     * Reads the line and convert them into Commands objects
     * @param line The line read from the file
     * @return Command
     */
    public Command previousCommandsLineParser(String line) {
        String[] input = line.split(" \\| ");
        if (input[0].equals("delete")) {
            String tempLine = "";
            for (int i = 0; i < input.length; i++) {
                if (i < 2) {
                    continue;
                }
                if (i < input.length - 1) {
                    tempLine += input[i] + " | ";
                } else {
                    tempLine += input[i];
                }
            }
            Task task = parseLine(tempLine);
            DeleteCommand comm = new DeleteCommand(Integer.parseInt(input[1]));
            comm.taskSetter(task);
            return comm;
        } else if (input[0].equals("add")) {
            return new AddCommand("", "", "", "");
        } else if (input[0].equals("mark")) {
            return new MarkCommand(Integer.parseInt(input[1]));
        } else if (input[0].equals("unmark")) {
            return new UnmarkCommand(Integer.parseInt(input[1]));
        }
        return null;
    }

}
