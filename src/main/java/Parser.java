import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import exceptions.*;

public class Parser {
    private final Database db; // parsed result lead to actions on db
    public Parser(Database db) {
        this.db = db;
    }

    /*
    parseAndExecute parses the input to handle exceptions and
    call database functions to execute
     */
    public void parseAndExecute(String input) {
        String[] words = input.split(" ", 2);

        Keyword currentKey;
        // if keyword not recognised
        try {
            currentKey = Keyword.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidKeywordException();
        }
            // keyword is recognised
            if (currentKey == Keyword.LIST) {
                this.db.displayData();
            } else {
                // if empty arguments
                if (words.length == 1) {
                    throw new EmptyArgumentException(currentKey.toString());
                } else {
                    // have correct keyword and arguments, just switch and execute
                    switch (currentKey) {
                        case MARK:
                            try {
                                int index = Integer.parseInt(words[1]);
                                this.db.markAsDone( index - 1);
                                break;
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Sorry you must provide the task number to mark/unmark it!");
                            } catch (IndexOutOfBoundsException e){
                                throw new IndexOutOfBoundsException(String.format("Sorry you do not have task #%s, input \"list\" to check your current list of tasks!", words[1]));
                            }

                        case UNMARK:
                            try {
                                int index = Integer.parseInt(words[1]);
                                this.db.markAsNotDone( index - 1);
                                break;
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Sorry you must provide the task number to mark/unmark it!");
                            } catch (IndexOutOfBoundsException e){
                                throw new IndexOutOfBoundsException(String.format("Sorry you do not have task #%s, input \"list\" to check your current list of tasks!", words[1]));
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
            }




        }

    }




