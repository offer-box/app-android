package br.com.app.offer_box.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class Bidding {

    int id;
    String lat;
    String lng;
    String product;
    String qtd;
    List<Tag> tags;
    String tagId;

    public Bidding(String product, String qtd, String lat, String lng, List<Tag> tags, String tagId) {
        this.lat = lat;
        this.lng = lng;
        this.product = product;
        this.qtd = qtd;
        this.tags = tags;
        this.tagId = tagId;
    }

    public Bidding(String product, String qtd, String lat, String lng, List<Tag> tags) {
        this.lat = lat;
        this.lng = lng;
        this.product = product;
        this.qtd = qtd;
        this.tags = tags;
    }


    public Bidding(int id, String product, String qtd, String lat, String lng, List<Tag> tags, String tagId) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.product = product;
        this.qtd = qtd;
        this.tags = tags;
        this.tagId = tagId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bidding() {

    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
