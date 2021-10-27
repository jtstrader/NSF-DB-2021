# Local Database Setup
To get the local database started, you first need to set up a proper login on the localhost server and then set up TCP/IP connecting through the SQL Server Configuration Manager.

### Importing Data
1. Start SQL Server Management Studio and enter `localhost` as the server name using **Windows Authentication** as your authentication method. Then, right-click the server (at the top of your server explorer on the left) and click "New Query" which should open the query editor. Copy the code from the init.sql query in this repo under the queries/ directory. Place the query in the query editor and hit F5 to run the query. This should create the database NSF_480, the tables CSFounder and CSSubject, and set which columns are the primary keys in the respective tables. It will also populate the tables with all of the data for the CSFounder and CSSubject tables.
    - *if the database is not appearing on the server explorer, you may need to hit the refresh button* 

2. To ensure that your data populated correctly, run the following queries to test CSFounder and CSSubject:
    ```sql
    USE [NSF_480]
    GO
    
    SELECT * FROM [CSSubject];
    ```

    ```sql
    USE [NSF_480]
    GO
    
    SELECT * FROM [CSFounder];
    ```

### Getting a Connection Established
1. First you want to create a new account. Copy the following query into your query editor.
    ```sql
    USE [master]
    GO

    CREATE LOGIN [administrator] WITH PASSWORD = 'Mercer1234!' -- create new login information
    ALTER SERVER ROLE [sysadmin] ADD MEMBER [administrator] -- add to sysadmin role giving administrator privileges
    ```
    This creates a new account with the following information:
    
    Username: administrator<br>Password: Mercer1234!

2. Right-click on the server in the server explorer and click "Properties." Go into the Security tab and select "SQL Server and Windows Authentication mode". Click OK to exit.

3. Start up SQL Server Configuration Manager and select "yes" if it prompts you for administrator access. Open the "SQL Server Network Configuration" dropdown and select "Protocols for MSSQLSERVER." Right-click `TCP/IP` and select "Enable." This will allow you to access the localhost from a TCP/IP connection which will allow the API to connect to the server.

4. In your Spring Boot Application, go the application.properties file and make sure that the connection properties looks like this:
    ```properties
    spring.datasource.url=jdbc:sqlserver://localhost;databaseName=NSF_480
    spring.datasource.username=administrator
    spring.datasource.password=Mercer1234!
    spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
    spring.jpa.show-sql=true
    spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
    spring.jpa.hibernate.ddl-auto=none
    ```

5. Everything should be good to go! You can now run your application :D

# Database Refactoring
The AWS Database (our server) is very slow and the GUI in SSMS (SQL Server Management Studio) is very slow because of this. It is a good idea to use queries as much as possible to speed up any process you wish to carry out on the server. In this directory is a C++ program specifically build to generate queries to refactor columns in a table, piped out to a file titled  rename_cols_query.sql (make sure not to overwrite your files!)

### Bash
```bash
c++ rename_col_builder.cpp
./a.out
```

### Powershell
```powershell
c++ rename_col_builder.cpp
./a.exe
```

The program will prompt you to enter the table you wish to manipulate, and then the column name you wish to change and what you wish to change it to. In your query editor in SSMS, make sure you define what database you are **using** and then put the `GO` keyword to group your SQL commands into a single batch to send to the server at once. Copy paste the results of the C++ program under this and it should like this:

```sql
USE [MY_DATABASE]
GO

-- paste code here, should look like:
EXEC sp_rename 'MYTABLE.col_old_name1', 'col_new_name1', 'COLUMN';
EXEC sp_rename 'MYTABLE.col_old_name2', 'col_new_name2', 'COLUMN';
EXEC sp_rename 'MYTABLE.col_old_name3', 'col_new_name3', 'COLUMN';
EXEC sp_rename 'MYTABLE.col_old_name4', 'col_new_name4', 'COLUMN';
```

# Query Definitions
 - **init.sql**: the "database maker." Completely generates the database, as well as all columns for every table w/ their respective information.
 - **cssfi.sql**: CSSubjectFamilyInfo table creator. Specifically created to avoid having to use the .csv format again for importing, as the values from the .csv are always imported by default as varchar(50) types which is against specifications.