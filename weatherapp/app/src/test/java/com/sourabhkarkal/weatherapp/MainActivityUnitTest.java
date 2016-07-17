package com.sourabhkarkal.weatherapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabhkarkal.weatherapp.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MainActivityUnitTest {

    String sampleJson = "{\"query\":{\"count\":1,\"created\":\"2016-07-17T06:29:36Z\",\"lang\":\"en-us\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Amsterdam, NH, NL\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-727232/\",\"description\":\"Yahoo! Weather for Amsterdam, NH, NL\",\"language\":\"en-us\",\"lastBuildDate\":\"Sun, 17 Jul 2016 08:29 AM CEST\",\"ttl\":\"60\",\"location\":{\"city\":\"Amsterdam\",\"country\":\"Netherlands\",\"region\":\" NH\"},\"wind\":{\"chill\":\"64\",\"direction\":\"290\",\"speed\":\"7\"},\"atmosphere\":{\"humidity\":\"83\",\"pressure\":\"1021.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"5:41 am\",\"sunset\":\"9:52 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Amsterdam, NH, NL at 07:00 AM CEST\",\"lat\":\"52.373119\",\"long\":\"4.89319\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-727232/\",\"pubDate\":\"Sun, 17 Jul 2016 07:00 AM CEST\",\"condition\":{\"code\":\"26\",\"date\":\"Sun, 17 Jul 2016 07:00 AM CEST\",\"temp\":\"64\",\"text\":\"Cloudy\"},\"forecast\":[{\"code\":\"12\",\"date\":\"17 Jul 2016\",\"day\":\"Sun\",\"high\":\"72\",\"low\":\"63\",\"text\":\"Rain\"},{\"code\":\"34\",\"date\":\"18 Jul 2016\",\"day\":\"Mon\",\"high\":\"74\",\"low\":\"60\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"19 Jul 2016\",\"day\":\"Tue\",\"high\":\"81\",\"low\":\"57\",\"text\":\"Sunny\"},{\"code\":\"4\",\"date\":\"20 Jul 2016\",\"day\":\"Wed\",\"high\":\"84\",\"low\":\"66\",\"text\":\"Thunderstorms\"},{\"code\":\"47\",\"date\":\"21 Jul 2016\",\"day\":\"Thu\",\"high\":\"73\",\"low\":\"63\",\"text\":\"Scattered Thunderstorms\"},{\"code\":\"28\",\"date\":\"22 Jul 2016\",\"day\":\"Fri\",\"high\":\"73\",\"low\":\"60\",\"text\":\"Mostly Cloudy\"},{\"code\":\"28\",\"date\":\"23 Jul 2016\",\"day\":\"Sat\",\"high\":\"72\",\"low\":\"60\",\"text\":\"Mostly Cloudy\"},{\"code\":\"30\",\"date\":\"24 Jul 2016\",\"day\":\"Sun\",\"high\":\"71\",\"low\":\"57\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"25 Jul 2016\",\"day\":\"Mon\",\"high\":\"74\",\"low\":\"59\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"26 Jul 2016\",\"day\":\"Tue\",\"high\":\"72\",\"low\":\"61\",\"text\":\"Partly Cloudy\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/26.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Cloudy\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Sun - Rain. High: 72Low: 63\\n<BR /> Mon - Mostly Sunny. High: 74Low: 60\\n<BR /> Tue - Sunny. High: 81Low: 57\\n<BR /> Wed - Thunderstorms. High: 84Low: 66\\n<BR /> Thu - Scattered Thunderstorms. High: 73Low: 63\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-727232/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}";
    String wrongSampleJson = "\"query\":{\"count\":1,\"created\":\"2016-07-17T06:29:36Z\",\"lang\":\"en-us\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Amsterdam, NH, NL\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-727232/\",\"description\":\"Yahoo! Weather for Amsterdam, NH, NL\",\"language\":\"en-us\",\"lastBuildDate\":\"Sun, 17 Jul 2016 08:29 AM CEST\",\"ttl\":\"60\",\"location\":{\"city\":\"Amsterdam\",\"country\":\"Netherlands\",\"region\":\" NH\"},\"wind\":{\"chill\":\"64\",\"direction\":\"290\",\"speed\":\"7\"},\"atmosphere\":{\"humidity\":\"83\",\"pressure\":\"1021.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"5:41 am\",\"sunset\":\"9:52 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Amsterdam, NH, NL at 07:00 AM CEST\",\"lat\":\"52.373119\",\"long\":\"4.89319\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-727232/\",\"pubDate\":\"Sun, 17 Jul 2016 07:00 AM CEST\",\"condition\":{\"code\":\"26\",\"date\":\"Sun, 17 Jul 2016 07:00 AM CEST\",\"temp\":\"64\",\"text\":\"Cloudy\"},\"forecast\":[{\"code\":\"12\",\"date\":\"17 Jul 2016\",\"day\":\"Sun\",\"high\":\"72\",\"low\":\"63\",\"text\":\"Rain\"},{\"code\":\"34\",\"date\":\"18 Jul 2016\",\"day\":\"Mon\",\"high\":\"74\",\"low\":\"60\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"19 Jul 2016\",\"day\":\"Tue\",\"high\":\"81\",\"low\":\"57\",\"text\":\"Sunny\"},{\"code\":\"4\",\"date\":\"20 Jul 2016\",\"day\":\"Wed\",\"high\":\"84\",\"low\":\"66\",\"text\":\"Thunderstorms\"},{\"code\":\"47\",\"date\":\"21 Jul 2016\",\"day\":\"Thu\",\"high\":\"73\",\"low\":\"63\",\"text\":\"Scattered Thunderstorms\"},{\"code\":\"28\",\"date\":\"22 Jul 2016\",\"day\":\"Fri\",\"high\":\"73\",\"low\":\"60\",\"text\":\"Mostly Cloudy\"},{\"code\":\"28\",\"date\":\"23 Jul 2016\",\"day\":\"Sat\",\"high\":\"72\",\"low\":\"60\",\"text\":\"Mostly Cloudy\"},{\"code\":\"30\",\"date\":\"24 Jul 2016\",\"day\":\"Sun\",\"high\":\"71\",\"low\":\"57\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"25 Jul 2016\",\"day\":\"Mon\",\"high\":\"74\",\"low\":\"59\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"26 Jul 2016\",\"day\":\"Tue\",\"high\":\"72\",\"low\":\"61\",\"text\":\"Partly Cloudy\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/26.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Cloudy\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Sun - Rain. High: 72Low: 63\\n<BR /> Mon - Mostly Sunny. High: 74Low: 60\\n<BR /> Tue - Sunny. High: 81Low: 57\\n<BR /> Wed - Thunderstorms. High: 84Low: 66\\n<BR /> Thu - Scattered Thunderstorms. High: 73Low: 63\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-727232/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}";
    MainActivity mainActivity;

    @Before
    public void init(){
        mainActivity = new MainActivity();
    }

    @Test
    public void testPreconditions() {
        assertNotNull("MainActivity is Null",mainActivity);
    }

    @Test
    public void value_check(){
        assertNotNull("array is null",  mainActivity.forecastDTOs);
        assertEquals( mainActivity.channelDTO.getLocation().getCity(),"Amsterdam");
    }


    @Test
    public void weather_data_parsing_test() throws IOException {
        JsonNode root = new ObjectMapper().readTree(sampleJson);
        mainActivity.readDataFromJson(root);
    }

    @Test
    public void weather_wrong_data_parsing_test() throws IOException {
        JsonNode root = new ObjectMapper().readTree(wrongSampleJson);
        mainActivity.readDataFromJson(root);
    }

}