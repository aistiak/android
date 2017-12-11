package com.routineapp.aristaik.routine20;

/**
 * Created by AR Istaik on 11/16/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FileDowanloader {


    static String fetchDataFromNetwork() throws MalformedURLException, IOException {
        // TODO code application logic here

        String res = "";
        //String url = "http://localhost/test.json";
        String url = "https://arifistiak.000webhostapp.com/mbstu_routine/data.xml";
        URL urlObj = new URL(url);
        URLConnection lu = urlObj.openConnection();

// Send data - if you don't need to send data
// ignore this section and just move on to the next one
        String data = URLEncoder.encode("yourdata", "UTF-8");
        lu.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(lu.getOutputStream());
        wr.write(data);
        wr.flush();

// Get the response
        BufferedReader rd = new BufferedReader(new InputStreamReader(lu.getInputStream()));
        String line = " ";
        while ((line = rd.readLine()) != null) {
            res += line;
        }

        wr.flush();
        wr.close();

        return res;


    }


}
