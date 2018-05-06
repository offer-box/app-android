package br.com.app.offer_box.webservice;

import java.util.ArrayList;

import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.model.Interested;
import br.com.app.offer_box.model.OfferProduct;
import br.com.app.offer_box.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public interface APIInterface {
    @POST(Constants.POST_ENV_BIDDING)
    Call<String> envBidding(@Body Bidding bidding);

    @POST(Constants.POST_UPDATE_OFFER)
    Call<String> updateOffer(@Body Interested interested);

    @GET(Constants.GET_INFO_OFFER)
    Call<OfferProduct> getInfoOffer(@Path("id_offer") String id_offer);

    @GET(Constants.GET_LIST_OFFER)
    Call<ArrayList<OfferProduct>> getListOffer(@Path("product") String product);

    @GET(Constants.GET_LIST_BIDDING)
    Call<ArrayList<Bidding>> getListBidding();

    @GET(Constants.GET_LIST_TAG)
    Call<ArrayList<String>> getListTag();

}
