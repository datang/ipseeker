package com.weidou.ip.seeker;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.weidou.ip.domain.Entry;

/**
 * 百度IP定位服务 http://developer.baidu.com/map/ip-location-api.htm
 * 注册邮箱：weidou_mota@163.com (passwd: weidou123) 登录密码：weidou123
 * 
 * 提供根据IP返回位置信息的功能
 * 
 * @author tzh
 * 
 */
public class BaiduIpLocation implements IIpSeeker {
	private static Logger logger = Logger.getLogger(BaiduIpLocation.class);

	private static final String AK = "77920ddbb069dbb2a0053475b896154a";
	private static final String URL = "http://api.map.baidu.com/location/ip";

	/**
	 * 输出的坐标格式 可选，coor不出现时，默认为百度墨卡托坐标；coor=bd09ll时，返回为百度经纬度坐标
	 */
	private static final String COOR = "bd09ll";

	private HttpClient httpClient;

	public BaiduIpLocation() {
		httpClient = this.getHttpClient();
	}

	@Override
	public Entry search(String ip) {
		String url = URL + "?ak=" + AK + "&coor=" + COOR + "ip=" + ip;
		Entry entry = null;

		// GET的URL,参数直接加URL后
		HttpGet httpget = new HttpGet(url);
		// 建立HttpPost对象
		HttpResponse response;
		try {
			// response = new DefaultHttpClient().execute(httpget);
			response = httpClient.execute(httpget);
			// 发送GET,并返回一个HttpResponse对象，相对于POST，省去了添加NameValuePair数组作参数
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				String result = EntityUtils.toString(response.getEntity());
				// 得到返回的字符串
				if (result != null) {
					JSONObject json = JSONObject.fromObject(result);
					// logger.info(json);
					int status = json.getInt("status");
					if (status == 0) {
						JSONObject content = json.getJSONObject("content");
						JSONObject addressDetail = content
								.getJSONObject("address_detail");

						entry = new Entry();
						entry.setIp(ip);
						// entry.setCountry(data.getString("country"));
						// entry.setArea(data.getString("area"));
						entry.setRegion(addressDetail.getString("province"));
						entry.setCity(addressDetail.getString("city"));
						entry.setCounty(addressDetail.getString("district"));
						entry.setStreet(addressDetail.getString("street"));
						// entry.setIsp(content.getString("isp"));
						entry.setPoint(content.getString("point"));

					} else {
						logger.info("获取ip信息错误，错误信息：" + json.getString("data"));
					}
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entry;
	}

	public HttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.getCookieSpecs().register("easy", csf);
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, "easy");
		HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 2000);
		return httpClient;
	}

	// customer cookie policy, ignore cookie check
	CookieSpecFactory csf = new CookieSpecFactory() {
		public CookieSpec newInstance(HttpParams params) {
			return new BrowserCompatSpec() {
				@Override
				public void validate(Cookie cookie, CookieOrigin origin)
						throws MalformedCookieException {
					// Oh, I am easy
				}
			};
		}
	};

	public static void main(String[] args) {
		IIpSeeker ips = new BaiduIpLocation();
		ips.search("123.125.71.114");

	}

}
