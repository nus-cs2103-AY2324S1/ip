import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ListIterator;
import java.nio.file.StandardOpenOption;

public class Duke {
    public static String indent = "     ";
    public static String horizontalLine = "-".repeat(22);

    public enum TaskKeyVal {ToDo, Deadline, Event, Delete, mark, unmark, bye, list};

    ;       //enum that stores all important constants

    public static void main(String[] args) {
        Path pathOfDirectory = Paths.get("./data/duke.txt");
        Map<String, String> hashMap = Map.of(
                "endMessage", "Bye. Hope to see you again soon!",
                "endVal", "bye",
                "listVal", "list");

        ArrayList<Task> storeTask = new ArrayList<>(1);   //stores all the tasks. refer to child classes of task
        try {
            Files.createDirectories(pathOfDirectory.getParent());
            if (Files.exists(pathOfDirectory)) {
                System.out.println("Please wait while your file opens...");
                List<String> fileLines = Files.readAllLines(pathOfDirectory);
                for (String task : fileLines) {
                    String[] taskVariablesTemp = task.split("\\|");     //since "|" is a special character, use "//"
                    if (taskVariablesTemp[0].equals("T")) {
                        storeTask.add(new ToDo(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2]));
                    } else if (taskVariablesTemp[0].equals("D")) {
                        storeTask.add(new Deadline(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2], taskVariablesTemp[3]));
                    } else if (taskVariablesTemp[0].equals("E")) {
                        storeTask.add(new Event(Integer.parseInt(taskVariablesTemp[1]), taskVariablesTemp[2], taskVariablesTemp[3], taskVariablesTemp[4]));
                    }
                    else {
                        System.out.println("Error...");
                    }
                }
            } else {
                System.out.println("Your file does not exist. Creating...");
                Files.createFile(pathOfDirectory);
                System.out.println("File created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred");
        } finally {
            System.out.println(indent + horizontalLine);                                        //Helps with readability
            System.out.println(indent + "Hello! I'm Tom!" + "\n" + indent + "What can I do for you?" + "\n");
            System.out.println(indent + horizontalLine);
            Scanner userInputObject = new Scanner(System.in);
            while (true) {
                System.out.println(indent + "What would you like to do next? : ");
                String userInput = userInputObject.nextLine();
                String[] userInputList = userInput.split(" ", 2);
                String userTaskChoiceKey = userInputList[0];

                try {
                    TaskKeyVal taskKeyVal = TaskKeyVal.valueOf(userTaskChoiceKey);              //Stores enum value. might throw exception if invalid input entered.
                    if (taskKeyVal == TaskKeyVal.bye) {
                        Files.write(pathOfDirectory, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);    //closes file and truncates it
                        for (int i = 0; i < storeTask.size(); i++) {
                            String taskToString = storeTask.get(i).storeToDisk() + "\n";
                            Files.write(pathOfDirectory, taskToString.getBytes(), StandardOpenOption.APPEND);
                        }
                        System.out.println("\n" + indent + hashMap.get("endMessage"));
                        break;
                    } else if (taskKeyVal == TaskKeyVal.list) {
                        int count = 0;
                        ListIterator<Task> ls = storeTask.listIterator();
                        System.out.println("\n" + indent + "Entries on memory...");
                        while (ls.hasNext()) {
                            count++;
                            System.out.println(indent + count + "." + ls.next().toString());
                        }
                    } else if (taskKeyVal == TaskKeyVal.mark || taskKeyVal == TaskKeyVal.unmark) {
                        String taskNumber = userInputList[1];
                        Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
                        System.out.println(indent + taskItem.changeStatus(userInputList[0]));
                    } else if (userInputList.length == 1 && enumCheck(userTaskChoiceKey)) {
                        throw new DukeException(" ☹ OOPS!!! The description of a task cannot be empty.");
                    } else if (taskKeyVal == TaskKeyVal.ToDo) {
                        storeTask.add(new ToDo(userInputList[1]));
                        System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                        System.out.println(indent + horizontalLine);
                    } else if (taskKeyVal == TaskKeyVal.Deadline) {
                        String[] deadlineList = userInputList[1].split("/", 2);
                        storeTask.add(new Deadline(deadlineList[0], deadlineList[1]));
                        System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                        System.out.println(indent + horizontalLine);
                    } else if (taskKeyVal == TaskKeyVal.Event) {
                        String[] eventList = userInputList[1].split("/", 3);
                        storeTask.add(new Event(eventList[0], eventList[1], eventList[2]));
                        System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                        System.out.println(indent + horizontalLine);
                    } else if (taskKeyVal == TaskKeyVal.Delete) {
                        Integer delUserChoice = Integer.parseInt(userInputList[1]);
                        if ((delUserChoice - 1) < 0) {                                          //if number entered smaller than 1, array will go negative index.
                            throw new DukeException("Invalid Task entered. Please try again...");
                        } else if (storeTask.isEmpty()) {
                            throw new DukeException("Task Scheduler is empty... Please try again!");
                        } else {
                            Task itemRemoved = storeTask.remove(delUserChoice - 1);
                            System.out.println(indent + "This task was removed..." + "\n" + itemRemoved);
                            System.out.println(indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
                        }
                    } else {                                                                    //in case wrong inpute like Delete abc entered
                        throw new DukeException("☹ OOPS!!! Sorry, but i do not know what that means :-(");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(indent + "Invalid character input");
                } catch (DukeException e) {
                    System.out.println(indent + "Error: " + e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indent + "Invalid entry / Task not in list... Please try again...");
                } catch (IllegalArgumentException e) {
                    System.out.println("☹ OOPS!!! Sorry, but i do not know what that means :-(");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An error occurred...");
                }
            }
        }



    }
    public static boolean enumCheck (String input){
        for (TaskKeyVal taskKey : TaskKeyVal.values()) {
            if (taskKey.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

}