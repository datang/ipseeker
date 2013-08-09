package com.weidou.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.weidou.ip.domain.Entry;
import com.weidou.ip.seeker.BaiduIpLocation;
import com.weidou.ip.seeker.IIpSeeker;
import com.weidou.ip.seeker.TaobaoIpSearch;

public class Test {

	public static void main(String[] args) throws IOException {
		IIpSeeker ips = new TaobaoIpSearch();
//		IIpSeeker ips = new BaiduIpLocation();

		BufferedReader br = new BufferedReader(new InputStreamReader(Test.class
				.getClassLoader().getResourceAsStream("ips")));

		String line = null;
		
		System.out.println("#########begin");
		while (br.ready()) {
			line = br.readLine().trim();
			if (line != null && line.isEmpty() != true) {
				Entry entry = ips.search(line);
				System.out.println(entry);
//				System.out.println(entry.getIp() + "\t" + entry.getCountry()
//						+ "\t" + entry.getArea() + "\t" + entry.getRegion()
//						+ "\t" + entry.getCity() + "\t" + entry.getCounty()
//						+ "\t" + entry.getIsp());
			}
		}
		br.close();
	}
}
