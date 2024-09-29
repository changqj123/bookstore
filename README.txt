1.Make sure you have installed mysql in you local

2.Start application:
Run the main method in DemoApplication.class

3.Test functions:
(1)Run test cases in test java package
(2)Test restful api functions(please find api path in the package /org/bookstore/demo/controller) through postman
(3)apis:
UserController.class: /users/register, /users/login, /users/getUserById, /users/getUserByName
BookController.class:/books/add, /books/delete, /books/update, /books/viewBookInfoById, /books/viewBookInfoByName
InventoryController.class:/inventory/increase, /inventory/decrease, /inventory/checkStockById, /inventory/addBookItem, /inventory/removeBookItem
OrderController.class:/orders/createOrder, /orders/cancelOrder, /orders/checkOrderState