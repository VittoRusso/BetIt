package co.edu.uninorte.betit.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import co.edu.uninorte.betit.model.JsonData;
import co.edu.uninorte.betit.retrofit.ApiService;
import co.edu.uninorte.betit.retrofit.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vitto on 14/03/2018.
 */

public class JsonDataViewModel extends AndroidViewModel{

    //Todo esto es preliminar para crear un ViewModel. crea el live data blah blah sino es null blah blah y se llama el metodo de get JSON.
    private MutableLiveData<JsonData> liveData;

    public JsonDataViewModel(@NonNull Application application){
        super(application);

        if(liveData == null){
            liveData = new MutableLiveData<>();
        }
//El metodo esta abajito.
        getJson();
    }

//Aqui se coge la extension se llama el metodo de getMyJsonData que hace el GET al URL y regresa el objeto en forma GSON
    private void getJson(){
        ApiService api = RetroClient.getApiService();
        Call<JsonData> dataCall = api.getMyJsonData();
        // Aqui se maneja el callback del objeto JSON, si fue exitoso o no.
        dataCall.enqueue(new Callback<JsonData>() {
            @Override
            public void onResponse(Call<JsonData> call, Response<JsonData> response) {
                Log.d("Json: ","Call onResponse sirve");
                //Si fue exitoso se lo pasamos al livedata
                setData(response.body());
            }

            @Override
            public void onFailure(Call<JsonData> call, Throwable t) {
                Log.d("Json: ","Call onFailure");
            }
        });
    }

    //Getter del livedata
    public MutableLiveData<JsonData> getLiveData() {
        return liveData;
    }

    //Setter del livedata pero especificamente para el JsonData class (Esta clase "JsonData" fue crear por la pagina esa que descompone la estructura del JSON Array)
    public void setData(JsonData data){liveData.setValue(data);}
}
