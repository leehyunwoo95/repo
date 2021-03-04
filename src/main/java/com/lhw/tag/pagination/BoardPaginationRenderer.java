package com.lhw.tag.pagination;

/**
 * 사용자화면에서 사용하는 페이지 이동 버튼 렌더러 BoardPaginationRenderer.java
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
 *  2021.03.01    이현우     최초 생성
 *
 * </pre>
 */

public class BoardPaginationRenderer extends AbstractPaginationRenderer {

	public BoardPaginationRenderer() {

		previousGroupStartTag = "<div class=\"p-page__control\">";
		previousGroupEndTag = "</div>";
		numberGroupStartTag = "<div class=\"p-page__link-group\">";
		numberGroupEndTag = "</div>";
		nextGroupStartTag = "<div class=\"p-page__control\">";
		nextGroupEndTag = "</div>";
		
		firstPageLabel = "<a href=\"{0}{1}\" class=\"p-page__link prev-end\"><span class=\"skip\">처음 페이지</span></a>"; 
		previousBlockPageLabel = "<a href=\"{0}{1}\" class=\"p-page__link prev\"><span class=\"skip\">이전 10 페이지</span></a>";
		previousPageLabel = "<a href=\"{0}{1}\" class=\"p-page__link prev-one\">이전 페이지</a>";

		currentPageLabel = "<strong title=\"현재 {0}페이지\" class=\"p-page__link active\">{0}</strong>";
		otherPageLabel = "<a href=\"{0}{1}\" title=\"{1}페이지 이동\" class=\"p-page__link \">{1}</a>";

		nextPageLabel = "<a href=\"{0}{1}\" class=\"p-page__link next-one\">다음 페이지</a>";
		nextBlockPageLabel = "<a href=\"{0}{1}\" class=\"p-page__link next\"><span class=\"skip\">다음 10 페이지</span></a>";
		lastPageLabel = "<a href=\"{0}{1}\" class=\"p-page__link next-end\"><span class=\"skip\">끝 페이지</span></a>";

	}
	
}
