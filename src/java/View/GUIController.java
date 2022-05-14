package View;

import Controller.Phonebook;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author A-Terra
 */
public class GUIController {
    private static GUIController instance;
    Phonebook pb;
    private String name = "";
    private String phone = "";
    private String email = "";
    private String description = "a";
    private boolean previousenabled = true;
    private boolean subsequentenabled = true;
    private boolean deleteenabled = true;
  
    private GUIController(){
        pb = new Phonebook(this);
    }
    
    /**
     *  Get the instance of the class
     * @return The instance of the class
     */
    public static GUIController getInstance() 
    { 
        if (instance == null){
            instance = new GUIController(); 
        } 
        return instance ; 
    } 

    /**
     *  Sets the data to be displayed.
     * @param name The name to display
     * @param email The email to display
     * @param phone The phone number to display
     */
    public void setData(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    /**
     *  Asks for the previous person
     */
    public void getPrevious(){
        pb.getPrevious();
    }
    
    /**
     *  Asks for the subsequent person
     */
    public void getSubsequent(){
        pb.getSubsequent();
    }
    
    /**
     *  Deletes the currently displayed person
     */
    public void deleteRecord(){
        pb.deleteRecord();
    }
    
    /**
     *  Inserts the data to the database
     * @param name The name of the person to be stored
     * @param email The email of the person to be stored
     * @param phone The phone number of the person to be stored
     */
    public void saveRecord(String name, String email, String phone){
        pb.saveRecord(name,email,phone);
    }
    
    /**
     *  Cleares the datafields and asks for a new user
     */
    public void addnew() {
        setData("", "", "");
        pb.addnew();
    }

    /**
     *  Sets the description 
     * @param string The description
     */
    public void updatedescription(String string) {
        this.description = string;
    }

    /**
     *  Sets if the previous button is enabled or not 
     * @param b The state of the button
     */
    public void enablepreviousselect(boolean b) {
        this.previousenabled = b;
    }

    /**
     *  Sets if the subsequent button is enabled or not 
     * @param b The state of the button
     */
    public void enablesubsequentselect(boolean b) {
        this.subsequentenabled = b;
    }

    /**
     *  Sets if the delete button is enabled or not 
     * @param b The state of the button
     */
    public void enabledelete(boolean b) {
        this.deleteenabled = b;
    }

    /**
     *  Gets the name
     * @return The name to display
     */
    public String getName() {
        return name;
    }

    /**
     *  Gets the phone number
     * @return The phone number to display
     */
    public String getPhone() {
        return phone;
    }

    /**
     *  Gets the email
     * @return The email to display
     */
    public String getEmail() {
        return email;
    }

    /**
     *  Gets the description
     * @return The description to display
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Sets the previous button state
     *  
     * @return An empty string or " disabled"
     */
    public String isPreviousenabled() {
        if (previousenabled){
            return "";
        }else{
            return " disabled";
        }
    }

    /**
     *  Sets the Subsequent button state
     * @return An empty string or " disabled"
     */
    public String isSubsequentenabled() {
        if (subsequentenabled) {
            return "";
        } else {
            return " disabled";
        }
        
    }

    /**
     *  Sets the Delete button state
     * @return An empty string or " disabled"
     */
    public String isDeleteenabled() {
        if (deleteenabled) {
            return "";
        } else {
            return " disabled";
        }
    }
    
}
