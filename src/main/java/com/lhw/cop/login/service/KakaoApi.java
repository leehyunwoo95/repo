package com.lhw.cop.login.service;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 카카오 API를 관리하기 위한  클래스
 * @author 이현우
 * @since 2021.03.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *  수정일      수정자   수정내용
 *  ---------- ------- ---------------------------
 *  2021.03.01 이현우 최초생성
 *
 * </pre>
 */
public class KakaoApi {
    private static String kakaoApiKey = "a301ae0ea3a703d2d023964f8d9c20cd"; 		//API  KEY	REAL
    private static String kakaoRedirectUri = "http://27.96.134.214/kakaoLogin.do";	//Redirect URI

    /**
     * 카카오 엑세스토큰을 가져오는 메소드
     * @param code
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
	public static JsonNode getKakaoAccessToken(String code) throws ClientProtocolException, IOException {
	   
		final String RequestUrl = "https://kauth.kakao.com/oauth/token"; // Host
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();

		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", getKakaoApiKey())); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", getKakaoRedirectUri())); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", code)); // 로그인 과정중 얻은 code 값

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		JsonNode returnNode = null;

		post.setEntity(new UrlEncodedFormEntity(postParams));

		final HttpResponse response = client.execute(post);
		final int responseCode = response.getStatusLine().getStatusCode();

		// JSON 형태 반환값 처리
		ObjectMapper mapper = new ObjectMapper();

		returnNode = mapper.readTree(response.getEntity().getContent());

		return returnNode;
	}

	/**
	 * 엑세스토큰을 이용하여 유저정보를 가져오는 메소드
	 * @param accessToken
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static JsonNode getKakaoUserInfo(JsonNode accessToken) throws ClientProtocolException, IOException {

		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + accessToken);

		JsonNode returnNode = null;

		final HttpResponse response = client.execute(post);
		final int responseCode = response.getStatusLine().getStatusCode();

		// JSON 형태 반환값 처리
		ObjectMapper mapper = new ObjectMapper();
		returnNode = mapper.readTree(response.getEntity().getContent());

		return returnNode;
	}

	
	public static String getKakaoApiKey() {
		return kakaoApiKey;
	}

	public static String getKakaoRedirectUri() {
		return kakaoRedirectUri;
	}

}
