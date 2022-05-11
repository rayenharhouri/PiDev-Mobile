/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.quizs;

import com.mycompany.myapp.services.Servicequiz;


/**
 *
 * @author dell
 */
public class AddQuizForm extends Form{
     public AddQuizForm(Form previous) {
        setTitle("Ajouter nouveau quiz");
        setLayout(BoxLayout.y());
        
        TextField tfImage = new TextField("","apercu du quiz");
        TextField tfMatiere= new TextField("", "matiere du quiz");
        TextField tfDifficulte= new TextField("", "difficulte du quiz");
        TextField tfResultat= new TextField("", "resultat du quiz");
        Button btnValider = new Button("Ajouter quiz");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfImage.getText().length()==0)||(tfMatiere.getText().length()==0)||(tfDifficulte.getText().length()==0)||(tfResultat.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        quizs q = new quizs(tfImage.getText() ,tfMatiere.getText(), tfDifficulte.getText(),Integer.parseInt(tfResultat.getText()));
                        if( Servicequiz.getInstance().addquiz(q)==true)
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfImage,tfMatiere,tfDifficulte,tfResultat,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
