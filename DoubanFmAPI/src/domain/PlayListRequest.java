package domain;

import java.util.HashMap;
import java.util.Map;

import pack.JsonUtil;

public class PlayListRequest extends AbstractRequest {
	/*
	 * b: bye(?), 播放以删除终止. 长报告
		e: end, 返回:'"OK"', 报告歌曲播放完毕, 短报告
		n: new, 返回新播放列表, 无其余必备参数(uid?). 长报告
		p: playing(?), 单首歌曲播放开始且播放列表已空时发送, 长报告, 疑似是专门为平淡地获取播放列表而设定的.
		s: skip, 用户点击”下一首“时即时报告
		u: unlike, 将sid的歌曲取消喜欢
		r: rated(?), 喜欢一首歌时即时报告
		*/
	protected String type;
	protected String sid;
	protected String pt;
	protected String channel;
	protected String pb;
	protected String start;
	protected String from;
	protected String r;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPb() {
		return pb;
	}

	public void setPb(String pb) {
		this.pb = pb;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

}
