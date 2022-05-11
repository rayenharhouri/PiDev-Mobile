/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.quizs;
import com.mycompany.myapp.services.Servicequiz;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListquizForm extends Form {
   Resources theme;
    public ListquizForm(Form previous) {
       theme = UIManager.initFirstTheme("/theme");
        setTitle("List quizs");
//        TextField tfPrenom= new TextField("", "recherche");
//         Button rechercher = new Button ("rechercher");
//         add(tfPrenom);
//         add(rechercher);
        ArrayList<quizs> list =Servicequiz.getInstance().getAllquizl();
        for(quizs q : list) {
        setLayout(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.x());
        Container cnt2 = new Container(BoxLayout.y());
//        Container cnt3 = new Container(new FlowLayout(CENTER,CENTER));
//        ImageViewer img = new ImageViewer(theme.getImage(pers.getPhoto()).scaled(150, 150));
//        cnt3.add(img);
        Label id = new Label("id : " + String.valueOf(q.getId_Quizs()));
        Label Matiere = new Label ("Matiere : " + q.getMatiere());
        Label Difficulte = new Label ("difficulte : " + q.getDifficulte()); 
        Label Resultat = new Label ("Resultat : " + q.getResultat()); 

         //Label photo = new Label ("photo : " + pers.getPhoto()); 
       cnt2.addAll(id,Matiere,Difficulte,Resultat);
       Button modifier = new Button ("modifier");
       Button supprimer = new Button ("supprimer");
       cnt1.addAll(cnt2,/*cnt3*/modifier,supprimer);
        add(cnt1);
       supprimer.addActionListener(e->{
       Dialog dig = new Dialog("supression");
       if (dig.show("suppression","Etes vous sûr de vouloir supprimer ce quiz ?","annuler","oui"))
            dig.dispose();
       else {
       dig.dispose();
       if (Servicequiz.getInstance().supprimerquiz(q.getId_Quizs())){
           System.out.println(q.getId_Quizs());
           new ListquizForm(previous).show();}
       }
       }); 
       
//       modifier.addActionListener(m-> {
//       new ModifierquizForm(previous, pers).show();
//       });
            
                
                
            
       
       }
//          rechercher.addActionListener(m-> {
//       new RechercherquizForm(previous, tfPrenom.getText().toString()).show();
//       });
       /* SpanLabel sp = new SpanLabel();
        sp.setText(Servicequiz.getInstance().getAllquizs().toString());
        add(sp); */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
