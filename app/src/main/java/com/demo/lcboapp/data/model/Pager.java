package com.demo.lcboapp.data.model;

import com.google.gson.annotations.SerializedName;

public class Pager {

	@SerializedName("records_per_page") int recordsPerPage;
	@SerializedName("total_records_count") int totalRecordsCount;
	@SerializedName("current_page_records_count") int currentPageRecordsCount;
	@SerializedName("is_first_page") boolean isFirstPage;
	@SerializedName("is_final_page") boolean isFinalPage;
	@SerializedName("current_page") int currentPage;
	@SerializedName("current_page_path") String currentPagePath;
	@SerializedName("next_page") int nextPage;
	@SerializedName("next_page_path") String nextPagePath;
	@SerializedName("previous_page") int previousPage;
	@SerializedName("previous_page_path") String previousPagePath;
	@SerializedName("total_pages") int totalPages;
	@SerializedName("total_pages_path") String totalPagesPath;

	public Pager() {}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getTotalRecordsCount() {
		return totalRecordsCount;
	}

	public void setTotalRecordsCount(int totalRecordsCount) {
		this.totalRecordsCount = totalRecordsCount;
	}

	public int getCurrentPageRecordsCount() {
		return currentPageRecordsCount;
	}

	public void setCurrentPageRecordsCount(int currentPageRecordsCount) {
		this.currentPageRecordsCount = currentPageRecordsCount;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean firstPage) {
		isFirstPage = firstPage;
	}

	public boolean isFinalPage() {
		return isFinalPage;
	}

	public void setFinalPage(boolean finalPage) {
		isFinalPage = finalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentPagePath() {
		return currentPagePath;
	}

	public void setCurrentPagePath(String currentPagePath) {
		this.currentPagePath = currentPagePath;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public String getNextPagePath() {
		return nextPagePath;
	}

	public void setNextPagePath(String nextPagePath) {
		this.nextPagePath = nextPagePath;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public String getPreviousPagePath() {
		return previousPagePath;
	}

	public void setPreviousPagePath(String previousPagePath) {
		this.previousPagePath = previousPagePath;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getTotalPagesPath() {
		return totalPagesPath;
	}

	public void setTotalPagesPath(String totalPagesPath) {
		this.totalPagesPath = totalPagesPath;
	}

}