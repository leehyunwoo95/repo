package com.lhw.cmm;

import org.apache.commons.lang3.StringUtils;

/**
 * 문자열관련 기능을 모은 클래스
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
 * </pre>
 */
public class StringUtil extends StringUtils{

	/**
	 * 데이터가 비어있는지 유무를 판단하여 돌려줌
	 * @param str
	 * @return  boolean
	 * @exception
	 */
	public static boolean isEmpty(String str) {
		if( str == null ) return true;
		return str.trim().length() > 0 ? false : true;
	}

	/**
	 * 스트링이 비어있으면 ""  있으면 입력 str 리턴
	 * @param str
	 * @return "" or  str
	 */
	public static String nvl(String str){
		if(isEmpty(str)){
			return "";
		}else{
			return str;
		}
	}

}
