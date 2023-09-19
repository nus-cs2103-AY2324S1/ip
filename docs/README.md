# Chatty User Guide

## Features 

### List tasks: `list`

List all tasks in the task list. Tasks are listed in the order they were added. This will be useful for mark, unmark, and delete functions.

Example of usage: 

`list`

Expected outcome: List of tasks.

Description of the outcome:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Jun 06 2023)
3. [E][ ] project meeting (from: Aug 06 2023 6:00 PM to: Aug 06 2023 8:00 PM)
```

### Mark task as done: `mark <index>`

Mark the task at the specified index as done. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, ... The index must be within the range of the task list.

Example of usage: 

`mark 1`

Expected outcome: Mark task at index 1 as done, reference to the list above.

Description of the outcome:

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] return book (by: Jun 06 2023)
3. [E][ ] project meeting (from: Aug 06 2023 6:00 PM to: Aug 06 2023 8:00 PM)
```
### Unmark task as done: `unmark <index>`

Unmark the task at the specified index as done. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, ... The index must be within the range of the task list.

Example of usage: 

`unmark 1`

Expected outcome: Unmark task at index 1 as done, reference to the list above.

Description of the outcome:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: Jun 6 2023)
3. [E][ ] project meeting (from: Aug 6 2023 6:00 PM to: Aug 6 2023 8:00 PM)
```

### Delete task: `delete <index>`

Delete the task at the specified index. The index refers to the index number shown in the displayed task list. The index must be a positive integer 1, 2, 3, ... The index must be within the range of the task list.

Example of usage: 

`delete 1`

Expected outcome: Delete task at index 1, reference to the list above.

Description of the outcome:

```
Noted. I've removed task 1:
1. [T][ ] read book
Now you have 2 tasks in the list.
```

### Find tasks: `find <keyword>`

Keyword is case sensitive. Returns a list of tasks that contains the keyword.

Example of usage: 

`find book`

Expected outcome: List of tasks containing the keyword "book".

Description of the outcome:

```
Here are the tasks in your list:
1. [D][ ] return book (by: Jun 06 2023)
```

### Add todo task: `todo <description>`

Adds a todo task to the task list. The description cannot be empty.

Example of usage:

`todo read book`

Expected outcome: Add todo task with description "read book".

Description of the outcome:

```
Got it. I've added this task:
[T][ ] read book
Now you have 3 tasks in the list.
```

### Add deadline task: `deadline <description> /by <yyyy-mm-dd>`

Adds a deadline task to the task list. The description cannot be empty. The date must be in the format yyyy-mm-dd.

Example of usage:

`deadline complete CS2103T iP /by 2023-09-19`

Expected outcome: Add deadline task with description "complete CS2103T iP" and date "2023-09-19".

Description of the outcome:

```
Got it. I've added this task:
[D][ ] complete CS2103T iP (by: Sep 19 2023)
Now you have 4 tasks in the list.
```

### Add event task: `event <description> /from <from> /to <to>`

Adds an event task to the task list. Contains a description, a start moment, and an end moment. The description cannot be empty. The start and end moment need not be in the yyyy-mm-dd format.

Example of usage:

`event concert /from 2023-09-19 6pm /to 2023-09-19 8pm`

Expected outcome: Add event task with description "concert", start moment "2023-09-19 6pm", and end moment "2023-09-19 8pm".

Description of the outcome:

```
Got it. I've added this task:
[E][ ] concert (from: Sep 19 2023 6:00 PM to: Sep 19 2023 8:00 PM)
Now you have 5 tasks in the list.
```

### Exit the program: `bye`

Exits the program.

Example of usage:

`bye`

Expected outcome: Exits the program.

Description of the outcome:

```
Bye. Have "fun" in school!
```