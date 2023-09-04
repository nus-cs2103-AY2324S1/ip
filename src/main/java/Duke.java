import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import HelperClass.Task;

public class Duke {
    private static void printOneLine() {
        System.out.println("---------------------------");
    }

    private static final String MyName = "Rio";
    public static void Greet() {
        printOneLine();
        System.out.println("Hello! I'm " + MyName);
        System.out.println("What can I do for you?");
        printOneLine();
    }

    public  static void Exit() {

        System.out.println(" Bye. Hope to see you again soon!");

    }

    private static String GetUserTaskName() {
        Scanner getUserInput = new Scanner(System.in);
        String taskName = getUserInput.nextLine();
        if (taskName.isEmpty()) {
            System.out.println("OOPS!!! The name of a task cannot be empty.");
            return "";
        } else {
            return taskName;
        }

    }

    private static void BackgroundSetUp() {
        String directoryName = "data";
        String fileName = "list.txt";

        File dir = new File(directoryName);
        if (!(dir.exists())) {
            if (dir.mkdir()) {
                System.out.println("Directory '" + directoryName + "' created.");
            } else {
                System.err.println("Failed to create directory '" + directoryName + "'.");
                return;
            }
        }


        File file = new File(dir, fileName);

        if (!(file.exists())) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File '" + fileName + "' created in directory '" + directoryName + "'.");
                } else {
                    System.err.println("Failed to create file '" + fileName + "' in directory '" + directoryName + "'.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static List<String> ReadLine(String line) {
        List<String> formattedLine = new ArrayList<>();
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {

            String token = lineScanner.next();
            formattedLine.add(token);

        }
        lineScanner.close();

        return formattedLine;
    }



    private static Task[] LoadList() {
        Task[] userList = new Task[100];
        int positionPointer = 0;

        String fileName = "data/list.txt";
        Path path = Paths.get(fileName);
        try {
            Scanner fileScanner = new Scanner(path);
            while(fileScanner.hasNextLine()){

                // Record format: "Type | Status | Name | Time"
                // example: "D | 0 | return book | June 6th"
                // "0" for not done and "1" for done

                String line = fileScanner.nextLine();

                List<String> formattedLine = ReadLine(line);



                List<String> attributes = new ArrayList<>();
                String attributeName = "";

                for (Object element : formattedLine) {
                    if (element.equals("|")) {
                        attributes.add(attributeName);
                        attributeName = "";
                    } else {
                        attributeName = attributeName + element + " ";

                    }
                }

                attributes.add(attributeName);
                boolean isDone = attributes.get(1).equals("1");

                switch (attributes.get(0)) {
                    case "T": {
                        Task task = new Task(attributes.get(2), 1, "Null", isDone);
                        userList[positionPointer] = task;

                        break;
                    }
                    case "D": {
                        Task task = new Task(attributes.get(2), 2, attributes.get(3), isDone);
                        userList[positionPointer] = task;

                        break;
                    }
                    case "E": {
                        Task task = new Task(attributes.get(2), 3, attributes.get(3), isDone);
                        userList[positionPointer] = task;

                        break;


                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + attributes.get(0));
                }

                positionPointer++;


            }
            fileScanner.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return userList;
    }

    private static void SaveList(Task[] userList, int numberOfElements) {
        String fileName = "data/list.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < numberOfElements; i++) {
                writer.write(userList[i].ForRecordingInTextFile());
                writer.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        BackgroundSetUp();


        Greet();

        boolean wantToExit = false;
        Scanner getUserInput = new Scanner(System.in);
        Scanner getUserIndex = new Scanner(System.in);
        Task[] userList = LoadList();

        int listPointer = 0;

        while (!(wantToExit)) {
            String userInput = getUserInput.nextLine();

            printOneLine();
            switch (userInput) {

                case "bye":
                    wantToExit = true;
                    getUserInput.close();
                    getUserIndex.close();
                    Exit();

                    break;

                case "list":

                    if (listPointer < 1) {
                        System.out.println("No items in the list yet");
                    } else {
                        for (int i = 0; i < listPointer; i++) {
                            int num = i + 1;
                            System.out.println(num + userList[i].display());


                        }
                    }

                    break;

                case "mark":
                    System.out.println("Enter index:");
                    int index = getUserIndex.nextInt() - 1;
                    if (index < 0 || index >= listPointer) {
                        System.out.println("Invalid index.");
                    } else {

                        userList[index].markDone();

                    }

                    break;

                case "unmark":
                    System.out.println("Enter index:");
                    int i = getUserIndex.nextInt() - 1;
                    if (i < 0 || i >= listPointer) {
                        System.out.println("Invalid index.");
                    } else {

                        userList[i].unmarkDone();

                    }

                    break;

                case "todo":
                    System.out.println("Enter task name:");
                    String taskName = GetUserTaskName();
                    if (!(taskName.isEmpty())) {
                        userList[listPointer] = new Task(taskName, 1, "Null", false);

                        System.out.println("Got it. I've added this task:");

                        System.out.println(userList[listPointer].display());

                        listPointer = listPointer + 1;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");
                    }

                    break;

                case "deadline":
                    System.out.println("Enter task name:");
                    String taskN = GetUserTaskName();
                    if (!(taskN.isEmpty())) {
                        System.out.println("Enter deadline:");
                        String timePeriod = getUserInput.nextLine();
                        userList[listPointer] = new Task(taskN, 2, "by:" + timePeriod, false);

                        System.out.println("Got it. I've added this task:");

                        System.out.println(userList[listPointer].display());

                        listPointer = listPointer + 1;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");
                    }
                    break;

                case "event":
                    System.out.println("Enter task name:");
                    String tN = GetUserTaskName();
                    if (!(tN.isEmpty())) {
                        System.out.println("Enter start time:");
                        String startTime = getUserInput.nextLine();
                        System.out.println("Enter end time:");
                        String endTime = getUserInput.nextLine();
                        String timePeriod = "from: " + startTime + " to: " + endTime;
                        userList[listPointer] = new Task(tN, 3, timePeriod, false);

                        System.out.println("Got it. I've added this task:");

                        System.out.println(userList[listPointer].display());

                        listPointer = listPointer + 1;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");
                    }
                    break;

                case "delete":
                    System.out.println("Enter index:");
                    int ind = getUserIndex.nextInt() - 1;
                    if (ind < 0 || ind >= listPointer) {
                        System.out.println("Invalid index.");
                    } else {

                        System.out.println("Noted. I've removed this task:");

                        System.out.println(userList[listPointer].display());

                        Task[] newUserList = new Task[100];

                        for (int a = 0, k = 0; a < listPointer; a++) {

                            // if the index is
                            // the removal element index
                            if (a == ind) {
                                continue;
                            }

                            // if the index is not
                            // the removal element index
                            newUserList[k++] = userList[a];
                        }

                        listPointer = listPointer - 1;

                        userList = newUserList;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");

                    }

                    break;


                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");


            }
            printOneLine();

            SaveList(userList, listPointer);

        }




    }




}
