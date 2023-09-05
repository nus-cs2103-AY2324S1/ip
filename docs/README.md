# User Guide

Penguin is a friendly chatbot assistant 
that helps you manage tasks, deadlines and events.
It comes with the benefits of a Graphical User Interface (GUI)
and the speed and simplicity of command-line input. Let Penguin
manage your schedule today!

## Features 

### Remembers tasks

Penguin will remember tasks that you ask it to remember, 
even after Penguin is closed and re-opened. 

#### `list` - List all tasks currently remembered. 

Penguin will tell you the list of tasks it is currently remembering. 

Format: `list`

#### `todo` - Add a new to-do task. 

Tell Penguin to remember a new to-do task. Such tasks have no date attached to them. 

Format: `todo <TASK NAME>`

#### `deadline` - Add a new deadline task.

Tell Penguin to remember a new deadline. Such tasks have a do-by date attached to them.

Format: `deadline <TASK NAME> /by <DUE DATE IN YYYY-MM-DD FORMAT>`



#### `event` - Add a new event task.

Tell Penguin to remember a new event. Such tasks have a start and end date attached to them.

Format: `event <TASK NAME> /from <START DATE IN YYYY-MM-DD FORMAT> /to <END DATE IN YYYY-MM-DD FORMAT>`

#### `delete` - Delete a task.

Tell Penguin to forget this task. 

Format: `delete <INDEX OF TASK>`

### Tracks tasks

Penguin can mark tasks as done or undone. 

#### `mark` - Mark a task as done.

Tell Penguin to mark this task as done.

Format: `mark <INDEX OF TASK>`

#### `unmark` - Un-mark a task as done.

Tell Penguin to un-mark this task; it is not yet done.

Format: `unmark <INDEX OF TASK>`

### Search for tasks

Penguin can search for tasks containing a particular keyword. 

#### `find` - Find a task with specified keyword. 

Ask Penguin to search for a task with the specified keyword in its task name.  

Format: `find <KEYWORD>`

