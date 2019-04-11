#!/bin/sh
pipe=/home/testpipe
array=()
trap "rm -f $pipe" EXIT #precaution to remove the pipe when exited or signal interrupted

if [ ! -f $pipe ]; then
    mkfifo $pipe
    echo "named pipe created..."
fi

for i in {1..100}
do
    array+=($RANDOM) #push random numbers to array
done

echo ${array[*]} > $pipe #writing the array to pipe
echo "copied 100 random numbers to pipe"
exit 0