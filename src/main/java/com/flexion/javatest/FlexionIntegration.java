package com.flexion.javatest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.google.gson.Gson;

public class FlexionIntegration implements Integration {
	
	@Override
	public Purchase buy(String arg0) {
		Purchase flexionPurchase = new FlexionPurchase();
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://sandbox.flexionmobile.com/javachallenge/rest/developer/​laszlo%20lukacs​/buy/​"+arg0);
	    try {
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
		    String content = EntityUtils.toString(entity);
		    Gson gson = new Gson();
		    flexionPurchase = gson.fromJson(content, FlexionPurchase.class);
			client.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flexionPurchase;
	}

	@Override
	public void consume(Purchase arg0) {
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://sandbox.flexionmobile.com/javachallenge/rest/developer/​laszlo%20lukacs​/consume/​"+arg0.getId());
	    try {
			CloseableHttpResponse response = client.execute(httpPost);
			client.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Purchase> getPurchases() {
		List<Purchase> purchases = new ArrayList<>();
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpGet = new HttpPost("http://sandbox.flexionmobile.com/javachallenge/rest/developer/​laszlo%20lukacs​/buy/all");
	    try {
			CloseableHttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
		    String content = EntityUtils.toString(entity);
		    FlexionPurchaseWrapper flexionPurchaseWrapper = new FlexionPurchaseWrapper();
		    flexionPurchaseWrapper = new Gson().fromJson(content, FlexionPurchaseWrapper.class);
		    purchases = flexionPurchaseWrapper.getPurchases();
			client.close();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return purchases;
	}

}
