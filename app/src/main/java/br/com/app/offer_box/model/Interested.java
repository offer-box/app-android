package br.com.app.offer_box.model;

/**
 * Created by matheuscatossi on 06/05/18.
 */

public class Interested {

    String id;
    String interested;

    public Interested(String id, String interested) {
        this.interested = interested;
        this.id = id;
    }

    public Interested() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterested() {
        return interested;
    }

    public void setInterested(String interested) {
        this.interested = interested;
    }
}
