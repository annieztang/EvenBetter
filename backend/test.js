const request = require('request');
const fetch = require('node-fetch');

const fs = require('fs');
var options = {
    url: 'http://127.0.0.1:3000/cache',
    headers: {
        accept: 'application/json'
    }
};
let fileStream = fs.createWriteStream('storeData.js');
request(options).pipe(fileStream).on('finish', (() => console.log('done')));


 fetch('http://127.0.0.1:3000/cache').then((response) => {
 	console.log(response);
 });
