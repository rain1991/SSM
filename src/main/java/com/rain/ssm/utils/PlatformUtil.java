/**
 * @package com.clps.jfh.common.util.platform
 * @desc  第三方平台工具类
 * @auto longyuzichen@126.com
 * @date 2016年12月9日 上午10:21:05
 * @version V1.0 
 * @param 
 */
package com.rain.ssm.utils;

import java.util.Date;

public class PlatformUtil {

	/**
	 * 
	 * @desc 生成第三方平台订单号
	 * @auto longyuzichen@126.com
	 * @date 2016年12月9日 上午10:27:46
	 * @param type 第三方平台类型
	 * @param userid 用户id
	 * @return
	 */
	public static String createPlatformOreadCode(String type, String userid) {
		StringBuilder sb = new StringBuilder();
		sb.append(type).append(userid).append(DateUtil.date2Str("yyyyMMddHHmmss", new Date()))
				.append(String.valueOf((int) (Math.random() * 9000) + 1000));
		return sb.toString();
	}
}
