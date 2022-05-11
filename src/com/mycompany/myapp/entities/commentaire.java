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
public class commentaire {

    //VAR
    private int id_c;
    private int id_user;
    private int id_pub;
    private String commentaire;

    //constructor
    public commentaire() {
    }

    public commentaire(int id_commentaire, int id_user, int id_pub, String commentaire) {
        this.id_c = id_commentaire;
        this.id_user = id_user;
        this.id_pub = id_pub;
        this.commentaire = commentaire;
    }


    public commentaire(String commentaire, int id_pub, int id_user) {
        this.commentaire = commentaire;
        this.id_pub = id_pub;
        this.id_user = id_user;
    }

    public commentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setCommentaire(String commentaire, int id_pub, int id_user) {
        this.commentaire = commentaire;
        this.id_pub = id_pub;
        this.id_user = id_user;
    }
    
    public void setId( int id) {
        this.id_c = id; 
    }
    public int getId_commentaire() {
        return id_c;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_c = id_commentaire;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    @Override
    public String toString() {
        return "commentaire{" + "\nid_commentaire=" + id_c + "\nid_user=" + id_user + "\nid_pub=" + id_pub + "\ncommentaire=" + commentaire + "\n" + '}';
    }

}
