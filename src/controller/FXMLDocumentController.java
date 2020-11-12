/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Bio;

/**
 *
 * @author JUJU
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Button create;

    @FXML
    private Button read;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Button findnameandage;

    @FXML
    private Button findstudy;

    
            
    //*****CRUD code taken from class demo
           
   @FXML         
    void createUserbio(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Enter Age:");
        int age = input.nextInt();
        
        System.out.println("Enter Your School Year:");
        String schoolyear = input.next();
        
        System.out.println("Enter Your Major:");
        String major = input.next();
        
        System.out.println("Enter Your Minor, if any:");
        String minor = input.next();
        
        // create a user bio instance
        Bio userBio = new Bio();
        
        // set properties
        userBio.setId(id);
        userBio.setName(name);
        userBio.setAge(age);
        userBio.setSchoolyear(schoolyear);
        userBio.setMajor(major);
        userBio.setMinor(minor); 
        
        // create this user bio to databse when create button is clicked       
        create(userBio);
        label.setText("User bio created!");
    }

    @FXML
    void deleteUserbio(ActionEvent event) {
        Scanner input = new Scanner(System.in);
         // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
 
        Bio ubio = readById(id);
        System.out.println("Deleting this user's bio: "+ ubio.toString());
        label.setText("User bio deleted!");
        delete(ubio);
    }
    
      @FXML
    void readByID(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Bio ubio = readById(id);
        System.out.println(ubio.toString());

    }

    @FXML
    void readUserbio(ActionEvent event) {

    }
    
    
    @FXML
    void findByNameAndAge(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Enter Age:");
        int age = input.nextInt();
        
        // create a student instance      
        List<Bio> ubio =  readByNameAndAge(name, age);
        label.setText("Found!");
    }

    @FXML
    void findByStudy(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        
        System.out.println("Enter Major:");
        String major = input.next();
 
        // create a student instance      
        List<Bio> ubio =  readByStudy(major);
        label.setText("Found!");

    }

    @FXML
    void updateUserbio(ActionEvent event) {
    Scanner input = new Scanner(System.in);
        
        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name");
        String name = input.next();
        
        System.out.println("Enter Age:");
        int age = input.nextInt();
       
        System.out.println("Update School Year:");
        String schoolyear = input.next();
        
        System.out.println("Update Your Major:");
        String major = input.next();
        
        System.out.println("Update Your Minor, if any:");
        String minor = input.next();
        
        // create a user bio instance
        Bio userBio = new Bio();
        
        // set properties
        userBio.setId(id);
        userBio.setName(name);
        userBio.setAge(age);
        userBio.setSchoolyear(schoolyear);
        userBio.setMajor(major);
        userBio.setMinor(minor);  
        
        // save this student to databse by calling Create operation        
        update(userBio);
        label.setText("User bio has been updated!");
    }

    
    
    
   @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }


    //EntityManager taken from class demo
    //Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "JulieHoFXMLPU"
    manager = (EntityManager) Persistence.createEntityManagerFactory("JulieHoFXMLPU").createEntityManager();
    }  
    
    
       // Read Operations
    public List<Bio> readAll(){
        Query query = manager.createNamedQuery("Bio.findAll");
        List<Bio> users = query.getResultList();

        for (Bio ubio : users) {
            System.out.println(ubio.getId() + " " + ubio.getName() + " " + ubio.getAge() + " " + ubio.getSchoolyear() + " " + ubio.getMajor() + " " + ubio.getMinor());
        } 
       
        return users;
    }
    
    public Bio readById(int id){
        Query query = manager.createNamedQuery("Bio.findById");
        
        // setting query parameter
        query.setParameter("id", id);
        
        // execute query
        Bio ubio = (Bio) query.getSingleResult();
        if (ubio != null) {
            System.out.println(ubio.getId() + " " + ubio.getName() + " " + ubio.getAge() + " " + ubio.getSchoolyear() + " " + ubio.getMajor() + " " + ubio.getMinor());
        }
        return ubio;
    }  
    
       public List<Bio> readByNameAndAge(String name, Integer age){
        Query query = manager.createNamedQuery("Bio.findByNameAndAge");
        
        // setting query parameter
        query.setParameter("name", name);
        query.setParameter("age", age);

        // execute query
        List<Bio> users = query.getResultList();
        for (Bio ubio : users) {
            System.out.println(ubio.getId() + " " + ubio.getName() + " " + ubio.getAge() + " " + ubio.getSchoolyear() + " " + ubio.getMajor() + " " + ubio.getMinor());
        } 
        return users;
    }   
       
        public List<Bio> readByStudy(String major){
        Query query = manager.createNamedQuery("Bio.findByMajor");
        
        // setting query parameter
        query.setParameter("major", major);

        // execute query
        List<Bio> users = query.getResultList();
        for (Bio ubio : users) {
            System.out.println(ubio.getId() + " " + ubio.getName() + " " + ubio.getAge() + " " + ubio.getSchoolyear() + " " + ubio.getMajor() + " " + ubio.getMinor());
        } 
        return users;      
    } 
    
    
    
    public void create(Bio userBio) {
        try {
            // begin transaction
            manager.getTransaction().begin();
            
            // sanity check
            if (userBio.getId() != null) {
                
                // create student
                manager.persist(userBio);
                
                // end transaction
                manager.getTransaction().commit();
                
                System.out.println(userBio.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        
    public void update(Bio model) {
    try {

        Bio existingUserbio = manager.find(Bio.class, model.getId());

        if (existingUserbio != null) {
            // begin transaction
            manager.getTransaction().begin();

            // update all atttributes
            existingUserbio.setName(model.getName());
            existingUserbio.setAge(model.getAge());
            existingUserbio.setSchoolyear(model.getSchoolyear());
            existingUserbio.setMajor(model.getMajor());
            existingUserbio.setMinor(model.getMinor());
           
            // end transaction
            manager.getTransaction().commit();
            System.out.println(existingUserbio.toString() + " has been updated");
        }
    } 
    catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    }

    // Delete operation
    public void delete(Bio userBio) {
        try {
            Bio existingUserbio = manager.find(Bio.class, userBio.getId());

            // sanity check
            if (existingUserbio != null) {
                
                // begin transaction
                manager.getTransaction().begin();
                
                //remove student
                manager.remove(existingUserbio);
                
                // end transaction
                manager.getTransaction().commit();
                System.out.println(existingUserbio.toString() + " has been deleted");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

