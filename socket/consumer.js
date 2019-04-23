const datagram = require('dgram');
const server = datagram.createSocket('udp4');
var PORT = process.env.port || 12345;
var HOST = process.env.host || '127.0.0.1';

server.on('error', (err) => {
    console.log(`server error:\n${err.stack}`);
    server.close();
});

server.on('message', (msg, rinfo) => {
    console.log(`server got: ${msg.join(',')} from ${rinfo.address}:${rinfo.port}`);
});

server.on('listening', () => {
    const address = server.address();
    console.log(`server listening ${address.address}:${address.port}`);
});

server.bind(PORT, HOST);
