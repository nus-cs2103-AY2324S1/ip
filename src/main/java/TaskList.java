import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    public static List<Task> list;
    public static String fileName = "bruno.txt";
    public static String dirPath = "data/";
    TaskList() {
        list = new ArrayList<>();
    }
    public String addToDo(String task) throws DukeException {
        if (task.split(" ").length == 1) {
            System.out.print("\t");
            throw new DukeEmptyException(task.split(" ")[0]);
        }
        list.add(new ToDo(task.substring(task.indexOf(" ") + 1)));
        return "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
    }

    public String addDeadline(String task) throws DukeException {
        if (task.split(" ").length == 1) {
            System.out.print("\t");
            throw new DukeEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("deadline") + 1).contains("/by")) {
            System.out.print("\t");
            throw new DukeMissingDeadlineException();
        }
        list.add(new Deadline(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1), task.substring(task.lastIndexOf('/') + 4)));
        return "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
    }

    public String addEvent(String task) throws DukeException {
        if (task.split(" ").length == 1) {
            System.out.print("\t");
            throw new DukeEmptyException(task.split(" ")[0]);
        }
        if (!task.substring(task.indexOf("event") + 1).contains("/from") || !task.substring(task.indexOf("event") + 1).contains("/to")) {
            System.out.print("\t");
            throw new DukeMissingEventException();
        }
        list.add(new Event(task.substring(task.indexOf(' ') + 1, task.indexOf('/') - 1), task.substring(task.indexOf("from") + 5, task.lastIndexOf('/') - 1), task.substring(task.indexOf("to") + 3)));
        return "\tWoof. I have added this task:\n\t\t" + list.get(list.size() - 1).getString();
    }

    public String markTask(String task) throws DukeException {
        String markVal = task.split(" ")[1];
        if (!Character.isDigit(markVal.charAt(0))) {
            throw new DukeIntegerMismatchException("mark");
        }
        if (Integer.parseInt(markVal) > list.size()) {
            throw new DukeIndexOutOfBoundsException("mark");
        }
        if (Integer.parseInt(markVal) < 0) {
            throw new DukeNegativeArgException("mark");
        }
        list.get(Integer.parseInt(markVal) - 1).markAsDone();
        return "\tWoof Woof! I have marked the task as done.\n\t" + list.get(Integer.parseInt(markVal) - 1).getString();
    }

    public String unmarkTask(String task) throws DukeException {
        String unmarkVal = task.split(" ")[1];
        if (!Character.isDigit(unmarkVal.charAt(0))) {
            throw new DukeIntegerMismatchException("unmark");
        }
        if (Integer.parseInt(unmarkVal) > list.size()) {
            throw new DukeIndexOutOfBoundsException("unmark");
        }
        if (Integer.parseInt(unmarkVal) < 0) {
            throw new DukeNegativeArgException("unmark");
        }
        list.get(Integer.parseInt(unmarkVal) - 1).unMark();
        return "\tOK, I have marked the task as not done yet.\n\t" + list.get(Integer.parseInt(unmarkVal) - 1).getString();
    }

    public String deleteTask(String task) throws DukeException {
        String deleteVal = task.split(" ")[1];
        if (!Character.isDigit(deleteVal.charAt(0))) {
            throw new DukeIntegerMismatchException("delete");
        }
        if (Integer.parseInt(deleteVal) > list.size()) {
            throw new DukeIndexOutOfBoundsException("delete");
        }
        if (Integer.parseInt(deleteVal) < 0) {
            throw new DukeNegativeArgException("delete");
        }
        String s1 = list.get(Integer.parseInt(deleteVal) - 1).getString();
        list.remove(Integer.parseInt(deleteVal) - 1);
        return "\tI have removed this task from your list:\n\t" + s1;
    }

    public String displayListSum() {
        return "\tNow you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list.";
    }
    public void displayList() {
        System.out.print("\t");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print("\t\t");
            System.out.println((i + 1) + ". " + list.get(i).getString());
        }
    }

    public void writeToFile() {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(dirPath + fileName);
            for (Task task : list) {
                fileWriter.write(task.getFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFile() throws DukeException {
        try {
            File directory = new File(dirPath);
            if (!directory.exists()) {
                System.out.println("Directory \"data\" has been created.");
                directory.mkdir();
            }
            File file = new File(dirPath + fileName);
            if (!file.exists()) {
                System.out.println("File \"bruno.txt\" has been created");
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] task = s.split("\\|");
                if (task[0].equals("T")) {
                    list.add(new ToDo(task[2]));
                } else if (task[0].equals("D")) {
                    list.add(new Deadline(task[2], task[3]));
                } else if (task[0].equals("E")) {
                    list.add(new Event(task[2], task[3], task[4]));
                }
                else {
                    throw new DukeIncorrectFormatException();
                }
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
