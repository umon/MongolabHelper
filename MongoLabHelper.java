package com.example.umut.mongolabhelper;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by Umut on 13.02.2016.
 */

public class MongoLabHelper extends AsyncTask<String, Void, String> {
    final String baseUrl = "https://api.mongolab.com/api/1/";

    private String apiKey = "ZDAi1zTB7g95vbfp31F2Gsp36UMa7Xq5";
    private String database = "veritabani";
    private String collection = "umutonur.com";
    private URL url = null;
    private HttpURLConnection httpURLConnection = null;


    private String requestMethod;
    private boolean doOutputMethod;
    private String query;
    private String outputData;

    String response = null;


    private String getApiKey() {
        return "apiKey=" + apiKey;
    }

    private String getBaseURL() {
        return baseUrl;
    }

    private String getDatabaseURL() {
        return getBaseURL() + "databases" + "?" + getApiKey();
    }

    private String getDatabaseURL(String database) {
        return getBaseURL() + "databases/" + database + "?" + getApiKey();
    }

    private String getCollectionURL() {
        return getBaseURL() + "databases/" + database + "/collections" + "?" + getApiKey();
    }

    private String getCollectionURL(String collection) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?"
                + getApiKey();
    }

    private String getCollectionURL(String database, String collection) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?"
                + getApiKey();
    }

    private String getDocumentURL(String documentId) {
        return baseUrl + "databases/" + database + "/collections/" + collection + "/" + documentId
                + "?" + getApiKey();
    }

    private String getFindOneURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&fo=true&" + getApiKey();
    }

    private String getFindURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&" + getApiKey();
    }

    private String getUpdateManyURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&m=true&" + getApiKey();
    }

    private String getUpsertURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&u=true&" + getApiKey();
    }

    private String getCountURL(String query) {
        return getBaseURL() + "databases/" + database + "/collections/" + collection + "?q="
                + query + "&c=true&" + getApiKey();
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    private void setDatabase(String database) {
        this.database = database;
    }

    private void setCollection(String collection) {
        this.collection = collection;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setRequestProperty("Content-type", "application/json");

            if (requestMethod.equals("GET")) {
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                response = bufferedReader.readLine();
                bufferedReader.close();
                Log.e("MongoLab Process", "Getting Data..");
            } else if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
                OutputStreamWriter outputStreamWriter =
                        new OutputStreamWriter(httpURLConnection.getOutputStream());
                outputStreamWriter.write(outputData);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                Log.e("MongoLab Process", "Sending Data..");
            } else {
                Log.e("MongoLab Process", "Deleting Data..");
            }
            Log.e("MongoLab Status Code", String.valueOf(httpURLConnection.getResponseCode()));
            Log.e("MongoLab Status Message", httpURLConnection.getResponseMessage());
            if (requestMethod.equals("GET")) {
                Log.e("MongoLab Response", response);
            } else if (requestMethod.equals("POST") || requestMethod.equals("PUT")) {
                Log.e("MongoLab Output Data", outputData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    public String getCollection(String database, String collection) {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getCollectionURL(collection));
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getCollection(String collection) {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getCollectionURL(collection));
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getCollection() {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getCollectionURL());
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getDocument(String docId) {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getDocumentURL(docId));
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String findOne(String query) {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getFindOneURL(query));
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String find(String query) {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getFindURL(query));
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void insert(JSONObject newData) {
        try {
            this.requestMethod = "POST";
            this.outputData = newData.toString();
            this.url = new URL(getCollectionURL(collection));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void insert(JSONArray newData) {
        try {
            this.requestMethod = "POST";
            this.outputData = newData.toString();
            this.url = new URL(getCollectionURL(collection));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void update(String docId, JSONObject newData) {
        try {
            this.requestMethod = "PUT";
            this.outputData = "{ $set: " + newData.toString() + " }";
            this.url = new URL(getDocumentURL(docId));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void update(JSONObject newData, String query) {
        try {
            this.requestMethod = "PUT";
            this.outputData = "{ $set: " + newData.toString() + " }";
            this.url = new URL(getFindURL(query));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void updateMany(JSONObject newData, String query) {
        try {
            this.requestMethod = "PUT";
            this.outputData = "{ $set: " + newData.toString() + " }";
            this.url = new URL(getUpdateManyURL(query));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void upsert(JSONObject newData, String query) {
        try {
            this.requestMethod = "PUT";
            this.outputData = "{ $set: " + newData.toString() + " }";
            this.url = new URL(getUpdateManyURL(query));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String docId) {
        try {
            this.requestMethod = "DELETE";
            this.url = new URL(getDocumentURL(docId));
            execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int count(String query) {
        try {
            this.requestMethod = "GET";
            this.url = new URL(getCountURL(query));
            Log.e("URL", getCountURL(query));
            response = execute().get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(response);
    }
}
