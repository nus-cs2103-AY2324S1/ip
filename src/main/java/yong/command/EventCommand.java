package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Task;
import yong.tasks.Event;

import yong.exception.DukeException;


/**
 * Represents the actions needed if the user inputs an event task
 */
public class EventCommand extends Command {

    private String inp;

    /**
     * Constructor for the event command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param inp Line of input from the CLI
     */
    public EventCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inp = inp;
    }

    /**
     * Method to be executed when this command is called.
     * Initializes an Event object and adds it to the taskList.
     */
    @Override
    public String execute() {
        try {
            String[] parts = inp.split("/", 3);
            String[] typeDescription = parts[0].split(" ", 2);
            String type = typeDescription[0];
            String description = typeDescription[1];
            String from = parts[1].trim().split(" ", 2)[1];
            String to = parts[2].trim().split(" ", 2)[1];

            Task newTask = new Event(description, from, to);

            taskList.add(newTask);

            outputString = "Okay! Task added \n" + newTask;

            return outputString;
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Please give a valid description for an Event task!");
        }
    }
}
