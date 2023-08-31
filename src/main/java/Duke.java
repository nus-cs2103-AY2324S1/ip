import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * array of task
     */
    private static ArrayList<Task> arrayList = new ArrayList<>();

    private static String pathFile = "src/main/TaskStorage.txt";

    /**
     * method to run the code
     * @param arrayList the array containing the tasks
     * @return boolean to check bye or not
     * @throws DukeException throws DukeException
     */
    private static boolean runProgram(ArrayList<Task> arrayList) throws Exception {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        if (s == null) {
            s = in.nextLine();
        }

        if (s.equals("bye")) { // if input is bye, terminate code

            String exit = "    ____________________________________________________________\n" +
                    "     Bye. Hope to see you again soon!\n" +
                    "    ____________________________________________________________";
            System.out.println(exit);
            return false;
        } else {
            String response = "";
            String[] splitMark = s.split(" ");
            String[] splitTask = s.split(" ", 2);

            if (s.equals("list")) {
                String items = "";
                

                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i) == null) {
                        break;
                    }
                    items += "     " + (i + 1) + "." + arrayList.get(i).toString() + "\n";
                }
                response = "    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        items +
                        "    ____________________________________________________________\n";

            } else if (splitMark[0].equals("delete") && splitMark.length == 2 ) {
                int index;

                try {
                    index = Integer.parseInt(splitMark[1]);
                } catch (NumberFormatException e) {
                    throw new DukeInvalidDeleteException(splitMark[0]);
                }

                if (index > 0 && arrayList.get(index - 1) != null) {
                    response = "    ____________________________________________________________\n" +
                            "     Noted. I've removed this task:" + "\n" +
                            "       " + arrayList.get(index-1).toString() + "\n" +
                            "     Now you have " + (arrayList.size() - 1) + " tasks in the list." + "\n" +
                            "    ____________________________________________________________\n";
                    arrayList.remove(index - 1);

                }

            } else {
                boolean condition1 = splitMark[0].equals("mark") || splitMark[0].equals("unmark"); //first word is mark or unmark

                if (splitMark.length == 2 && condition1) {
                    int index = 0;
                    try {
                        index = Integer.parseInt(splitMark[1]);
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidMarkException(splitMark[0]);
                    }

                    if (index > 0 && arrayList.get(index - 1) != null) {
                        if (splitMark[0].equals("mark")) {
                            arrayList.get(index - 1).mark();

                            response = "    ____________________________________________________________\n" +
                                    "     Nice! I've marked this task as done:" + "\n" +
                                    "       " + arrayList.get(index - 1).toString() + "\n" +
                                    "    ____________________________________________________________\n";
                        }
                        if (splitMark[0].equals("unmark")) {
                            arrayList.get(index - 1).unmark();

                            response = "    ____________________________________________________________\n" +
                                    "     OK, I've marked this task as not done yet:" + "\n" +
                                    "       " + arrayList.get(index - 1).toString() + "\n" +
                                    "    ____________________________________________________________\n";
                        }
                    }
                } else {
                    Task newTask = Task.createTaskType(splitTask);
                    arrayList.add(newTask);
                    int i = arrayList.indexOf(newTask);

                    response = "    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "      " + newTask + "\n" +
                            "     Now you have " + (i + 1) + " tasks in the list." + "\n" +
                            "    ____________________________________________________________\n";
                }
            }

            System.out.println(response);
            s = null;
            writeFile();
            return true;
        }
    }

    private static void readFile() throws Exception {
        File file = new File(Duke.pathFile);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String scanned = sc.nextLine();
            String[] singleTaskArray = scanned.split(" / ");

            //do a checking <------

            Task task;
            switch (singleTaskArray[0]) {
            case "T":
                task  = new Todo(singleTaskArray[2]);
                break;
            case "D":
                task = new Deadline(singleTaskArray[2], singleTaskArray[3]);
                break;
            case "E":
                task = new Event(singleTaskArray[2], singleTaskArray[3], singleTaskArray[4]);
                break;
            default:
                throw new DukeNotTaskException(singleTaskArray[0]);
            }

            if (singleTaskArray[1].equals("1")) {
                task.mark();
            }

            arrayList.add(task);
        }



    }

    private static void writeFile() throws Exception {
        String newData = Duke.arrayToDataString();

        try {
            FileWriter fileWriter = new FileWriter(pathFile, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(newData);

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void checkFile() throws Exception {
        try {
            File file = new File(pathFile);
            if (!file.exists()) {
                boolean created = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String arrayToDataString() {
        String dataString = "";

        for (int i = 0; i < arrayList.size(); i++) {
            dataString += arrayList.get(i).toDataString() + "\n";
        }
        return dataString;
    }

    public static void main(String[] args) throws Exception {
        checkFile();
        readFile();

        String greet = "    ____________________________________________________________\n" +
                "     Hello! I'm Siri\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(greet);
        //Time.isCorrectFormat("ok");

        while (true) {
            try {
                if (!Duke.runProgram(arrayList)) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        writeFile();
    }
}
