package duke;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
        assert counter >= 0 : "Counter should not be negative";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(counter + "\n");
            for (int i = 0; i < counter; i++) {
                int isDoneNum = isDone[i] ? 1 : 0;
                writer.write(type[i] + " | " + isDoneNum + " | " + actions[i]);

                if (type[i].equals("D")) {
                    assert dueStrings[i] != null : "Due string should not be null";
                    writer.write(" | " + dueStrings[i]);
                } else if (type[i].equals("E")) {
                    assert startTimes[i] != null : "Start time should not be null";
                    assert endTimes[i] != null : "End time should not be null";
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
    public static int load(String fileName, String[] actions, String[] type, boolean[] isDone, String[] dueStrings, LocalDateTime[] startTimes, LocalDateTime[] endTimes) {
        int count = 0;
        try (FileReader fileReader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(fileReader);
            Arrays.fill(actions, null);
            Arrays.fill(type, null);
            Arrays.fill(isDone, false);
            Arrays.fill(dueStrings, null); // Clear dueStrings array
            Arrays.fill(startTimes, null); // Clear startTimes array
            Arrays.fill(endTimes, null);   // Clear endTimes array

            while (scanner.hasNextLine() && count < actions.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length >= 3) {
                    type[count] = parts[0];
                    isDone[count] = parts[1].equals("1");
                    actions[count] = parts[2];

                    if (type[count].equals("D")) {
                        if (parts.length >= 4) {
                            dueStrings[count] = parts[3];
                        } else {
                            dueStrings[count] = ""; // Set a default value
                        }
                    } if (type[count].equals("E")) {
                        if (parts.length >= 6) {
                            String startTimeStr = parts[4];
                            String endTimeStr = parts[5];

                            LocalDateTime startTimeValue = tryParseDateTime(startTimeStr, "d/M/yyyy HHmm");
                            LocalDateTime endTimeValue = tryParseDateTime(endTimeStr, "d/M/yyyy HHmm");

                            if (startTimeValue != null && endTimeValue != null) {
                                startTimes[count] = startTimeValue;
                                endTimes[count] = endTimeValue;
                            } else {
                                // Handle parsing error, e.g., log a warning or set default values
                            }
                        } else {
                            // Set startTimes and endTimes to appropriate default values or null
                            startTimes[count] = null;
                            endTimes[count] = null;
                        }
                    }
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    private static LocalDateTime tryParseDateTime(String dateTimeStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            // Handle parsing error, e.g., log a warning or return null
            return null;
        }
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

    public String[] loadDueStrings() {
        String[] dueStrings = new String[100];
        int count = 0;
        try (FileReader fileReader = new FileReader(fileDir)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                if (dueStrings.length > count) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts[0].equals("D")) {
                        dueStrings[count] = parts[3]; // Assuming due string is at index 4
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dueStrings;
    }

    public LocalDateTime[] loadStartTimes() {
        LocalDateTime[] startTimes = new LocalDateTime[100];
        int count = 0;
        try (FileReader fileReader = new FileReader(fileDir)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                if (startTimes.length > count) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 6 && parts[0].equals("E")) {
                        startTimes[count] = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return startTimes;
    }

    public LocalDateTime[] loadEndTimes() {
        LocalDateTime[] endTimes = new LocalDateTime[100];
        int count = 0;
        try (FileReader fileReader = new FileReader(fileDir)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                if (endTimes.length > count) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 6 && parts[0].equals("E")) {
                        endTimes[count] = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return endTimes;
    }
}





