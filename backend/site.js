const request = require('request');
const express = require('express');
const path = require('path');
const router = express.Router();
const fs = require('fs');
const Prase = require('parse/node');
const _ = require('lodash');
const app = express();
const url = 'https://api.evenfinancial.com/leads/rateTables/';
var net = require('net');

let pathTo = './data.json';
var content = require(pathTo);

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
var server = net.createServer();

server.once('error', function(err) {
  if (err.code === 'EADDRINUSE') {
    // port is currently in use
  }
 else 
  app.listen(process.env.port || 3000);
});

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
              let bankMonths = content['financialInformation']['monthsAtBank'];
              let loanAmount = content['loanInformation']['loanAmount'];
              let mortgageBalance = content['mortgageInformation']['mortgageBalance'];
              let employedMonths = content['employmentInformation']['monthsEmployed'];
              let income = content['financialInformation']['annualIncome'];
              let value = content['mortgageInformation']['propertyValue'];
              let fico = content['creditInformation']['providedNumericCreditScore'];
              let userData = {
                fico: fico,
                drive_id : content['personalInformation']['driversLicenseNumber'],
                newFico : calcuateCredit(bankMonths, employedMonths, fico, mortgageBalance, loanAmount, income, value)
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
}).then(() =>  {
  app.use('/', router);
}
  )};

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

 // Calculate new FICO score
 function calcuateCredit(bankMonths,employedMonths,fico,mortgageBalance, loanAmount, income, value) {
  let capBank = bankMonths / 40;
  let creditUtil = (mortgageBalance + loanAmount) / (income + .5 *(value - mortgageBalance))
  let creditCap = Math.min(creditUtil * 2000, .3 * 850);
  employedMonths = Math.min(employedMonths * 10, .15 * 850);
  fico = Math.min(fico*.1 + 20, 85);
  bankMonths = Math.min(.35 * 850 * capBank);
  return Math.max(750, creditCap + fico + bankMonths + employedMonths + 150);
 }
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


                

