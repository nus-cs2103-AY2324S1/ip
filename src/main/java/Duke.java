import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<Task> data = new ArrayList<>();

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

    public static void print(String input, Duke roo, Scanner sc) {
        while (true) {
            if (input.equals("list")) {
                roo.list();
                input = sc.nextLine();
            } else if (input.startsWith("unmark")) {
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
            } else if (input.startsWith("mark")) {
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
            } else if (input.startsWith("delete")) {
                try {
                    if (input.length() < 8) {
                        throw new DukeException("Please delete your task using this format: \"delete [serial number]\"\n");
                    }
                    int t = Integer.parseInt(input.substring(7));
                    if (t > roo.data.size()) {
                        throw new DukeException("We dunhave so many task lah =_=\nq");
                    }
                    roo.delete(t - 1);
                } catch (DukeException e) {
                    System.err.println(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.startsWith("todo")) {
                String tsk = input.substring(4);
                try {
                    roo.add(new Todo(tsk));
                } catch (DukeException e) {
                    System.err.println(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (!input.contains("/by")) {
                        throw new DukeException("Please enter your task with this format: \"deadline task_description /by deadline\"\n");
                    }
                    String tsk = input.substring(8, input.indexOf("/") - 1);
                    String date = input.substring(input.indexOf("/by") + 3);
                    roo.add(new Deadline(tsk, date));
                } catch (DukeException e) {
                    System.err.println(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.startsWith("event")) {
                try {
                    if (!input.contains("/from") || !input.contains("/to")) {
                        throw new DukeException("Please enter your task with this format: \"event task_description /from start /to end\"\n");
                    }
                    String tsk = input.substring(5, input.indexOf("/from") - 1);
                    String start = input.substring(input.indexOf("/from") + 5, input.indexOf("/to") - 1);
                    String end = input.substring(input.indexOf("/to") + 3);
                    roo.add(new Event(tsk, start, end));
                } catch (DukeException e) {
                    System.err.println(e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.startsWith("end")) {
                sc.close();
                roo.bye();
                break;
            } else {
                try {
                    throw new DukeException("I dunno what u mean!!!\n");
                } catch (DukeException e) {
                    System.err.println(e);
                    input = sc.nextLine();
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke roo = new Duke();
        roo.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        print(input, roo, sc);
    }
}
