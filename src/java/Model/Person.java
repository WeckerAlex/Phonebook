/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.SqlConnector;

/**
 *
 * @author A-Terra
 */
public class Person {
    private int id =-1;
    private String name;
    private String email;
    private String phone;

    /**
     *  Creates a new person with a name an email and a phone number
     * @param name The name of the person
     * @param email The email of the person
     * @param phone The phone number of the person
     */
    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     *  Gets the id of the person
     * @return The id of the person
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of a person
     * @param id The id of the person
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets the name of the person
     * @return The name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the person
     * @return The email of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the person
     * @return The phone number of the person
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     *  Saves the person to the database and updates the id
     * @param sc 
     */
    public void savePerson(SqlConnector sc) {
        this.id = sc.savePerson(this);
    }

    /**
     *  Set the name of the person
     * @param name The name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the email of the person
     * @param email The email of the person
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the phone number of the person
     * @param phone The phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Updates the person
     * @param sc 
     */
    public void updatePerson(SqlConnector sc) {
        sc.updateRecord(this);
    }
}
