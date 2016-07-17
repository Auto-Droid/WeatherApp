package com.sourabhkarkal.weatherapp.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabhkarkal.weatherapp.R;
import com.sourabhkarkal.weatherapp.databinding.ActivityMainBinding;
import com.sourabhkarkal.weatherapp.model.ChannelDTO;
import com.sourabhkarkal.weatherapp.model.ForecastDTO;
import com.sourabhkarkal.weatherapp.service.RestTemplateExecutor;
import com.sourabhkarkal.weatherapp.service.TagRequest;
import com.sourabhkarkal.weatherapp.service.iWebListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sourabhkarkal
 * Main screen that displays most of the content fetched from {@link #targetUrl}
 * Android DataBinding is used to display contents in the UI which allows the app to follow MVVM pattern
 * In {@link #onTaskComplete(int, Object, boolean)} all the data is set to {@link ForecastAdapter} to show
 * list of forecasted day
 */
public class MainActivity extends AppCompatActivity implements iWebListener {

    String targetUrl = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20%28select%20woeid%20from%20geo.places%281%29%20where%20text%3D%22amsterdam%22%29&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
    public ChannelDTO channelDTO;
    public ActivityMainBinding binding;
    public ArrayList<ForecastDTO> forecastDTOs;
    String temperature = "";
    ForecastAdapter forecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        channelDTO = new ChannelDTO();
        binding.setChannel(channelDTO);

        binding.rvDaysList.setLayoutManager(new LinearLayoutManager(this));
        binding.webViewCondition.setBackgroundColor(Color.TRANSPARENT);

        if(isNetworkAvailable()) {
            //Calling executor to get data from url
            TagRequest tagRequest = new TagRequest(101, targetUrl, getApplicationContext(), this);
            new RestTemplateExecutor().callServerApi(tagRequest);
        }else{
            Toast.makeText(this,"No Active Internet Connection",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onTaskComplete(int taskId, Object object, boolean isError) {
        if(taskId==101){
            if(isError){
                Toast.makeText(this, R.string.error_msg,Toast.LENGTH_LONG).show();
            }else{
                try {
                    JsonNode root = new ObjectMapper().readTree(object.toString());
                    readDataFromJson(root);
                    if(channelDTO!=null){
                        binding.setChannel(channelDTO);
                        forecastDTOs = channelDTO.getItem().getForecast();
                        temperature = channelDTO.getUnits().getTemperature();
                        binding.tvMaxTxt.setText("Max");
                        binding.tvMinTxt.setText("Min");
                        if(forecastDTOs!=null) {
                            forecastAdapter = new ForecastAdapter(forecastDTOs);
                            binding.rvDaysList.setAdapter(forecastAdapter);
                            /*Spanned htmlAsSpanned = Html.fromHtml(channelDTO.getItem().getDescription());
                            binding.tvCityName.setText(htmlAsSpanned);*/

                            //Note: this is a dirty fix to read img src from CDATA could have been done using DOM parsing
                            Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(channelDTO.getItem().getDescription());
                            while (matcher.find()) {
                                Log.d("img url: ", matcher.group(1));
                                binding.webViewCondition.loadUrl(matcher.group(1));
                                binding.webViewCondition.setBackgroundColor(Color.TRANSPARENT);
                            }

                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this,R.string.error_msg,Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * This method traverses to the result section in json and sets the value in DTO using Object mapper
     * method used in {@link #onTaskComplete(int, Object, boolean)} ()}
     *
     * @param root
     * @throws IOException
     */
    public void readDataFromJson(JsonNode root) throws IOException {

        JsonNode node = root.path("query").path("results").path("channel");
        channelDTO = new ObjectMapper().readValue(node.toString(), ChannelDTO.class);

    }


    /**
     * Adapter to display the list for forecast for up coming days
     */
    class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
        private ArrayList<ForecastDTO> itemsData;

        public ForecastAdapter(ArrayList<ForecastDTO> itemsData) {
            this.itemsData = itemsData;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.days_list_item, parent ,false);

            // create ViewHolder
            ViewHolder viewHolder = new ViewHolder(itemLayoutView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

            if(position!=0) { // skipping todays forecast as it is already displaying on top
                viewHolder.tvDayOfWeek.setText(itemsData.get(position).getDay());
                viewHolder.tvDate.setText(itemsData.get(position).getDate());
                viewHolder.tvHigh.setText(itemsData.get(position).getHigh() + " " + temperature);
                viewHolder.tvLow.setText(itemsData.get(position).getLow() + " " + temperature);
                viewHolder.tvClimate.setText(itemsData.get(position).getText());
            }

        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvDayOfWeek,tvDate,tvHigh,tvLow,tvClimate;
            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                tvDayOfWeek = (TextView) itemLayoutView.findViewById(R.id.tvDayOfWeek);
                tvDate = (TextView) itemLayoutView.findViewById(R.id.tvDate);
                tvHigh = (TextView) itemLayoutView.findViewById(R.id.tvHigh);
                tvLow = (TextView) itemLayoutView.findViewById(R.id.tvLow);
                tvClimate = (TextView) itemLayoutView.findViewById(R.id.tvClimate);

            }
        }


        // Return the size of your itemsData (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return itemsData.size();
        }

    }

    /**
     * Checks and returns whether there is an Internet connectivity or not. This
     * would be useful to check the network connectivity before making a network
     * call.
     *
     * @return "True" -> is Connected , "False" -> if not.
     */
    public boolean isNetworkAvailable() {
        boolean isConnected = false;
        final ConnectivityManager connectivityService = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityService != null) {
            final NetworkInfo networkInfo = connectivityService
                    .getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                isConnected = true;
            }
        }
        return isConnected;
    }

}
