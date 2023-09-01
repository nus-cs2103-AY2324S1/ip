package trackerbot.utils;

import trackerbot.task.TaskList;
import trackerbot.exception.TrackerBotException;
import trackerbot.task.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    private Storage() {};

    /**
     * Parses the save string in the input file. <br>
     * The save string is generated by the {@link #... toSaveString}
     * method, and will be parsed based on the format.
     *
     * @param saveStr The input save string, in the aforementioned format.
     * @return The Task object from parsing the String.
     */
    private static Task parseSaveLine(String saveStr) throws DateTimeParseException {
        String delimiter = "|";
        String[] args = saveStr.split("[|]");
        return Task.ofSaveString(args[0], Arrays.copyOfRange(args, 1, args.length));
    }

    public static void read(TaskList tasks) throws TrackerBotException {
        Path path = Paths.get("TrackerBot", "data.txt");
        if (Files.notExists(path)) {
            return;
        }

        try (Scanner input = new Scanner(new FileReader(path.toFile()))) {
            while (input.hasNextLine()) {
                tasks.importSave(parseSaveLine(input.nextLine()));
            }
        } catch (IOException e) {
            throw new TrackerBotException("Failed to load save file: " + e.getMessage());
        } catch (DateTimeParseException e) {
            tasks.clear();
            throw new TrackerBotException("Failed to parse date: " + e.getMessage());
        } catch (NumberFormatException e) {
            tasks.clear();
            throw new TrackerBotException("Corrupted save data in DateTime.");
        } catch (IllegalArgumentException e) {
            tasks.clear();
            throw new TrackerBotException("Corrupted save data in Arguments.");
        }
    }

    public static void save(TaskList tasks) throws TrackerBotException {
        Path path = Paths.get("TrackerBot", "data.txt");
        File file = path.toFile();
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new TrackerBotException(e.getMessage());
        }

        try (FileOutputStream output = new FileOutputStream(file, false)) {
            output.write(tasks.exportSave().getBytes());
        } catch (IOException e) {
            throw new TrackerBotException(e.getMessage());
        } // the try with resources statement auto-closes output.
    }
}
