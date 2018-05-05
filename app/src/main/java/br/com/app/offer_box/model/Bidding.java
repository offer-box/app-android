package br.com.app.offer_box.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class Bidding {

    int id;
    long lat;
    long lng;
    String product;
    String qtd;
    List<Tag> tags;

    public Bidding(String product, String qtd, long lat, long lng, List<Tag> tags) {
        this.lat = lat;
        this.lng = lng;
        this.product = product;
        this.qtd = qtd;
        this.tags = tags;
    }

    public Bidding(int id, String product, String qtd, long lat, long lng, List<Tag> tags) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.product = product;
        this.qtd = qtd;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bidding() {

    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQtd() {
        return qtd;
    }

    public void setQtd(String qtd) {
        this.qtd = qtd;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
