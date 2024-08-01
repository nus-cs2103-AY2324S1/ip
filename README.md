# J.A.R.V.I.S.
> A personal chatbot assistant to help you with your daily tasks.

<img src="docs/Ui.png" width="300">

## Types of Tasks
Jarvis supports 3 types of tasks: Todo, Deadline and Event.

1. Todo: Tasks that need to be done but have no deadline or time limit.
2. Deadline: Tasks that need to be done before a specific date and time.
3. Event: Tasks that start and end at a specific date and time. It spans over a period of time.

## Method of interaction
You can interact with Jarvis with a pre-defined set of commands following the format `command (optional arguments)`.
The list of all the commands that Jarvis supports is listed below.

- Add a task to the task list: `todo`, `deadline`, `event`
- Delete a task from the task list: `delete`
- Mark a task as done/undone: `mark`, `unmark`
- List all the tasks in the task list: `list`
- Find all tasks in the task list with a keyword: `find`
- Tag/untag a task: `tag`, `untag`
- Exit the program: `exit`

More details on the usage of each command can be found in the [Features](#features) section.

## Features 

> Note:
> 1. Words in <> represent compulsory arguments. 
> 2. Words in [] are optional arguments. They can be omitted.
> 3. Arguments with ... after them can be used multiple times, including zero times.
> 4. Commands like `mark`, `delete`, etc. require the index of the task in the task list as arguments. 
     The index starts from 1.

### Add a Task
- Todo task: `todo <task description>`. The task description cannot be empty.
- Deadline task: `deadline <task description> /by <datetime>`. The datetime must be in the format `yyyy-MM-dd HH:mm`.
  <br/> For example, `deadline return book /by 2020-09-18 23:59`.
- Event task: `event <task description> /from <start datetime> /to <end datetime>`. The datetime must be in the same
  format as the deadline command. 
  <br/> For example, `event project meeting /from 2020-09-18 14:00 /to 2020-09-18 16:00`.

### Mark a Task as Done/Undone
- Mark a task as done: `mark <task index>`.
  <br/> For example, `mark 1` marks the first task in the task list as done.
- Mark a task as undone: `unmark <task index>`.
  <br/> For example, `unmark 3` marks the third task in the task list as done.

If you try to mark or unmark a task index that is not in the task list, an error message will be shown to you.

### Delete a Task
- Delete a task: `delete <task index>`.
  <br/> For example, `delete 2` deletes the second task in the task list. The task indices of the tasks after the
  deleted task will be updated accordingly.

### Find Tasks
- Find all tasks containing the keyword: `find <keyword>`. The keyword cannot be empty, and the matching tasks will 
  be listed in the order they were added to the task list.

### List All Tasks
- List all tasks in the task list: `list`. Do not provide any arguments for this command.

### Tagging
- Tag a task: `tag <task index> <tag name 1> [tag name 2]...`. The tag name cannot be empty. Multiple tags can 
  be added to a task.
  <br/> For example, `tag 1 urgent school cs2103t` tags the first task in the task list with three tags: `urgent`,
  `school` and `cs2103t`.
- Untag a task: `untag <task index> <tag name 1> [tag name 2]...`. The same restrictions apply as the `tag` command.  
  Note that when the task is not tagged by some tag names provided, the command will just ignore those tag names.
  <br/> For example, `untag 1 urgent drama cs2103t` removes the tags `urgent` and `cs2103t` from the first task. The 
  `drama` tag is simply ignored.

### Exit
- Exit Jarvis: `exit`. Do not provide any arguments for this command.