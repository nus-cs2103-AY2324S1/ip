# User Guide
**Task404Bot** is a desktop app for managing
tasks, optimized for use via a Command Line Interface
(CLI) while still having the benefits of a Graphical 
User Interface (GUI). If you can type fast, **Task404Bot** can 
get your contact management tasks done faster than 
traditional GUI apps.

## Features 

### Viewing help: `help`
### Adding tasks: `todo` `deadline` `event`
### Mark and Unmark tasks: `mark` `unmark`
### Listing all tasks: `list`
### Deleting tasks: `delete`
### Print deadline or event on a date: `print_date`
### Finding tasks by keyword: `find`
### Loading tasks: `load`
### Sorting tasks: `sort`
### Making aliases: `alias`
### Saving the data
### Exiting the program: `bye`

## Usage

### Viewing help: `help`

Shows a message explaining how to use a command.
Help can be viewed for all commands.

#### Format:

* `help`
* `help [command]`

#### Example of usage: 

* `help` Shows a list of all commands.
* `help todo` Shows a message explaining how to use the _todo_ command.
* `help alias` Shows a message explaining how to use the _alias_ command.

#### Expected outcome:
When you execute`help todo`, below is the expected outcome.
```
todo <task description>
==================
- Adds a todo task.
- The task description should not be empty.
```
---

### Adding tasks: `todo` `deadline` `event`

Adds a task to **Task404Bot**.
There are 3 types of tasks that can be added to **Task404Bot**.
1. *Todo* tasks are tasks that do not have a deadline or event date.
2. *Deadline* tasks are tasks that have a deadline.
3. *Event* tasks are that have a start and end date.

#### Format:

* `todo <task description>`
* `deadline <task description> /by <date>`
* `event <task description> /from <start date> /to <end date>`\
Note: The all date format is `dd/MM/yyyy HH:mm`.
#### Example of usage:

* `todo read book` Adds a todo task read book.
* `deadline iP submission /by 24/09/2023 23:59` Adds a deadline task iP submission due on 24/09/2023 23:59.
* `event Hackthon /from 04/09/2023 07:00 /to 09/09/2023 23:45` 
Adds an event task Hackthon from 04/09/2023 07:00 to 09/09/2023 23:45.

#### Expected outcome:
When you execute`deadline iP submission /by 24/09/2023 23:59`, below is the expected outcome.
```
Awesome! I've added the following task:
  [D][ ] iP submission (by: 24/09/2023 23:59)
Now you have 2 task in the list.
```
---

### Mark and Unmark tasks: `mark` `unmark`

All the tasks in **Task404Bot** can be marked as done or undone.
This is useful for keeping track of the tasks that have been completed.

#### Format:

* `mark <task number>`
* `unmark <task number>`


* `mark all`
* `unmark all`

#### Example of usage:

* `mark all` Marks all the tasks in **Task404Bot** as done.
* `unmark 2` Unmarks the second task in **Task404Bot**.

#### Expected outcome:
When you execute `mark all`, below is the expected outcome.
```
Noted. I've marked all tasks as done.
```
When you execute `unmark 2`, below is the expected outcome.
```
OK, I've marked this task as undone:
  [D][ ] iP submission (by: 24 Sep 2023, 11:59PM)
```
---

### Listing all tasks: `list`

All the tasks in **Task404Bot** can be listed out.

#### Format:

* `list`

#### Example of usage:

* `list` Lists all the tasks in **Task404Bot**.

#### Expected outcome:
When you execute `list`, below is the expected outcome.
```
Here are the tasks in your list:
  1. [T][X] read book
  2. [D][ ] iP submission (by: 24 Sep 2023, 11:59PM)
  3. [E][X] Hackthon (from: 4 SEP 2023, 7:00AM to: 9 Sep 2023, 11:45PM)
```
---

### Deleting tasks: `delete`

**Task404Bot** can delete tasks that are no longer needed.

#### Format:

* `delete <task number>`
* `delete all`

