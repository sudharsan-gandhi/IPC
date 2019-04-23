const RSMQPromise = require('rsmq-promise'); // redis message queue
const queueName = 'message_queue';
const rsmq = new RSMQPromise({
    host: "127.0.0.1",
    port: 6379
});
let i = 0;

// check if already queue is present if so delete and recreate the queue with name.
rsmq.listQueues()
    .then(queues => {
        if (queues.includes(queueName))
            rsmq.deleteQueue({ qname: queueName }).then(() => createQueue());
        else 
            createQueue();
    })
    .catch(err => console.log(err));

// creating new queue
function createQueue() {
    rsmq.createQueue({ qname: queueName })
        .then(done => {
            console.log('Queue created!');
            sendMessage();
            receiveMessage();
        })
        .catch(err => console.log(err));
}

// to receive the buffer
function receiveMessage() {
    rsmq.receiveMessage({ qname: queueName })
        .then(message => console.log(message))
        .catch(err => console.log(err));
}

// adding values to redis queue
function sendMessage() {
    if(i < 100)
    rsmq.sendMessage({ qname: queueName, message: (Math.floor((Math.random()*100))).toString() })
        .then(result => {
            console.log("Message id", result);
                i++;
                sendMessage();
                receiveMessage();    
    })
        .catch(err => console.log(err));
}