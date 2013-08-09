package com.weidou.ip.seeker;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.weidou.ip.domain.Entry;

/**
 * 淘宝IP地址库
 * http://ip.taobao.com/index.php
 * 
 * 访问限制：为了保障服务正常运行，每个用户的访问频率需小于10qps。
 * 
 * @author tzh
 *
 */
public class TaobaoIpSearch implements IIpSeeker {
	private static Logger logger = Logger.getLogger(TaobaoIpSearch.class);
	private static final String URL = "http://ip.taobao.com/service/getIpInfo.php";

	@Override
	public Entry search(String ip) {
		String url = URL + "?ip=" + ip;
		Entry entry = null;

		// GET的URL,参数直接加URL后
		HttpGet httpget = new HttpGet(url);
		// 建立HttpPost对象
		HttpResponse response;
		try {
			response = new DefaultHttpClient().execute(httpget);
			// 发送GET,并返回一个HttpResponse对象，相对于POST，省去了添加NameValuePair数组作参数
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				String result = EntityUtils.toString(response.getEntity());
				// 得到返回的字符串
				if (result != null) {
					JSONObject json = JSONObject.fromObject(result);
					int code = json.getInt("code");
					if (code == 0) {
						JSONObject data = json.getJSONObject("data");
						entry = new Entry();
						entry.setIp(data.getString("ip"));
						entry.setCountry(data.getString("country"));
						entry.setArea(data.getString("area"));
						entry.setRegion(data.getString("region"));
						entry.setCity(data.getString("city"));
						entry.setCounty(data.getString("county"));
						entry.setIsp(data.getString("isp"));
						
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

	public static void main(String[] args) {
		IIpSeeker ips = new TaobaoIpSearch();
		
	}

}
