package com.lhw.tag.pagination;

import com.lhw.cmm.StringUtil;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

import java.text.MessageFormat;

public class AbstractPaginationRenderer implements PaginationRenderer {

	protected String firstAppendStartTag = "";
	protected String lastAppendStartTag = "";
	protected String previousGroupStartTag;
	protected String previousGroupEndTag;
	protected String numberGroupStartTag;
	protected String numberGroupEndTag;
	protected String nextGroupStartTag;
	protected String nextGroupEndTag;

	protected String firstPageLabel;
	protected String previousBlockPageLabel;
	protected String previousPageLabel;
	protected String currentPageLabel;
	protected String otherPageLabel;
	protected String nextPageLabel;
	protected String nextBlockPageLabel;
	protected String lastPageLabel;

	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {

		StringBuffer strBuff = new StringBuffer();

		strBuff.append(firstAppendStartTag + "\n");

		int firstPageNo = paginationInfo.getFirstPageNo();
		int firstPageNoOnPageList = paginationInfo.getFirstPageNoOnPageList();
		int totalPageCount = paginationInfo.getTotalPageCount();
		int pageSize = paginationInfo.getPageSize();
		int lastPageNoOnPageList = paginationInfo.getLastPageNoOnPageList();
		int currentPageNo = paginationInfo.getCurrentPageNo();
		int lastPageNo = paginationInfo.getLastPageNo();

		//if (totalPageCount > pageSize) {
			if( !StringUtil.isEmpty(previousGroupStartTag) ) {
				strBuff.append(previousGroupStartTag);
			}
			if (firstPageNoOnPageList > pageSize) {
				strBuff.append(MessageFormat.format(firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo), "" } ));
				strBuff.append(MessageFormat.format(previousBlockPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList - 1)  , " "}));
			} else {
				strBuff.append(MessageFormat.format(firstPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) , " inactive " }));
				strBuff.append(MessageFormat.format(previousBlockPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNo) , " inactive " }));
			}
			if( !StringUtil.isEmpty(previousPageLabel) ) {
				if(currentPageNo > firstPageNoOnPageList) {
					strBuff.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction,Integer.toString(currentPageNo-1), ""} )).append("\n");
				} else {
					strBuff.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction,Integer.toString(firstPageNo) , " inactive "})).append("\n");
				}
			}
			if( !StringUtil.isEmpty(previousGroupEndTag) ) {
				strBuff.append(previousGroupEndTag);
			}
		//}

		if( !StringUtil.isEmpty(numberGroupStartTag) ) {
			strBuff.append(numberGroupStartTag);
		}
		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if (i == currentPageNo) {
				strBuff.append(MessageFormat.format(currentPageLabel, new Object[] { Integer.toString(i), " inactive " }));
			} else {
				strBuff.append(MessageFormat.format(otherPageLabel, new Object[] { jsFunction, Integer.toString(i), Integer.toString(i), " inactive " }));
			}
		}
		if( !StringUtil.isEmpty(numberGroupEndTag) ) {
			strBuff.append(numberGroupEndTag);
		}

		if( !StringUtil.isEmpty(nextGroupStartTag) ) {
			strBuff.append(nextGroupStartTag);
		}
		if( !StringUtil.isEmpty(nextPageLabel) ) {
			if(currentPageNo < lastPageNoOnPageList) {
				strBuff.append(MessageFormat.format(nextPageLabel,new Object[]{jsFunction,Integer.toString(currentPageNo+1), "  "})).append("\n");
			} else {
				strBuff.append(MessageFormat.format(nextPageLabel,new Object[]{jsFunction,Integer.toString(lastPageNo), " inactive "})).append("\n");
			}
		}
		//if (totalPageCount > pageSize) {
			if (lastPageNoOnPageList < totalPageCount) {
				strBuff.append(MessageFormat.format(nextBlockPageLabel, new Object[] { jsFunction, Integer.toString(firstPageNoOnPageList + pageSize) , "  "}));
				strBuff.append(MessageFormat.format(lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) , "  "}));
			} else {
				strBuff.append(MessageFormat.format(nextBlockPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo), " inactive " }));
				strBuff.append(MessageFormat.format(lastPageLabel, new Object[] { jsFunction, Integer.toString(lastPageNo) , " inactive "}));
			}
		//}
		if( !StringUtil.isEmpty(nextGroupEndTag) ) {
			strBuff.append(nextGroupEndTag);
		}
		strBuff.append(lastAppendStartTag + "\n");
		return strBuff.toString();

	}
	
}