package br.com.app.offer_box.model;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class OfferProduct {

    int id;
    double price_unit;
    int id_company;
    String name_company;
    int id_bidding;
    String product;
    String distance;
    int qtd_offers_company;
    String type_company;

    public OfferProduct(int id, double price_unit, int id_company, String name_company, int id_bidding, String product, String distance, int qtd_offers_company, String type_company) {
        this.id = id;
        this.price_unit = price_unit;
        this.id_company = id_company;
        this.id_bidding = id_bidding;
        this.distance = distance;
        this.distance = distance;
        this.qtd_offers_company = qtd_offers_company;
        this.type_company = type_company;
        this.name_company = name_company;
        this.product = product;
    }

    public OfferProduct(double price_unit, int id_company, String name_company, int id_bidding, String product, String distance, int qtd_offers_company, String type_company) {
        this.price_unit = price_unit;
        this.id_company = id_company;
        this.id_bidding = id_bidding;
        this.distance = distance;
        this.distance = distance;
        this.qtd_offers_company = qtd_offers_company;
        this.type_company = type_company;
        this.name_company = name_company;
        this.product = product;
    }

    public OfferProduct() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(double price_unit) {
        this.price_unit = price_unit;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public int getId_bidding() {
        return id_bidding;
    }

    public void setId_bidding(int id_bidding) {
        this.id_bidding = id_bidding;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getQtd_offers_company() {
        return qtd_offers_company;
    }

    public void setQtd_offers_company(int qtd_offers_company) {
        this.qtd_offers_company = qtd_offers_company;
    }

    public String getType_company() {
        return type_company;
    }

    public void setType_company(String type_company) {
        this.type_company = type_company;
    }

    public String getName_company() {
        return name_company;
    }

    public void setName_company(String name_company) {
        this.name_company = name_company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
