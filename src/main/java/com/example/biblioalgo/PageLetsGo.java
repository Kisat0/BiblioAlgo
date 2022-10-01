package com.example.biblioalgo;

import com.example.Bibliothèque.Livres;
import com.example.Utils.Convertion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PageLetsGo implements Initializable {
    public AnchorPane pageLetsGo;

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

    ////////
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // BINAIRE
        btnValiderconv.setOnMouseClicked(btnvalue -> {
            String decimal = Decimal.getText();
            String binaire = Binaire.getText();

            if (Livres.isNumeric(decimal) && binaire.isEmpty()) {
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
                else if (Livres.isNumeric(binaire) && decimal.isEmpty()){
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

            if (Livres.isNumeric(deci) && hexadeci.isEmpty()) {
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
            } else if (deci.isEmpty()) {
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
                int calculIMC = Integer.parseInt(getpoids) / (Integer.parseInt(gettaille) / 10);
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
                ResultIMC.setText("Votre IMC est de : " + String.valueOf(calculIMC) + "Vous êtes considéré comme " + etat);
            }
            else{
                //TODO error
            }
        });
        // FIN IMC



    }
}