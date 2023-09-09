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
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles the mapping of TaskList to JSON and vice versa.
 */
public final class RenObjectMapper {
    static ObjectMapper objectMapper = new ObjectMapper();
    private static String cacheFileAddress = "./src/main/resources/cacheTaskList.txt";

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
            String home = System.getProperty("user.home");
            java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "resources", "cacheTaskList.txt");
            String json = objectMapper.writeValueAsString(taskList);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                System.out.println("Creating directory: " + path);
                java.nio.file.Files.createDirectories(path.getParent());
                java.nio.file.Files.createFile(path);
            }
            java.io.FileWriter myWriter = new java.io.FileWriter(path.toString());
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
            String home = System.getProperty("user.home");
            java.nio.file.Path path = java.nio.file.Paths.get(home, "src", "main", "resources", "cacheTaskList.txt");
            boolean directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                System.out.println("Creating directory: " + path);
                java.nio.file.Files.createDirectories(path.getParent());
                java.nio.file.Files.createFile(path);
            }

            Path cachePath = Path.of(cacheFileAddress);
            assert java.nio.file.Files.exists(cachePath) : "Cache file should exist";
            File myObj = cachePath.toFile();
            Scanner sc = new Scanner(myObj);
            String json = sc.nextLine();
            taskList = objectMapper.readValue(json, TaskList.class);
            System.out.println("Successfully read from the file.");
            sc.close();
            return taskList;
        } catch (java.io.FileNotFoundException e) {
            try (java.io.InputStream inputStream = RenObjectMapper.class.getResourceAsStream("/cacheTaskList.txt");
                 java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(inputStream))) {
                String contents = reader.lines()
                        .collect(Collectors.joining(System.lineSeparator()));
                taskList = objectMapper.readValue(contents, TaskList.class);
                System.out.println("Successfully read from the file.");
                return taskList;
            } catch (java.io.IOException ioException) {
                System.out.println("Can't read from cache file");
                ioException.printStackTrace();
            }
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
