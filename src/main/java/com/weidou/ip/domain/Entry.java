package com.weidou.ip.domain;

import net.sf.json.JSONObject;

/**
 * ip地址详细内容
 * 
 * @author tzh
 *
 */
public class Entry {
	private String ip; // IP地址
	private String country; // 国家
	private String area; // 地区
	private String region; // 省
	private String city; // 市
	private String county; // 区、县
	private String isp; // 运营商

	public Entry(JSONObject json) {
		this.setIp(json.getString("ip"));
		this.setCountry(json.getString("country"));
		this.setArea(json.getString("area"));
		this.setRegion(json.getString("region"));
		this.setCity(json.getString("city"));
		this.setCounty(json.getString("county"));
		this.setIsp(json.getString("isp"));
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}
	
	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("ip", this.ip);
		json.put("country", this.country);
		json.put("area", this.area);
		json.put("region", this.region);
		json.put("city", this.city);
		json.put("county", this.county);
		json.put("isp", this.isp);
		return json;
	}

	public String toString() {
		return this.toJSONObject().toString();
	}
}
