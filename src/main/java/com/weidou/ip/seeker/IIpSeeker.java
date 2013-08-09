package com.weidou.ip.seeker;

import com.weidou.ip.domain.Entry;

/**
 * 根据用户提供的IP地址，快速查询出该IP地址所在的地理信息和地理相关的信息，包括国家、省、市和运营商。
 * 
 * @author tzh
 * 
 */
public interface IIpSeeker {

	/**
	 * 根据用户提供的IP地址，快速查询出该IP地址所在的地理信息和地理相关的信息，包括国家、省、市和运营商。
	 * 
	 * @param ip ip地址
	 * @return
	 */
	public Entry search(String ip);
}
