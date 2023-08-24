import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class EpochMind {
    public static String logo = "\n" +
            "                                                                                                    \n" +
            "                                                             ____                                   \n" +
            "    ,---,.                                ,---,            ,'  , `.                                 \n" +
            "  ,'  .' |,-.----.                      ,--.' |         ,-+-,.' _ |  ,--,                     ,---, \n" +
            ",---.'   |\\    /  \\    ,---.            |  |  :      ,-+-. ;   , ||,--.'|         ,---,     ,---.'| \n" +
            "|   |   .'|   :    |  '   ,'\\           :  :  :     ,--.'|'   |  ;||  |,      ,-+-. /  |    |   | : \n" +
            ":   :  |-,|   | .\\ : /   /   |   ,---.  :  |  |,--.|   |  ,', |  ':`--'_     ,--.'|'   |    |   | | \n" +
            ":   |  ;/|.   : |: |.   ; ,. :  /     \\ |  :  '   ||   | /  | |  ||,' ,'|   |   |  ,\"' |  ,--.__| | \n" +
            "|   :   .'|   |  \\ :'   | |: : /    / ' |  |   /' :'   | :  | :  |,'  | |   |   | /  | | /   ,'   | \n" +
            "|   |  |-,|   : .  |'   | .; :.    ' /  '  :  | | |;   . |  ; |--' |  | :   |   | |  | |.   '  /  | \n" +
            "'   :  ;/|:     |`-'|   :    |'   ; :__ |  |  ' | :|   : |  | ,    '  : |__ |   | |  |/ '   ; |:  | \n" +
            "|   |    \\:   : :    \\   \\  / '   | '.'||  :  :_:,'|   : '  |/     |  | '.'||   | |--'  |   | '/  ' \n" +
            "|   :   .'|   | :     `----'  |   :    :|  | ,'    ;   | |`-'      ;  :    ;|   |/      |   :    :| \n" +
            "|   | ,'  `---'.|              \\   \\  / `--''      |   ;/          |  ,   / '---'        \\   \\  /   \n" +
            "`----'      `---`               `----'             '---'            ---`-'                `----'    \n" +
            "                                                                                                    \n";
    public static String[] exitMessage = {"May you seek the truth.", "May the truth prevail.", "May you yet again thirst for wisdom.", "May your quest for wisdom be unwavering.", "May your pursuit of knowledge illuminate your path."};
    public static List<Task> tasks = new ArrayList<>();
    public static String filePath = new File("").getAbsolutePath() + "/data/EpochMind.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(logo + "___________________________________________________________________________________________________________\n" +
                " Greetings! I'm EpochMind, Seer of the Cosmos, Keeper of Knowledge, Pantheon of Wisdom, the Eternal Truth\n" +
                " Ask and ye may receive.\n" +
                "___________________________________________________________________________________________________________\n");
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] commandList = command.trim().toLowerCase().split(" ");
            switch (commandList[0]) {
                case "bye":
                    bye();
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(commandList);
                    break;
                case "unmark":
                    unmark(commandList);
                    break;
                case "todo":
                    todo(command);
                    break;
                case "deadline":
                    deadline(command);
                    break;
                case "event":
                    event(command);
                    break;
                case "delete":
                    delete(commandList);
                    break;

                default:
                    EpochMindException e = new EpochMindException("There is no such command in the Arcana of Knowledge");
                    System.out.println(e);
                    break;
            }
            save(filePath);
        }
    }

    /**
     * Exits the program
     */
    public static void bye() {
        System.out.println("___________________________________________________________________________________________________________\n" +
                " May you seek the truth\n" +
                "___________________________________________________________________________________________________________\n");
        System.exit(0);
    }

    /**
     * List out the task list
     */
    public static void list() {
        System.out.println("___________________________________________________________________________________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i+1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            System.out.println(sb);
        }
        System.out.println("___________________________________________________________________________________________________________\n");
    }

    /**
     * Mark a task as completed
     * @param commandList An Array of Strings of the command, separated by whitespaces
     */
    public static void mark(String[] commandList) {
        if (commandList.length == 1) {
            EpochMindException e = new EpochMindException("Thou hast forgotten to specify thine index");
            System.out.println(e);
        } else {
            try {
                int index = parseInt(commandList[1]);
                if (tasks.size() < index) {
                    EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                    System.out.println(e);
                } else {
                    tasks.get(index - 1).mark();
                    System.out.println("___________________________________________________________________________________________________________\n" + "The Mind sees that this task is completed \n" +
                            tasks.get(index - 1) +
                            "\n___________________________________________________________________________________________________________\n");
                }
            } catch (Exception e) {
                System.out.println("Thou hast given an invalid argument");
            }
        }
    }

    /**
     * Unmark a completed task
     * @param commandList An Array of Strings of the command, separated by whitespaces
     */
    public static void unmark(String[] commandList) {
        if (commandList.length == 1) {
            EpochMindException e = new EpochMindException("Thou hast forgotten to specify thine index");
            System.out.println(e);
        } else {

            try {
                int index = parseInt(commandList[1]);
                if (tasks.size() < index) {
                    EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                    System.out.println(e);
                } else {
                    tasks.get(index - 1).unmark();
                    System.out.println("___________________________________________________________________________________________________________\n" + "The Mind sees that this task is not yet completed \n" +
                            tasks.get(index - 1) +
                            "\n___________________________________________________________________________________________________________\n");
                }
            } catch (Exception e) {
                System.out.println("Thou hast given an invalid argument");
            }
        }
    }

    /**
     * Add a todo task
     * @param command String obtained from scanner
     */
    public static void todo(String command) {
        String restOfCommand = removeCommandWord(command);
        if (restOfCommand.trim().equals("")) {
            EpochMindException e = new EpochMindException("Thou hast not specified a task");
            System.out.println(e);
        } else {
            ToDo toDo = new ToDo(restOfCommand);
            tasks.add(toDo);
            System.out.println("___________________________________________________________________________________________________________\n" + "The Mind has added a new task \n" + toDo + "\nThere are now " + tasks.size() + " tasks left to complete" +
                    "\n___________________________________________________________________________________________________________\n");
        }

    }

    /**
     * Add a deadline task
     * @param command String obtained from scanner
     */
    public static void deadline(String command) {
        String restOfCommand = removeCommandWord(command);
        int endIndex = restOfCommand.indexOf("/by ");
        if (endIndex == -1) {
            EpochMindException e = new EpochMindException("The Mind needs a deadline");
            System.out.println(e);
        } else {
            String taskDescription = restOfCommand.substring(0, endIndex).trim();
            if (taskDescription.trim().equals("")) {
                EpochMindException e = new EpochMindException("Thou hast not specified a task");
                System.out.println(e);
            } else {
                int deadlineIndex = restOfCommand.indexOf("/by ") + 3;
                String deadlineString = restOfCommand.substring(deadlineIndex);
                Deadline deadline = new Deadline(taskDescription, deadlineString);
                tasks.add(deadline);
                System.out.println("___________________________________________________________________________________________________________\n" + "The Mind has added a new task \n" + deadline + "\nThere are now " + tasks.size() + " tasks left to complete" +
                        "\n___________________________________________________________________________________________________________\n");
            }
        }
    }

    /**
     * Add an event task
     * @param command String obtained from scanner
     */
    public static void event(String command) {
        String restOfCommand = removeCommandWord(command);
        int fromIndex = restOfCommand.indexOf("/from");
        if (fromIndex == -1) {
            EpochMindException e = new EpochMindException("The Mind needs a start time");
            System.out.println(e);
        } else {
            String description = restOfCommand.substring(0, fromIndex).trim();
            if (description.trim().equals("")) {
                EpochMindException e = new EpochMindException("Thou hast not specified a task");
                System.out.println(e);
            } else {
                fromIndex = restOfCommand.indexOf("/from") + "/from".length();
                int toIndex = restOfCommand.indexOf("/to");
                if (toIndex == -1) {
                    EpochMindException e = new EpochMindException("The Mind needs a end time");
                    System.out.println(e);
                } else {
                    String startString = restOfCommand.substring(fromIndex, toIndex).trim();
                    toIndex = restOfCommand.indexOf("/to") + "/to".length();
                    String endString = restOfCommand.substring(toIndex);
                    Event event = new Event(description, startString, endString);
                    tasks.add(event);
                    System.out.println("___________________________________________________________________________________________________________\n" + "The Mind has added a new task \n" + event + "\nThere are now " + tasks.size() + " tasks left to complete" +
                            "\n___________________________________________________________________________________________________________\n");
                }
            }
        }
    }

    /**
     * Delete a task from the list
     * @param commandList An Array of Strings of the command, separated by whitespaces
     */
    public static void delete(String[] commandList) {
        if (commandList.length == 1) {
            EpochMindException e = new EpochMindException("Thou hast forgotten to specify thine index");
            System.out.println(e);
        } else {
            int index = parseInt(commandList[1]);
            if (tasks.size() < index) {
                EpochMindException e = new EpochMindException("Thou hast specified an index larger than thy list");
                System.out.println(e);
            } else {
                Task task = tasks.remove(index - 1);
                System.out.println("___________________________________________________________________________________________________________\n" + "The Mind has eradicated the task \n" +
                        task + "\nThere are now " + tasks.size() + " tasks left to complete" +
                        "\n___________________________________________________________________________________________________________\n");

            }
        }
    }

    /**
     * Helper function to remove the first command word
     * @param command String obtained from scanner
     * @return String without first command word
     */
    public static String removeCommandWord(String command) {
        int firstSpaceIndex = command.indexOf(' ');

        if (firstSpaceIndex == -1 || firstSpaceIndex == command.length() - 1) {
            return "";
        }
        // Extract the substring starting from the position after the first space
        return command.substring(firstSpaceIndex + 1);
    }

    /**
     * Saves the task list to a txt file at the path
     * @param filePath The path to store the file
     */
    public static void save(String filePath) {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        try (PrintWriter pw = new PrintWriter(file);){
            for (Task task : tasks) {
                pw.println(task.toString());
            }
        } catch (Exception e) {
            System.out.println("The Mind has failed to save the tasks");
        }

    }
}
