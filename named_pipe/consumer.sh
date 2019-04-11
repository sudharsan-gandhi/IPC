pipe=/home/testpipe
while true; 
do
    #if file exists break the loop and print
    if [[ -p $pipe ]]; then 
          break     
    fi
    echo "waiting for the pipe to start, will recheck in 5 seconds" 
    sleep 5
done
#print line by line #also can do cat < $pipe to print the whole file
while read line <$pipe 
do
    if [[ "$line" == 'quit' ]]; then
        break
    fi
    echo $line
done

exit 0