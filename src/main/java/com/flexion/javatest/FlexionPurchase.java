package com.flexion.javatest;

import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.Purchase;

@Component
public class FlexionPurchase implements Purchase {

	private boolean consumed;
	private String id;
	private String itemId;
	
	@Override
	public boolean getConsumed() {
		return consumed;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getItemId() {
		return itemId;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
