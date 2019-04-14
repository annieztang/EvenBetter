const request = require('request');
const express = require('express');
const app = express();
const path = require('path');
const router = express.Router();
const fs = require('fs');


let data = {
  "productTypes": [
    "loan", 
    "savings"
  ], 
  "personalInformation": {
    "firstName": "John", 
    "lastName": "Doe", 
    "email": "john@example.com", 
    "city": "New York", 
    "state": "NY", 
    "workPhone": "2125551234", 
    "primaryPhone": "2125556789", 
    "address1": "45 West 21st Street", 
    "address2": "5th Floor", 
    "zipcode": "10010", 
    "monthsAtAddress": 5, 
    "driversLicenseNumber": "111222333", 
    "driversLicenseState": "NY", 
    "ipAddress": "8.8.8.8", 
    "activeMilitary": false, 
    "militaryVeteran": true, 
    "dateOfBirth": "1993-10-09", 
    "educationLevel": "bachelors", 
    "ssn": "111-22-3333"
  }, 
  "loanInformation": {
    "purpose": "home_refi", 
    "loanAmount": 10000
  }, 
  "mortgageInformation": {
    "propertyType": "condo", 
    "propertyStatus": "own_with_mortgage", 
    "propertyValue": 200000, 
    "mortgageBalance": 10000, 
    "lenderName": "Bank OF NY", 
    "hasFHALoan": true, 
    "currentWithLoan": true
  }, 
  "creditCardInformation": {
    "allowAnnualFee": true, 
    "cardBenefits": [
      "travel_incentives"
    ]
  }, 
  "creditInformation": {
    "providedCreditRating": "excellent", 
    "providedNumericCreditScore": 750
  }, 
  "financialInformation": {
    "employmentStatus": "employed", 
    "employmentPayFrequency": "weekly", 
    "annualIncome": 120000, 
    "monthlyNetIncome": 10000, 
    "bankName": "Santander", 
    "bankRoutingNumber": "231372691", 
    "bankAccountType": "savings", 
    "monthsAtBank": 10, 
    "bankAccountNumber": "1234567890"
  }, 
  "employmentInformation": {
    "employerName": "EVEN Financial", 
    "employerAddress": "45 W 21st St", 
    "employerCity": "New York", 
    "employerState": "NY", 
    "employerZip": "10010", 
    "jobTitle": "Software Engineer", 
    "monthsEmployed": 14, 
    "directDeposit": true, 
    "payDate1": "2004-10-06", 
    "payDate2": "2004-11-06"
  }, 
  "legalInformation": {
    "consentsToFcra": true, 
    "consentsToTcpa": true, 
    "tcpaLanguage": "I agree to be contacted by Even Financial and its partners at the telephone number(s) I have provided above to explore personal loan offers, including contact through automatic dialing systems, artificial or pre-recorded voice messaging, or text message. I understand my consent is not required as a condition to purchasing any goods or services from anyone."
  }, 
  "clientTags": {
    "hello": [
      "world", 
      "there"
    ], 
    "something": [
      "else"
    ]
  }
};
const url = 'https://api.evenfinancial.com/leads/rateTables/';
const dir = './public';
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
app.use('/', router);

app.listen(process.env.port || 3000);

request.post(
    { 
    	url: url,
    	headers: {
    		'Authorization': 'Bearer e7675dd3-ff3b-434b-95aa-70251cc3784b_88140dd4-f13e-4ce3-8322-6eaf2ee9a2d2'
    	},
    	json:data },
    function (error, response, body) {
        if (!error && response.statusCode == 200) {
            console.log(body);
        }
        else {
        	console.log("error: " + error)
            console.log("response.statusCode: " + response.statusCode)
            console.log("response.statusText: " + response.statusText)
        }
    }
);