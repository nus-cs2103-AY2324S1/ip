# Chad

Chad is a desktop app for mananging your everyday tasks, optimized for use via a **Command Line Interface**
(CLI) while still having the benefits of a **Graphical User Interface** (GUI). 

## Features 

### Adding Task: `todo, deadline, event`

Adds a todo/deadline/event task to the current task list.

Format: `todo [TASK DESCRIPTION]`, `deadline [TASK DESCRIPTION] /by [DEADLINE DATE AND TIME]`, 
`event project meeting /from [START DATE AND TIME] /to [END DATE AND TIME]`

> [!IMPORTANT]
> There must be a spacing between the time entered and the annotation `AM/PM`

Examples: 
- `todo return book`
- `deadline return book /by 2023-09-23 4 PM`
- `event project meeting /from 2023-09-22 2 PM /to 2023-09-23 4 PM`

### Deleting a task: `delete`

Delete an existing task from the current task list.

Format: `delete [TASK NUMBER]`

Example: `delete 1` deletes the first task in the task list.

### Find a task: `find`

Find all tasks that contains the given keyword.

Format: `find book`

Example: `find book` returns `[T] [ ] return book`

### List all tasks: `list`

Shows a list of all current tasks in the list. 

### Mark a task as completed: `mark`

Marks a task as completed. 

Format: `mark [TASK NUMBER]`

Example: `mark 1` marks the first task as completed.

### Unmark a task as completed: `unmark`

Unmarks a task as completed. 

Format: `unmark [TASK NUMBER]`

Example: `unmark 1` unmarks the first task as uncompleted. 

### Viewing Help: `help`

Provides help to the user by providing 3 most common issues faced.

Format: `help [OPTION NUMBER]`

Example: `help` launches the 3 available options. `help 1` selects the first option and 
gives the corresponding answer.

### Exit Chatbot: `bye`

Exits the chatbot.

Format: `bye`

