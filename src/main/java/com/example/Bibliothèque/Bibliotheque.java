package com.example.Bibliothèque;

import java.util.ArrayList;

public class Bibliotheque  {

   private ArrayList<Livres> allLivres = new ArrayList<Livres>();
   public Bibliotheque (){

   }
   public ArrayList<Livres> getAllLivres(){
      return allLivres;
   }

   public void setAllLivres(ArrayList<Livres> myList){
      this.allLivres = myList;
   }

   @Override
   public String toString() {
      return "Bibliotheque{" +
              "allLivres=" + allLivres +
              '}';
   }
}
