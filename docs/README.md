# Jyuuni

A chatbot to help organise your tasks.

## Features 

### GUI

Jyuuni comes with it's own GUI for a pleasant user experience.

### Load/Store data

User tasks are saved at the end of the session, and automatically preloaded at the start of the next session.

## Commands

All commands are to be input using the text field. Jyuuni's responses will be reflected on the screen just like a
messaging app.

### `help`
Lists all user commands.

### `mark/unmark [int]`
Marks a command as complete/incomplete respectively.

Example of usage:
`mark 1`
`unmark 3`
Marks the 1st task, and unmarks the 3rd task.

### `delete [int]`
Deletes a task from the list.

Example of usage:
`delete 4`
Deletes the 4th task in the list.

### `todo [description]`
Adds a todo task with a description to the users task list.

Example of usage:
`todo Buy indomie`
Returns a todo task with the description `Buy indomie`.

### `deadline [description] /by [endDateTime]`
Adds a deadline task with a description and deadline.
`[endDateTime]` argument takes the format `dd.mm.yyyy tttt`.

Example of usage:
`deadline Project #A /by 27.04.2023 1700`
Returns a deadline task with the description `Project #A`
with the deadline `27 April 2023, 5pm`.

### `event [description] /from [startDateTime] /to [endDateTime]`
Adds an event task with a description, start and end dateTime.
`[startDateTime]` & `[endDateTime]` arguments take the format `dd.mm.yyyy tttt`.

Example of usage:
`event Japan trip /from 26.04.2023 1000 /to 14.05.2023 1800`
Returns an event task with the description `Japan trip` starting at
`26 April 2023 10am` to `14 May 2023 6pm`.

### `schedule [dateTime]`
Displays a list of unmarked tasks containing
1. All `todo` tasks.
2. `deadline` tasks with a limit after `[dateTime]`.
3. `event` tasks where `[dateTime]` is within its duration.

Example of usage:
`schedule 10.04.2023 1000`
When used on a list of tasks containing the 3 tasks from above, only
the `todo` & `deadline` tasks will be displayed as a list.

### `find [string]`
Displays all tasks with a description matching `[string]`.
The input query is not case-sensitive.

Example of usage:
`find japan`
When used on a list of tasks containing the 3 tasks from above, only
the `event` task will be displayed as a list.

### `list`
Displays every task as a list.

### `end`
Ends the current Jyuuni session.