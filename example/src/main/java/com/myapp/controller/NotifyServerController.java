// @formatter:off
package com.myapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 回调
 *
 * @author Administrator
 *
 */
@Controller
@RequestMapping(method = RequestMethod.GET, value = "/notifyServer/")
public class NotifyServerController {
	private static final Logger log = Logger.getLogger(NotifyServerController.class);

	@RequestMapping(method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		Document doc = null;
		try {
			// 解析流，打印log
			InputStream in = request.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			String str = null;
			StringBuffer xmlfile = new StringBuffer();
			while ((str = bf.readLine()) != null) {
				xmlfile.append(str);
			}
			log.info(" --- xml body --- :" + xmlfile);
			doc = DocumentHelper.parseText(xmlfile.toString());
		} catch (DocumentException e) {
			log.error(" *** DocumentException ***", e);
		} catch (IOException e1) {
			log.error(" *** IOException ***", e1);
		}
		Element root = doc.getRootElement();
		String action = root.elementTextTrim("action");

		if (action.equals("CallInvite")) {
			// 呼叫发起通知
			parseCallInvite(root);
		} else if (action.equals("CallEstablish")) {
			// 呼叫建立通知
			parseCallEstablish(root);
		} else if (action.equals("Hangup")) {
			// 挂机通知
			parseHangup(root);
		}
	}

	/**
	 * 解析呼叫建立
	 *
	 * @param e
	 *            Element
	 * @return result
	 */
	private void parseCallInvite(Element e) {
		CallInvite call = new CallInvite();
		call.setType(e.elementTextTrim("type"));
		call.setAppId(e.elementTextTrim("appId"));
		call.setSubId(e.elementTextTrim("subId"));
		call.setCaller(e.elementTextTrim("caller"));
		call.setCalled(e.elementTextTrim("called"));
		call.setUserFlag(e.elementTextTrim("userFlag"));
		call.setSubType(e.elementTextTrim("subType"));
		call.setCallId(e.elementTextTrim("callId"));
		call.setDateCreated(e.elementTextTrim("dateCreated"));
		call.setUserData(e.elementTextTrim("userData"));
	}

	/**
	 * 解析发起呼叫
	 *
	 * @param e
	 *            Element
	 * @return result
	 */
	private void parseCallEstablish(Element e) {
		CallEstablish call = new CallEstablish();
		call.setType(e.elementTextTrim("type"));
		call.setAppId(e.elementTextTrim("appId"));
		call.setSubId(e.elementTextTrim("subId"));
		call.setCaller(e.elementTextTrim("caller"));
		call.setCalled(e.elementTextTrim("called"));
		call.setSubType(e.elementTextTrim("subType"));
		call.setCallId(e.elementTextTrim("callId"));
		call.setDateCreated(e.elementTextTrim("dateCreated"));
		call.setUserData(e.elementTextTrim("userData"));
	}

	/**
	 * 解析挂断请求
	 *
	 * @param e
	 *            Element
	 * @return result
	 */
	private void parseHangup(Element e) {
		CallHangup call = new CallHangup();
		call.setType(e.elementTextTrim("type"));
		call.setAppId(e.elementTextTrim("appId"));
		call.setSubId(e.elementTextTrim("subId"));
		call.setCaller(e.elementTextTrim("caller"));
		call.setCalled(e.elementTextTrim("called"));
		call.setStartTimeA(e.elementTextTrim("startTimeA"));
		call.setStartTimeB(e.elementTextTrim("startTimeB"));
		call.setEndTime(e.elementTextTrim("endTime"));
		call.setDuration(e.elementTextTrim("duration"));
		call.setSubType(e.elementTextTrim("subType"));
		call.setCallId(e.elementTextTrim("callId"));
		call.setRecordUrl(e.elementTextTrim("recordUrl"));
		call.setByeType(e.elementTextTrim("byeType"));
		call.setDateCreated(e.elementTextTrim("dateCreated"));
		call.setUserData(e.elementTextTrim("userData"));
	}

	/**
	 *
	 * 呼叫建立通知接口(详见文档)
	 *
	 * @author Administrator
	 *
	 */

	class CallEstablish {
		// 呼叫类型
		private String type;
		// 应用ID
		private String appId;
		// 子账号id
		private String subId;
		// 主叫号码
		private String caller;
		// 被叫号码
		private String called;
		// 主被叫标示(0标示主叫，1标示被叫)
		private String userFlag;
		// 外呼显号标示
		private String subType;
		// 呼叫的唯一标示
		private String callId;
		// 请求时间
		private String dateCreated;
		// 用户数据
		private String userData;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getSubId() {
			return subId;
		}

		public void setSubId(String subId) {
			this.subId = subId;
		}

		public String getCaller() {
			return caller;
		}

		public void setCaller(String caller) {
			this.caller = caller;
		}

		public String getCalled() {
			return called;
		}

		public void setCalled(String called) {
			this.called = called;
		}

		public String getUserFlag() {
			return userFlag;
		}

		public void setUserFlag(String userFlag) {
			this.userFlag = userFlag;
		}

		public String getSubType() {
			return subType;
		}

		public void setSubType(String subType) {
			this.subType = subType;
		}

		public String getCallId() {
			return callId;
		}

		public void setCallId(String callId) {
			this.callId = callId;
		}

		public String getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
		}

		public String getUserData() {
			return userData;
		}

		public void setUserData(String userData) {
			this.userData = userData;
		}

		@Override
		public String toString() {
			return "CallEstablish [type=" + type + ", appId=" + appId + ", subId=" + subId + ", caller=" + caller
					+ ", called=" + called + ", userFlag=" + userFlag + ", subType=" + subType + ", callId=" + callId
					+ ", dateCreated=" + dateCreated + ", userData=" + userData + "]";
		}
	}

	/**
	 * 呼叫挂机通知
	 *
	 * @author Administrator
	 *
	 */
	class CallHangup {
		// 呼叫类型
		private String type;
		// 应用ID
		private String appId;
		// 子账号id
		private String subId;
		// 主叫号码
		private String caller;
		// 被叫号码
		private String called;
		// 回拨时，为主叫接听时间；
		private String startTimeA;
		// 回拨时，为被叫接听时间
		private String startTimeB;
		// 通话结束时间
		private String endTime;
		// 通话时长。
		private String duration;
		// 外呼显号标示
		private String subType;
		// 呼叫的唯一标示
		private String callId;
		// 录音地址
		private String recordUrl;
		// 通话挂机类型
		private String byeType;
		// 请求时间
		private String dateCreated;
		// 用户数据
		private String userData;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getSubId() {
			return subId;
		}

		public void setSubId(String subId) {
			this.subId = subId;
		}

		public String getCaller() {
			return caller;
		}

		public void setCaller(String caller) {
			this.caller = caller;
		}

		public String getCalled() {
			return called;
		}

		public void setCalled(String called) {
			this.called = called;
		}

		public String getStartTimeA() {
			return startTimeA;
		}

		public void setStartTimeA(String startTimeA) {
			this.startTimeA = startTimeA;
		}

		public String getStartTimeB() {
			return startTimeB;
		}

		public void setStartTimeB(String startTimeB) {
			this.startTimeB = startTimeB;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getSubType() {
			return subType;
		}

		public void setSubType(String subType) {
			this.subType = subType;
		}

		public String getCallId() {
			return callId;
		}

		public void setCallId(String callId) {
			this.callId = callId;
		}

		public String getRecordUrl() {
			return recordUrl;
		}

		public void setRecordUrl(String recordUrl) {
			this.recordUrl = recordUrl;
		}

		public String getByeType() {
			return byeType;
		}

		public void setByeType(String byeType) {
			this.byeType = byeType;
		}

		public String getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
		}

		public String getUserData() {
			return userData;
		}

		public void setUserData(String userData) {
			this.userData = userData;
		}

		@Override
		public String toString() {
			return "CallHangup [type=" + type + ", appId=" + appId + ", subId=" + subId + ", caller=" + caller
					+ ", called=" + called + ", startTimeA=" + startTimeA + ", startTimeB=" + startTimeB + ", endTime="
					+ endTime + ", duration=" + duration + ", subType=" + subType + ", callId=" + callId
					+ ", recordUrl=" + recordUrl + ", byeType=" + byeType + ", dateCreated=" + dateCreated
					+ ", userData=" + userData + "]";
		}
	}

	/**
	 * 呼叫发起通知接口(详见文档)
	 *
	 * @author Administrator
	 *
	 */
	class CallInvite {
		// 呼叫类型
		private String type;
		// 应用ID
		private String appId;
		// 子账号id
		private String subId;
		// 主叫号码
		private String caller;
		// 被叫号码
		private String called;
		// 主被叫标示(0标示主叫，1标示被叫)
		private String userFlag;
		// 外呼显号标示
		private String subType;
		// 呼叫的唯一标示
		private String callId;
		// 请求时间
		private String dateCreated;
		// 用户数据
		private String userData;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getSubId() {
			return subId;
		}

		public void setSubId(String subId) {
			this.subId = subId;
		}

		public String getCaller() {
			return caller;
		}

		public void setCaller(String caller) {
			this.caller = caller;
		}

		public String getCalled() {
			return called;
		}

		public void setCalled(String called) {
			this.called = called;
		}

		public String getUserFlag() {
			return userFlag;
		}

		public void setUserFlag(String userFlag) {
			this.userFlag = userFlag;
		}

		public String getSubType() {
			return subType;
		}

		public void setSubType(String subType) {
			this.subType = subType;
		}

		public String getCallId() {
			return callId;
		}

		public void setCallId(String callId) {
			this.callId = callId;
		}

		public String getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
		}

		public String getUserData() {
			return userData;
		}

		public void setUserData(String userData) {
			this.userData = userData;
		}

		@Override
		public String toString() {
			return "CallInvite [type=" + type + ", appId=" + appId + ", subId=" + subId + ", caller=" + caller
					+ ", called=" + called + ", userFlag=" + userFlag + ", subType=" + subType + ", callId=" + callId
					+ ", dateCreated=" + dateCreated + ", userData=" + userData + "]";
		}

	}

}
