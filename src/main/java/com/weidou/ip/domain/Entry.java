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
	private String street; // 街道
	private String isp; // 运营商
	private String point; // 经纬度坐标值

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
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		json.put("ip", this.ip);
		json.put("country", this.country);
		json.put("area", this.area);
		json.put("region", this.region);
		json.put("city", this.city);
		json.put("county", this.county);
		json.put("street", this.street);
		json.put("isp", this.isp);
		json.put("point", this.point);
		return json;
	}

	public String toString() {
		return this.toJSONObject().toString();
	}
}
