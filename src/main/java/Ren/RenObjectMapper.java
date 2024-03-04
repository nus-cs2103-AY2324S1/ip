package ren;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import ren.task.Deadline;
import ren.task.Event;
import ren.task.TaskList;
import ren.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Handles the mapping of TaskList to JSON and vice versa.
 */
public final class RenObjectMapper {
    static ObjectMapper objectMapper = new ObjectMapper();
    private static String cacheFileAddress = "./cacheTaskList.txt";

    public RenObjectMapper() {
        objectMapper.registerSubtypes(new NamedType(ToDo.class, "ren.task.ToDo"));
        objectMapper.registerSubtypes(new NamedType(Deadline.class, "ren.task.Deadline"));
        objectMapper.registerSubtypes(new NamedType(Event.class, "ren.task.Event"));
    }

    /**
     * Stores state of TaskList into cache file.
     *
     * @param taskList TaskList to be stored
     */
    public static void storeIntoHarddisk(TaskList taskList) {
        try {
            String home = System.getProperty("user.dir");
            Path path = Paths.get(home, "cacheTaskList.txt");
            String json = objectMapper.writeValueAsString(taskList);
            boolean directoryExists = Files.exists(path);
            if (!directoryExists) {
                System.out.println("Creating directory: " + path);
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            FileWriter myWriter = new FileWriter(path.toString());
            if (json == null || json.isEmpty()) {
                throw new NullPointerException("JSON string is null or empty");
            }
            myWriter.write(json);
            myWriter.close();
        } catch (JsonProcessingException e) {
            System.out.println("An JSON error occurred.");
            e.printStackTrace();
        } catch (java.io.IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves state of TaskList from cache file.
     *
     * @return TaskList retrieved from cache file
     */
    public static TaskList retrieveFromHarddisk() {
        TaskList taskList = null;
        try {
            String home = System.getProperty("user.dir");
            Path path = Paths.get(home, "cacheTaskList.txt");
            boolean directoryExists = Files.exists(path);
            System.out.println("Directory exists: " + directoryExists);
            if (!directoryExists) {
                System.out.println("Creating directory: " + path);
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            Path cachePath = Path.of(cacheFileAddress);
            assert Files.exists(cachePath) : "Cache file should exist";
            File myObj = cachePath.toFile();
            Scanner sc = new Scanner(myObj);
            String json = sc.nextLine();
            taskList = objectMapper.readValue(json, TaskList.class);
            System.out.println("Successfully read from the file.");
            sc.close();
            return taskList;
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("Can't Map");
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.out.println("Can't Process");
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            System.out.println("No Cache");
            taskList = new TaskList();
        } catch (java.io.IOException e) {
            System.out.println("An IO error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }
}
