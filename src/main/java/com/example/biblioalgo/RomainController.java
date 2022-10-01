package com.example.biblioalgo;

import java.util.Locale;

import com.example.Bibliothèque.Livres;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class RomainController implements Initializable{

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String decimal = valeurHexaRomain.getText();
        validerRomain.setOnMouseClicked(btnaction->{

            System.out.println("a"+decimal+"a");
            if(Livres.isNumeric(decimal)){
                valeurRomain.setText(arab2roman(Integer.parseInt(decimal)));
            } else {
                System.out.println("a"+decimal+"a");
                valeurHexaRomain.setText(String.valueOf(roman2arab(valeurRomain.getText())));
            }
        });
    }
}
