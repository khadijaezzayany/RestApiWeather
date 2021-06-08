package ma.youcode.restapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.jar.JarException;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    EditText datainput;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        btn = findViewById(R.id.button);
//        button2 = findViewById(R.id.button2);
//        button3 = findViewById(R.id.button3);
//
//        name = findViewById(R.id.Name);
        // assing values to each control on the layout
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        list = findViewById(R.id.list);
        datainput = findViewById(R.id.datainput);

        final WeatherData weatherDataService = new WeatherData(MainActivity.this);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityID(datainput.getText().toString(), new WeatherData.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of "+ cityID, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                weatherDataService.getCityForecastByID("44418", new WeatherData.ForeCastByIDResponse() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponce(List<WeatherReportModel> weatherReportModel) {

                        //Toast.makeText(MainActivity.this, "Returned an ID of ", Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter= new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,weatherReportModel);
                        list.setAdapter(arrayAdapter);
                    }

                });



            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weatherDataService.getCityforecasByName(datainput.getText().toString(), new WeatherData.GetCityForecastByNameCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Somethong wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        /////put the entire list into the listVew control
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        list.setAdapter(arrayAdapter);
                    }
                });
            }
        });
    }

}
