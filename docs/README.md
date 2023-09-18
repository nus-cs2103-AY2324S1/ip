# Jyuuni

A chatbot to help organise your tasks.

## Features 

### GUI

Jyuuni comes with it's own GUI for a pleasant user experience.

### Load/Store data

User tasks are saved at the end of the session, and automatically preloaded at the start of the next session.<br>
If a particular line in the data file cannot be parsed, Jyuuni will ignore and delete the line.<br>

To change the default location of the data file used to load and store chatbot data, navigate to
`src/main/java/duke/Duke.java` and change the file location used in the storage field of the Duke constructor.
```java
public Duke() {
    storage = new Storage("./data");
    taskList = new TaskList();
}
```


## Commands

All commands are to be input using the text field. Jyuuni's responses will be reflected on the screen just like a
messaging app.
<br>
The following commands are supported by Jyuuni.

### `help`
Lists all user commands.

### `mark/unmark [int]`
Marks a command as complete/incomplete respectively.

Example of usage:<br>
`mark 1`<br>
`unmark 3`<br>
Marks the 1st task, and unmarks the 3rd task.

### `delete [int]`
Deletes a task from the list.

Example of usage:<br>
`delete 4`<br>
Deletes the 4th task in the list.

### `todo [description]`
Adds a todo task with a description to the users task list.

Example of usage:<br>
`todo Buy indomie`<br>
Returns a todo task with the description `Buy indomie`.

### `deadline [description] /by [endDateTime]`
Adds a deadline task with a description and deadline.<br>
`[endDateTime]` argument takes the format `dd.mm.yyyy tttt`.

Example of usage:<br>
`deadline Project #A /by 27.04.2023 1700`<br>
Returns a deadline task with the description `Project #A` with the deadline `27 April 2023, 5pm`.

### `event [description] /from [startDateTime] /to [endDateTime]`
Adds an event task with a description, start and end dateTime.<br>
`[startDateTime]` & `[endDateTime]` arguments take the format `dd.mm.yyyy tttt`.

Example of usage:<br>
`event Japan trip /from 26.04.2023 1000 /to 14.05.2023 1800`<br>
Returns an event task with the description `Japan trip` starting at `26 April 2023 10am` to `14 May 2023 6pm`.

### `schedule [dateTime]`
Displays a list of unmarked tasks containing
1. All `todo` tasks.
2. `deadline` tasks with a limit after `[dateTime]`.
3. `event` tasks where `[dateTime]` is within its duration.

Example of usage:<br>
`schedule 10.04.2023 1000`<br>
When used on a list of tasks containing the 3 tasks from above, only the `todo` & `deadline` tasks will be displayed 
as a list.

### `find [string]`
Displays all tasks with a description matching `[string]`. The input query is not case-sensitive.

Example of usage:<br>
`find japan`<br>
When used on a list of tasks containing the 3 tasks from above, only the `event` task will be displayed as a list.

### `list`
Displays every task as a list.

### `end`
Ends the current Jyuuni session.