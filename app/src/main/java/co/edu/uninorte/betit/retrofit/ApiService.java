package co.edu.uninorte.betit.retrofit;

import co.edu.uninorte.betit.model.JsonData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vitto on 14/03/2018.
 */

//En dado caso de tener una URL en la cual hay muchos jsons diferentes aqui se pondria la ultima parte de la URL
public interface ApiService {
    @GET("master/data.json")
    Call<JsonData> getMyJsonData();
}
