# Chatty User Guide
## Features 

### Task Management

Chatty is your new personal assistant, helping you manage tasks by adding todos, setting deadlines, and scheduling events. Chatty also lists out all your tasks, and can help you mark them as complete/incomplete, delete any task, and search for specific tasks with keywords.

### Save Tasks Locally

Chatty remembers everything. At the end of every session, your tasks will be saved locally, allowing you to access them again the next time you open up Chatty.

## Usage

### Viewing list of commands - `help`

Provides a list of all possible commands.

Example of usage: 

`help`

Expected outcome:

```
I'm Chatty, here to help you with your ever-growing list of things to do. I have a list of pretty handy commands:
help: displays the list of commands available, as well as how to use them.
list: lists all the tasks you currently have recorded here.
add todo <description>: create a new Todo task.
add event <description> /from <yyyy-mm-dd HHMM> /to <yyyy-mm-dd HHMM>: create a new Event task from <time> to <time>.
add deadline <description> /by <yyyy-mm-dd HHMM>: create a new Deadline task to do by <time>.
mark <number>: mark the task <number> as done.
unmark <number>: mark the task <number> as incomplete.
delete <number>: delete task <number> on your list.
find <description>: fetches all tasks that match the specified <description> given. It's just a search function.
You'll forget this, so just type 'help' whenever you need it.
```

### Adding a Todo task - `todo`

Adds a Todo task to the tasklist

Command format: 

`todo DESCRIPTION`
- DESCRIPTION is a description of your todo task

Example Input:
```
todo return book
```

Expected outcome:

```
Ok. Your tasklist has grown longer with this addition:
	[T][ ] return book
You now have 1 things to do.
I'm sure you're dying to finish them.
```

### Adding a Deadline task - `deadline`

Adds a Deadline task to the tasklist

Command format: 

`deadline DESCRIPTION /by DATETIME`
- DESCRIPTION is a description of your deadline task
- DATETIME is the date and time your deadline task is due, in the YYYY-MM-DD HHMM format.

Example input:

```
deadline assignment /by 2023-09-22 2359
```

Expected outcome:

```
Ok. Your tasklist has grown longer with this addition:
	[D][ ] assignment (by: Sep 22 2023 2359)
You now have 1 things to do.
I'm sure you're dying to finish them.
```

### Adding an Event task - `event`

Adds an event task to the tasklist

Command format: 

`event DESCRIPTION /from DATETIME /to DATETIME`
- DESCRIPTION is a description of your event task
- DATETIME is the date and time your deadline task is due, in the YYYY-MM-DD HHMM format.

Example input:

```
event holiday /from 2023-09-22 0600 /to 2023-09-30 1800
```

Expected outcome:

```
Ok. Your tasklist has grown longer with this addition:
	[E][ ] holiday (from: Sep 22 2023 0600 to: Sep 30 2023 1800)
You now have 1 things to do.
I'm sure you're dying to finish them.
```

### Listing all tasks - `list`

Lists all tasks in the tasklist

Command format: 

`list`

Example input:

```
list
```

Expected outcome:

```
Looking at your tasks again to remind yourself is good, but remember to actually do them:
1. [E][ ] holiday (from: Sep 22 2023 0600 to: Sep 30 2023 1800)
2. [D][ ] assignment (by: Sep 22 2023 2359)
```

### Marking a task as completed - `mark`

Marks a task as completed

Command format: 

`mark INDEX`
- INDEX is the index of the specified task in the displayed task list

Example input:

```
mark 1
```

Expected outcome:

```
Someone's productive. Marked it done for you:
	[X] cs2103t assignment
```

### Marking a task as incomplete - `unmark`

Marks a task as incomplete by removing any mark that marks it as 'complete'

Command format: 

`unmark INDEX`
- INDEX is the index of the specified task in the displayed task list

Example input:

```
unmark 1
```

Expected outcome:

```
What happened to being productive? Marked it as not done yet:
	[] cs2103t assignment
```

### Deleting a task - `delete`

Deletes a task from the tasklist

Command format: 

`delete INDEX`
- INDEX is the index of the specified task in the displayed task list

Example input:

```
delete 1
```

Expected outcome:

```
Looks like you have more time to sleep now. Deleted this for you:
  [] cs2103t assignment
You now have 2 things to do.
```

### Finding a task - `find`

Finds tasks with a description containing any of the given keywords

Command format: 

`find DESCRIPTION`
- DESCRIPTION is a description of your event task

Example input:

```
find assignment
```

Expected outcome:

```
Why can't you find them yourself? Here, the matching tasks:
1. [T][ ] japanese assignment
2. [D][ ] cs2103t assignment (by: Sep 22 2023 2359)
```

### Exiting the program - `bye`

Exits the program

Command format: 

`bye`

Expected outcome:

```
Finally. Don't come back!
```
