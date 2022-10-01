package com.example.biblioalgo;


import com.example.Bibliothèque.Bibliotheque;
import com.example.Bibliothèque.Livres;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    Bibliotheque maBiblio = new Bibliotheque();
    ArrayList<Livres> maListe = maBiblio.getAllLivres();

    @FXML
    private Label welcomeText;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    private Label titreDuFormulaire;
    @FXML
    private Label informations;
    @FXML
    private TextField nomform;
    @FXML
    private TextField auteurform;
    @FXML
    private TextField parutionform;
    @FXML
    private TextField colonneform;
    @FXML
    private TextField rangeeform;
    @FXML
    private TextArea resumeform;
    @FXML
    private TextField urlform;

    public boolean isStringInt(String s){
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }


    @FXML
    protected void validateAction() {
        boolean positionExisteDeja = false;
        boolean parutionIsCorrect = false;
        boolean colonneIsCorrect = false;
        boolean rangeeIsCorrect = false;
        boolean resumeIsCorrect = false;


        String nomRegex = nomform.getText().trim();
        String auteurRegex = auteurform.getText().trim();
        String parutionRegex = parutionform.getText().replaceAll("\\s+","");
        String colonneRegex = colonneform.getText().trim();
        String rangeeRegex = rangeeform.getText().trim();
        String resumeNoRegex = resumeform.getText().trim();

        informations.setText("");
        if(nomRegex == ""){
            informations.setText("NOP indique moi le nom\n");
        }
        if(auteurRegex == ""){
            informations.setText(informations.getText()+"NOP indique moi l'auteur\n");
        }
        //
        if(parutionRegex==""){
            informations.setText(informations.getText()+"NOP indique moi la date de parution\n");
        }
        // parution int     // parution < 2022
        else if(isStringInt(parutionRegex) == false){
            informations.setText(informations.getText()+"La Date de Parution doit être un nb inférieur à 2022\n");
        }
        else if(isStringInt(parutionRegex) == true){
            int test = Integer.parseInt(parutionRegex);
            if(test < 2022){
                parutionIsCorrect = true;
            }
            else{
                informations.setText(informations.getText()+"La Date de Parution doit être un nb inférieur à 2022\n");
            }
        }
        //
        if(colonneRegex==""){
            informations.setText(informations.getText()+"NOP indique moi la Colonne\n");
        }
        // colonne >0  et  <7
        else if(isStringInt(colonneRegex) == false){
            informations.setText(informations.getText()+"La Colonne doit être entre 1 et 6\n");
        }
        else if(isStringInt(colonneRegex) == true){
            int test = Integer.parseInt(colonneRegex);
            if(test < 7 && test >0){
                colonneIsCorrect = true;
            }
            else{
                informations.setText(informations.getText()+"La Colonne doit être entre 1 et 6\n");
            }
        }
        //
        if(rangeeRegex==""){
            informations.setText(informations.getText()+"NOP indique moi la Rangée\n");
        }
        // Rangee >0  et <8
        else if(isStringInt(rangeeRegex) == false){
            informations.setText(informations.getText()+"La Rangée doit être entre 1 et 7\n");
        }
        else if(isStringInt(rangeeRegex) == true){
            int test = Integer.parseInt(rangeeRegex);
            if(test < 8 && test >0){
                rangeeIsCorrect = true;
            }
            else{
                informations.setText(informations.getText()+"La Rangée doit être entre 1 et 7\n");
            }
        }
        // Verif position deja prise
        for(int i=0;i<maListe.toArray().length;i++){
            String col = maListe.get(i).getColonne();
            String rang = maListe.get(i).getRangee();

            if((colonneRegex.equals(col)) && (rangeeRegex.equals(rang))){
                positionExisteDeja = true;
                informations.setText(informations.getText()+"POSITION DEJA PRISE !! Change de rangée ou de colonne\n");
            }
        }
        // Fin VERIF

        if(resumeNoRegex.length()<15){
            informations.setText(informations.getText()+"NOP indique moi un résumé d'au moins 15char\n");
        }
        // Resumé superieur a 15 char
        else{
            resumeIsCorrect = true;
        }
        if(urlform.getText()==""){
            informations.setText(informations.getText()+"NOP indique moi un URL\n");
        }


        if( nomRegex!="" && auteurRegex!="" && parutionRegex!="" && (parutionIsCorrect == true) && colonneRegex!="" && (colonneIsCorrect == true)
                && rangeeRegex!="" && (rangeeIsCorrect == true) && resumeIsCorrect==true && urlform.getText()!=""
        && positionExisteDeja == false){

            informations.setText("Nom : "+nomRegex +
                    "\nAuteur : "+ auteurRegex +
                    "\nParution : "+ parutionRegex +
                    "\nColonne : "+ colonneRegex +
                    "\nRangée : "+ rangeeRegex +
                    "\nResumé : "+ resumeNoRegex +
                    "\nUrl : "+ urlform.getText()
            );

            Livres livre = new Livres(nomRegex,auteurRegex,resumeNoRegex,colonneRegex,rangeeRegex,parutionRegex);
            maListe.add(livre);
            //System.out.println(maListe);
            maBiblio.setAllLivres(maListe);

            System.out.println(maListe);
            maListe.get(0).getColonne();
            // Parcourir Ma liste
            /*
            for(int i=0;i<maListe.toArray().length;i++){
                System.out.println("\nLIVRE num : "+i);
                System.out.println(maListe.get(i));
                System.out.println("Sa Colonne : "+maListe.get(i).getColonne());
                System.out.println("Sa Rangée : "+maListe.get(i).getRangee());
            }*/

            //Clear le form
            nomform.clear();
            auteurform.clear();
            parutionform.clear();
            colonneform.clear();
            rangeeform.clear();
            resumeform.clear();
            urlform.clear();
            titreDuFormulaire.setText("Validé");
        }
    }

    @FXML
    protected void supprimerFormulaire() {
        titreDuFormulaire.setText("SALUT!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}