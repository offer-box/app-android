package br.com.app.offer_box.utils;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class Constants {

    public static final String TAG = "STATUS_TAG";
    public static final String MESSAGE = "STATUS_MESSAGE";
    public static final String STATUSCONNECTION = "STATUS_CONNECTION";

    public static final String BASE_URL_JAVA = "https://offer-box.mybluemix.net/api/offer-box/";
    public static final String BASE_URL_NODE = "https://hacka-compras-publicas.herokuapp.com/";

    public static final String POST_ENV_BIDDING = "save_bidding";
    public static final String GET_INFO_OFFER = "list_offers_by_id/{id_offer}";
    public static final String GET_LIST_OFFER = "list_offers_by_product/{product}";
    public static final String GET_LIST_BIDDING = "list_bidding_by_product/all";
    public static final String POST_UPDATE_OFFER = "update_interested_offer";

    public static final String GET_LIST_TAG = "list-tags";
}
