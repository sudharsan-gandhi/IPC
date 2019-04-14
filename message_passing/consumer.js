const RSMQPromise = require('rsmq-promise');
const queueName = 'message_queue';
const rsmq = new RSMQPromise({
    host: "127.0.0.1",
    port: 6379
});

function receiveMessage() {
    rsmq.receiveMessage({ qname: queueName })
        .then(message => {
            console.log(message);
            receiveMessage();
        })
        .catch(err => console.log(err));
}

receiveMessage();