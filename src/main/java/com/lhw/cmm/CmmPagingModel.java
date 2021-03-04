package com.lhw.cmm;

import com.lhw.tag.pagination.LhwPaginationInfo;

/**
 * 공통으로 사용하는 페이징 모델 클래스
 * @author 이현우
 * @since 2021.03.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일           수정자     수정내용
 *  ------------- -------- ---------------------------
 *  2021.03.01 이현우     최초 생성
 *
 * </pre>
 */
public class CmmPagingModel {

	/** 검색조건 */
    private String searchCnd = "all";
    
    /** 검색단어 */
    private String searchKrwd = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;
    
    /** 첫페이지 인덱스 */
    private int firstIndex = 1;
    
    /** 마지막페이지 인덱스 */
    private int lastIndex = 1;
    
    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getSearchKrwd() {
		return searchKrwd;
	}

	public void setSearchKrwd(String searchKrwd) {
		this.searchKrwd = searchKrwd;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	/**
	 * 페이징 정보를 리턴한다
	 * @param totCnt
	 * @return
	 */
	public LhwPaginationInfo getLhwPaginationInfo(int totCnt) {

		LhwPaginationInfo lhwPaginationInfo = new LhwPaginationInfo();
		lhwPaginationInfo.setCurrentPageNo(getPageIndex());
		lhwPaginationInfo.setRecordCountPerPage(getPageUnit());
		lhwPaginationInfo.setPageSize(getPageSize());
		lhwPaginationInfo.setTotalRecordCount(totCnt);
		
		setFirstIndex(lhwPaginationInfo.getFirstRecordIndex());
		setLastIndex(lhwPaginationInfo.getLastRecordIndex());
		setRecordCountPerPage(lhwPaginationInfo.getRecordCountPerPage());
		
		return lhwPaginationInfo;
		
	}
}
