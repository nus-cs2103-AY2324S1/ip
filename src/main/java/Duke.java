import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> data = new ArrayList<>();
    private File file;
    private String filePath;

    private enum Command {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, END, UNKNOWN
    }

    private void greet() {
        try {
            this.filePath = "roo.txt";
            this.file = new File(this.filePath);
            if (!file.createNewFile()) {
                this.readFileContents();
            }
        } catch (IOException e) {
            System.err.println("I/O error greet: " + e.getMessage());
        } finally {
            String logo = " ____                \n"
                    + "|  _ \\  _____  _____ \n"
                    + "| |/ / |  _  ||  _  |\n"
                    + "| |\\ \\ | |_| || |_| |\n"
                    + "|_| \\_\\|_____||_____|\n";
            String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
            System.out.println("Hello from\n" + logo + hello);
        }
    }

    private void bye() {
        System.out.println("Ciao! Hope to see you soon yorr!!");
    }

    private void add(Task task) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(task.toString() + "\n");
            writer.close();
            this.data.add(task);
            System.out.println("\"" + task.toString() + "\" added :)");
            System.out.println("Now got " + data.size() + " task liao T_T\n");
        } catch (IOException e) {
            System.err.println("I/O error add " + e.getMessage());
        }

    }

    private void list() {
        System.out.println("Although I dunwan to list... But here is your list:");
        for (int i = 0; i < data.size(); i++) {
            Task dt = data.get(i);
            System.out.println((i + 1) + ". " + dt.toString());
        }
        System.out.println(" ");
    }

    private void markDone(int i) {
        data.get(i).markDone();
        this.modifyFile();
        System.out.println("Yay! \"" + data.get(i) + "\" done liao!!\n");
    }

    private void markUndone(int i) {
        data.get(i).markUndone();
        this.modifyFile();
        System.out.println("Hmm... Why just now don't mark \"" + data.get(i) + "\" as done properly...\n");
    }

    private void delete(int i) {
        System.out.println("Okay!! Task \"" + data.get(i) + "\" removed :) ");
        data.remove(i);
        this.modifyFile();
        System.out.println("You still have " + data.size() + " tasks in the list\n");
    }

    private void print(String input, Scanner sc) {
        boolean loop = true;

        while (loop) {
            Command c;
            if (input.equals("list")) {
                c = Command.LIST;
            } else if (input.startsWith("unmark")) {
                c = Command.UNMARK;
            } else if (input.startsWith("mark")) {
                c = Command.MARK;
            } else if (input.startsWith("delete")) {
                c = Command.DELETE;
            } else if (input.startsWith("todo")) {
                c = Command.TODO;
            } else if (input.startsWith("deadline")) {
                c = Command.DEADLINE;
            } else if (input.startsWith("event")) {
                c = Command.EVENT;
            } else if (input.startsWith("end")) {
                c = Command.END;
            } else {
                c = Command.UNKNOWN;
            }

            switch (c) {
                case LIST:
                    this.list();
                    input = sc.nextLine();
                    break;

                case UNMARK:
                    try {
                        if (input.length() < 8) {
                            throw new DukeException("Please unmark your task using this format: \"unmark [serial number]\"\n");
                        }
                        int t = Integer.parseInt(input.substring(7));
                        if (t > this.data.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\n");
                        } else if (!this.data.get(t - 1).done()) {
                            throw new DukeException("Weihh... It's unmark ehhh\n");
                        }
                        this.markUndone(t - 1);
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case MARK:
                    try {
                        if (input.length() < 6) {
                            throw new DukeException("Please mark your task using this format: \"mark [serial number]\"\n");
                        }
                        int t = Integer.parseInt(input.substring(5));
                        if (t > this.data.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\nq");
                        } else if (this.data.get(t - 1).done()) {
                            throw new DukeException("Weihh... It's already mark ehhh\n");
                        }
                        this.markDone(t - 1);
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case DELETE:
                    try {
                        if (input.length() < 8) {
                            throw new DukeException("Please delete your task using this format: \"delete [serial number]\"\n");
                        }
                        int t = Integer.parseInt(input.substring(7));
                        if (t > this.data.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\n");
                        }
                        this.delete(t - 1);
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case TODO:
                    String tsk = input.substring(4);
                    try {
                        this.add(new Todo(tsk));
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case DEADLINE:
                    try {
                        if (!input.contains("/by")) {
                            throw new DukeException("Please enter your task with this format: \"deadline task_description /by deadline\"\n");
                        }
                        String tk = input.substring(8, input.indexOf("/") - 1);
                        String date = input.substring(input.indexOf("/by") + 3);
                        this.add(new Deadline(tk, date));
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case EVENT:
                    try {
                        if (!input.contains("/from") || !input.contains("/to")) {
                            throw new DukeException("Please enter your task with this format: \"event task_description /from start /to end\"\n");
                        }
                        String ts = input.substring(5, input.indexOf("/from") - 1);
                        String start = input.substring(input.indexOf("/from") + 5, input.indexOf("/to") - 1);
                        String end = input.substring(input.indexOf("/to") + 3);
                        this.add(new Event(ts, start, end));
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case END:
                    loop = false;
                    sc.close();
                    this.bye();
                    break;

                case UNKNOWN:
                    try {
                        throw new DukeException("I dunno what u mean!!!\n");
                    } catch (DukeException e) {
                        System.err.println(e);
                        input = sc.nextLine();
                    }
                    break;
            }

        }
    }

    private Task makeTask(String str) {
        try {
            if (str.startsWith("[T]")) {
                if (str.contains("[x]")) {
                    return new Todo(str.substring(8), true);
                } else if (str.contains("[ ]")) {
                    return new Todo(str.substring(8), false);
                }
            } else if (str.startsWith("[D]")) {
                int sub = str.indexOf("by: ");
                if (str.contains("[x]")) {
                    return new Deadline(str.substring(8, sub - 1), str.substring(sub + 2), true);
                } else if (str.contains("[ ]")) {
                    return new Deadline(str.substring(8, sub - 1), str.substring(sub + 2), false);
                }
            } else if (str.startsWith("[E]")) {
                int sub1 = str.indexOf("from: ");
                int sub2 = str.indexOf("to: ");
                if (str.contains("[x]")) {
                    return new Event(str.substring(8, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                            str.substring(sub2 + 4), true);
                } else if (str.contains("[ ]")) {
                    return new Event(str.substring(8, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                            str.substring(sub2 + 4), false);
                }
            }
        } catch (DukeException e) {
            System.err.println(e);
        }
        return null;
    }

    private void readFileContents() throws FileNotFoundException {
        Scanner sc = new Scanner(this.file);
        while (sc.hasNext()) {
            this.data.add(this.makeTask(sc.nextLine()));
        }
        sc.close();
    }

    private void modifyFile() {
        try {
            Files.delete(Paths.get(filePath));
            this.file = new File(this.filePath);
            FileWriter writer = new FileWriter(filePath, true);
            for (Task t : this.data) {
                writer.write(t.toString()  + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("I/O error modify " + e.getMessage());
        }

    }

    public static void main(String[] args) {
        Duke roo = new Duke();
        roo.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        roo.print(input, sc);
    }
}
