var core = require('./core.js');

module.exports = function(app)
{

	//All Server Routes go here
	app.post('/gettodos', core.getTodos);

	app.post('/addtodo', core.addTodo);

	app.post('/updatetodo', core.updateTodo);

	app.post('/deletetodo', core.deleteTodo);

}