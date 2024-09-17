# THE MEDIC User Guide

## Features 
THE MEDIC keep tracks of your tasks for you. It is
1. text based
2. easy to learn
3. ~~FAST~~ REALLY FAST

- [x] Manage tasks
- [x] Manage deadlines
- [x] Manage events
- [x] Manage expenses

### Managing Tasks (also stores deadlines!)

Keeps track of all your tasks. THE MEDIC is also capable of storing deadlines and timings for events. 

### Managing Expenses

Keeps track of all your expenses. The user is able to edit their expenses at any time.

## Usage

### `Todo` - Adds a basic description of your task.

Records the description of your task.

Example of usage: 

`todo read book`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
Got it. I've added this task:
[T][ ] read book
```

### `Deadline` - Adds a basic description of your task and deadline.

Records the description of your task. The date format should be in YYYY-MM-DD.

Example of usage: 

`deadline return book/by 2020-12-08`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
Got it. I've added this task:
[D][ ] return book (by 08 Dec 2020) 
```

### `Event` - Adds a basic description of your event and time.

Records the description of your task. The format should be from (Day Time) to (Time).

Example of usage: 

`event project meeting/from Mon 2pm/to 4pm`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
Got it. I've added this task:
[E][ ] project meeting (from Mon 2pm to 4pm) 
```

### `Expense` - Adds a basic description of your expenses.

Records the description of your expense. 

Example of usage: 

`expense transport/$100`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
Got it. I've added this expense:
transport : $100 
```

### `list` - Prints the list of all tasks.

Prints the entire tasks list. 

Example of usage: (assuming there are 2 current tasks)

`list` 

Expected outcome: The bot should return the following description.

Description of the outcome.

```
1. [T][ ] read book
2. [D][ ] return book (by: 08 Dec 2020 1800)
```

### `report` - Prints the list of the expense report.

Prints a list of all the recorded expenses. 

Example of usage: (assuming there are 2 current expenses)

`report` 

Expected outcome: The bot should return the following description.

Description of the outcome.

```
1. transport : $100
2. food : $400
```

### `Mark` - Marks a task as completed.

Marks the task as completed and the second [ ] will appear as [X]. 

Example of usage: 

`mark 1` where 1 is the index of the task you want to mark in the list.

Expected outcome: The bot should return the following description.

Description of the outcome.

```
"OK, I've marked this task as done:
[T][X] read book
```

### `Unmark` - Unmarks a task to indicate that it is incomplete.

Unmarks the task as it is not completed and the second [ ] will appear as [ ]. 

Example of usage: 

`unmark 1` where 1 is the index of the task you want to mark in the list.

Expected outcome: The bot should return the following description.

Description of the outcome.

```
"OK, I've marked this task as not done yet:
[T][ ] read book
```

### `Delete` - Deletes the corresponding task in the list.

Deletes the specified task from the list. The index of the task to be deleted must be provided.

Example of usage: 

`delete 1`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
Noted. I have removed this task.
[T][ ] read book
```

### `Find` - Returns the list filtered by the keyword.

Uses the keyword to filter tasks which contain the keyword.

Example of usage: 

`find book`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
1. [T][ ] read book
2. [T][ ] summarise book
```
### `editExpense` - Edits the cost of the expense.

Takes in a new String and replaces the cost of the current expense.

Example of usage: 

`editExpense 1 $250` where 1 represents the index of the expense in the report, and $250 is the String representing the cost.

Expected outcome: The bot should return the following description.

Description of the outcome.

```
I have updated the expense.
transport : $250
```

### `bye` - Ends the program.

Exits the program.

Example of usage: 

`bye`

Expected outcome: The bot should return the following description.

Description of the outcome.

```
Bye. Hope to see you again soon!
```

### `MEDIC!` - Some special text from the bot!
