/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.quizs;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dell
 */
public class Servicequiz {
    
    
    public ArrayList<quizs> quizl;
    
    public static Servicequiz instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private Servicequiz() {
         req = new ConnectionRequest();
    }

    public static Servicequiz getInstance() {
        if (instance == null) {
            instance = new Servicequiz();
        }
        return instance;
    }
    
    
    public boolean addquiz(quizs q) {
        System.out.println(q);
     
       String url = Statics.BASE_URL + "quizs/addQuizsJSON/new?image="  + q.getImage()+ "&matiere=" + q.getMatiere()+ "&difficulte=" + q.getDifficulte()+ "&resultat=" + q.getResultat();
        System.out.println(url);
    
       req.setUrl(url);
       
       req.addArgument("image", q.getImage());
       req.addArgument("matiere", q.getMatiere()+"");
       req.addArgument("difficulte", q.getDifficulte()+"");
       req.addArgument("resultat ", q.getResultat()+"");
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

     public ArrayList<quizs> parsequizl(String jsonText){
        try {
            quizl=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> quizlListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)quizlListJson.get("root");
           
            for(Map<String,Object> obj : list){
                quizs q = new quizs();
                float id = Float.parseFloat(obj.get("id_Quizs").toString());
                q.setId_Quizs((int)id);
                q.setImage(obj.get("image").toString());
                q.setMatiere(obj.get("matiere").toString());
                q.setDifficulte(obj.get("difficulte").toString());
                q.setResultat((int)Float.parseFloat(obj.get("resultat").toString()));

                    
                quizl.add(q);
            }
            
            
        } catch (IOException ex) {
            
        }
        return quizl;
    }
    
    public ArrayList<quizs> getAllquizl(){
        //String url = Statics.BASE_URL+"/quizl/";
        String url = Statics.BASE_URL+"quizs/listQuizsJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizl = parsequizl(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return quizl;
    }
    
    
    
    
    
//     public boolean modifierquiz(quizs c) {
//        System.out.println(c);
//   
//       String url = Statics.BASE_URL + "updatequizJSON/" + c.getId_quiz()+ "?nom_quiz=" +c.getNom_quiz()+ "&contenu_quiz=" +c.getContenu_quiz()+ "&nb_pages=" +c.getNb_pages()+ "&nb_chapitres=" +c.getNb_chapitres();
//         System.out.println(url);
//    
//       req.setUrl(url);
//
//       req.addArgument("nom_quiz", c.getNom_quiz());
//       req.addArgument("contenu_quiz", c.getContenu_quiz()+"");
//       req.addArgument("nb_pages", c.getNb_pages()+"");
//       req.addArgument("nb_chapitres ", c.getNb_chapitres()+"");
//       req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//      
//   
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    
// 
//     }
      public boolean supprimerquiz(int id_quiz){
     String url = Statics.BASE_URL+"quizs/deleteQuizsJSON/"+id_quiz;
          System.out.println(url);
     req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
    
    }
     
  
}
