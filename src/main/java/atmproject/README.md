ATM PROJECT

This is a project where I have prototyped an ATM system. The types of users are customer and administrator.
Admins can create, display, edit, or delete a customer. Both type can log in system only through username and password.
When a customer is created by an administrator simultaneously a customer transaction file is created under Database/Transactions folder.
Customers' information are kept in CustomerDB.csv under Database folder.

#PREREQUSITE FOR RUNNING THE APP
 
We need to create an administrator in AdminDB under Database folder.

Therefore, we can sign in the system as administrator and create a customer.
Thus, the app allows users to log in as customers.

#ISSUES

-The program runs only once this means one process at a time. I tried to fix this problem however,
have not figured out it yet. the problem is related to Scanner issue. 

#IMPROVEMENTS

-There are some functions which have not been implemented yet. I plan to integrate them in the next scrum.

-We also need to implement user validations to avoid unexpected user inputs while running the application