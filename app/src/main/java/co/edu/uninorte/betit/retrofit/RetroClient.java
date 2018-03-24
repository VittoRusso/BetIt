package co.edu.uninorte.betit.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vitto on 14/03/2018.
 */

public class RetroClient {

    //Este es la primera parte del URL en dado caso que la extension final de la URL se puede cambiar para obtener diferentes JSON
    //Por lo pronto este es el ROOT
    private static final String ROOT_URL = "https://raw.githubusercontent.com/lsv/fifa-worldcup-2018/";

    //Esto crea una instancia de la clase que proviene de la libreria Retrofit a la cual se le entrega el ROOT y el conversor.
    //En este caso el conversor es de JSON a GSON
    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    //A la instancia se le crea la API que se creo en ApiService.java la cual tiene el GET de la extension.
    public static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);    }
}
