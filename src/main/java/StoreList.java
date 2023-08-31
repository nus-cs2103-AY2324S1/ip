import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class StoreList {

    private static String filePath = "../../duke.txt";
    ArrayList<Task> list = new ArrayList<>();
    StoreList() {
    }

    String add(Commands type, String item) {
        try {

            Task task = Task.create(type, item);
            list.add(task);
            return String.format(
                    "added: %s\nYou have %d tasks.",
                    task,
                    list.size()
            );
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String add(Task task) {
        list.add(task);
        return String.format(
                "added: %s\nYou have %d tasks.",
                task,
                list.size()
        );
    }

    public void addTasks(Collection<? extends Task> tasks) {
        list.addAll(tasks);
    }

    String markDone(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.get(index);
            task.markAsDone();
            return String.format("Nice! You have completed the task:\n    %s", task);
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    String markUndone(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.get(index);
            task.markAsNotDone();
            return String.format("Ok! Task marked undone:\n    %s", task);
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }

    String delete(String position) {
        try {
            int index = Integer.parseInt(position) - 1;
            Task task = list.remove(index);
            return String.format(
                    "removed: %s\nYou have %d tasks.",
                    task,
                    list.size()
            );
        } catch (NumberFormatException e) {
            return "Err: Index provided is not an integer";
        } catch (IndexOutOfBoundsException e) {
            return "Err: Index provided is out of position of the list";
        }
    }
    @Override
    public String toString() {
        if (list.size() == 0) {
            return "You have no tasks :).";
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += String.format("    %d. %s\n", i + 1, list.get(i));
        }
        return result;
    }

    public boolean readFromFile() {
        File file = new File(filePath);
        Scanner sc;
        try {
            file.createNewFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            System.out.println("Unable to find or create file.");
            return false;
        }

        while(sc.hasNext()) {
            String s = sc.nextLine();
            String[] tokens = s.split(" ", 3);
            try {
                Commands c = Commands.valueOf(tokens[0]);
                boolean done = !tokens[1].equals("0");
                String desc = tokens[2];
                Task task = Task.create(c, desc);
                if (done) {
                    task.markAsDone();
                } else {
                    task.markAsNotDone();
                }
                list.add(task);
            } catch (IllegalArgumentException | IndexOutOfBoundsException | DukeException e) {
                System.out.println("Corrupted file. Delete all text in the duke.txt to use it again.");
                return false;
            } catch (RuntimeException e) {
                System.out.println("Corrupted file. Delete all text in the duke.txt to use it again.");
                return false;
            }
        }
        sc.close();

        return true;
    }

    public boolean writeToFile() {
        String s = "";
        for (Task t: list) {
            s += t.fileString() + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fileWriter);
            pw.write(s);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to duke.txt");
            return false;
        }

        return true;
    }

    public String showSaveText() {
        String saveText = "";
        for (int i = 0; i < list.size(); i++) {
            saveText += list.get(i).fileString() + (i + 1 == list.size() ? "" : "\n");
        }
        return saveText;
    }
}