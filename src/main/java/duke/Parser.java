package duke;

import duke.task.DukeList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Parser {
    Scanner scanner;
    boolean notBye;

    public Parser() {
        scanner = new Scanner(System.in);
        notBye = true;
    }

    public void parse(Storage storage, DukeList dukelist, Ui ui) {
        while (notBye) {


            try {
                String input = scanner.nextLine();
                String[] splited = input.split(" ", 2);
                if (splited[0].equals("bye") && splited.length == 1) {
                    notBye = false;
                    ui.bye();
                } else if (splited[0].equals("list")) {
                    dukelist.printList();
                } else if (splited[0].equals("find")) {
                    String desc = splited[1];
                    DukeList newList = new DukeList();
                    System.out.println("Here are the matching tasks in the list:");
                    for (int i = 0; i < dukelist.getList().size(); i++) {
                        int count = 1;
                        if (dukelist.getList().get(i).description.contains(desc)) {
                            System.out.println(String.valueOf(count) + ". " + dukelist.getList().get(i).toString());
                            count++;
                        }
                    }
                } else if (splited[0].equals("mark")) {
                    int number = Integer.parseInt(splited[1]);
                    dukelist.setDone(number);
                    storage.saveData(dukelist.getList());
                } else if (splited[0].equals("unmark")) {
                    int number = Integer.parseInt(splited[1]);
                    dukelist.setUndone(number);
                    storage.saveData(dukelist.getList());
                } else if (splited[0].equals("todo")) {
                    if (splited.length > 1) {
                        String[] job = input.split(" ", 2);
                        dukelist.addTodo(job[1]);
                        storage.saveData(dukelist.getList());
                    } else {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                } else if (splited[0].equals("deadline")) {
                    if (splited.length > 1) {
                        String[] splitted = input.split(" ", 2);
                        String[] deadline = splitted[1].split("/by", 2);
                        LocalDateTime by = formatData(deadline[1]);
                        dukelist.addDeadline(deadline[0], by);
                        storage.saveData(dukelist.getList());
                    } else {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                } else if (splited[0].equals("event")) {
                    if (splited.length > 1) {
                        String[] splitted = input.split(" ", 2);
                        String[] from = splitted[1].split("/from", 2);
                        String[] to = from[1].split("/to", 2);
                        LocalDateTime start = formatData(to[0]);
                        LocalDateTime end = formatData(to[1]);
                        dukelist.addEvent(from[0], start, end);
                        storage.saveData(dukelist.getList());
                    } else {
                        throw new DukeException("The description of an event cannot be empty.");
                    }

                } else if (splited[0].equals("delete")){
                    int num = Integer.parseInt(splited[1]);
                    dukelist.deleteTask(num - 1);
                    storage.saveData(dukelist.getList());
                }
                else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
        }

        public LocalDateTime formatData(String data) {
            String trimmed = data.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDate;
            return localDate = LocalDateTime.parse(trimmed, formatter);
        }

}
