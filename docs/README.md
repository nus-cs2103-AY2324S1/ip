# User Guide for May

May is a text-based task management application that helps you keep track of your to-do items, deadlines, and events. This guide will help you get started with May and make the most out of its features.

## Features 

### Adding a Todo: `todo`

Adds a todo to the list.

Format: `todo [DESCRIPTION]`

Examples:
- `todo read book`
- `todo run`

### Adding a Deadline: `deadline`

Adds a task with a deadline to the list.

Format: `deadline [DESCRIPTION] /by [DUE_DATE]`

Examples:
- `deadline return book /by Monday`
- `deadline assignment /by 23/9/2023`
- `deadline homework /by 23/9/2023 2359`


### Adding an Event: `event`

Adds an event with the timing to the list.

Format: `event [DESCRIPTION] /from [START_TIME] /to [END_TIME]`

Examples:
- `event project meeting /from 2pm /to 4pm`
- `event lecture /from 8am /to 10am`

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Finding tasks by name: `find`

Finds tasks whose descriptions contain any of the given words.

Format: `find [KEYWORDS] `
- Only the description is searched.

Examples:
- `find book` returns `return book` and `read book`
- `find read` returns `read book`

### Deleting a task: `delete`

Deletes the specified task from the list.

Format: `delete [INDEX]`
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
- `delete 2` deletes the 2nd task in the task list.

### Marking a task as done: `mark`

Marks the specified task from the list as done.

Format: `mark [INDEX]`
- Marks the task at the specified INDEX as done.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
- `mark 2` marks the 2nd task in the task list as done.

### Marking a task as not done: `unmark`

Marks the specified task from the list as not done.

Format: `unmark [INDEX]`
- Marks the task at the specified INDEX as not done.
- The index refers to the index number shown in the task list.
- The index must be a positive integer 1, 2, 3, …​

Examples:
- `unmark 2` marks the 2nd task in the task list as not done.

### Exiting the program: `bye`

Exits the program.

Format: `bye`