#### Example of usage:

* `delete 1` Deletes the first task in **Task404Bot**.

#### Expected outcome:
When you execute `delete 1`, below is the expected outcome.
```
Noted. I've removed this task:
  [T][X] read book
Now you have 2 tasks in the list.
```
---

### Print deadline or event on a date: `print_date`

You can check what deadlines or events are on a specific date.\
Note: The end date of a deadline or event must be after the
specified date.

#### Format:

* `print_date deadline /on <date>`
* `print_date event /on <date>`\
Note: The date format is `dd/MM/yyyy`.

#### Example of usage:

* `print_date deadline /on 20/09/2023` Prints all the deadlines on or after 20/09/2023.

#### Expected outcome:
When you execute `print_date deadline /on 20/09/2023`, below is the expected outcome.
```
Here are the 1 tasks happening on 20 Sep 2023:
  [D][ ] iP submission (by: 24 Sep 2023, 11:59PM)
```
---

### Finding tasks by keyword: `find`

**Task404Bot** can find tasks that contain a keyword.

#### Format:

* `find <keyword>`

#### Example of usage:

* `find iP` Finds all the tasks that contain the keyword _iP_.

#### Expected outcome:
When you execute `find iP`, below is the expected outcome.
```
Here are the matching tasks in your list:
1.[D][ ] iP submission (by: 24 Sep 2023, 11:59PM)
```
---

### Loading tasks: `load`

You can load other tasks from a file into **Task404Bot**.

#### Format:

* `load`
* `load <file name>`\
Note: The file can only be loaded from the `./data` folder.

#### Example of usage:

* `load` Loads the tasks from the __default__ file `./data/task404bot.txt`.
* `load my_tasks.txt` Loads the tasks from the file `./data/my_tasks.txt`.

#### Expected outcome:
When you execute `load my_tasks.txt`, below is the expected outcome.
```
Loading tasks list from my_tasks.txt...

done!
``` 
Now the tasks saved in `./data/my_tasks.txt` are loaded into **Task404Bot**.

---

### Sorting tasks: `sort`

Tasks in **Task404Bot** can be sorted by _name_, _deadline_ or _type_.

#### Format:

* `sort`
* `sort name`
* `sort deadline`
* `sort type`\
Note: `sort` and `sort name` are equivalent.

#### Example of usage:

* `sort` Sorts the tasks in **Task404Bot** by name/task description.
* `sort type` Sorts the tasks in **Task404Bot** by type.\
The order is `Todo`, `deadline` and `event`, from front to back.
* `sort deadline` Sorts the tasks in **Task404Bot** by deadline.\
`Todo` tasks are sorted to the back.

#### Expected outcome:
When you execute `sort deadline`, below is the expected outcome.
```
Sorting task list by deadline...

done!
``` 
Now the tasks in **Task404Bot** are sorted by deadline.

---

### Making aliases: `alias`

You can make aliases for anything! **Task404Bot** will
remember the aliases and use them in the future.

#### Format:

* `alias`
* `alias <from>`
* `alias <from> <to>`\
Here `<from>` is what can be used inplace of `to`.\

#### Example of usage:

* `alias` Shows all the current aliases.
* `alias t todo` Makes the alias `t` for `todo`.\
Now instead of typing `todo` you can type `t`.
* `alias t` Removes the alias for `t`.\

Note: You cannot make an alias for an existing alias.
For example: `alias td todo` then `alias t td` are not allowed.

#### Expected outcome:
When you execute `alias t todo`, below is the expected outcome.
```
Nice! I've added the alias: t -> todo
``` 
Now, **Task404Bot** can understand t as todo.

When you execute `alias t`, below is the expected outcome.
```
OK, I've removed the alias: t -> todo
```

---

### Saving the data

**Task404Bot** data is saved in the hard disk automatically
after any command that changes the data. There is no need to
save manually.

---

### Exiting the program: `bye`

You can exit the program.
#### Format:
`bye`
---
