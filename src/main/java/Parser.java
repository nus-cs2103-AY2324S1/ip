import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
public class Parser {

    public Parser() {
        //empty constructor to initialize class objects
    }

    public void chat(String str, List<Task> tasks) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Ui ui = new Ui();
        try {
                if (!str.equals("list")) {
                    if (str.startsWith("mark ")) {
                        String num = str.substring(5);
                        int number = Integer.valueOf(num);
                        if (number <= 0 || number > tasks.size()) {
                            throw new InvalidInputException(str);
                        }
                        int index = number - 1; //index for task list
                        Task done = tasks.get(index);
                        done.markAsDone();
                        ui.printDone(done);
                    } else if (str.startsWith("unmark ")) {
                        String num = str.substring(7);
                        int number = Integer.valueOf(num);
                        if (number <= 0 || number > tasks.size()) {
                            throw new InvalidInputException(str);
                        }
                        int index = number - 1; //index for task list
                        Task notDone = tasks.get(index);
                        notDone.markAsNotDone();
                        ui.printNotDone(notDone);
                    } else if (str.startsWith("delete ")) {
                        String num = str.substring(7);
                        int number = Integer.valueOf(num);
                        if (number <= 0 || number > tasks.size()) {
                            throw new InvalidInputException(str);
                        }
                        int index = number - 1;
                        Task toBeDeleted = tasks.remove(index);
                        ui.printDelete(toBeDeleted, tasks);
                    } else {
                        if (str.startsWith("todo")) {
                            String todo = str.substring(4);
                            //remove any leading and trailing whitespace characters and
                            //check whether there is a task after the instruction
                            if (todo.trim().isEmpty()) {
                                //this would mean the instruction is incomplete
                                throw new ToDoCommandUseException(str);
                            }
                            String string = str.substring(5);
                            Task task = new ToDo(string);
                            tasks.add(task);
                            ui.printAddTask(task, tasks);
                        } else if (str.startsWith("deadline")) {
                            if (!str.contains("/by ")) {
                                throw new DeadlineCommandUseException(str); //needs to check for /by
                            } else {
                                String byWhen = "/by ";
                                int index = str.indexOf(byWhen);
                                String deadline = str.substring(index + 4); //remove /by from the substring
                                if (deadline.trim().isEmpty()) { //needs to check whether there is anything after /by
                                    throw new DeadlineCommandUseException(str);
                                }
                                String workToDo = str.substring(9, index);
                                Task task = new Deadline(workToDo, LocalDateTime.parse(deadline, formatter));
                                tasks.add(task);
                                ui.printAddTask(task, tasks);
                            }
                        } else if (str.startsWith("event")) {
                            if (!str.contains("from")) {
                                throw new EventCommandUseException(str);
                            } else {
                                String fromMarker = "/from "; //mark the /from index of the string
                                int firstIndex = str.indexOf(fromMarker);
                                int secondIndex;
                                String fromWhen;
                                String toWhen;
                                String workToDo = str.substring(6, firstIndex);
                                String afterFirstIndex = str.substring(firstIndex + 6);
                                if (!afterFirstIndex.contains("/to ")) { //to check the input of /to after /from
                                    throw new EventCommandUseException(str);
                                } else {
                                    String toMarker = "/to "; //mark the /to index of the string
                                    secondIndex = afterFirstIndex.indexOf(toMarker); //to make sure we get the /to after the /from
                                    fromWhen = afterFirstIndex.substring(0, secondIndex).trim(); //get the from timing
                                    toWhen = afterFirstIndex.substring(secondIndex + 3).trim(); //get the to timing
                                    if (fromWhen.trim().isEmpty() ||
                                            toWhen.trim().isEmpty()) { //needs to check whether there is anything after /by
                                        throw new EventCommandUseException(str);
                                    }
                                    Task task = new Event(workToDo, LocalDateTime.parse(fromWhen, formatter),
                                            LocalDateTime.parse(toWhen, formatter));
                                    tasks.add(task);
                                    ui.printAddTask(task, tasks);
                                }
                            }
                        } else {
                            throw new InvalidInputException(str);
                        }
                    }
                } else {
                    Task.listTasks(tasks);
                    System.out.println();
                }
        } catch (java.time.format.DateTimeParseException e) {
            //detect inputs that don't follow the yyyy-MM-dd HHmm format
            ui.printException();
        } catch (InvalidInputException | EventCommandUseException |
                 DeadlineCommandUseException | ToDoCommandUseException e) {
            ui.printException(e.getMessage());
        }
    }
}
