package com.lhw.cop.login.web;

import com.lhw.cmm.StringUtil;
import com.lhw.cop.login.service.KakaoApi;
import com.lhw.cop.login.service.LoginVO;
import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

	/**
	 * 로그인 View
	 * @param loginVO
	 * @param rurl
	 * @param session
	 * @param request
	 * @param model
	 * @return lhw/cop/login/login
	 * @throws Exception
	 */
	@RequestMapping("/loginView.do")
	public String loginView(@ModelAttribute("loginVO") LoginVO loginVO,
                            @RequestParam(value="rurl", required=false) String rurl,
                            HttpSession session, HttpServletRequest request, ModelMap model) throws Exception {

		// 리턴 url 없으면 이전 페이지 값
		if ( StringUtil.isEmpty(rurl) ) {
			rurl = (String)request.getHeader("REFERER");
		}

		request.getSession().setAttribute("loginVO", null);

		// 리턴 url 세션에 세팅
		if ( !StringUtil.isEmpty(rurl) ) {
			request.getSession().setAttribute("RETURN_URL", rurl);
		}

		/*Kakao Login*/
		String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize"; //Call URL
		kakaoAuthUrl = kakaoAuthUrl+"?client_id="+ KakaoApi.getKakaoApiKey()+"&redirect_uri="+KakaoApi.getKakaoRedirectUri()+"&response_type=code";
		model.addAttribute("kakaoAuthUrl", kakaoAuthUrl);

		return "lhw/cop/login/login";

	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, ModelMap model) throws Exception {
		request.getSession().setAttribute("loginVO", null);
		String rurl = request.getParameter("rurl");
        model.addAttribute("msg", "로그아웃 되었습니다.");
		if (!StringUtil.isEmpty(rurl)) {
			model.addAttribute("url", rurl);
			return "lhw/cmm/redirectMsg";
		}else{
            model.addAttribute("url", "/");
            return "lhw/cmm/redirectMsg";
        }
	}

	/**
	 * 카카오 로그인 Callback
	 * @param code
	 * @param model
	 * @param request
	 * @return rurl
	 * @throws Exception
	 */
	@RequestMapping(value = "/kakaoLogin.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String kakaoLogin(@RequestParam String code, ModelMap model, HttpServletRequest request) throws Exception {

		// JsonNode트리형태로 토큰받아온다
		JsonNode jsonToken = KakaoApi.getKakaoAccessToken(code);
		// 여러 json객체 중 access_token을 가져온다
		JsonNode accessToken = jsonToken.get("access_token");

		// access_token을 통해 사용자 정보 요청
		JsonNode userInfo = KakaoApi.getKakaoUserInfo(accessToken);

		// 유저정보 카카오에서 가져오기 Get properties
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");

		LoginVO loginVO = new LoginVO();
		loginVO.setUserId(userInfo.path("id").asText()); 			// 고유아이디
		loginVO.setEmail(kakao_account.path("email").asText());		// 이메일
		loginVO.setUserNm(properties.path("nickname").asText());	// 이름(별명)

		String rurl = (String)request.getSession().getAttribute("RETURN_URL");
		if(StringUtil.isEmpty(rurl)){
			rurl = "/";
		}

		if( null == loginVO.getUserId() ) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "lhw/cmm/backMsg";
		} else {
			request.getSession().setAttribute("loginVO", loginVO);
			model.addAttribute("msg", "로그인 되었습니다.");
			model.addAttribute("url", rurl);
			return "lhw/cmm/redirectMsg";
		}

	}

}
