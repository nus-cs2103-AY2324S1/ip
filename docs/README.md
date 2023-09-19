# User Guide for Tired

Tired is a chatbot created with Java that helps a person keep track of various tasks. There are 3 task cateogories - `todo`, `event`, `deadline`.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `Tired.jar` from [here](https://github.com/chewjh1234/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Tired chatbot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Tired.jar` command to run the application. 

5. Type the command in the command box and press `Send` to execute it.
   
6. Refer to [Usage](https://github.com/chewjh1234/ip/master/docs/README.md#usage) below for details of each command.
   
## Features 

### Adding a task
A task can be added into the task list. 
Each task belongs to 1 of the 3 task categories - `todo`, `deadline` and `event`. 

### Removing a task

After a task is added, it can be removed from the task list. 

### Marking a task as done

A task can be marked as done. The task will remain in the task list. 

### Marking a task as not done

A task can be marked as not done yet. By default, tasks are marked as not done when added. 

## Usage

### `todo` - Adds a todo task

A todo task is a task without any date/time attached to it. It is represented by the `[T]` symbol. 

Format: 

`todo [TASK_DESCRIPTION]`

Example of usage: 

`todo run`

Expected outcome:

The todo task will be added into the task list. 
The total number of tasks will be displayed. 

Description of the outcome:
```
Got it. I've added this task:
[T][] run
Now you have 1 task(s) in the list.
```

### `deadline` - Adds a deadline task

A deadline task is a task that needs to be done before a specific date/time. It is represented by the `[D]` symbol. 

Format: 

`deadline [TASK_DESCRIPTION] /by [YYYY-MM-DD HHmm]`

Example of usage: 

`deadline run /by 2023-10-13 2020`

Expected outcome:

The deadline task will be added into the task list. 
The total number of tasks will be displayed. 

Description of the outcome:
```
Got it. I've added this task:
[D][] run (by: Oct 13 2023 2020)
Now you have 1 task(s) in the list.
```
### `event` - Adds an event task

An event task is a task that start at a specific date/time and ends at a specific date/time. It is represented by the `[E]` symbol. 

Format: 

`event [TASK_DESCRIPTION] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]`

Example of usage: 

`event run /from 2023-10-13 2020 /to 2023-10-13 2120`

Expected outcome:

The deadline task will be added into the task list. 
The total number of tasks will be displayed. 

Description of the outcome:
```
Got it. I've added this task:
[E][] run (from: Oct 13 2023 2020 to: Oct 13 2023 2120)
Now you have 1 task(s) in the list.
```
### `list` - Lists all tasks

Displays all tasks stored in the Tired chatbot.

Format: 

`list`

Expected outcome:

All tasks and their completion status, will be shown. 

Description of the outcome:
```
Here are the tasks in your list:
1. [T][] eat
2. [D][] submit assignment (by: Oct 13 2023 2020)
3. [E][] run (from: Oct 13 2023 2020 to: Oct 13 2023 2120)
You have 3 task(s) remaining
```


### `mark` - Marks a task as done

A task can be marked as done. A task that is marked as done has the `[X]` symbol beside the task symbol. 

Format: 

`mark [TASK_INDEX]`

Example of usage: 

`mark 1`

Expected outcome:

The task which corresponds to the `TASK_INDEX` in the task list produced by `list` will be marked as done. 
The task description will also be displayed. 

Description of the outcome:
```
Nice! I've marked this task as done:
[T][X] run
```

### `unmark` - Marks a task as not done

A task can be marked as not done. A task that is marked as not done has the `[ ]` symbol beside the task symbol. 

Format: 

`unmark [TASK_INDEX]`

Example of usage: 

`unmark 1`

Expected outcome:

The task which corresponds to the `TASK_INDEX` in the task list produced by `list` will be marked as not done. 
The task description will also be displayed. 

Description of the outcome:
```
OK, I've marked this task as not done yet:
[T][X] run
```

### `delete` - Removes a task 

Removes the task from the task list stored in Tired. 

Format: 

`delete [TASK_INDEX]`

Example of usage: 

`delete 1`

Expected outcome:

The task which corresponds to the `TASK_INDEX` in the task list produced by `list` will be removed. 
The task description of the removed task, as well as the number of tasks remaining, will also be displayed. 

Description of the outcome:
```
Noted. I've removed this task:
[T][X] run
You have 2 task(s) remaining
```

### `archive` - Archives task list 

Archives the current task list and removes the task list from the current `Storage` so that users can start with a clean slate. 

Format: 

`archive list`

Expected outcome:

The task list will be removed from the current `Storage` object and stored in a new file named `archive.txt` in the `data` folder. 

Description of the outcome:
```
Your task list has been successfully archived in ./data/archive.txt.
```

### `find` - Finds a task 

Finds a task with a given keyword. 

Format: 

`find [KEYWORD]`

Example of usage: 

`find run`

Expected outcome:

All tasks containing `KEYWORD` in the task description will be displayed. 

Descriptions of the outcomes:
```
Here are the matching tasks in your list:
[T][X] run
```
If no matching tasks are found,
```
No matching tasks found.
```

### `bye` - Exits the program 

Exits the program after storing the updated task list locally. 

Format: 
`bye`
