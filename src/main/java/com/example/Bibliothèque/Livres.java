package com.example.Bibliothèque;

public class Livres  {
    String nom;
    String auteur;
    String resume;
    String colonne;
    String rangee;
    String parution;


    public String getColonne() {
        return colonne;
    }
    public String getRangee() {
        return rangee;
    }

    public Livres(String nomAdd, String auteurAdd, String resumeAdd, String colonneAdd, String rangeeAdd, String parutionAdd){
        this.nom = nomAdd;
        this.auteur = auteurAdd;
        this.resume = resumeAdd;
        this.colonne = colonneAdd;
        this.rangee = rangeeAdd;
        this.parution = parutionAdd;
    }

    public static boolean isNumeric(String a)
    {
        try
        {
            Integer.parseInt(a);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean verifColonne(String colonne) {
        try {
            int col = Integer.parseInt(colonne);
            if (col >= 1 && col <= 5) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean verifRangee(String rangee) {
        try {
            int rang = Integer.parseInt(rangee);
            if (rang >= 1 && rang <= 7) {
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Livres{" +
                "nom='" + nom + '\'' +
                ", auteur='" + auteur + '\'' +
                ", resume='" + resume + '\'' +
                ", colonne='" + colonne + '\'' +
                ", rangee='" + rangee + '\'' +
                ", parution='" + parution + '\'' +
                '}';
    }
}
