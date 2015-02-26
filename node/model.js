module.exports.Todo = Todo;
function Todo(row)
{
	this.id = row.id;
	this.desc = row.description;
	this.date = row.date;
}