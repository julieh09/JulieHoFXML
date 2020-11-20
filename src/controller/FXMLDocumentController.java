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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchStudyField;
    
    @FXML
    private Button advancedSearch;


    @FXML
    private TableView<Bio> userTable;

    @FXML
    private TableColumn<Bio, Integer> userID;

    @FXML
    private TableColumn<Bio, String> userName;

    @FXML
    private TableColumn<Bio, Integer> userAge;

    @FXML
    private TableColumn<Bio, String> userSchoolyear;

    @FXML
    private TableColumn<Bio, String> userMajor;

    @FXML
    private TableColumn<Bio, String> userMinor;
    
    private ObservableList<Bio> bioData;
    public void setTableData(List<Bio> userList) {
        bioData = FXCollections.observableArrayList(); 
        userList.forEach(x -> { 
            bioData.add(x);
        });
        userTable.setItems(bioData);
        userTable.refresh();
    }
    
    @FXML
    void searchByStudy(ActionEvent event) {
        System.out.println("Clicked");
        String major = searchStudyField.getText();
        List<Bio> users = readByStudy(major);
        if (users == null || users.isEmpty()) { 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("Error occured. Please try again");
            alert.setContentText("Major was not found");
            alert.showAndWait(); 
        } 
        else { 
            setTableData(users);
        }
    }
    
    @FXML
    void searchBySchoolyearAdvanced(ActionEvent event) {
        System.out.println("Clicked");
        String schoolyear = searchStudyField.getText();
        List<Bio> users = readBySchoolyearAdvanced(schoolyear);
        if (users == null || users.isEmpty()) { 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog Box");
            alert.setHeaderText("Error occured. Please try again");
            alert.setContentText("Not found");
            alert.showAndWait(); 
        } 
        else { 
            setTableData(users);
        }
    }

            
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
       
    }

    @FXML
    void deleteUserbio(ActionEvent event) {
        Scanner input = new Scanner(System.in);
         // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();
 
        Bio ubio = readById(id);
        System.out.println("Deleting this user's bio: "+ ubio.toString());
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
    }

    @FXML
    void findByStudy(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        
        // read input from command line
        
        System.out.println("Enter Major:");
        String major = input.next();
 
        // create a student instance      
        List<Bio> ubio =  readByStudy(major);

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
    }

    
    
    
   @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
 

    //EntityManager taken from class demo
    //Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "JulieHoFXMLPU"
    manager = (EntityManager) Persistence.createEntityManagerFactory("JulieHoFXMLPU").createEntityManager();
    userID.setCellValueFactory(new PropertyValueFactory<>("id"));
    userName.setCellValueFactory(new PropertyValueFactory<>("name"));
    userAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    userSchoolyear.setCellValueFactory(new PropertyValueFactory<>("schoolyear"));
    userMajor.setCellValueFactory(new PropertyValueFactory<>("major"));
    userMinor.setCellValueFactory(new PropertyValueFactory<>("minor"));
    
    userTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
        
        public List<Bio> readBySchoolyearAdvanced(String schoolyear) {
        Query query = manager.createNamedQuery("Bio.findBySchoolyearAdvanced");

        // setting query parameter
        query.setParameter("schoolyear", schoolyear);

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

