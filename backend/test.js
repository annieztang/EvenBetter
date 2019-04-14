const request = require('request');
const fetch = require('node-fetch');

const fs = require('fs');
var options = {
    url: 'https://empty-lizard-44.localtunnel.me/cache',
    headers: {
        accept: 'application/json'
    }
};
let fileStream = fs.createWriteStream('storeData.js');
request(options).pipe(fileStream).on('finish', (() => console.log('done')));


 fetch('https://empty-lizard-44.localtunnel.me/cache').then((response) => {
 	console.log(response);
 });
