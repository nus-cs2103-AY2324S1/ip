# Duke Jokey

This is a duke named Jokey that records your task for you. Run the jar file to start using it.
Jokey will saves all tasks you entered as soon as you add the task!

## Basic Features
### Add a Todo task
A Todo task no strict time interval.
Format: `todo {task_description}`
Example: `todo do laundry`
### Add a Deadline task
A Deadline task must have a date as deadline. 
Format: `deadline {task_description} /by {deadline}`
Example: `deadline CS2103T iP /by 2023-10-12`
### Add an Event task
An Event task must have a time interval.
Format: `event {task_description} /from {start_date} /to {end_date}`
Example: `event AI workshop /from 2023-12-10 /to 2023-10-15`
### Mark a task
Mark a task as done.
Format: `mark {task_index_in_list}`
Example: `mark 1`
### Unmark a task
Mark a task as not done.
Format: `unmark {task_index_in_list}`
Example: `unmark 1`
### Delete task
Delete a task.
Format: `delete {task_index_in_list}`
Example: `delete 1`
### Find task
Find all tasks that has the matching word you entered.
Format: `find {keyword}`
Example: `find book`
Sample execution of `find book`:
```
Here are the matching tasks in your list:
 1. [T][ ] read book
 2. [D][ ] return book (by: Oct 12 2023)
 3. [E][ ] mansion booking (from: Mar 12 2023 to: Mar 15 2023)
```
### Exit program
Exit the program.
Format: `bye`
Example: `bye`