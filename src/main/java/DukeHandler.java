import TaskPackages.TaskList;
import Utility.DukeException;
import Utility.InvalidCommandException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DukeHandler {
    
    TaskList tasklist;

    public DukeHandler() {
        tasklist = returnTaskList();
    }

    public void writeTaskList() {
        String homedir = new File(System.getProperty("user.dir")).getParent();
        Path path = Paths.get(homedir, "data", "duke.txt");
        System.err.println(path);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            } 
            BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            while (!tasklist.isEmpty()) {
                String tempString = tasklist.clearList();
                System.err.println(tempString);
                writer.write(tempString + "\n");
                writer.flush();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private TaskList returnTaskList() {
        String homedir = new File(System.getProperty("user.dir")).getParent();
        Path path = Paths.get(homedir, "data", "duke.txt");
        if (Files.exists(path)) {
            TaskList tasklist = new TaskList();
            try {
                List<String> contents = Files.readAllLines(path);
                for (String content : contents) {
                    System.err.println(content);
                    String[] parsedContent = content.split(" # ");
                    System.err.println(parsedContent.length);
                    System.err.println(parsedContent[0]);
                    if (parsedContent.length == 4 && parsedContent[0].charAt(0) == 'D') {
                        System.err.println(tasklist.addDeadline(parsedContent[2] + " " + parsedContent[3]));
                    } else if (parsedContent.length == 5 && parsedContent[0].charAt(0) == 'E') {
                        System.err.println(tasklist.addEvent(parsedContent[2] + " " + parsedContent[3] + " " + parsedContent[4]));
                    } else if (parsedContent.length == 3 && parsedContent[0].charAt(0) == 'T') {
                        System.err.println(tasklist.addTodo(parsedContent[2]));
                    } else {
                        System.err.println("Invalid file format");
                        break;
                    }
                    if (Integer.parseInt(parsedContent[1]) == 1) {
                        tasklist.markAsDone(String.format("%d", tasklist.getSize()));
                    }
                }
                return tasklist;
            } catch (FileNotFoundException e) {
                System.err.println(e);
                return new TaskList();
            } catch (IOException e) {
                System.err.println(e);
                return new TaskList();
            } catch (NumberFormatException e) {
                System.err.println(e);
                return new TaskList();
            } catch (Exception e) {
                System.err.println(e);
                return new TaskList();
            }
        } else {
            return new TaskList();
        }
    }

    private static String[] inputParser(String input) {
        int index = input.indexOf(' ');
        if (index > -1) {
            return input.split(" ", 2);
        } else {
            String[] tempString = {input, ""};
            return tempString;
        }
    }

    public boolean checkExit(String input) {
        if (input.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public DukeEnum map(String command) throws DukeException {
        for(DukeEnum e: DukeEnum.values()) {
            if (command.equals(e.getText())) {
                return e;
            }  
        }
        throw new InvalidCommandException();
    }

    public String handle(String input) throws DukeException{
        String[] parsedInput = inputParser(input);
        String command = parsedInput[0];
        String rest = parsedInput[1];
        
        try {
            DukeEnum commandtype = map(command);
            switch (commandtype) {
                case LIST:
                    return tasklist.toString();
                case MARK:
                    return tasklist.markAsDone(rest);
                case UNMARK:
                    return tasklist.unmarkAsDone(rest);
                case TODO:
                    return tasklist.addTodo(rest);
                case DEADLINE:
                    return tasklist.addDeadline(rest);
                case EVENT:
                    return tasklist.addEvent(rest);
                case DELETE:
                    return tasklist.delete(rest);
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            throw e;
        } 
    }
}
