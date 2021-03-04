package com.lhw.cmm;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 날짜관련 기능을 모은 클래스
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
public class DateUtil {

	/**
	 * 현재 날짜와 시간을 param의 포멧 형태로 돌려준다.
	 * @param Format
	 * @return  String
	 * @exception
	 */
	public static String getNowDateTime( String Format ) {
		SimpleDateFormat sdf = new SimpleDateFormat(Format);
		return sdf.format(new Date()).toString();
	}

}
