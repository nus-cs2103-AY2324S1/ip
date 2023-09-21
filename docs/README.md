# User Guide

## Features 

### Add Commands

Add commands should only begin with "todo", "event", "deadline"

Formats:

a) todo [task description]
  - eg. todo read book

b) event [task description] /from [day time] /to [time]
  - eg. event project meeting /from Mon 2pm /to 4pm

c) deadline [task description] /by [date]
  - eg. deadline return book /by Sunday

Expected output: chatbot notifies that the task is added and the number of tasks in the list.

### Delete Commands

Format: delete [index]

Parameter constraints: index should be from 1-n, where n refers to the size of list.

Expected output: chatbot notifies that the task is deleted and the number of tasks left.

### Mark/ Unmark Commands

Format: mark/ unmark [index]

Expected output: chatbot notifies that the task is marked/ unmarked.

### list Command

Format: list

Expected output: chatbot returns a list of tasks.

### find Command

Format: find [keyword]

Parameter constaints: keyword is in the form of string

Expected output: chatbot returns tasks that contains the keyword.

