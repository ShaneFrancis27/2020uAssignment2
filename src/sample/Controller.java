package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import java.util.*;

import java.io.File;



public class Controller {

//    @FXML
//    private Button download = new Button();
//
//    @FXML
//    private Button upload = new Button();

    @FXML
    private ListView<String> listView;

    //File file = new File("C:/Users/Shane/Desktop/Assignment2SharedFile");
    private ObservableList<String> LocalItems = FXCollections.observableArrayList();



    public void ShowDIR() {

        //making a file object for the shared folder directory
        File file = new File("/2020uAssignment2/Assignment2SharedFile");

        //parse each file inside the directory
        if (file.isDirectory()) {

            //loops through all the files in the folder
            for (File current : Objects.requireNonNull(file.listFiles())) {

                //if its a file, it adds the file to the observable list
                if (current.isFile()) {
                    LocalItems.add(current.getName());
                } else {
                    System.out.format("Directory name : %s%n", current.getName());
                }
            }
        }

        //the files in the observable list from the loop will the presented in the listView
        listView.setItems(LocalItems);

   }

}
