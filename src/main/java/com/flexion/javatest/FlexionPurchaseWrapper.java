package com.flexion.javatest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.flexionmobile.codingchallenge.integration.Purchase;

@Component
public class FlexionPurchaseWrapper {

	private List<Purchase> purchases;
	
	public FlexionPurchaseWrapper() {
		purchases = new ArrayList<>();
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
}
