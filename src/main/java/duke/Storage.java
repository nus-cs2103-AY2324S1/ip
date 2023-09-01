package duke;

import java.io.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The storage class handles saving and loading data.
 *
 */
public class Storage {
    private String fileDir;

    public Storage(String fileDir) {
        this.fileDir = fileDir;
    }

    /**
     * Saves the task data to a file for future retrival.
     * @param fileName name of file to save data.
     * @param actions Array of actions.
     * @param type Array of type of the action.
     * @param isDone Array of boolean indicating whether an action is done.
     * @param dueStrings Array of String indicating when action is due.
     * @param startTimes Array indicating starting time of activity.
     * @param endTimes Array indicating ending time of activity.
     * @param counter The number of tasks in list.
     */

    public static void save(String fileName, String[] actions, String[] type, boolean[] isDone, String[] dueStrings, LocalDateTime[] startTimes, LocalDateTime[] endTimes, int counter) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(counter + "\n");
            for (int i = 0; i < counter; i++) {
                int isDoneNum = isDone[i] ? 1 : 0;
                writer.write(type[i] + " | " + isDoneNum + " | " + actions[i]);

                if (type[i].equals("D")) {
                    writer.write(" | " + dueStrings[i]);
                } else if (type[i].equals("E")) {
                    writer.write(" | " + startTimes[i].format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                            + " | " + endTimes[i].format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm")));
                }

                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data retrived from the saved file.
     *
     * @param fileName File's name to save the data.
     * @param actions Array to show name of action.
     * @param type Array of the type of action.
     * @param isDone Array of boolean indicating whether action is done.
     * @return number of tasks saved in tasklist.
     */
    public static int load(String fileName, String[] actions, String[] type, boolean[] isDone) {
        int count = 0;
        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine() && count < actions.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                // to split the line read into the different parts action num and done
                if (parts.length >= 3) {
                    type[count] = parts[0];
                    isDone[count] = parts[1].equals("1");
                    actions[count] = parts[2];
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    /**
     * Loads an array whether the activity is done.
     * @return Array of whether the task is done.
     */
    public boolean[] loadIsDone() {
        boolean[] isDone = new boolean[100];
        try (FileReader fileReader = new FileReader(fileDir)) {
            Scanner scanner = new Scanner(fileReader);
            int count = 0;
            while (scanner.hasNextLine()) {
                if (isDone.length > count) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 3) {
                        isDone[count] = parts[1].equals("1");
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return isDone;
    }

    /**
     * An array of type of activity.
     * @return Array of activity's type.
     */
    public String[] loadTypes() {
        String[] type = new String[100];
        int count = 0;
        try (FileReader fileReader = new FileReader(fileDir)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                if (type.length > count) {
                    count = 0;
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 3) {
                        type[count] = parts[0];
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return type;
    }

    /**
     * Loads the array of action.
     * @return An array of actions.
     */
    public String[] loadActions() {
        String[] actions = new String[100];
        int count = 0;
        try (FileReader fileReader = new FileReader(fileDir)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                if (actions.length > count) {
                    count = 0;
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 3) {
                        actions[count] = parts[0];
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return actions;
    }
}





