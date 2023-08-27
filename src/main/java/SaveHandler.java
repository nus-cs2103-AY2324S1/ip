import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SaveHandler {
    private static int listSize = 0;
    private static Task[] tasks = new Task[100];
    private static File file = new File("src/main/List.txt");

    public SaveHandler() {

    }

    public static void saveTo() {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
            printWriter.write("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Gomen peko, something broke...");
            }
        } finally {
            for (Task t : tasks) {
                if (t == null) {
                    break;
                }
                String toStore = t.toStore() + "\n";



                //System.out.println("toStore: " + toStore);




                try {
                    Writer temp;
                    temp = new BufferedWriter(new FileWriter("src/main/List.txt", true));
                    temp.append(toStore);
                    temp.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static Task[] loadFrom() {

        try {
            int pos = 0;
            Scanner scanner = new Scanner(file.getAbsoluteFile());
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] arr = s.split(" \\| ");
                Task t = stringToTask(arr);
                if (t != null) {
                    tasks[pos] = t;
                    pos++;
                }

            }
            listSize = pos;
        } catch (Exception e){

        }
        return tasks;
    }

    private static Task stringToTask(String[] arr) {
        Task t;
        try {

            switch (arr[0]) {
                case "T":
                    t = new ToDos( arr[2]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "D":
                    t = new Deadline(arr[2] + " /by " + arr[3]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "E":
                    t = new Event(arr[2] + " /from " + arr[3] + " /to " + arr[4]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                default:
                    throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            System.out.println(e);
            System.out.println("There's an error in the list, Pain peko, I'll delete it");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incomplete task an error in the list, Pain peko, I'll delete it");
        }
        return  null;
    }

    public static int size() {
        return listSize;
    }


}
