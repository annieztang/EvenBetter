const request = require('request');
const express = require('express');
const path = require('path');
const router = express.Router();
const fs = require('fs');

const app = express();
const url = 'https://api.evenfinancial.com/leads/rateTables/';
const dir = './public';
let pathTo = './data.json';
var content = require(pathTo);

if (!fs.existsSync(dir)) {
	fs.mkdirSync(dir);
}
router.get('/',function(req,res){
  res.sendFile(path.join(__dirname+'/conway.html'));
  //__dirname : It will resolve to your project folder.
});

router.get('/timeline',function(req,res){
  res.sendFile(path.join(__dirname+'/timeline.html'));
});
/*
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname + '/conway.html')).then() => {
  res.sendFile(path.join(__dirname + '/timeline.html'));
}
});
*/
const uuid;
app.use('/', router);
app.listen(process.env.port || 3000);
request.post(
    { 
    	url: url,
    	headers: {
    		'Authorization': 'Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2',
        'Accept': 'application/vnd.evenfinancial.v1+json'
    	},
    	json:content },
    function (error, response, body) {
        if (!error && response.statusCode == 200) {
            console.log(body['uuid']);
            uuid = body['uuid'];
        }
        else {
        	console.log("error: " + error)
            console.log("response.statusCode: " + response.statusCode)
            console.log("response.statusText: " + response.statusText)
        }
    }
);
getUrl = 'https://api.evenfinancial.com/originator/rateTables/{UUID}'
request.get(
  getUrl: getUrl,
  headers: {
        'Authorization': 'Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2'
      },

  )

