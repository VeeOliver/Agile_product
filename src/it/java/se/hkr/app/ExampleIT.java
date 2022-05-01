package se.hkr.app;

public class ExampleIT {
    /*
    For integration tests a database is set up which is always in the same state at
    the start of the test run. It containes the sample data defined in 
    /sql/sample_data_insert.ddl which can be used to validate own method output.

    When doing integration tests against this containerized database, give the method
    DatabaseConnection.getInstance() the String "127.0.0.1:5000" as an argument.
    Then call the connect() function on the DatabaseConnnection object to build the
    connection to the database in the container and use this for whichever query
    you want to execute.
    
    Example code to build connection for integration tests:
    @Test
    public void tryConnectionToDocker() {
        DatabaseConnection dbCon = DatabaseConnection.getInstance("127.0.0.1:5000");
        Connection con = dbCon.connect();
        assertNotNull(con);
    }*/
}
