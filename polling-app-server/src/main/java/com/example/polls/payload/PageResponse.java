package com.example.polls.payload;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public class PageResponse<T> {

      private int number;                     //현재 페이지
      private int size;                            //페이지 크기
      private int totalPages;                 //전체 페이지 수
      private int numberOfElements;   //현재 페이지에 나올 데이터 수
      private long totalElements;         //전체 데이터 수
      private boolean hasPreviousPage;    //이전 페이지 여부
      private boolean isFirstPage;              //현재 페이지가 첫 페이지 인지 여부
      private boolean hasNextPage;           //다음 페이지 여부
      private boolean isLastPage;               //현재 페이지가 마지막 페이지 인지 여부
      private Pageable nextPageable;         //다음 페이지 객체, 다음 페이지가 없으면 null
      private Pageable previousPageable;   //다음 페이지 객체, 이전 페이지가 없으면 null
      private List<T> content;               //조회된 데이터
      private Sort sort;                           //정렬정보
      
	public PageResponse(int number, int size, int totalPages, int numberOfElements, long totalElements,
			boolean hasPreviousPage, boolean isFirstPage, boolean hasNextPage, boolean isLastPage,
			Pageable nextPageable, Pageable previousPageable, Sort sort) {
		super();
		this.number = number;
		this.size = size;
		this.totalPages = totalPages;
		this.numberOfElements = numberOfElements;
		this.totalElements = totalElements;
		this.hasPreviousPage = hasPreviousPage;
		this.isFirstPage = isFirstPage;
		this.hasNextPage = hasNextPage;
		this.isLastPage = isLastPage;
		this.nextPageable = nextPageable;
		this.previousPageable = previousPageable;
		this.sort = sort;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isLastPage() {
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	public Pageable getNextPageable() {
		return nextPageable;
	}
	public void setNextPageable(Pageable nextPageable) {
		this.nextPageable = nextPageable;
	}
	public Pageable getPreviousPageable() {
		return previousPageable;
	}
	public void setPreviousPageable(Pageable previousPageable) {
		this.previousPageable = previousPageable;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
      
}
