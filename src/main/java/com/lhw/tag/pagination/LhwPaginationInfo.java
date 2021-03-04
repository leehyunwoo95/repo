package com.lhw.tag.pagination;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 페이지정보 클래스
 * 
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
public class LhwPaginationInfo extends PaginationInfo {

	/** 현재 페이지 목록의 시작 번호 */
	private int currentPageStartNo;
	
    /**
     * 페이징 처음번호를 돌려준다
     * @param 
     * @return  int
     * @exception 
     */
	public int getFirstRecordIndex() {
			
		int firstRecordIndex = 0;

		firstRecordIndex = ( getCurrentPageNo() - 1 ) * getRecordCountPerPage();
		
		return firstRecordIndex;
		
	}

    /**
     * 페이징 마지막번호를 돌려준다
     * @param 
     * @return  int
     * @exception 
     */
	public int getLastRecordIndex() {
		
		int lastRecordIndex = 0;

		lastRecordIndex = getRecordCountPerPage();

		return lastRecordIndex;
		
	}

    /**
     * 현재 페이지 목록의 시작 번호를 돌려준다
     * @param 
     * @return  int
     * @exception 
     */
	public int getCurrentPageStartNo() {
		currentPageStartNo = getTotalRecordCount() - getRecordCountPerPage() * (getCurrentPageNo() - 1);
		return currentPageStartNo;
	}

	public void setCurrentPageStartNo(int currentPageStartNo) {
		this.currentPageStartNo = currentPageStartNo;
	}
	
}
