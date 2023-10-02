# User Guide

## Description
Moss is a simple chatbot helps you to record messages into a list.

## How to Begin

1. Search Heran9 in github.
2. Download the jar file from the release in IP.
3. Move the jar file to your desktop, double click to begin.

## Features

### `todo` - Add a Todo Task

   **todo [description]** 

   Adds a new todo task with the provided description.
   
   Example of usage:
   
   - todo Buy groceries
   
   Expected outcome:

   - Got it. I've added this task:

     [T][ ] Buy groceries

     Now you have 1 task in the list.


### `deadline` - Add a Deadline Task

   **deadline [description] /by [YYYY-MM-DD]** 

   Adds a new deadline task with the provided description and deadline date in the format YYYY-MM-DD.
   
   Example of usage:
   
   - deadline Submit report /by 2023-09-30
   
   Expected outcome:

   - Got it. I've added this task:

     [D][ ] Submit report (by: Sep 30 2023)

     Now you have 1 task in the list.


### `event` - Add a Event Task

   **event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]** 

   Adds a new event task with the provided description, start date, and end date in the format YYYY-MM-DD.
   
   Example of usage:
   
   - event Team meeting /from 2023-10-01 /to 2023-10-03
   
   Expected outcome:

   - Got it. I've added this task:

     [E][ ] Team meeting (from: Oct 01 2023 to Oct 03 2023)

      Now you have 1 task in the list.


### `list` - List All Tasks

   **list** 

   Lists all tasks currently stored.

   Example of usage:
   
   - list
   
   Expected outcome:

   - Here are the tasks in your list:

     [T][ ] Buy groceries

     [D][ ] Submit report (by: Sep 30 2023)

     [E][ ] Team meeting (from: Oct 01 2023 to Oct 03 2023)
   ...


### `mark` - Mark a Task as Done

   **mark [task number]** 

   Marks the task at the specified number as done.

   Example of usage:
   
   - mark 1
   
   Expected outcome:

   - Nice! I've marked this task as done:

     [T][X] Buy groceries


### `unmark` - Mark a Task as Undone


   Marks the task at the specified number as undone.

   Example of usage:
   
   - unmark 1
   
   Expected outcome:

   - Noted. I've marked this task as undone:

     [T][ ] Buy groceries


### `delete` - Delete a Task

   **delete [task number]** 
   
   Deletes the task at the specified number.
   
   Example of usage:
   
   - delete 1
   
   Expected outcome:

   - Noted. I've removed this task:

     [T][ ] Buy groceries

     Now you have 0 tasks in the list.


### `find` - Find Tasks by Keyword

   **find [keyword]** 

   Finds tasks containing the specified keyword in their description.
   
   Example of usage:
   
   - find meeting
   
   Expected outcome:

   - Here are the matching tasks in your list:

     [E][X] Team meeting (from: Oct 01 2023 to Oct 03 2023)


### `help` - Display Help Information

   **help** 

   Displays information about available commands and their usage.
   
   Example of usage:
   
   - help
   
   Expected outcome:

   - Here are the available commands:
     1. todo [description]: Adds a new todo task.
     2. deadline [description] /by <date>: Adds a new deadline task.
     3. event [description] /from <start date> /to <end date>: Adds a new event task.
     4. list: Lists all tasks.
     5. mark [task number]: Marks a task as done.
     6. unmark [task number]: Marks a task as undone.
     7. delete [task number]: Deletes a task.
     8. find [keyword]: Finds tasks containing the specified keyword in their description.
     9. bye: Exits the application.
       Use the commands without '<>' and replace <description>, <date>, <start date>, <end date>, and <task number> accordingly.
       Example usage: todo Buy groceries


### `bye` - Exit the Application


   **bye**

   Exits the application.

   Example of usage:

   - bye

   Expected outcome:

   - Bye! Hope to see you again soon!
