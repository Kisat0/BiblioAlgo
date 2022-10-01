package com.example.biblioalgo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.soldats.Generaux;
import com.example.soldats.Humains;
import com.example.soldats.Soldats;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

public class SoldatController implements Initializable {

    @FXML
    private VBox formusoldat;
    @FXML
    private TextField grades;
    @FXML
    private VBox gradesold;
    @FXML
    private TextField hps;
    @FXML
    private VBox hpsold;
    @FXML
    private TextField namegs;
    @FXML
    private VBox nbsoldat;
    @FXML
    private TextField nbsoldg;
    @FXML
    private TreeView<Humains> treeView;
    @FXML
    private Button validates;

    public static Generaux AddGeneral() {
        Generaux generalAdd = new Generaux("Bobby", "5");
        return generalAdd;
    }
    
    public static Soldats AddSoldat() {
       Soldats soldatAdd = new Soldats("Timothé", "5","poteau");
        return soldatAdd;
    }

    TreeItem<Humains> armee = new TreeItem<Humains>(new Humains());
    TreeItem<Humains> general = new TreeItem<Humains>(AddGeneral());
    TreeItem<Humains> soldat = new TreeItem<Humains>(AddSoldat());


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        treeView.setRoot(armee);
        formusoldat.setVisible(false);

        treeView.setOnMouseClicked(action -> {

        
            if (treeView.getSelectionModel().getSelectedItem() == armee) {
                armee.getChildren().add(general);
                formusoldat.getChildren().removeAll(hpsold, gradesold);
                formusoldat.getChildren().addAll(nbsoldat);
            } else if (treeView.getSelectionModel().getSelectedItem() == general) {
                general.getChildren().add(soldat);
                formusoldat.getChildren().removeAll(nbsoldat);
                formusoldat.getChildren().addAll(hpsold, gradesold);
            }
        });

    }

}