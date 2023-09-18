# SHIBA-BOT User Guide

## Features 

### Feature - Command line interface

Manage your tasks quickly by typing commands.

### Feature - Adding, deleting tasks

Add or remove tasks from your personal task list.

### Feature - 3 different task types

- Todo tasks with no deadline
- Deadline tasks that must be completed by a date/time
- Event tasks that happen between 2 points in time

### Feature - Marking tasks as done/not done

Mark tasks as done or not done to keep track of your progress.

### Feature - Finding tasks

Find tasks by searching for keyword.

### Feature - Saved tasks

Tasks are saved automatically and loaded when you start the program.

### Feature - Fun commands for SHIBA lovers

Play with SHIBA-BOT with a few in-built commands!

## Usage

### `list` - List tasks

Lists all tasks stored in your task list.

Example of usage: 

`list`

Expected outcome:

A numbered list of all tasks stored in your task list.

```
1. [T][X] read book
2. [D][ ] return book (by: 22 Sep 2023)
3. [E][ ] project meeting (from: 19 Sep 2023 2:00pm to 19 Sep 2023 4:00pm)
```
<br>

### `mark` - Mark a task as done

Marks the task as completed.

Example of usage:

`mark <task number>`

Expected outcome:

The task with the task number will be marked as done.

```
Woof! I've marked this task as done:
  [D][X] return book (by: 22 Sep 2023)
```

SHIBA-BOT will inform you if the task is already marked as done.

```
Woof! This task is already done!
  [T][X] read book
```
<br>

### `unmark` - Mark a task as not done

Marks the task as not completed.

Example of usage:

`unmark <task number>`

Expected outcome:

The task with the task number will be marked as not done.

```
Woof! I've marked this task as not done yet:
  [T][ ] read book
```

SHIBA-BOT will inform you if the task is already marked as not done.

```
Woof! You have not done this task yet!
  [D][ ] return book (by: 22 Sep 2023)
```
<br>

### `todo` - Add a todo task

Adds a todo task to your task list.

Example of usage:

`todo <task description>`

Expected outcome:

The todo task with the description will be added to your task list.

```
Woof! I've added this task:
  [T][ ] play game
You now have 3 tasks in your list. Now gimme some treats.
```
<br>

### `deadline` - Add a deadline task

Adds a deadline task to your task list.

Example of usage:

`deadline <task description> /by <date/time>`
- Date/time must be in the format `YYYY-MM-DD` or `YYYY-MM-DD HH:mm`.

Expected outcome:

The deadline task with the description and date/time will be added to your task list.

```
Woof! I've added this task:
  [D][ ] clean room (by: 22 Sep 2023 8:00PM)
You now have 4 tasks in your list. Now gimme some treats.
```
<br>

### `event` - Add an event task

Adds an event to your task list.

Example of usage:

`event <task description> /from <start date/time> /to <end date/time>`
- Date/time must be in the format `YYYY-MM-DD` or `YYYY-MM-DD HH:mm`.

Expected outcome:

The event task with the description and start, end date/time will be added to your task list.

```
Woof! I've added this task:
  [E][ ] badminton (from: 25 Sep 2023 10:00AM to 25 Sep 2023 12:00PM)
You now have 5 tasks in your list. Now gimme some treats.
```
<br>

### `delete` - Delete a task

Deletes a task from your task list.

Example of usage:

`delete <task number>`

Expected outcome:

The task with the task number will be deleted from your task list.

```
Woof! I've deleted this task:
  [T][ ] play game
You now have 4 tasks in your list. Some headpats please?
```
<br>

### `find` - Find tasks

Finds tasks that contain the keyword.

Example of usage:

`find <keyword>`

Expected outcome:

A list of tasks that contain the keyword will be displayed.

```
Woof! Here are the tasks containing the keyword!
  1. [T][ ] read book
  2. [D][ ] return book (by: 22 Sep 2023)
```

If there are no matching tasks, SHIBA-BOT will inform you.

```
Woof! No tasks containing keyword found!
```
<br>

### `bye` - Exit the program

Exits the program.

Example of usage:

`bye`

Expected outcome:

SHIBA-BOT will bid you farewell and exit the program after a few seconds.

```
Woof! Hope to bark at you again soon!
```
<br>

### `help` - View help

Displays a list of commands and their usage, or displays specific information about a command.

Example of usage:

`help (optional command name)`

Expected outcome:

If no command name is specified, a list of commands and their usage will be displayed.

```
Woof! Here are all the commands I can understand:
  bye
  list
  mark
  unmark
  ...
```

If a command name is specified, specific information about the command will be displayed.

```
Woof! Here's the help message for the mark command:
  mark <task number>
  Marks the task as done.
```
<br>

### `pat` - Pat SHIBA-BOT

Pats SHIBA-BOT.

Example of usage:

`pat`

Expected outcome:

SHIBA-BOT will respond favourably to the headpats.

```
<Happy Shiba noises>
```
<br>

### `bellyrub` - Rub SHIBA-BOT's belly

Rubs SHIBA-BOT's belly.

Example of usage:

`bellyrub`

Expected outcome:

SHIBA-BOT will respond very favourably to the belly rubs.

```
Woof! More please!
```
<br>

### `boop` - Boop SHIBA-BOT's nose

Boops SHIBA-BOT's nose.

Example of usage:

`boop`

Expected outcome:

SHIBA-BOT will boop you back.

```
<Boops you back>
```