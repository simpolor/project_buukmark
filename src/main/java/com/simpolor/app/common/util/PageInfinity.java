package com.simpolor.app.common.util;

public class PageInfinity {
	
	private static final int DEFAULT_PAGE_NO = 1; //한번에 보여질 페이징 카운터 Max
    private static final int DEFAULT_PAGE_VIEWCOUNT = 10; //View에 보여질 리스트 수
    
    private int totalCount = 0;		// 전체 수
    private int totalPage = 0;  	// 전체 페이지
    private int pageNo = 0;     	// 현재 페이지
    private int pageViewCount = 0;  // 한 페이지의 게시물 갯수
    
    private int offset = 0;  		// mysql을 위한 offset
    private int limit = 0;  		// mysql을 위한 limit
    
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

	public void makePageInfinity(){
		
		// totalCount가 0보다 작으면 모든 값을 0으로 초기화
		if(totalCount > 0){
			
			// 값이 없을 경우 기본값으로 세팅
			if(pageNo <= 0) pageNo = DEFAULT_PAGE_NO;
			if(pageViewCount <= 0) pageViewCount = DEFAULT_PAGE_VIEWCOUNT;
	    	
			// mysql offset
			offset = 0;
	    	
	    	// mysql limit
	    	limit = pageNo * pageViewCount;
	    	
		}else{
			totalCount = 0;
			totalPage = 0;
			pageNo = 1;
			pageViewCount = 0;
			offset = 0;
			limit = 0;
		}
	}
}