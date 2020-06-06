package com.example.catsgallery;

import android.widget.ImageView;

import java.util.ArrayList;
/*
* Classe que armazena os links e ids das fotos
*
* */
class CreateList {

    //private String image_title;
    private String image_id;
    //private ImageView photo;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /*public String getImage_title() {
            return image_title;
        }

        public void setImage_title(String image_title) {
            this.image_title = image_title;
        }
    */
    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

   /* public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }*/
}
