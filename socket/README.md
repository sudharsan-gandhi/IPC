# Sockets
Datagrams are created using Node js with in built dgram feature. datagram buffers are passed on to the consumer using sockets support.

## Dependencies
OS: Any
Softwares: Node

## To run
Run the consumer first to start listening the port
default port: 12345
default host: 127.0.0.1

```
host='producer_address' port='port_number' node consumer.js
host='producer_address' port='port_number' node producer.js
```