const request = require('request');
const express = require('express');
const path = require('path');
const router = express.Router();
const fs = require('fs');
const Prase = require('parse/node');
const _ = require('lodash');

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
router.get('/cache',function(req,res){
  res.sendFile(path.join(__dirname+'/cache.js'));
});

/*
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname + '/conway.html')).then() => {
  res.sendFile(path.join(__dirname + '/timeline.html'));
}
});
*/
let uuid;
let getUrl;
app.use('/', router);
app.listen(process.env.port || 3000);

function postInfo() {
  return new Promise((resolve, reject) => {
     resolve(request.post({ 
        url: url,
        headers: {
          'Authorization': 'Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2',
          'Accept': 'application/vnd.evenfinancial.v1+json'
        },
        json:content },
      function (error, response, body) {
          if (!error && response.statusCode == 200) {

              let userData = {
                fico : content['creditInformation']['providedNumericCreditScore'],
                drive_id : content['personalInformation']['driversLicenseNumber'],
                newFico : 0
              };
              let filename = 'cache.js';
              fs.appendFile(filename, `userData.push(${JSON.stringify(userData)})`,  (error) => {
                if (error) throw error;
                else {
                  console.log("Saved!");
                  console.log(userData)
                }
              });

              uuid = body['uuid'];
              getUrl = `https://api.evenfinancial.com/originator/rateTables/${uuid}`;
          }
          else {
              console.log("error: " + error)
              console.log("response.statusCode: " + response.statusCode)
              console.log("response.statusText: " + response.statusText)
          }
      }
  ));
})};

postInfo();
function waitForElement(){
    if(typeof getUrl !== "undefined"){
        getInfo();
    }
    else{
        setTimeout(waitForElement, 250);
    }
}
waitForElement();
function getInfo() {
  console.log(getUrl);
  let req = request.get({
        url: getUrl,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2',
            'Accept': 'application/vnd.evenfinancial.v1+json'
        },
      },
      function(err, response, body) {
       // console.log(response);
        if (!err && response.statusCode == 200) {

               
                var dirty = JSON.parse(body);
                 var clean = pruneEmpty(dirty);
                console.log(clean == dirty);
                console.log(JSON.stringify(clean, null, 2).slice(0,15));

            }
        else {
           console.log("error: " + err);
           console.log("response.statusCode: " + response.statusCode);
           console.log("response.statusText: " + response.statusText);
       }
    });
  req.on('error', (e) => {
    console.log(e);
  })
};

 // Helper function for pruning JSON.
 function pruneEmpty(obj) {
                  return function prune(current) {
                    _.forOwn(current, function (value, key) {
                      if (_.isUndefined(value) || _.isNull(value) || _.isNaN(value) ||
                        (_.isString(value) && _.isEmpty(value)) ||
                        (_.isObject(value) && _.isEmpty(prune(value)))) {

                        delete current[key];
                      }
                    });
                    
                    // remove any leftover undefined values from the delete 
                    // operation on an array
                    if (_.isArray(current)) _.pull(current, undefined);
                    return current;
                  }(_.cloneDeep(obj));  // Do not modify the original object, create a clone instead
                };


                

