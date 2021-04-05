package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));


        //DIR : the shared folder's files will be shown to the user at startup
        Controller controller = new Controller();
        controller.ShowDIR();

        //Client Object
        Client client = new Client();

        //Making the ListView to Display the Files in local and Shared Folders
        ListView<String> LocalItems = null;
        ListView<String> SharedItems = null;

        //Making the ObservableList to Display the Files in local and Shared Folders
        ObservableList<String> ServerFiles = FXCollections.observableArrayList();

        //Making The Upload and Download Buttons
        Button download = new Button("Download");
        Button upload = new Button("Upload");

        //File Object
        File file = new File("/2020uAssignment2/Assignment2SharedFile");

        //Download Button Action When Pressed
        download.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                client.ServerConnect();
                String DownloadFile = SharedItems.getSelectionModel().getSelectedItem();
                String message = null;

                try{

                    message = client.networkIn.readLine();
                    String temp[] = message.split("/");

                    File output = new File(file + DownloadFile);
                    FileWriter fileWriter = new FileWriter(output);

                    for(int i=0; i < temp.length; i++){
                        fileWriter.append(temp[i] + "\n");
                    }
                    fileWriter.flush();
                    fileWriter.close();

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });



        //Upload Button Action When Pressed
        upload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Scanner fileScanner = new Scanner(file);
                    while (fileScanner.hasNextLine()) {
                        String temp = fileScanner.nextLine();
                        client.networkOut.println(temp);
                    }

                    client.networkOut.println("Upload Complete");
                    fileScanner.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                ServerFiles.clear();

            }
        });


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
