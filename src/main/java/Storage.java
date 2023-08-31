import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.File;
public class Storage {
    File tmpDir;
    public Storage(File file) {
        tmpDir = file;
    }
    public void loadFile(TaskList taskList) throws FileNotFoundException {
        Scanner sc = new Scanner(tmpDir);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            int stat = Integer.parseInt(input.substring(2, 3));
            StringBuilder desc = new StringBuilder();
            StringBuilder start = new StringBuilder();
            Task curr;
            if (input.charAt(0) == 'T') {
                curr = new Todo(input.substring(4));
            } else if (input.charAt(0) == 'D') {
                int i = 4;
                while (input.charAt(i) != '|') {
                    desc.append(input.charAt(i));
                    i++;
                }
                i++;
                LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                curr = new Deadline(desc.toString(), endDateTime);
            } else {
                int i = 4;
                while (input.charAt(i) != '|') {
                    desc.append(input.charAt(i));
                    i++;
                }
                i++;
                while (input.charAt(i) != '|') {
                    start.append(input.charAt(i));
                    i++;
                }
                i++;
                LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
                LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
                curr = new Event(desc.toString(), startDateTime, endDateTime);
            }
            if (stat == 1) {
                curr.markDone();
            }
            taskList.addTaskSilent(curr);
        }
    }
    public void storeList(TaskList taskList) {
        try {
            Files.write(tmpDir.toPath(), taskList.storeList().getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
