const datagram = require('dgram');
const client = datagram.createSocket('udp4');
var PORT = process.env.port || 12345;
var HOST = process.env.host || '127.0.0.1';
let buffer_array = [];
let i = 0;

while (i < 100) {
    buffer_array.push(Buffer.from(Math.floor((Math.random()*100)).toString()));
    i++;
};

client.send(buffer_array, PORT, HOST, function(err, bytes) {
    if (err) throw err;
    console.log(`sending 100 random numbers ${buffer_array.join(',')} ${HOST}:${PORT}`);
    client.close();
});

