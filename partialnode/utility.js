var model = require('./model.js');

module.exports.getTodoList = getTodoList;
function getTodoList(rows)
{
	var todoList = [];

	for (var i = 0; i < rows.length; i++)
	{
		var todo = new model.Todo(rows[i]);
		todoList.push(todo);
	}

	return todoList;
}