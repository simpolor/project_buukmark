package com.simpolor.app.common.util;

public class PageNavigation {

	private static final int DEFAULT_PAGE_NO = 1; // 한번에 보여질 페이징 카운터 Max
	private static final int DEFAULT_PAGE_COUNT = 10; // 한번에 보여질 페이징 카운터 Max
	private static final int DEFAULT_PAGE_VIEWCOUNT = 10; // View에 보여질 리스트 수

	private int totalCount = 0; // 전체 수
	private int totalPage = 0; // 전체 페이지
	private int startPage = 0; // 시작 페이지 [1] <-- 1 or 11 or 21 or 31 .... 91
	private int endPage = 0; // 끝나는 페이지 시작이 1이면 - 10 11 이면 - 20 ...100
	private int prevPage = 0; // 이전페이지
	private int nextPage = 0; // 다음페이지
	private int pageNo = 0; // 현재 페이지
	private int pageCount = 0; // 페이지의 범위
	private int pageViewCount = 0; // 한 페이지의 게시물 갯수

	private int offset = 0; // mysql을 위한 offset
	private int limit = 0; // mysql을 위한 limit

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageViewCount() {
		return pageViewCount;
	}

	public void setPageViewCount(int pageViewCount) {
		this.pageViewCount = pageViewCount;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void makePageNavigation() {

		// totalCount가 0보다 작으면 모든 값을 0으로 초기화
		if (totalCount > 0) {
			// 값이 없을 경우 기본값으로 세팅
			if (pageNo <= 0)
				pageNo = DEFAULT_PAGE_NO;
			if (pageCount <= 0)
				pageCount = DEFAULT_PAGE_COUNT;
			if (pageViewCount <= 0)
				pageViewCount = DEFAULT_PAGE_VIEWCOUNT;

			// 시작 페이지를 구함 ( 항상 1로 시작 )
			startPage = ((pageNo - 1) / pageCount) * pageCount + 1;
			// startPage = ((pageNo - 1) / pageCount) * pageCount;

			// pageCount에 따라 마지막 페이지를 구함
			endPage = (startPage + pageCount) - 1;

			// 총 페이지 수를 구함
			totalPage = (totalCount / pageViewCount) + (totalCount % pageViewCount == 0 ? 0 : 1);

			// 마지막 페이지가 총 페이지 수보다 클 경우 총 페이지 수를 마지막 페이지로 변경
			endPage = endPage > totalPage ? totalPage : endPage;

			// 이전 페이지를 구함
			if (pageNo == 1) {
				prevPage = 1;
			} else {
				prevPage = pageNo - 1;
			}

			// 다음 페이지를 구함
			if (pageNo >= totalPage) {
				nextPage = endPage;
			} else {
				nextPage = pageNo + 1;
			}

			// mysql offset
			offset = (pageNo - 1) * pageViewCount;

			// mysql limit
			limit = pageViewCount;

		} else {
			totalCount = 0;
			totalPage = 0;
			pageNo = 1;
			startPage = 1;
			endPage = 1;
			prevPage = 1;
			nextPage = 1;
			pageCount = 0;
			pageViewCount = 0;
			offset = 0;
			limit = 0;
		}
	}
}