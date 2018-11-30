package application;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import dataframe.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class TableViewSample extends Application{
	Button button;
    Scene sc1, sc2;
    Stage window;
    Label label;
    static DataFrame x;
 
    private TableView<Person> table = new TableView<Person>();
    public ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("1", "a", "0.1234"),
            new Person("2", "b", "0.534"),
            new Person("3", "c", "1.3435"),
            new Person("4", "d", "1.41"),
            new Person("5", "e", "2.73")
        );
   
    public static void main(String[] args) {
        launch(args);
    }
    
    public static class Content{
         DataFrame tmp = new DataFrame(new String[] {"a","b","c"}, new Class[] {StringV.class,IntegerV.class,StringV.class});
         StringV ss = new StringV();
 		IntegerV ff = new IntegerV();
 		/*tmp.dodaj2(new Value[] {ss.create("AAA"),ff.create("1"),ss.create("bbb")});
 		tmp.dodaj2(new Value[] {ss.create("ABA"),ff.create("1"),ss.create("bbb")});
 		tmp.dodaj2(new Value[] {ss.create("AAA"),ff.create("1"),ss.create("bbb")});*/
 		//data.add(tmp.wiersz(0));
    }
 
    @Override
    public void start(Stage stage) throws IOException, IllegalAccessException, InstantiationException{
    	
    	AtomicReference<DataFrame> res = new AtomicReference<>();
        AtomicReference<ArrayList<DataFrame>> DFList = new AtomicReference<>();
        AtomicReference<ArrayList<Value>> aggregated = new AtomicReference<>();
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);
        /*try {
    	DataFrame tmp1 = new DataFrame("C:\\Users\\xhapp\\Downloads\\data.csv", new Class[] {StringV.class,StringV.class,StringV.class});
    	}catch (IOException e){
    		e=null;
    	}*/
        window = stage;
        window.setTitle("DataFrame Manager 0.5");

        FileChooser fileChooser = new FileChooser();
        try {

            File f = fileChooser.showOpenDialog(window);
           // DataFrame check = new DataFrame(f.getPath(), new Class[]{StringV.class,StringV.class, StringV.class});
    	}catch (Exception ee) {
            System.out.println(ee);
        }
       // DataFrame tmp = new DataFrame(new String[] {"a","b","c"}, new Class[] {StringV.class,IntegerV.class,StringV.class});
        final Label label = new Label("Data Frame");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("col1");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("col2");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("col3");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    }
} 