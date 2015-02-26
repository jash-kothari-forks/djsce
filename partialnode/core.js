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

//KNOW WHAT UTILITY IS DOING AND MODEL IS DOING HERE
var utility = require('./utility.js');

var service = {};


service.getTodos = function(req, res)
{
	knex('todo_data')
	.select('*')
	.exec(function(err, rows)
	{
		//HANDLE ERROR, AND SEND SUCCESSFUL RESPONSE
	});
}

service.addTodo = function(req, res)
{
	console.log(req.body);
	
	//GET THE REQ.BODY VARIABLES AND MAKE AN OBJECT TO INSERT

	knex('todo_data')
	.insert(/* TODO OBJECT TO INSERT */)
	.exec(function(err, rows)
	{
		//RETURN ERROR OR SUCCESS
	});
}

service.updateTodo = function(req, res)
{
	//GET ID AND UPDATED DESC

	knex('todo_data')
	.where('id', /* THE TODO ID */)
	.update({description: /* UPDATED DESC */})
	.exec(function(err, rows)
	{
		//RETURN ERROR OR SUCCESS
	});
}

service.deleteTodo = function(req, res)
{
	//GET TO DELETE ID

	knex('todo_data')
	.where('id', /* DELETE ID */)
	.del()
	.exec(function(err, rows)
	{
		//RETURN ERROR OR SUCCESS
	});
}










module.exports = service;