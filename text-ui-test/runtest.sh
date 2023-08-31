#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "./bin" ]
then
    mkdir ./bin
fi

# delete output from previous run
if [ -e "./text-ui-test/ACTUAL.TXT" ]
then
    rm ./text-ui-test/ACTUAL.TXT
fi

# Rename saveTasks data
if [ -e "./data/savedTasks.txt" ]
then
  mv ./data/savedTasks.txt ./data/savedTasksTmp.txt
fi

# Ensure existence of defaults saved tasks to use
if ! [ -e "./data/savedTasksDefaults.txt" ]
then
  echo "************ BUILD FAILURE: Cannot load default savedTasks ************"
fi

# Create copy of defaults to be used for the test
cp ./data/savedTasksDefaults.txt ./data/savedTasks.txt

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ./src/main/java -Xlint:none -d ./bin ./src/main/java/duke/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ./bin Duke < ./text-ui-test/input.txt > ./text-ui-test/ACTUAL.TXT

# Relocate and rename savedTasks
mv ./data/savedTasks.txt ./text-ui-test/ACTUAL-SAVED.TXT

# Restore previous savedTask (if any)
if [ -e "./data/savedTasksTmp.txt" ]
then
  mv ./data/savedTasksTmp.txt ./data/savedTasks.txt
fi

# convert to UNIX format
cp ./text-ui-test/EXPECTED.TXT ./text-ui-test/EXPECTED-UNIX.TXT
dos2unix ./text-ui-test/ACTUAL.TXT ./text-ui-test/EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ./text-ui-test/ACTUAL.TXT ./text-ui-test/EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi