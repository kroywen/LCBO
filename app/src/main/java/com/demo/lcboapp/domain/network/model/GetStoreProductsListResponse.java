package com.demo.lcboapp.domain.network.model;

import com.demo.lcboapp.data.model.Pager;
import com.demo.lcboapp.data.model.Product;
import com.demo.lcboapp.data.model.Store;

import java.util.List;

public class GetStoreProductsListResponse {

	int status;
	String message;
	Pager pager;
	List<Product> result;
	Store store;
	String suggestion;

	public GetStoreProductsListResponse() {}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<Product> getResult() {
		return result;
	}

	public void setResult(List<Product> result) {
		this.result = result;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
}