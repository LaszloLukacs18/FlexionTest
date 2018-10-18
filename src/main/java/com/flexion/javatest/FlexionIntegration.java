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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import com.google.gson.Gson;

@Component
public class FlexionIntegration implements Integration {
	
    private static final String CLIENT_PROTOCOL_EXCEPTION = "ClientProtocolException occured!";
	private static final String IO_EXCEPTION = "IO exception occured!";
	private static final Logger LOGGER = LoggerFactory.getLogger(FlexionIntegration.class);
	
	@Autowired
	private FlexionPurchase flexionPurchase;
	
	@Autowired
	private FlexionPurchaseWrapper flexionPurchaseWrapper;
	
	@Override
	public Purchase buy(String itemId) {
		final HttpPost httpPost = new HttpPost("http://sandbox.flexionmobile.com/javachallenge/rest/developer/​laszlo%20lukacs​/buy/​"+itemId);
	    try (final CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
	    	final CloseableHttpResponse response = closeableHttpClient.execute(httpPost);) {
			final HttpEntity entity = response.getEntity();
		    final String responseContent = EntityUtils.toString(entity);
		    final Gson gson = new Gson();
		    flexionPurchase = gson.fromJson(responseContent, FlexionPurchase.class);
	    } catch (IOException ioException) {
			LOGGER.error(IO_EXCEPTION + ioException.getMessage());
		}
		return flexionPurchase;
	}

	@Override
	public void consume(Purchase arg0) {
		final HttpPost httpPost = new HttpPost("http://sandbox.flexionmobile.com/javachallenge/rest/developer/​laszlo%20lukacs​/consume/​"+arg0.getId());
	    try (final CloseableHttpClient client = HttpClients.createDefault();
	    	 final CloseableHttpResponse response = client.execute(httpPost);) {
		} catch (ClientProtocolException clientProtocolException) {
			LOGGER.error(CLIENT_PROTOCOL_EXCEPTION + clientProtocolException.getMessage());
		} catch (IOException ioException) {
			LOGGER.error(IO_EXCEPTION + ioException.getMessage());
		}
	}

	@Override
	public List<Purchase> getPurchases() {
		List<Purchase> purchases = new ArrayList<>();
		final HttpPost httpGet = new HttpPost("http://sandbox.flexionmobile.com/javachallenge/rest/developer/​laszlo%20lukacs​/buy/all");
		try (final CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
			final CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);	) {
			final HttpEntity httpEntity = closeableHttpResponse.getEntity();
		    final String content = EntityUtils.toString(httpEntity);
		    final Gson gson = new Gson();
		    flexionPurchaseWrapper = gson.fromJson(content, FlexionPurchaseWrapper.class);
		    purchases = flexionPurchaseWrapper.getPurchases();
		} catch (ClientProtocolException clientProtocolException) {
			LOGGER.error(CLIENT_PROTOCOL_EXCEPTION + clientProtocolException.getMessage());
		} catch (IOException ioException) {
			LOGGER.error(IO_EXCEPTION + ioException.getMessage());
		}
		return purchases;
	}

}
