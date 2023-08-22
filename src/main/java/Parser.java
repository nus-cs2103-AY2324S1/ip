import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import exceptions.*;

public class Parser {
    private final Database db;
    public Parser(Database db) {
        this.db = db;
    }

    public void parseAndExecute(String input) {
        if (!input.isEmpty()) {
            String[] words = input.split(" ", 2);

            // if keyword not recognised
            if (!inKeywords(words[0])) {
                throw new InvalidKeywordException();
            }

            Keyword currentKey = Keyword.valueOf(words[0].toUpperCase());
            if (currentKey == Keyword.LIST) {
                this.db.displayData();
            } else {

                // if empty arguments
                if (words.length == 1) {
                    throw new EmptyArgumentException(currentKey.toString());
                }

                switch (currentKey) {
                    case MARK:
                    case UNMARK:
                        if (words[1].chars().allMatch(Character::isDigit)) {
                            int index = Integer.parseInt(words[1]);
                            if (currentKey == Keyword.MARK) {
                                this.db.markAsDone(index - 1);
                            } else {
                                this.db.markAsNotDone(index - 1);
                            }
                            break;
                        } else {
                            throw new NumberFormatException("Sorry you must provide the task number to mark/unmark it!");
                        }

                        // input of tasks are assumed to be in correct format
                    case TODO:
                        this.db.addTask(new Todo(words[1]));
                        break;
                    case EVENT:
                        words = words[1].split(" /from ");
                        String eventDescription = words[0];
                        words = words[1].split(" /to ");
                        String from = words[0];
                        String to = words[1];
                        this.db.addTask(new Event(eventDescription, from, to));
                        break;
                    case DEADLINE:
                        words = words[1].split(" /by ");
                        String ddlDescription = words[0];
                        String by = words[1];
                        this.db.addTask(new Deadline(ddlDescription, by));
                        break;
                }
            }
        } else {
            UI.displayCheems();
            System.out.println("Now tell me, do you want some fries?");
        }
    }

    private static boolean inKeywords(String word) {
        for (Keyword k: Keyword.values()) {
            if (k.name().toLowerCase().equals(word)) {
                return true;
            }
        }
        return false;
    }

}




