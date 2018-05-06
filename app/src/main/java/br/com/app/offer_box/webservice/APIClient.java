package br.com.app.offer_box.webservice;

import br.com.app.offer_box.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getServiceNode() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL_NODE).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }

    public static Retrofit getServiceJava() {

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL_JAVA).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}