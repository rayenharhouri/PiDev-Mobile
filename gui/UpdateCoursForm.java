///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.myapp.gui;
//
//import com.codename1.ui.Button;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.TextField;
//import com.codename1.ui.layouts.BoxLayout;
//import com.mycompany.myapp.entities.Cours;
//import com.mycompany.myapp.services.ServiceCours;
//
///**
// *
// * @author dell
// */
//public class UpdateCoursForm extends Form{ 
//    public UpdateCoursForm(Form previous,Cours c) {
//        setTitle("modifier cours");
//        setLayout(BoxLayout.y());
//        
//        TextField tfNom = new TextField(c.getNom_cours(),"nom du cours");
//        TextField tfContenu= new TextField(c.getContenu_cours(), "contenu du cours");
//        TextField tfPages= new TextField(String.valueOf(c.getNb_pages()), "Pages");
//          TextField tfChapitres= new TextField(String.valueOf(c.getNb_chapitres()), "chapitres");
//        Button btnValider = new Button("modifier Cours");
//        
//        
//        btnValider.addActionListener(e -> {
//         c.setNom_cours(tfNom.getText());
//         c.setContenu_cours(tfContenu.getText());
//         c.setNb_pages(Integer.parseInt(tfPages.getText()));
//            c.setNb_chapitres(Integer.parseInt(tfChapitres.getText()));
//         if (ServiceCours.getInstance().modifierCours(c)){
//           System.out.println(c.getId_cours());
//           new ListCoursForm(previous).show();}
//       
//        });
//        
//        addAll(tfNom,tfContenu,tfPages,tfChapitres,btnValider);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
//                
//    }
//    
//}
