# Sam Chatbot User Guide

***Meet SAM, your trusty Task Manager*** 🚀

**SAM** isn't your ordinary task manager; it's the Gandalf of productivity, the Sherlock Holmes of organization, and 
the Mary Poppins of reminders, all rolled into one nifty app. 🎩✨

With **SAM** by your side, those daunting to-do lists become thrilling quests, deadlines are no match for your 
ninja-like scheduling skills, and chaos turns into pure, organized magic! 🪄📅

So, say farewell to the chaos, bid adieu to procrastination, and let **SAM** be your loyal companion on your journey 
to conquering tasks like a true hero. 🦸‍♀️

Ready to embark on this epic adventure of productivity? Let's go! 🌟

![SAMDemo](../docs/Ui.png)

## Features 

### Task Management
Task Management is at the heart of SAM's functionality. It empowers you to efficiently handle your to-dos, 
events, and deadlines all in one place. With intuitive commands and a user-friendly interface, 
SAM's Task Management feature ensures you stay on top of your tasks, helping you achieve productivity 
like never before.

## Usage

### 1. Viewing help : `help`

Whenever you find yourself in need of guidance, this command is here to assist you by providing a comprehensive 
list of all available instructions.

Example of usage: 

`help`

Expected outcome:

- A list of all available instructions with its respective formats.

```
List of possible commands: 
todo: Adds a todo to the task list. 
Example: todo borrow books

event: Adds an event to the task list. 
Parameters: DESCRIPTION /from DATETIME /to DATETIME 
Example: event go to project meeting /from 2019-10-15 1800 /to 2020-12-2 2000

deadline: Adds a deadline to the task list. 
Parameters: DESCRIPTION /by DATETIME 
Example: deadline return homework /by 2023-11-15 0800 

delete: Deletes the task identified by the index number.
Parameters: INDEX
Example: delete 1

mark: mark the tasks identified by the index number as completed.
Parameters: INDEX
Example: mark1

unmark: mark the tasks identified by the index number as incomplete.
Parameters: INDEX
Example: unmark1

tag: tag the tasks identified by the index number with tags.
Parameters: INDEX #DESCRIPTION 
Example: tag 1 #fun

list: list the tasks in the task list.
Example: list

help: Shows program usage instructions.
Example: help

bye: Exits the program.
Example: bye
```

### 2. Making a ToDo Task: `todo n/TASKNAME`

Use this command to craft a new to-do task item effortlessly.

Example of usage:

`todo return book`

Expected outcome:

- Success Message:

```
Got it. I've added this task:
	[T][ ] return book
Now you have 1 task in the list.
```
- Error Message:
```
☹ OOPS!!!
Invalid command format! 
todo: Adds a todo to the task list. 
Example: todo borrow books
```

### 2. Making a Deadline Task: `deadline n/TASKNAME /by DATETIME`

Use this command to craft a new deadline task item effortlessly.

Example of usage:

`deadline return homework /by 2023-11-15 0800`

Expected outcome:

- Success Message:

```
Got it. I've added this task:
	[D][ ] return homework (by: Nov 15 2023 08:00 am)
Now you have 1 task in the list.
```
- Error Message:
```
☹ OOPS!!!
Invalid command format! 
deadline: Adds a deadline to the task list. 
Parameters: DESCRIPTION /by DATETIME 
Example: deadline return homework /by 2023-11-15 0800 
```

### 4. Making a Event Task: `event n/TASKNAME /from DATETIME /to DATETIME`

Use this command to craft a new event item effortlessly.

Example of usage:

`event go to project meeting /from 2019-10-15 1800 /to 2020-12-2 2000`

Expected outcome:

- Success Message:

```
Got it. I've added this task:
	[E][ ] go to project meeting (from: Oct 15 2019 06:00 pm, to: Dec 2 2020 08:00 pm)
Now you have 5 tasks in the list.
```
- Error Message:
```
☹ OOPS!!!
Invalid command format! 
event: Adds an event to the task list. 
Parameters: DESCRIPTION /from DATETIME /to DATETIME 
Example: event go to project meeting /from 2019-10-15 1800 /to 2020-12-2 2000
```

### 5. Delete Task: `delete n/INDEX`

When it's time to bid farewell to a task, simply use this command with the task's index to delete it from your list.

**Warning:**
Potentially Dangerous Operation! Deleting a task is irreversible and cannot be undone.

Example of usage:

`delete 2`

Expected outcome:

- Success Message:

```
Noted. I've removed this task:
	[T][ ] return book
Now you have 3 tasks in the list.
```
- Error Message for wrong index number:
```
☹ OOPS!!!
Invalid task index.
```
- Error Message for wrong command format:
```
☹ OOPS!!!
Invalid command format! 
delete: Deletes the task identified by the index number.
Parameters: INDEX
Example: delete 1
Could not find index number to parse
```

### 6. Completing a Task: `mark n/INDEX`

To mark a task as done, simply use this command with the task's index.

Example of usage:

`mark 2`

Expected outcome:

- Success Message:

```
Nice! I've marked this task as done:
	[D][X] return homework (by: Nov 15 2023 08:00 am)
```
- Error Message for wrong index number:
```
☹ OOPS!!!
Invalid task index.
```
- Error Message for wrong command format:
```
☹ OOPS!!!
Invalid command format! 
mark: mark the tasks identified by the index number as completed.
Parameters: INDEX
Example: mark 1
Could not find index number to parse
```

### 7. Reversing Task Completion: `unmark n/INDEX`

To mark a task as not done or incomplete, utilize this command with the task's index.

Example of usage:

`unmark 2`

Expected outcome:

- Success Message:

```
OK, I've marked this task as not done yet:
	[D][ ] return homework (by: Nov 15 2023 08:00 am)
```
- Error Message for wrong index number:
```
☹ OOPS!!!
Invalid task index.
```
- Error Message for wrong command format:
```
☹ OOPS!!!
Invalid command format! 
unmark: mark the tasks identified by the index number as incomplete.
Parameters: INDEX
Example: unmark 1
Could not find index number to parse
```

### 8. Tagging a task: `tag n/INDEX n/#TAGNAME`

Easily add tags to your tasks by using this command with the desired tag name.

Example of usage:

`tag 2 #cool`

Expected outcome:

- Success Message:

```
Nice! I've tagged this task:
	[D][X] return homework  #cool  (by: Nov 15 2023 08:00 am)
```
- Error Message for wrong index number:
```
☹ OOPS!!!
Invalid task index.
```
- Error Message for wrong command format:
```
☹ OOPS!!!
Invalid command format! 
tag: tag the tasks identified by the index number with tags.
Parameters: INDEX #DESCRIPTION 
Example: tag 1 #fun
```

### 9. Listing All Tasks: `list`

View all your tasks in one comprehensive list with this straightforward command.

Example of usage:

`list`

Expected outcome:

- Success Message:

```
Here are the tasks in your list:
	1.[T][ ] zoo  #fun #grr 
	2.[E][X] go to projecting  #notfun  (from: Oct 15 2019 06:00 pm, to: Dec 2 2020 08:00 pm)
	3.[D][X] return homework  #cool  (by: Nov 15 2023 08:00 am)
```

### 10. End Program: `bye`

View all your tasks in one comprehensive list with this straightforward command.

Example of usage:

`bye`

Expected outcome:

- Success Message:

```
Bye. Hope to see you again soon!
Remember, the universe is vast, but I'm always here for you :D
```

