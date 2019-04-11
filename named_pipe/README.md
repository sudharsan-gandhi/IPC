# Named Pipe
written on Bash scripting. Used mkfifo to create pipes
## instructions
Always run with 'sudo' so the file be created with permission issues.
```
cd named_pipe
sudo ./consumer.sh #in separate terminal
sudo ./producer.sh #in separate terminal
```
If debian based os try using to resolve dash issues
```
sudo /bin/bash ./consumer.sh #in separate terminal
sudo /bin/bash ./producer.sh #in separate terminal
```