package application;

import dataframe.*;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;


public class Controller {
		private DataFrame dataFrame;
	    private static File file;
	    private static int index=0,i=0;
	    private static String[] names;
	    private static Class<? extends Value>[] types;
	    private HashMap<String, Object> hashMap = new HashMap<>();
	    private Stage myStage,columnsOrHeader=new Stage(),type=new Stage();
	    private Pane pane=new Pane();
	    private String[] groupby;
	    private double one_width;
	    private static boolean t = false,f=false;
	    private String[] options = {"Max","Min","Sum","Var","Std"};
	    private static String x,y;


	    @FXML
	    AnchorPane mainPane,plotChooser;

	    @FXML
	    ScrollPane spane;

	    @FXML
	    Label fileInfo,info,info1;

	    @FXML
	    Button nextButton,valinteger,valdouble,valfloat,valboolean,valstring,valdate;

	    @FXML
	    Button max,min,sum,std,var,btn;

	    @FXML
	    Menu stat;

	    @FXML
	    ImageView img;

	    @FXML
	    CheckBox header;

	    @FXML
	    TextArea columnsNames,groupBy,colChooser;

	    static int numberOfColumns;
	    
	    
	    public File chooseFile(){
	        FileChooser fileChooser = new FileChooser();
	        configureFileChooser(fileChooser);
	        file = fileChooser.showOpenDialog(myStage);
	        return file;
	    }
	    private static void configureFileChooser(
	            final FileChooser fileChooser) {
	        fileChooser.setTitle("Select CSV file");
	        fileChooser.setInitialDirectory(
	                new File(System.getProperty("user.home"),"/csv")
	        );
	        fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("CSV", "*.csv")
	        );
	    }
	    
	    public void readFile(ActionEvent event){
	        t=false;
	        file = chooseFile();
	        System.out.println(file.getPath());
	        System.out.println(1);
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(getClass().getResource("columnsOrHeader.fxml"));
	        try {
	            Parent root = FXMLLoader.load(getClass().getResource("columnsOrHeader.fxml"));
	            columnsOrHeader.setScene(new Scene(root,300,313));
	            columnsOrHeader.showAndWait();
	            types = new Class[numberOfColumns];
	            for (int i=0; i<numberOfColumns; ++i){
	                index = i;
	                fxmlLoader = new FXMLLoader(getClass().getResource("TypeChoose.fxml"));
	                root = fxmlLoader.load();
	                Controller controller = fxmlLoader.<Controller>getController();
	                type.setScene(new Scene(root,600,140));
	                controller.info.setText("Choose type for "+names[i]+" column.");
	                type.showAndWait();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        if(t && types[numberOfColumns-1]!=null){
	            try{
	                dataFrame = new DataFrame(file.getPath(),types,names);
	                hashMap.put("dataframe",dataFrame);
	            }
	            catch (Exception e){
	                errorDisplayGroupby(e, "Error while creating dataframe");
	                return;
	            }
	            fileInfo.setText("Loaded file: "+file.getName());
	            fileInfo.setDisable(false);
	            info1.setDisable(false);
	            groupBy.setDisable(false);
	            max.setDisable(false);
	            min.setDisable(false);
	            std.setDisable(false);
	            sum.setDisable(false);
	            var.setDisable(false);
	            for (String str:names){
	                MenuItem add = new MenuItem(str);
	                /*add.setOnAction(new EventHandler<ActionEvent>() {
	                    public void handle(ActionEvent t) {
	                                        stats(str);
	                                    }});
	                stat.getItems().add(add);*/
	            }
	            one_width=spane.getWidth();
	        }
	    }
	    void setStage(Stage stage) {
	        myStage = stage;
	        System.out.println(myStage);
	        System.out.println(concatArray(new String[]{}));
	    }
	    String concatArray(String[] array){
	        StringBuilder stringBuilder = new StringBuilder();
	        for (String str: array) stringBuilder.append(str);
	        if (stringBuilder.toString().length()==0) {
	            System.out.println("Empty");return null;}
	        return stringBuilder.toString();
	    }
	    private void errorDisplayGroupby(Exception e, String s) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error Dialog");
	        alert.setHeaderText(s);
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
	    }
}
