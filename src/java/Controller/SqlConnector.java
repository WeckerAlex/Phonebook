/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Person;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author weckeralex
 */
public class SqlConnector {
    int por = 3306;
    String username = "your_user";
    String password = "yourpasswort";
    String database = "dbphonebook";
    String table = "person";
    Connection con;
    
    /**
     *  Connects to the database
     */
    public void connect() {
        try {
          Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch ( ClassNotFoundException e ) {
          System.err.println( "Keine Treiber-Klasse!" );
        }
        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);
            con = DriverManager.getConnection("jdbc:mysql://localhost:"+por+"/"+database+"?serverTimezone=UTC",connectionProps);
        }catch(SQLException sqle){
            System.err.println("Connection Error");
            System.err.println(sqle);
        }catch(Exception e){
            System.err.println("Connection Error");
            System.err.println(e);
        }
        
    }
    
    /**
     * Get the person stored at a specific position when ordered by name.
     * @param i The position of the person to retrieve
     * @return The person saved at position i when ordered by name
     */
    public Person getdata(int i){
        Person result=null;
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM person ORDER BY name LIMIT ?,1")) {
            ps.setInt(1,i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Person resultperson = new Person(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
                resultperson.setId(rs.getInt("id"));
                result= resultperson;
            }
            rs.close();
        }catch(SQLException sqle){
            System.err.println("Database Error");
            System.err.println(sqle);
        }
        return result;    
    }

    /**
     * Get the number of persons stored.
     * @return The number of persons stored in the phonebook.
     */
    public int getRowCount() {
        int i=-1;
        try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM person");
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                i= rs.getInt(1);
            }
            rs.close();
        }catch(SQLException sqle){
            System.err.println("Counting Error");
            System.err.println(sqle);
        }
        return i; 
    }

    /**
     *  Updates the person
     * @param p The person to be updated
     */
    public void updateRecord(Person p) {
        try (PreparedStatement ps = con.prepareStatement("update person SET name=?,email=?,phone=? where id = ?")) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getPhone());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
        } catch (SQLException sqle) {
            System.err.println("Deleting Error");
            System.err.println(sqle);
        }
    }

    /**
     *  Delete a person with given id
     * @param id The id of the person to delete
     */
    public void deleteRecord(int id) {
        try (PreparedStatement ps = con.prepareStatement("delete from person where id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException sqle){
            System.err.println("Deleting Error");
            System.err.println(sqle);
        }
    }

    /**
     *  Saves a person
     * @param newperson The person to save
     * @return The id of the inserted person
     */
    public int savePerson(Person newperson) {
        int entry = -1;
        try ( PreparedStatement ps = con.prepareStatement("INSERT INTO person(name,email,phone) VALUES(?,?,?)")) {
            ps.setString(1, newperson.getName());
            ps.setString(2, newperson.getEmail());
            ps.setString(3, newperson.getPhone());
            ps.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement("SELECT MAX(id) FROM person");
            ResultSet rs = ps2.executeQuery();
            while (rs.next()) {
                entry = rs.getInt(1);
            }
            ps2.close();
            rs.close();
        } catch (SQLException sqle) {
            System.err.println("Save Error");
            System.err.println(sqle);
        }
        return entry;
    }
    
    /**
     *  Searches for the position of the person with a given id
     * @param personid The id of the person
     * @return The position of the person when ordered by name
     */
    public int search(int personid){
        int entry=1;
        String statement1 ="SET @row_number=0;";
        String statement2 ="SELECT num from (SELECT(@row_number:=@row_number + 1)as num,id,name FROM person ORDER BY name) as all_entries where id=?;";
        try (PreparedStatement ps1 = con.prepareStatement(statement1)){
            ps1.execute();
        } catch (SQLException sqle) {
            System.err.println("Search Error");
            System.err.println(sqle);
        }
        try (PreparedStatement ps2 = con.prepareStatement(statement2)) {
            ps2.setInt(1,personid);
            ResultSet rs = ps2.executeQuery();
            while (rs.next()){
                entry= rs.getInt(1);
            }
            rs.close();
        }catch(SQLException sqle){
            System.err.println("Search Error");
            System.err.println(sqle);
        }
        return entry-1;
    }
  
}
