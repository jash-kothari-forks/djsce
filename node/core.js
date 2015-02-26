//For DB
var knex = require('knex')({
	client: 'mysql',
	connection: {
		host: 'localhost',
		user: 'root',
		password: 'lotus',
		database: 'vivek'
	},
	debug: true
});

var utility = require('./utility.js');


var service = {};


service.getTodos = function(req, res)
{
	knex('todo_data')
	.select('*')
	.exec(function(err, rows)
	{
		if(err)
		{
			console.log(err);
			return res.send({'error': 0});
		}
		else
		{
			var todoList = utility.getTodoList(rows);
			if(todoList.length === 0)
			{
				return res.send({'error': "No todos"});
			}
			else
			{
				return res.send(todoList);
			}
		}
	});
}

service.addTodo = function(req, res)
{
	console.log(req.body);
	var item_desc = req.body.desc;

	var todo = {};
	todo.description = item_desc;
	todo.date = knex.raw('now()');

	knex('todo_data')
	.insert(todo)
	.exec(function(err, rows)
	{
		if(err)
		{
			console.log(err);
			return res.send({'error': 'Couldn\'t add todo, please try again!'});
		}
		else
		{
			return res.send({'success': 1});
		}
	});
}

service.updateTodo = function(req, res)
{
	var updateId = req.body.id;
	var desc = req.body.desc;

	knex('todo_data')
	.where('id', updateId)
	.update({description: desc})
	.exec(function(err, rows)
	{
		if(err)
		{
			console.log(err);
			return res.send({'error': 'Couldn\'t update todo, please try again!'});
		}
		else
		{
			return res.send({'success': 1});
		}
	});
}

service.deleteTodo = function(req, res)
{
	var deleteId = req.body.id;
	knex('todo_data')
	.where('id', deleteId)
	.del()
	.exec(function(err, rows)
	{
		if(err)
		{
			console.log(err);
			return res.send({"error": "Couldn\'t delete todo, please try again!"});
		}
		else
		{
			return res.send({'success': 1});
		}
	});
}










module.exports = service;