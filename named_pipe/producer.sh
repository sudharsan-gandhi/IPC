#!/bin/sh
pipe=/home/testpipe
array=()
trap "rm -f $pipe" EXIT #removes the pipe when exited or signal interrupted

if [ ! -f $pipe ]; then
    mkfifo $pipe
    echo "named pipe created..."
fi

for i in {1..100}
do
    array+=($RANDOM)
done

echo ${array[*]} > $pipe
echo "copied 100 random numbers to pipe"
exit 0