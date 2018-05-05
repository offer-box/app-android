package br.com.app.offer_box.webservice;

import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.utils.Constants;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public interface APIInterface {

    @POST(Constants.POST_ENV_BIDDING)
    Call<String> envBidding(@Body Bidding bidding);

}
