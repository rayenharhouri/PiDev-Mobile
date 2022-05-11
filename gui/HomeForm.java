/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("e_laab");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));

       Button btnAddQuiz = new Button("Add quiz");
        Button btnListquiz = new Button("List quiz");

       // Button Map = new Button("map");
       /// Button Stats = new Button ("stats");
       
//        btnAddTask.addActionListener(e-> new AddFonctionForm(current).show());
//        btnListTasks.addActionListener(e-> new ListFonctionForm(current).show());
        btnAddQuiz.addActionListener(e-> new AddQuizForm(current).show());
        btnListquiz.addActionListener(e-> new ListquizForm(current).show());
//        btnListOffres.addActionListener(e-> new ListOffresForm(current).show());
//        btnListCultures.addActionListener(e-> new ListCulturesForm(current).show());
//        btnAddOffres.addActionListener(e-> new AddOffreForm(current).show());
//        btnAddCultures.addActionListener(e-> new AddCultureForm(current).show());
//        btnListCoin.addActionListener(e-> new afficherCoins(current).show());
//        btnListPlats.addActionListener(e-> new afficherPlats(current).show());
//        btnAjouterCoin.addActionListener(e-> new ajouterCoin(current).show());
//        btnAjouterPlats.addActionListener(e-> new ajouterPlat(current).show());
       // Map.addActionListener(e-> new MapForm());
        addAll(btnListquiz,btnAddQuiz);
        
        
    }
    
    
}
