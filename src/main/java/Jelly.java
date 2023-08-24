import java.util.ArrayList;
import java.util.Scanner;
import exceptions.JellyException;
import exceptions.JellyBlankMessageException;
import exceptions.JellyUnknownCommandException;

public class Jelly {
    public static void main(String[] args) throws JellyException{
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<>();
        int index = 0;
        System.out.println("Hello! I'm Jelly");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            try {
            String str = sc.nextLine();
            String[] stringArray = str.split(" ");
            if (stringArray[0].equals("mark")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("mark");
                }
                int num = Integer.parseInt(stringArray[1]);
                if (num <= 0 || num > 100) {
                    System.out.println("Invalid input");
                } else if (storage.get(num - 1) != null) {
                    if (storage.get(num -1).isDone) {
                        System.out.println("Uh, it appears that you've finished this task o.o");
                    } else {
                        storage.get(num - 1).markAsDone();
                        System.out.println("Good job! I've marked this task as done :)");
                    }
                } else {
                    System.out.println("Invalid input");
                }
            } else if (stringArray[0].equals("unmark")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("unmark");
                }
                int num = Integer.parseInt(stringArray[1]);
                if (num <= 0 || num > 100) {
                    System.out.println("Invalid input");
                } else if (storage.get(num - 1) != null) {
                    if (!storage.get(num - 1).isDone) {
                        System.out.println("Yo,you can't unmark something you haven't done yet o.o");
                    } else {
                        storage.get(num - 1).markAsUndone();
                        System.out.println("Bad job! I've marked this task as not done :(");
                    }
                } else {
                    System.out.println("Invalid input");
                }
            } else if (stringArray[0].equals("delete")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("delete");
                }
              int num = Integer.parseInt(stringArray[1]);
              if (num <= 0 || num > 100 || num > storage.size()) {
                  System.out.println("Invalid input");
              } else if (storage.get(num - 1) != null) {
                  System.out.println("Okay, I've removed this task: \n" + storage.get(num - 1).toString());
                  storage.remove(num - 1);
                  index--;
              } else {
                  System.out.println("Invalid input");
              }
            } else if (stringArray[0].equals("todo")) {
                String toDoString = "";
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("todo");
                }
                for (int i = 1; i < stringArray.length; i++) {
                    if (stringArray[i] != null) {
                        toDoString += stringArray[i] + " ";
                    } else {
                        toDoString = toDoString.trim();
                        break;
                    }
                }
                storage.add(new Todo(toDoString));
                System.out.println("Ok! I've added this task: \n" + storage.get(index).toString());
                index++;
                System.out.println("Now you have " + storage.size() + " tasks in the list.");
            } else if (stringArray[0].equals("deadline")) {

                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("deadline");
                }

                String deadlineString = "";
                String byWhen = "";

                for (int i = 1; i < stringArray.length; i++) {
                    if (stringArray[i] != null) {
                        if (stringArray[i].equals("/by")) {
                            for (int j = i + 1; j < stringArray.length; j++) {
                                byWhen += stringArray[j] + " ";
                            }
                            byWhen = byWhen.trim();
                            break;
                        }
                        deadlineString += stringArray[i] + " ";
                    } else {
                        deadlineString = deadlineString.trim();
                        break;
                    }
                }

                storage.add(new Deadline(deadlineString, byWhen));
                System.out.println("Ok! I've added this task: \n" + storage.get(index).toString());
                index++;
                System.out.println("Now you have " + storage.size() + " tasks in the list.");
            } else if (stringArray[0].equals("event")) {

                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("event");
                }

                String deadlineString = "";
                String fromWhen = "";
                String toWhen = "";
                for (int i = 1; i < stringArray.length; i++) {
                    if (stringArray[i] != null) {
                        if (stringArray[i].equals("/from")) {
                            for (int j = i + 1; j < stringArray.length; j++) {
                                if (stringArray[j].equals("/to")) {
                                    for (int k = j + 1; k < stringArray.length; k++) {
                                        toWhen += stringArray[k] + " ";
                                    }
                                    toWhen = toWhen.trim();
                                    break;
                                }
                                fromWhen += stringArray[j] + " ";
                            }
                            fromWhen = fromWhen.trim();
                            break;
                        }
                        deadlineString += stringArray[i] + " ";
                    } else {
                        deadlineString = deadlineString.trim();
                        break;
                    }
                }
                storage.add(new Event(deadlineString, fromWhen, toWhen));
                System.out.println("Ok! I've added this task: \n" + storage.get(index).toString());
                index++;
                System.out.println("Now you have " + storage.size() + " tasks in the list.");
            } else if (stringArray[0].equals("bye")) {
                System.out.println("Bye, have a nice day!");
                return;
            } else if (stringArray[0].equals("list")) {
                System.out.println("Here are the tasks you listed:");
                for (int i = 0; i < storage.size(); i++) {
                    System.out.println((i + 1) + "." + storage.get(i).toString());
                }
            } else {
                throw new JellyUnknownCommandException();
            }
        } catch (JellyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
