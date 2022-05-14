/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Person;
import View.GUIController;

/**
 *
 * @author weckeralex
 */
public class Phonebook {
    int tableposition;
    int personcount;
    SqlConnector sc;
    GUIController mf;
    Person shownperson;

    /**
     *
     * @param mf The instance which is responsible of modifying the UI
     */
    public Phonebook(GUIController mf) {
        this.mf = mf;
        sc = new SqlConnector();
        sc.connect();
        personcount=sc.getRowCount();
        if (personcount!=0){
            //database not empty
            tableposition = 0;
            shownperson = sc.getdata(tableposition);
            getpersonselected();
        }else{
            addnew();
            //database empty
        }
    }
    
    
    /**
     *  Sends the data of the person to display to the view
     */
    public void writePerson(){
        if(shownperson != null) {;
            mf.setData(shownperson.getName(), shownperson.getEmail(), shownperson.getPhone());
        }else{
            mf.setData("", "", "");
        }
        
    }

    /**
     *   Goes to the previous person in the phonebook and updates the UI
     */
    public void getPrevious() {
        tableposition--;
        shownperson = sc.getdata(tableposition);
        getpersonselected();
    }
    
    /**
     *  Updates the UI
     */
    public void getpersonselected() {
        checkposition();
        writePerson();
        mf.updatedescription("Record "+(tableposition+1)+" of "+personcount);
    }

    /**
     *  Goes to the subsequent person in the phonebook and updates the UI
     */
    public void getSubsequent() {
        tableposition++;
        shownperson = sc.getdata(tableposition);
        getpersonselected();
    }

    /**
     *  Deletes the currently selected person and displays the next person. If there is no person left it will ask to insert a person.
     */
    public void deleteRecord() {
        sc.deleteRecord(shownperson.getId());
        personcount = sc.getRowCount();
        if (personcount==0) {
            addnew();
        }else{
            tableposition = (tableposition == personcount) ? --tableposition : tableposition;
            shownperson = sc.getdata(tableposition);
            getpersonselected();
        }
    }
    
    private void checkposition(){
        if(tableposition==0){
            mf.enablepreviousselect(false);
        }else{
            mf.enablepreviousselect(true);
        }
        if(tableposition==personcount-1){
            mf.enablesubsequentselect(false);
        }else{
            mf.enablesubsequentselect(true);
        }
    }
 
    /**
     *  Saves the data to the database. If the person has already been saved to the database, it will be updated.
     *  If the person has not already been saved, it will be inserted.
     * @param name The name of the person to save
     * @param email The email of the person to save
     * @param phone The phone number of the person to save
     */
    public void saveRecord(String name, String email, String phone) {
        if(tableposition!=-1){
            //Person person = new Person(name,email,phone);
            shownperson.setName(name);
            shownperson.setEmail(email);
            shownperson.setPhone(phone);
            shownperson.updatePerson(sc);
            writePerson();
            
        }else{
            shownperson =new Person(name,email,phone);
            shownperson.savePerson(sc);
            tableposition= sc.search(shownperson.getId());
            personcount=sc.getRowCount();
            mf.enabledelete(true);
            getpersonselected();
        }
    }

    /**
     *  Moves the position to the position used to insert a new person.
     *  Disables all buttons used to navigate and delete
     */
    public void addnew() {
        tableposition=-1;
        mf.enablesubsequentselect(false);
        mf.enablepreviousselect(false);
        mf.enabledelete(false);
        mf.updatedescription("New Person");
    }
    
}
