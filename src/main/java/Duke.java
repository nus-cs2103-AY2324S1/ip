import java.io.*;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String SAVE_FILE = "data/saved_tasks.csv";

    private static int checkIndexArg(String indexArg, int lstSize){
        if (!indexArg.matches("^\\d+$")) {
            return -1;
        }
        int index = Integer.parseInt(indexArg) - 1;
        if (0 > index || index >= lstSize){
            return -1;
        }
        return index;
    }

    public static void main(String[] args) {
        File saveFile = new File(SAVE_FILE);
        if (!saveFile.exists()) {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String name = "Ip Bot";
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello I'm " + name + "!");
        System.out.println("While I may not be able to fight like Ip Man, I can assist you in other areas!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);

        List<Task> list = new ArrayList<>();

        try {
            FileReader fr = new FileReader(SAVE_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Task.fromString(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TaskFormatException e) {
            System.out.println(e.getMessage());
            return;
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return;
        }

        while(true) {
            String command = scanner.nextLine().strip();
            System.out.println(HORIZONTAL_LINE);
            try {
                if (command.isEmpty()) {
                    System.out.println("Nothing happened!");
                } else {
                    int commandEndIndex = 0;
                    while (commandEndIndex < command.length() && command.charAt(commandEndIndex) != ' ') {
                        commandEndIndex++;
                    }
                    String commandNameStr = command.substring(0, commandEndIndex).toLowerCase();
                    Command commandName = Command.commandEnum(commandNameStr);
                    if(commandName == null){
                        System.out.println("Error: " + commandNameStr + " is not a valid command!");
                    }
                    else {
                        String commandArgs = command.substring(commandEndIndex).strip();
                        if (command.equalsIgnoreCase("bye")) {
                            System.out.println("Bye. Hope to see you again soon!");
                            System.out.println(HORIZONTAL_LINE);
                            break;
                        }
                        String desc;
                        switch (commandName) {
                            case LIST:
                                if (!commandArgs.isEmpty()) {
                                    throw new CommandArgumentException("list does not take in arguments!");
                                }
                                for (int index = 0; index < list.size(); index++) {
                                    System.out.printf("%d. %s\n", index + 1, list.get(index).toString());
                                }
                                break;
                            case MARK:
                                int markIndex = checkIndexArg(commandArgs, list.size());
                                if (markIndex == -1) {
                                    throw new CommandArgumentException("Invalid task to mark: " + commandArgs);
                                }
                                boolean wasNotMarked = list.get(markIndex).markDone();
                                if (wasNotMarked) {
                                    System.out.println("Marking task as done: " + list.get(markIndex).toString());
                                } else {
                                    System.out.println("Task was already marked as done: " + list.get(markIndex).toString());
                                }
                                break;
                            case UNMARK:
                                int unmarkIndex = checkIndexArg(commandArgs, list.size());
                                if (unmarkIndex == -1) {
                                    throw new CommandArgumentException("Invalid task to unmark: " + commandArgs);
                                }
                                boolean wasNotUnmarked = list.get(unmarkIndex).unmarkDone();
                                if (wasNotUnmarked) {
                                    System.out.println("Marking task as undone: " + list.get(unmarkIndex).toString());
                                } else {
                                    System.out.println("Task was already marked as undone: " + list.get(unmarkIndex).toString());
                                }
                                break;
                            case TODO:
                                if (commandArgs.isEmpty()) {
                                    throw new CommandArgumentException("Task description cannot be empty!");
                                }
                                list.add(new ToDo(commandArgs));
                                System.out.println("Added todo item: " + list.get(list.size() - 1));
                                break;
                            case DEADLINE:
                                int byIndex = commandArgs.indexOf("/by");
                                if (byIndex == -1) {
                                    throw new CommandArgumentException("Deadline missing a /by argument!");
                                }
                                desc = commandArgs.substring(0, byIndex).strip();
                                String by = commandArgs.substring(byIndex + "/by".length()).strip();
                                if (desc.isEmpty()) {
                                    throw new CommandArgumentException("Task description cannot be empty!");
                                }
                                if (by.isEmpty()) {
                                    throw new CommandArgumentException("/by argument cannot be empty!");
                                }
                                list.add(new Deadline(desc, by));
                                System.out.println("Added deadline item: " + list.get(list.size() - 1));
                                break;
                            case EVENT:
                                int fromIndex = commandArgs.indexOf("/from");
                                int toIndex = commandArgs.indexOf("/to");
                                if (fromIndex == -1) {
                                    throw new CommandArgumentException("Deadline missing a /from argument!");
                                }
                                if (toIndex == -1) {
                                    throw new CommandArgumentException("Deadline missing a /to argument!");
                                }
                                String from, to;
                                if (fromIndex < toIndex) {
                                    desc = commandArgs.substring(0, fromIndex).strip();
                                    from = commandArgs.substring(fromIndex + "/from".length(), toIndex).strip();
                                    to = commandArgs.substring(toIndex + "/to".length()).strip();
                                } else {
                                    desc = commandArgs.substring(0, toIndex).strip();
                                    from = commandArgs.substring(fromIndex + "/from".length()).strip();
                                    to = commandArgs.substring(toIndex + "/to".length(), fromIndex).strip();
                                }
                                if (desc.isEmpty()) {
                                    throw new CommandArgumentException("Task description cannot be empty!");
                                }
                                if (from.isEmpty()) {
                                    throw new CommandArgumentException("/from argument cannot be empty!");
                                }
                                if (to.isEmpty()) {
                                    throw new CommandArgumentException("/to argument cannot be empty!");
                                }
                                list.add(new Event(desc, from, to));
                                System.out.println("Added event item: " + list.get(list.size() - 1));
                                break;
                            case DELETE:
                                int deleteIndex = checkIndexArg(commandArgs, list.size());
                                if (deleteIndex == -1) {
                                    throw new CommandArgumentException("Invalid task to delete: " + commandArgs);
                                }
                                Task task = list.remove(deleteIndex);
                                System.out.println("Deleted item: " + task.toString());
                                break;
                        }
                    }
                }
            }
            catch(CommandArgumentException e){
                System.out.println(e.getMessage());
            }
            catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(HORIZONTAL_LINE);
            String writeString = "";
            for (int i=0; i<list.size(); i++) {
                writeString += list.get(i).toCommaString() + "\n";
            }
            try {
                FileWriter fw = new FileWriter(SAVE_FILE);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(writeString);
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
