package com.example.sumeesh.madweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sumeesh on 09/06/16.
 */
public class WeatherUtil {

    static public class WeatherJSONParser {
        static ArrayList<Weather> parseWeather(String in) throws JSONException {

            ArrayList<Weather> weatherList = new ArrayList<Weather>();

            JSONObject root = new JSONObject(in);
            JSONArray listJSONArray = root.getJSONArray("list");

            JSONObject weatherMainJSONObject = listJSONArray.getJSONObject(0).getJSONObject("main");
            JSONArray weatherJSONArray = listJSONArray.getJSONObject(0).getJSONArray("weather");

            JSONObject weatherOnlyObject = weatherJSONArray.getJSONObject(0);

            Weather weather = new Weather();

            weather.setTemperature(weatherMainJSONObject.getString("temp"));
            weather.setHumidity(weatherMainJSONObject.getString("humidity"));
            weather.setPressure(weatherMainJSONObject.getString("pressure"));
            weather.setWeatherDesc(weatherOnlyObject.getString("description"));
            weather.setIcon(weatherOnlyObject.getString("icon"));
            weather.setWeatherID(weatherOnlyObject.getString("id"));
            weather.setWeatherMain(weatherOnlyObject.getString("main"));

            weatherList.add(weather);


            return weatherList;

        }

    }


/*    static public class PersonJSONParser {
        static ArrayList<Weather> parsePersons(String in) throws JSONException {

            ArrayList<Weather> personList = new ArrayList<Weather>();

            JSONObject root = new JSONObject(in);
            JSONArray personArray = root.getJSONArray("persons");

            for(int i = 0; i < personArray.length() ; i++) {
                JSONObject personJSONObject  = personArray.getJSONObject(i);
                Weather person = new Weather();
                person.setName(personJSONObject.getString("name"));
                person.setDepartment(personJSONObject.getString("department"));
                person.setId(personJSONObject.getInt("id"));
                person.setAge(personJSONObject.getInt("age"));
                personList.add(person);
            }

            return personList;
        }
    }
*/
}
