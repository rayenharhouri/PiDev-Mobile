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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.commentaire;
import com.mycompany.myapp.entities.publication;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Abdelaziz
 */
public class ServiceCommentaire {
    public ArrayList<commentaire> commentaires;
    
    public static ServiceCommentaire instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCommentaire() {
         req = new ConnectionRequest();
    }

    public static ServiceCommentaire getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }
    
    
    public ArrayList<commentaire> parseTasks(String jsonText){
        try {
            commentaires =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                commentaire t = new commentaire();
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_commentaire((int)id);
                t.setCommentaire(obj.get("comment").toString());
                
                 
                commentaires.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return commentaires;
    } 
    

    
    public ArrayList<commentaire> getAllCommentaires(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"/ComJson";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(commentaires.toString());
        return commentaires;
    }
    
        public boolean DeleteProduct(int id) {
         
       String url = Statics.BASE_URL + "/deleteComJson/"+id ;
    
       req.setUrl(url);
       req.setPost(false); 
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
    

    public void addComment(commentaire commentaire, publication publication) {
//id mtaa user khallitou statique lyn tsir l'intégration
        int userId = 1;
        String url = Statics.BASE_URL + "ADDComJSON/new/" + publication.getId_pub()+ "?comment=" + commentaire.getCommentaire()+ "&id_u=" + userId;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
   public boolean deleteComment(int id) {
        String url = Statics.BASE_URL + "mobile/deleteComment?id=" + id;

        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
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
   
   
    public boolean editPub(commentaire commentaire) {
        String url = Statics.BASE_URL + "updateComJson/?id_c=" + commentaire.getId_commentaire()+ "&comment=" + commentaire.getCommentaire();
        req.setUrl(url); //Insert url in connection request

        req.setFailSilently(true);
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
    


    public ArrayList<commentaire> showComments() {
        ArrayList<commentaire> result = new ArrayList<>();

        String url = Statics.BASE_URL + "ComJson";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapReclamations.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        commentaire re = new commentaire();

                        float id = Float.parseFloat(obj.get("id").toString());

                        String description = obj.get("comment").toString();
                        
                        re.setId((int) id);
                        re.setCommentaire(description);

                        result.add(re);
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

    
    
    
    public ArrayList<commentaire> getComment(publication publication){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"mobile/showPubComment?publciation="+publication.getId_pub();
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         System.out.println(commentaires.toString());
        return commentaires;
    }
    
    
    
    
}
