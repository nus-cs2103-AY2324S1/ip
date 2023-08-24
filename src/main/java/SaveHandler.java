import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SaveHandler {
    private Task[] tasks;
    private File file;

    public SaveHandler(Task[] tasks, File file) {
        this.tasks = tasks;
        this.file = file;
    }

    public void saveTo() {
        PrintWriter printWriter;
        try {
            Scanner sc = new Scanner(file);
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
                //System.out.println(toStore);
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

    public Task[] loadFrom() {
        try {
            int pos = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] arr = s.split(" \\| ");
                Task t = stringToTask(arr);
                if (t == null) {
                    continue;
                } else {
                    tasks[pos] = t;
                    pos++;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("There's no file with your to do list peko");
        }
        return tasks;
    }

    private Task stringToTask(String[] arr) {
        Task t;
        try {
            switch (arr[0]) {
                case "T":
                    t = new ToDos("todo " + arr[2]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "D":
                    t = new Deadline("deadline " + arr[2] + " /by " + arr[3]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "E":
                    t = new Event("event " + arr[2] + " /from " + arr[3] + " /to " + arr[4]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                default:
                    throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            System.out.println("There's an error in the list, Pain peko, I'll delete it");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incomplete task an error in the list, Pain peko, I'll delete it");
        }
        return  null;
    }


}
