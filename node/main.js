var app = require('express')();

app.use(require('body-parser')());

require('./routes.js')(app);

app.listen(5000);