package com.example.biblioalgo;


import com.example.Bibliothèque.Bibliotheque;
import com.example.Bibliothèque.Livres;
import com.example.Utils.Convertion;
import com.example.soldats.Generaux;
import com.example.soldats.Humains;
import com.example.soldats.Soldats;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class mergeController implements Initializable{
    //BINAIRE
    @FXML
    private TextField Binaire;
    @FXML
    private TextField Decimal;
    @FXML
    private Button btnValiderconv;
    // FIN BINAIRE

    // HEXA
    @FXML
    private TextField decimaltext;
    @FXML
    private TextField hexatext;
    @FXML
    private Button validerHexa;
    // FIN HEXA

    // IMC
    @FXML
    private Label ResultIMC;
    @FXML
    private Button btncalcul;
    @FXML
    private TextField poids;
    @FXML
    private TextField taille;
    //FIN IMC

    Bibliotheque maBiblio = new Bibliotheque();
    ArrayList<Livres> maListe = maBiblio.getAllLivres();

    @FXML
    private Label welcomeText;



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



    // VOIRRRRRRRRR
    @FXML
    private VBox montreConversions;
    @FXML
    private VBox montreFormulaire;
    @FXML
    private VBox montrerRomains;
    @FXML
    private VBox montrerSoldats;
    @FXML
    private VBox vue;

    @FXML
    void afficherFormulaire(ActionEvent event) {
        vue.getChildren().removeAll(montreConversions);
        vue.getChildren().removeAll(montrerRomains);
        vue.getChildren().removeAll(montrerSoldats);

        vue.getChildren().addAll(montreFormulaire);
    }
    @FXML
    void afficherConversions(ActionEvent event) {
        vue.getChildren().removeAll(montreFormulaire);
        vue.getChildren().removeAll(montrerRomains);
        vue.getChildren().removeAll(montrerSoldats);

        vue.getChildren().addAll(montreConversions);
    }
    @FXML
    void afficherRomains(ActionEvent event) {
        vue.getChildren().removeAll(montreFormulaire);
        vue.getChildren().removeAll(montreConversions);
        vue.getChildren().removeAll(montrerSoldats);

        vue.getChildren().addAll(montrerRomains);
        System.out.println("ROMAINS!");
    }
    @FXML
    void afficherSoldats(ActionEvent event) {
        vue.getChildren().removeAll(montreFormulaire);
        vue.getChildren().removeAll(montreConversions);
        vue.getChildren().removeAll(montrerRomains);

        vue.getChildren().addAll(montrerSoldats);
        System.out.println("Soldats!");
    }
    // FIN VOIR


    // ROMAIN
    @FXML
    private TextField valeurHexaRomain;

    @FXML
    private TextField valeurRomain;

    @FXML
    private Button validerRomain;

    public static String arab2roman(int arab){
        char[] symbols = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        String roman = "";
        for (int i = symbols.length-1; i>=0; i--){
            int milestone = romanValueOf(symbols[i]);
            while (arab >= milestone){
                arab -= milestone;
                roman = roman + symbols[i];
            }
            if (i > 0){
                int substract = i - ((i+1) % 2);
                substract--;
                if (arab >= milestone - romanValueOf(symbols[substract])){
                    arab -= milestone - romanValueOf(symbols[substract]);
                    roman = roman + symbols[substract] + symbols[i];
                }
            }
        }
        return roman;
    }

    public static int roman2arab(String roman){
        roman = roman.trim().toUpperCase(Locale.ROOT);
        if (isCorrectRoman(roman)) {
            int arab = 0;
            char romanChar;
            char romanChar2;
            boolean isAdded;
            for (int i = 0; i<roman.length(); i++){
                romanChar = roman.charAt(i);
                isAdded = true;
                for (int j = i; j<roman.length(); j++){
                    romanChar2 = roman.charAt(j);
                    if (romanValueOf(romanChar) < romanValueOf(romanChar2)){
                        isAdded = false;
                    }
                }
                if (isAdded){
                    arab += romanValueOf(romanChar);
                } else {
                    arab -= romanValueOf(romanChar);
                }
            }
            if(arab2roman(arab).equals(roman)){
                return arab;
            }
        }
        return 0;
    }

    public static boolean isCorrectRoman(String toTest){
        char test;
        for (int i = 0; i<toTest.length(); i++){
            test = toTest.charAt(i);
            if (test != 'I' && test != 'V' && test != 'X' && test != 'L' && test != 'C' && test != 'D' && test != 'M'){
                return false;
            }
        }
        return  true;
    }

    public static int romanValueOf(char symbol){
        if (symbol == 'I'){
            return 1;
        }if(symbol == 'V'){
            return 5;
        }if(symbol == 'X'){
            return 10;
        }if(symbol == 'L'){
            return 50;
        }if(symbol == 'C'){
            return 100;
        }if(symbol == 'D'){
            return 500;
        }if(symbol == 'M'){
            return 1000;
        }
        return 0;
    }



    // SOLDATSSSSS
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

    // FIN SOLDATSSSSSSSS





    ////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vue.getChildren().removeAll(montreConversions,montreFormulaire,montrerRomains,montrerSoldats);


        // BINAIRE
        btnValiderconv.setOnMouseClicked(btnvalue -> {
            String decimal = Decimal.getText();
            String binaire = Binaire.getText();

            if (Livres.isNumeric(decimal) && Livres.isNumeric(binaire)) {
                int deci = Integer.parseInt(decimal);

                if (deci >= 0) {
                    String nbBin = "";

                    while (deci > 1) {
                        System.out.println("Deci: " + deci);
                        int value = deci % 2;
                        System.out.println("Value: " + value);
                        deci = deci / 2;
                        nbBin += String.valueOf(value);
                    }
                    if (deci == 0 || deci == 1) {
                        nbBin += deci;
                    }
                    Binaire.setText(Convertion.reverse(nbBin));
                }
                else{
                    int nbDeci=0;
                    char[] numberBin = Convertion.reverse(binaire).toCharArray();
                    for (int i = 0; i < numberBin.length; i++) {

                        if (numberBin[i] == 1){
                            nbDeci += Math.pow(2, i);
                        }
                    }
                    Decimal.setText(String.valueOf(nbDeci));
                }
            }
        });
        // FIN BINAIRE


        // HEXA
        validerHexa.setOnMouseClicked(btnaction -> {
            char[] characterHexa = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
            String[] characterDeci = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9","10","11","12","13","14"};
            String deci = decimaltext.getText();
            String hexadeci = hexatext.getText();
            String hexa = "";
            int nbDeci = 0;

            if (Livres.isNumeric(deci)) {
                int decimal = Integer.parseInt(deci);
                for (int i = 6; i>=0; i--) {
                    int puissanceActuelle = (int)Math.pow(16, i);
                    if (decimal >= puissanceActuelle) {
                        int value = decimal / puissanceActuelle;
                        decimal =  decimal % puissanceActuelle;
                        hexa += characterHexa[value];
                    }
                }
                hexatext.setText(String.valueOf(hexa));
            } else {
                int value;
                char[] numberHex = Convertion.reverse(hexadeci).toCharArray();
                for (int i = 0; i < numberHex.length; i++) {
                    if(Convertion.isNotNumeric(numberHex[i])){
                        int index =  new String (characterHexa).indexOf(numberHex[i]);
                        value = Integer.parseInt(characterDeci[index]);
                    }
                    else{
                        value = Character.getNumericValue(numberHex[i]);
                    }
                    nbDeci +=  value * Math.pow(16, i);
                }

                decimaltext.setText(String.valueOf(nbDeci));
            }
        });
        // FIN HEXA

        // IMC
        btncalcul.setOnMouseClicked(btnaction -> {
            String getpoids = poids.getText();
            String gettaille = taille.getText();
            String etat = "";

            if (Livres.isNumeric(getpoids) && Livres.isNumeric(gettaille)) {
                double taille = Double.parseDouble(gettaille) / 100;
                double itmc = Double.parseDouble(getpoids);
                System.out.println(taille);
                double calculIMC = itmc / Math.pow(taille, 2);
                if (calculIMC <= 18.4) {
                    etat = "Maigre";
                } else if (calculIMC > 18.4 && calculIMC <= 24.9) {
                    etat = "Normal";
                } else if (calculIMC >= 25 && calculIMC <= 29.9) {
                    etat = "en Surpois";
                } else if (calculIMC >= 30 && calculIMC <= 34.9) {
                    etat = "en Obésité modérée";
                } else if (calculIMC >= 35 && calculIMC <= 39.9) {
                    etat = "en Obésité sévère";
                } else {
                    etat = "en Obésité MoRbIdE";
                }
                ResultIMC.setText("Votre IMC est de : " + String.valueOf(calculIMC) + "\n" +"Vous êtes considéré comme " + "\n" + etat);
            }
            else{
                //TODO error
            }
        });
        // FIN IMC


        // ROMAIN
        validerRomain.setOnMouseClicked(btnaction->{
            String decimal = valeurHexaRomain.getText();
            if(Livres.isNumeric(decimal)){
                valeurRomain.setText(arab2roman(Integer.parseInt(decimal)));
            } else {
                valeurHexaRomain.setText(String.valueOf(roman2arab(valeurRomain.getText())));
            }
        });



        // SOLDATSSSSS
        treeView.setRoot(armee);
        formusoldat.setVisible(false);

        treeView.setOnMouseClicked(action -> {


            if (treeView.getSelectionModel().getSelectedItem() == armee) {
                armee.getChildren().add(general);
                formusoldat.setVisible(true);
                formusoldat.getChildren().removeAll(hpsold, gradesold);
                formusoldat.getChildren().addAll(nbsoldat);
            } else if (treeView.getSelectionModel().getSelectedItem() == general) {
                general.getChildren().add(soldat);
                formusoldat.setVisible(true);
                formusoldat.getChildren().removeAll(nbsoldat);
                formusoldat.getChildren().addAll(hpsold, gradesold);
            }
        });




    }
    @FXML
    public Button closeButton;

    @FXML
    void quitter(ActionEvent event){
        System.exit(0);
    }

}