import java.io.*;
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
            file.createNewFile();
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
        return null;
    }


}
