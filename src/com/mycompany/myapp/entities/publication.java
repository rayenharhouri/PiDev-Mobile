/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author RiNo
 */
import java.util.Date;

public class publication {

    private int id_pub;
    private int reactions;
    private Date date_pub;
    private int nbre_commentaire;
    private String topic;
    private int id_u;

    //default constructor
    public publication() {
    }

    //constuctor with params without ID_pub&user
    public publication(int reactions, int nbre_commentaire, String topic) {
        this.reactions = reactions;
        this.nbre_commentaire = nbre_commentaire;
        this.topic = topic;
    }

    public publication(int id_u, String topic) {
        
        this.id_u = id_u;
        this.topic = topic;
    }

    public publication(int id_pub, int reactions, Date date_pub, int nbre_commentaire, String topic, int id_u) {
        this.id_pub = id_pub;
        this.reactions = reactions;
        this.date_pub = date_pub;
        this.nbre_commentaire = nbre_commentaire;
        this.topic = topic;
        this.id_u = id_u;
    }

    //getters and setters
    public int getId_pub() {
        return this.id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public int getReactions() {
        return this.reactions;
    }

    public void setReactions(int reactions) {
        this.reactions = reactions;
    }

    public int getNbre_commentaire() {
        return this.nbre_commentaire;
    }

    public void setNbre_commentaire(int nbre_commentaire) {
        this.nbre_commentaire = nbre_commentaire;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getId_u() {
        return this.id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    //toString
    @Override
    public String toString() {
        return "publication{" + "\nid_pub= " + id_pub + "\nreactions= " + reactions + "\ndate_pub= " + date_pub + "\nnbre_commentaire=" + nbre_commentaire + "\ntopic= " + topic + "\nid_u= " + id_u + "}\n";
    }

}
