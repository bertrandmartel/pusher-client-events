var express = require('express');
var bodyParser = require('body-parser');
var Pusher = require('pusher');

 //TODO: change the configuration below
var pusher = new Pusher({
  appId: 'XXXXXX',
  key: 'XXXXXXXXXXXXXXXXXXX',
  secret: 'XXXXXXXXXXXXXXXXXX',
  cluster: 'eu',
  encrypted: true
});

var app = express();
app.use(express.static('public'))
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.post('/pusher/auth', function(req, res) {
  var socketId = req.body.socket_id;
  var channel = req.body.channel_name;
  var auth = pusher.authenticate(socketId, channel);
  res.send(auth);
});

var port = process.env.PORT || 5000;
app.listen(port);