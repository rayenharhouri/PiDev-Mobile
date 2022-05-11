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
import com.mycompany.myapp.entities.publication;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author bhk
 */
public class ServicePublication {

    public static ServicePublication instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;
    private boolean resultat;

    public ArrayList<publication> Publications;

    public static ServicePublication getInstance() {
        if (instance == null) {
            instance = new ServicePublication();
        }
        return instance;
    }

    public ServicePublication() {
        req = new ConnectionRequest();
    }

    public void addPub(publication publication) {
        int id_u = 1;
        String url = Statics.BASE_URL + "ADDPUBJSON/new?topic=" + publication.getTopic() + "&id_u=" + id_u;
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean deletePub(int id) {
        String url = Statics.BASE_URL + "deletePubJson/" + id;

        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultat = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultat;
    }

        public void editPub(publication publication) {
        String url = Statics.BASE_URL + "updatePubJson/"+publication.getId_pub()+"?topic=" + publication.getTopic() + "&id_u="+ 1;
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<publication> parseTasks(String jsonText){
        try {
            Publications =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                publication t = new publication();
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_pub((int)id);
                
                t.setTopic(obj.get("topic").toString());
           
                Publications.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Publications;
    } 
    
    
    
    public ArrayList<publication> getAllTasks(){
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"PubJson";
        System.out.println("===>"+url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Publications = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Publications;
    }
    
    
    
      public ArrayList<publication> findPubs(String search) {
        ArrayList<publication> result = new ArrayList<>();

        String url = Statics.BASE_URL + "mobile/findPub?topic="+search;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapPublications = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPublications.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        publication pub = new publication();

                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());

                        String titre = obj.get("topic").toString();
                        //String description = obj.get("topic").toString();
                        //String image = obj.get("imagePubl").toString();

                        pub.setId_pub((int) id);
                        pub.setTopic(titre);
                      

                      

                        //insert data into ArrayList result
                        result.add(pub);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }
}
