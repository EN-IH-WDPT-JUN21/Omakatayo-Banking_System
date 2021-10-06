Welcome to my Midterm Project - Banking System.
==========================
<br>
<big>This is a browser/postman based app to manage simple banking system with few basic operations.</big>
<br>
<br>

To start working with the app please do one of the following:
==========================
1. Copy the github link https://github.com/EN-IH-WDPT-JUN21/Omakatayo-Banking_System.git and open the repository from Version Control in JDK of your choice
2. Download zip file, unpack it on your computer and open the project in JDK of your choice

Second crucial step is MySQL setup
==========================

Please copy code below and run it in MySQL Workbench to create a database and give permissions to that has been set up in app properties.<br><br>

<code>create database banking_system;

use banking_system;

CREATE USER 'ironhacker'@'localhost' IDENTIFIED BY '1r0nH@ck3r';

GRANT ALL PRIVILEGES ON \*.\* TO 'ironhacker'@'localhost';

FLUSH PRIVILEGES;</code><br><br>

At this point database is set to update data so every run you'll start with previously saved data. 
If you would like the database to be empty with every run just set <code>spring.jpa.hibernate.ddl-auto</code> to <code>create</code> in application.properties.<br>

How the program works
==========================

<big>Using <b><font color="yellow">browser or Postman</font></b> navigate to given URL's to go through program functions.<br>
In <b><font color="yellow">resources/static</font></b> folder you can find a collection of request witch you can import to postman - this way you won't have to write all @RequestBody values from scratch.</big>

Program functionality
==========================

<big>For yor convenience during the runtime 3 users are created:</big>
1. Admin user: <b><font color="yellow">admin</font></b>
2. AccountHolder user: <b><font color="yellow">accountholder</font></b>
3. ThirdParty user: <b><font color="yellow">thirdparty</font></b>

Password for every user is "<b><font color="yellow">123456</font></b>"

You can use any of those accounts for authorization but program functionality will depend on the user you'll choose.<br><br>


<big>These are the operations that you can run in the app<br><font color="red">( add <b>http://localhost:8080/api/v1/ </b> before all given URL's )</font>:</big>

<big>1. Create new bank accounts (mappings and additional info below):</big>
- use <b><font color="yellow">new/checking</font></b> - for creating a <b><font color="green">CHECKING</font></b> account or if the condition is met a <b><font color="green">STUDENT_CHECKING</font></b> account
- use <b><font color="yellow">new/creditcard</font></b> - for creating a <b><font color="green">CREDITCARD</font></b> account
- use <b><font color="yellow">new/savings</font></b> - for creating <b><font color="green">SAVINGS</font></b> account<br><br>

<big>2. Create new UserType:</big>
- use <b><font color="yellow">new/account_holder</font></b> - for creating new <b><font color="green">ACCOUNT_HOLDER</font></b> user type
- use <b><font color="yellow">new/admin</font></b> - for creating new <b><font color="green">ADMIN</font></b> user type
- use <b><font color="yellow">new/third_party</font></b> - for creating new <b><font color="green">THIRD_PARTY</font></b> user type<br><br>

<big>3. Show information about user types and accounts stored in Database:</big>
- use <b><font color="yellow">show/myaccounts</font></b> - to show all accounts created by logged-in User
- use <b><font color="yellow">show/myaccounts/{id}</font></b> - to show details of an account created by logged-in User
- use <b><font color="yellow">show/allaccounts</font></b> - to show all accounts - <b><font color="red">only for Admin</font></b>
- use <b><font color="yellow">show/allaccounts/{id}</font></b> - to show details of any account - <b><font color="red">only for Admin</font></b>
- use <b><font color="yellow">show/account_holder</font></b> - to show all account_holder user types created - <b><font color="red">only for Admin</font></b>
- use <b><font color="yellow">show/admin</font></b> - to show all admin user types created - <b><font color="red">only for Admin</font></b>
- use <b><font color="yellow">show/third_party</font></b> - to show all third_party user types created - <b><font color="red">only for Admin</font></b><br><br>

<big>4. Transfer money - Account Holder:</big>
- use <b><font color="yellow">account_holder/transfer?userAccountId={}&userLogin={}&transferAccountId={}&primaryOwnerId={}&transferAmount={}</font></b> to transfer money from logged-in user account to any other account
- in the link change <b><font color="yellow">{}</font></b> to appropriate values
- <b><font color="yellow">userAccountId</font></b> - id of an account you want to make a transfer from - User that is logged-in must be an owner of this account
- <b><font color="yellow">userLogin</font></b> - username of a User that is logged-in <b><font color="red">( only accountholder can transfer money using this method but to make it more robust it still compares param value with logged-in username )</font></b>
- <b><font color="yellow">transferAccountId</font></b> - id of an account you want to make a transfer to
- <b><font color="yellow">primaryOwnerId</font></b> or <b><font color="yellow">secondaryOwnerId</font></b> - id of a primary or secondary owner of an account you want to make a transfer to - you can use only one of these parameters - <b><font color="yellow">primaryOwnerId</font></b> accepts only id of a primary owner and <b><font color="yellow">secondaryOwnerId</font></b> only accepts id of a secondary owner
- <b><font color="yellow">transferAmount</font></b> - amount that you want to transfer from userAccount to transferAccount<br><br>

<big>5. Change account properties:</big>
- use <b><font color="yellow">change_balance/{id}?balance={}</font></b> - to change balance of an account by given id and balance ( change <b><font color="yellow">{}</font></b> to a chosen value ) - <b><font color="red">only for Admin</font></b>
- use <b><font color="yellow">change_status/{id}?status={}</font></b> - to change status of an account by given id and status name - ( change <b><font color="yellow">{}</font></b> to a chosen value ) - available statuses are <b><font color="green">"ACTIVE"</font></b> and <b><font color="green">"FROZEN"</font></b> - <b><font color="red">only for Admin</font>