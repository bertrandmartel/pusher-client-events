'use strict';

var Pusher = require('pusher');

var pusher = new Pusher({
  appId: 'XXXXXXXXX',
  key: 'XXXXXXXXXXXXXXXXXX',
  secret: 'XXXXXXXXXXXXXXXXXXX',
  cluster: 'eu',
  encrypted: true
});

exports.auth = (req, res) => {
  var socketId = req.body.socket_id;
  var channel = req.body.channel_name;
  var auth = pusher.authenticate(socketId, channel);
  res.set('Access-Control-Allow-Origin', "*")
  res.set('Access-Control-Allow-Methods', 'GET, POST')
  res.send(auth);
};