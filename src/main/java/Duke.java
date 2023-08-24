import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> data = new ArrayList<>();

    private enum Command {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, END, UNKNOWN
    }

    public void greet() {
        String logo = " ____                \n"
                + "|  _ \\  _____  _____ \n"
                + "| |/ / |  _  ||  _  |\n"
                + "| |\\ \\ | |_| || |_| |\n"
                + "|_| \\_\\|_____||_____|\n";
        String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
        System.out.println("Hello from\n" + logo + hello);
    }

    public void bye() {
        System.out.println("Ciao! Hope to see you soon yorr!!");
    }

    public void add(Task task) {
        data.add(task);
        System.out.println("\"" + task.toString() + "\" added :)");
        System.out.println("Now got " + data.size() + " task liao T_T\n");
    }

    public void list() {
        System.out.println("Although I dunwan to list... But here is your list:");
        for (int i = 0; i < data.size(); i++) {
            Task dt = data.get(i);
            System.out.println((i + 1) + ". " + dt.toString());
        }
        System.out.println(" ");
    }

    public void markDone(int i) {
        data.get(i).markDone();
        System.out.println("Yay! \"" + data.get(i) + "\" done liao!!\n");
    }

    public void markUndone(int i) {
        data.get(i).markUndone();
        System.out.println("Hmm... Why just now don't mark \"" + data.get(i) + "\" as done properly...\n");
    }

    public void delete(int i) {
        System.out.println("Okay!! Task \"" + data.get(i) + "\" removed :) ");
        data.remove(i);
        System.out.println("You still have " + data.size() + " tasks in the list\n");
    }

    public void print(String input, Duke roo, Scanner sc) {
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
                    roo.list();
                    input = sc.nextLine();
                    break;

                case UNMARK:
                    try {
                        if (input.length() < 8) {
                            throw new DukeException("Please unmark your task using this format: \"unmark [serial number]\"\n");
                        }
                        int t = Integer.parseInt(input.substring(7));
                        if (t > roo.data.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\n");
                        } else if (!roo.data.get(t - 1).done()) {
                            throw new DukeException("Weihh... It's unmark ehhh\n");
                        }
                        roo.markUndone(t - 1);
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
                        if (t > roo.data.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\nq");
                        } else if (roo.data.get(t - 1).done()) {
                            throw new DukeException("Weihh... It's already mark ehhh\n");
                        }
                        roo.markDone(t - 1);
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
                        if (t > roo.data.size()) {
                            throw new DukeException("We dunhave so many task lah =_=\n");
                        }
                        roo.delete(t - 1);
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case TODO:
                    String tsk = input.substring(4);
                    try {
                        roo.add(new Todo(tsk));
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
                        roo.add(new Deadline(tk, date));
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
                        roo.add(new Event(ts, start, end));
                    } catch (DukeException e) {
                        System.err.println(e);
                    } finally {
                        input = sc.nextLine();
                    }
                    break;

                case END:
                    loop = false;
                    sc.close();
                    roo.bye();
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

    public static void main(String[] args) {
        Duke roo = new Duke();
        roo.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        roo.print(input, roo, sc);
    }
}
