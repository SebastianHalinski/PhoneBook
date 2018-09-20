package DAO;

import Models.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonDAO {

    private DBConnection dbdao = new DBConnection();
    private static ArrayList<Person> usersCollection = new ArrayList<Person>();
    private DBConnection database = DBConnection.getInstance();
    private Connection connection;

    public PersonDAO(){
        connection = database.getConnection();

        try {
            importUsersData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getUsersCollection() {
        try {
            importUsersData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersCollection;
    }


    public void importUsersData() throws SQLException {
        usersCollection.clear();
        String query = "SELECT * FROM PhoneBook";
        ArrayList<ArrayList<String>> users = dbdao.getAllObjectsFromDatabase(connection, query);
        for(int i =0; i < users.size(); i++) {
            ArrayList<String> personData = users.get(i);
            Person person = createUserObject(personData);
            usersCollection.add(person);
        }
    }

    private Person createUserObject(ArrayList<String> personData) throws SQLException {
        String name = personData.get(0);
        String surname = personData.get(1);
        String phone = personData.get(2);
        String email = personData.get(3);
        return new Person(name, surname, phone, email);
    }
}
