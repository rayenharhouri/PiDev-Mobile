/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.commentaire;
import com.mycompany.myapp.entities.publication;
import com.mycompany.myapp.services.ServiceCommentaire;
import java.util.ArrayList;

/**
 *
 * @author louay
 */
public class ShowComments extends SideMenuBaseForm {

    public ShowComments(Resources res, publication publication) {
        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                new Label("Publications", "Title")
                        )
                )
        );

        tb.setTitleComponent(titleCmp);
        Button newComment = new Button("Add new comment");
        newComment.setAlignment(LEFT);
        newComment.addActionListener((l) -> {
            new AddComment(res, publication).show();
        });

        add(new Label("Commentaires", ""));
        add(newComment);

        ArrayList<commentaire> commentaires = ServiceCommentaire.getInstance().showComments();

        for (commentaire co : commentaires) {

            Button editComment = new Button("Edit");
            editComment.getStyle().setBgColor(0xffffff);
            editComment.getStyle().setFgColor(0xFFA500);
            editComment.getStyle().setBgTransparency(255);
            editComment.getStyle().setBorder(Border.createRoundBorder(30, 30));
            editComment.getStyle().setPadding(2, 2, 2, 2);
            editComment.addActionListener((l) -> {
                new EditComment(res, co).show();
            });

            Button deleteComment = new Button("Delete");
            deleteComment.getStyle().setBgColor(0xffffff);
            deleteComment.getStyle().setFgColor(0xFF0000);
            deleteComment.getStyle().setBgTransparency(255);
            deleteComment.getStyle().setBorder(Border.createRoundBorder(30, 30));
            deleteComment.getStyle().setPadding(2, 2, 2, 2);
            deleteComment.addActionListener((l) -> {
                ServiceCommentaire.getInstance().deleteComment(co.getId_commentaire());
                if (ServiceCommentaire.getInstance().deleteComment(co.getId_commentaire())) {
                    Dialog.show("Success", "Comment deleted", "OK", null);
                    new ShowPub(res).show();
                    refreshTheme();
                }
            });

            Label username = new Label("username");
            username.getAllStyles().setFgColor(0xffffff);

            Label commentLabel = new Label(co.getCommentaire());
            commentLabel.getAllStyles().setFgColor(0xffffff);

//            Label date = new Label("Date: " + co.getTemps());

            Container post = BoxLayout.encloseY(commentLabel);

            Container pub = BoxLayout.encloseY(
                    BorderLayout.centerAbsolute(
                            BoxLayout.encloseY(
                                    username
                            )
                    ),//.add(BorderLayout.WEST, pubImage),
                    GridLayout.encloseIn(3, post, editComment, deleteComment)
            );

            pub.getStyle().setFgColor(0xffffff);
            pub.getStyle().setBgColor(0xfdb819);
            pub.getStyle().setBgTransparency(255);
            pub.getStyle().setPadding(7, 7, 7, 7);
            pub.getStyle().setMargin(20, 20, 30, 30);
            pub.getStyle().setBorder(Border.createRoundBorder(50, 50));

            add(pub);
        }
setupSideMenu(res);
    }

    @Override
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }

}
