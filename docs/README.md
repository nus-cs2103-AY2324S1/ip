# Ax User Guide

## Features

1. ### Task Management
    Ax can help you manage your tasks, with **todos**, **deadlines** and **events**.
2. ### Reminders
    Ax can remind you whenever a deadline has passed, or when an event is starting.
3. ### Todo List
    Ax can store all your items in a todo list which you can mark as done or not done.
4. ### Search
    Ax can help you look for your items no matter how large your list is.

## Usage

1. ### todo
    To create a todo, use `todo [todo name]`. This will reply `SUCCESS` if it was successfully added. Or else, Ax will
    reply with the error message.
    ```
    Example: todo bathe dog
    Output: SUCCESS
    ```
2. ### deadline
    To create a deadline, use `deadline [deadline name] /by [Date in ISO8601 format]`. This will reply `SUCCESS` if it
    was successfully added. Or else, Ax will reply with the error message.
    ```
    Example: deadline bathe dog /by 2023-10-01
    Output: SUCCESS
    ```
3. ### event

    To create an event, use `event [event name] /from [Date in ISO8601 format] /to [Date in ISO8601 format]`. This
    will reply `SUCCESS` if it was successfully added. Or else, Ax will reply with the error message.

    ```
    Example: event bathe dog /from 2023-10-01 /to 2023-10-02
    Output: SUCCESS
    ```

    > Note that for the deadline and event commands, the date must be in ISO8601 format (YYYY-MM-DD). You can find more information about ISO8601 format in the [official ISO documentation](https://www.iso.org/iso-8601-date-and-time-format.html).

4. ### list
    To list out the current items, use `list`.

    ```
    Example: list
    Output:
    1. [T] [✅] help bob
    2. [D] [ ] photog  (by: 2023-09-01)
    3. [E] [ ] studyyyyyy  (from: 2023-10-01 to: 2023-11-01)
    ```
   
5. ### find
    To look for an item, use `find [search term]`. This will return a list of items which contain the search term within
    it as a substring.

    ```
    Example: find help
    Output:
    1. [T] [✅] help bob
    ```
   
6. ### mark
    To mark an item on the list, use `list` to view the order. Then use `mark [index]` to get Ax to mark an item as done.
    
   ```
    Example: mark 1
    Output:
    1. [T] [✅] help bob
    ```
   
7. ### unmark
    To unmark an item on the list, use `list` to view the order. Then use `unmark [index]` to get Ax to unmark an item as
    done.

    ```
    Example: unmark 1
    Output:
    1. [T] [ ] help bob
    ```
   
8. ### reminders
    To view reminders, use `reminders`. This brings up a list of **deadlines** which are past their **due date** and **events** which are past their **start date**.
    
   ```
    Example: reminders
    Output:
    2. [D] [ ] photog  (by: 2023-09-01), has been due since 2023-09-01
    ```
   
9. ### bye
    `Bye` gets Ax to write to a save file, saving your todos, deadlines and events so that they remain even when you
    restart Ax.
    
   ```
    Example: bye
    Output: save file written
    ```
