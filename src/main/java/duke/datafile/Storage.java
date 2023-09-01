package duke.datafile;
import duke.Exceptions.InvalidDeadlineException;
import duke.Exceptions.InvalidEventException;
import duke.Exceptions.LemonException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
        private final String FILE_PATH;

        public Storage() {
            this.FILE_PATH = "./datafile/tasks.txt";
        }

        public Storage(String filePath) {
            this.FILE_PATH = filePath;
        }

        public void saveTasks(ArrayList<Task> tasks) {
            try {
                File file = new File(FILE_PATH);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                    System.out.println("Creating new file");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            try {
                writeToFile(FILE_PATH, "");
                if (tasks.size() > 0) {
                    for (int i = 0; i < tasks.size(); i++) {
                        addToFile(FILE_PATH, tasks.get(i).addDataFormat() + "\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Issues saving tasks to storage.");
            }

        }
        private static void writeToFile(String filePath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        }
        private static void addToFile(String filePath, String textToAdd) throws IOException {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd + System.lineSeparator());
            fw.close();
        }

        public ArrayList<Task> loadFile() throws LemonException {
            ArrayList<Task> tasks = new ArrayList<>();
            File dataFile = new File(FILE_PATH);
            Scanner scanner;
            // if there is no storage - no tasks
            if (!dataFile.exists()) {
                System.out.println("File doesn't exist?");
                return tasks;
            } else {
                try {
                    scanner = new Scanner(dataFile);
                    while (scanner.hasNextLine()) {
                        String[] input = scanner.nextLine().split(" \\| ");
                        String taskType = input[0];
                        switch(taskType) {
                            case "T":
                                boolean isDoneT = checkDone(input[1]);
                                String descriptionT = input[2];
                                Task toAddTaskT = new Todo(descriptionT, isDoneT);
                                tasks.add(toAddTaskT);
                                break;
                            case "D":
                                boolean isDoneD = checkDone(input[1]);
                                String descriptionD = input[2];
                                String by = input [3];
                                try {
                                    Task toAddTaskD = new Deadline(descriptionD, by, isDoneD);
                                    tasks.add(toAddTaskD);
                                } catch (InvalidDeadlineException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case "E":
                                boolean isDoneE = checkDone(input[1]);
                                String descriptionE = input[2];
                                String fromE = input[3];
                                String toE = input[4];
                                try {
                                    Task toAddTaskE = new Event(descriptionE, fromE, toE, isDoneE);
                                    tasks.add(toAddTaskE);
                                } catch (InvalidEventException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new LemonException("Storage file not available!");
                }
            }
            return tasks;
        }

        private static boolean checkDone(String isDone) {
            return isDone.equals("1");
        }



}
