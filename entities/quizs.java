/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author ABDELAZIZ
 */
public class quizs {

    //attributs
    private int id_Quizs;
     private int id_question;
   private String image;
   private String matiere;
   private String difficulte;
  
   private int resultat;
   

    

    public quizs(int id_Quizs, String image, String matiere, String difficulte) {
        this.id_Quizs = id_Quizs;
        this.image = image;
        this.matiere = matiere;
        this.difficulte = difficulte;
    }

    public quizs(int id_Quizs, String image, String matiere, String difficulte, int resultat) {
        this.id_Quizs = id_Quizs;
        this.image = image;
        this.matiere = matiere;
        this.difficulte = difficulte;
        this.resultat = resultat;
    }
    
    public quizs(int i, int i0){
        
    }
    
    public quizs() {
    }

    public quizs(String image, String matiere, String difficulte, int resultat) {
        this.image = image;
        this.matiere = matiere;
        this.difficulte = difficulte;
        this.resultat = resultat;
    }

    

    public int getId_Quizs() {
        return id_Quizs;
    }

    public void setId_Quizs(int id_Quizs) {
        this.id_Quizs = id_Quizs;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    @Override
    public String toString() {
        return "quiz{" + id_Quizs + ", image=" + image + ", matiere=" + matiere + ", difficulte=" + difficulte + ", resultat=" + resultat + '}';
    }

    

    
    

}
