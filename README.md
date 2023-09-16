# Duke User Guide

Duke is a chatbot built with Java aimed at helping you to remeber your tasks
effectively!

## Features
- List all tasks
- Add a task
- Mark and unmark a task
- Delete a task
- Search for a task
- Save tasks to file automatically
- Restore tasks automatically

## Other stuff to know
- The first word of the input is the command
- Available commands
   - todo
   - deadline
   - event
   - find
   - list
   - delete
   - mark
   - unmark
- Duke was built with Java 11!

## Usage

1. Clone the repository

```
git clone https://github.com/lipwei1808/ip.git
```

2. Build Duke

```
./gradlew shadowJar
```

3. Run Duke

```aidl
java -jar ./build/libs/duke.jar
```
