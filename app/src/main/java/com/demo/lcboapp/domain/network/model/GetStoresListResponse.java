package com.demo.lcboapp.domain.network.model;

import com.demo.lcboapp.data.model.Pager;
import com.demo.lcboapp.data.model.Store;

import java.util.List;

public class GetStoresListResponse {

	int status;
	String message;
	Pager pager;
	List<Store> result;

	public GetStoresListResponse() {}

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

	public List<Store> getResult() {
		return result;
	}

	public void setResult(List<Store> result) {
		this.result = result;
	}
}