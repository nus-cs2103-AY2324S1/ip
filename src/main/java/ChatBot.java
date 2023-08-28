import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static rayshawn.chatbot.messages.Messages.LINE_BREAK;

public class ChatBot {
    private static List<Task> list = new ArrayList<>();
    private static File file;

    public static void main(String[] args) {

        // Introduction message
        System.out.println(LINE_BREAK +
                "\n Hello! I'm Desolute\n" +
                " What can I do for you?\n" +
                LINE_BREAK);

        // Checks if data directory is available
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        // Checks if file is available
        file = new File("./data/list.txt");
        if (!file.exists()) {
            try {
                createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Reads all the tasks on the file
        try {
            readAllTasks();
        } catch (FileNotFoundException f) {
            System.out.println("\n Task save file is missing!!! ");
        } catch (DukeException d) {
            System.out.println(LINE_BREAK +
                    d.getMessage() +
                    LINE_BREAK);
        }

        Scanner sc = new Scanner(System.in);
        String next = sc.nextLine();

        // Loops till bye command is given
        while (!next.equals("bye")) {
            try {
                nextCommand(next);
            } catch (DukeException e) {
                System.out.println(LINE_BREAK +
                        e.getMessage()+
                        LINE_BREAK);
            }
            next = sc.nextLine();
        }

        // Exit message
        System.out.println(LINE_BREAK +
                "\n Bye. Hope to see you again soon!\n" +
                LINE_BREAK);
    }

    private static void nextCommand(String str) throws DukeException {
        // Splitting of string adapted from
        // https://stackoverflow.com/questions/9378394/remove-first-word-from-a-string-in-java
        String[] temp = str.split(" ", 2);

        // Checks if any predetermined commands are given
        switch (temp[0]) {
        case "list":
            showList();
            break;
        case "mark":
            markDone(temp[1]);
            break;
        case "unmark":
            unmarkDone(temp[1]);
            break;
        case "todo":
            if (temp.length != 2) {
                throw new DukeException("\n ☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            Task toDo = new ToDo(temp[1]);
            list.add(toDo);
            try {
                appendList(toDo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(LINE_BREAK +
                    "\n Got it. I've added this task:\n  " +
                    toDo.toString() +
                    getTaskCount() +
                    LINE_BREAK);
            break;
        case "deadline":
            if (temp.length != 2) {
                throw new DukeException("\n ☹ OOPS!!! The description of a deadline cannot be empty.\n");
            }
            String[] temp2 = temp[1].split(" /by ");
            if (temp2.length != 2) {
                throw new DukeException("\n ☹ OOPS!!! The date of a deadline cannot be empty.\n");
            }
            Task deadline;
            try {
                LocalDate deadlineDate = LocalDate.parse(temp2[1]);
                deadline = new Deadline(temp2[0], deadlineDate);
            } catch (DateTimeException e) {
                throw new DukeException("\n Incorrect date format! Date format should be YYYY-MM-DD");
            }
            list.add(deadline);
            try {
                appendList(deadline);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(LINE_BREAK +
                    "\n Got it. I've added this task:\n  " +
                    deadline.toString() +
                    getTaskCount() +
                    LINE_BREAK);
            break;
        case "event":
            if (temp.length != 2) {
                throw new DukeException("\n ☹ OOPS!!! The description of an event cannot be empty.\n");
            }
            String[] temp3 = temp[1].split(" /from ");
            if (temp3.length != 2) {
                throw new DukeException("\n ☹ OOPS!!! The date and time of an event cannot be empty.\n");
            }
            String[] temp4 = temp3[1].split(" /to ");
            if (temp4.length != 2) {
                throw new DukeException("\n ☹ OOPS!!! The end time of an event cannot be empty.\n");
            }
            String[] temp5 =  temp4[0].split(" ");
            Task event;
            try {
                LocalDate eventDate = LocalDate.parse(temp5[0]);
                LocalTime eventStart = LocalTime.parse(temp5[1], DateTimeFormatter.ofPattern("ha"));
                LocalTime eventEnd = LocalTime.parse(temp4[1], DateTimeFormatter.ofPattern("ha"));
                event = new Event(temp3[0], eventDate, eventStart, eventEnd);
            } catch (DateTimeException e) {
                throw new DukeException("\n Invalid Date/Time format! Format should be YYYY-MM-DD HH(AM/PM) " +
                        "/to HH(AM/PM) \n");
            }
            list.add(event);
            try {
                appendList(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(LINE_BREAK +
                    "\n Got it. I've added this task:\n  " +
                    event.toString() +
                    getTaskCount() +
                    LINE_BREAK);
            break;
        case "delete":
            delete(temp[1]);
            break;
        default:
            throw new DukeException("\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    private static void showList() {
        System.out.print(LINE_BREAK);
        if (list.isEmpty()) {
            System.out.println("\n The list is empty. Please add tasks in!");
        } else {
            System.out.println("\n Here are the tasks in your list:");
        }
        for (int i = 0; i < list.size(); i++) {
            Task temp = list.get(i);
            String current = String.format(" %d.%s", i + 1, temp.toString());
            System.out.println(current);
        }
        System.out.println(LINE_BREAK);
    }

    private static void markDone(String str) throws DukeException {
        // Adapted from https://stackoverflow.com/questions/43156077/how-to-check-if-a-string-is-float-or-int
        boolean isInteger = str.matches("\\b[1-9][0-9]*\\b");

        if (!isInteger) {
            throw new DukeException("\n The task number you have keyed in is not valid. Please try again.\n");
        }

        int num = Integer.parseInt(str) - 1;

        if (list.size() <= num) {
            throw new DukeException("\n This task number is not available. Please try again.\n");
        }

        Task curr = list.get(num);
        if (curr.checkDone()) {
            System.out.println(LINE_BREAK +
                    "\n This task is already marked done!\n" +
                    LINE_BREAK);
        } else {
            curr.markDone();
            System.out.println(LINE_BREAK +
                    "\n Nice! I've marked this task as done:\n " +
                    curr.toString() +
                    "\n" +
                    LINE_BREAK);
        }
        try {
            writeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void unmarkDone(String str) throws DukeException {
        // Adapted from https://stackoverflow.com/questions/43156077/how-to-check-if-a-string-is-float-or-int
        boolean isInteger = str.matches("\\b[1-9][0-9]*\\b");

        if (!isInteger) {
            throw new DukeException("\n The task number you have keyed in is not valid. Please try again.\n");
        }

        int num = Integer.parseInt(str) - 1;

        if (list.size() <= num) {
            throw new DukeException("\n This task number is not available. Please try again.\n");
        }

        Task curr = list.get(num);
        if (!curr.checkDone()) {
            System.out.println(LINE_BREAK +
                    "\n This task is not done yet!\n" +
                    LINE_BREAK);
        } else {
            curr.unmarkDone();
            System.out.println(LINE_BREAK +
                    "\n OK, I've marked this task not done yet:\n " +
                    curr.toString() +
                    "\n" +
                    LINE_BREAK);
        }

        try {
            writeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void delete(String str) throws DukeException {
        // Adapted from https://stackoverflow.com/questions/43156077/how-to-check-if-a-string-is-float-or-int
        boolean isInteger = str.matches("\\b[1-9][0-9]*\\b");

        if (!isInteger) {
            throw new DukeException(" The task number you have keyed in is not valid. Please try again.\n");
        }

        int num = Integer.parseInt(str) - 1;

        if (list.size() <= num) {
            throw new DukeException(" This task number is not available. Please try again.\n");
        }

        Task curr = list.get(num);
        list.remove(num);
        try {
            writeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(LINE_BREAK +
                "\n Noted. I've removed this task:\n  " +
                curr.toString() +
                getTaskCount() +
                LINE_BREAK);
    }

    private static void readAllTasks() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String now = sc.nextLine();
            String[] split = now.split(" \\| ");

            switch (split[0]) {
            case "T":
                Task toDo = new ToDo(split[2]);
                if (split[1].equals("X")) {
                    toDo.markDone();
                }
                list.add(toDo);
                break;
            case "D":
                Task deadline;
                try {
                    LocalDate deadlineDate = LocalDate.parse(split[3]);
                    deadline = new Deadline(split[2], deadlineDate);
                } catch (DateTimeException e) {
                    throw new DukeException("\n Incorrect date format! Date format should be YYYY-MM-DD \n");
                }
                if (split[1].equals("X")) {
                    deadline.markDone();
                }
                list.add(deadline);
                break;
            case "E":
                String[] temp = split[3].split(" ");
                Task event;
                try {
                    LocalDate eventDate = LocalDate.parse(temp[0]);
                    LocalTime eventStart = LocalTime.parse(temp[1], DateTimeFormatter.ofPattern("ha"));
                    LocalTime eventEnd = LocalTime.parse(temp[3], DateTimeFormatter.ofPattern("ha"));
                    event = new Event(split[2], eventDate, eventStart, eventEnd);
                } catch (DateTimeException e) {
                    throw new DukeException("\n Invalid Date/Time format! Format should be YYYY-MM-DD HH(AM/PM) " +
                            "/to HH(AM/PM) \n");
                }
                if (split[1].equals("X")) {
                    event.markDone();
                }
                list.add(event);
                break;
            default:
                throw new DukeException("\n There seems to be an invalid task type in your save file!!! \n");
            }
        }
    }

    private static void createFile(File file) throws IOException {
        file.createNewFile();
    }

    private static void appendList(Task task) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(System.getProperty("line.separator") + task.toList());
        fw.close();
    }

    private static void writeAll() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(list.get(0).toList());
        int num = list.size();
        for (int i = 1; i < num; i++) {
            fw.write(System.getProperty("line.separator") + list.get(i).toList());
        }
        fw.close();
    }

    private static String getTaskCount() {
        return String.format("\n Now you have %d tasks in the list.\n", list.size());
    }
}
