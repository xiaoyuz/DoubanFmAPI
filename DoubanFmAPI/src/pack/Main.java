package pack;

import java.lang.reflect.Field;
import java.util.List;

import com.alibaba.fastjson.JSON;

import domain.LoginRequest;
import domain.LoginResult;
import domain.PlayListRequest;
import domain.PlayListResult;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setApp_name("radio_desktop_win");
		loginRequest.setVersion("100");
		loginRequest.setEmail("alexanderxiaoyu@163.com");
		loginRequest.setPassword("2858300wc");
		
		String content = HttpUtils.postForm(loginRequest.toNVPair(), UrlUtil.LOGIN_URL);
		System.out.println(content);
		
		LoginResult loginResult = JSON.parseObject(content, LoginResult.class);
		System.out.println();
		
		PlayListRequest playListRequest = new PlayListRequest();
		playListRequest.setChannel("3478495");
		playListRequest.setFrom("mainsite");
		playListRequest.setPb("64");
		playListRequest.setPt("21.5");
		playListRequest.setR("3290a89ae7");
		playListRequest.setSid("1790295");
		playListRequest.setStart("1478495g9deeg3478495");
		playListRequest.setType("n");
		
		content = HttpUtils.getWithParams(playListRequest.toQueryMap(), UrlUtil.MINE_PLAY_LIST);
		
		System.out.println(content);
		
		PlayListResult playListResult = JSON.parseObject(content, PlayListResult.class);
		System.out.println(playListResult);
		
		playListRequest = new PlayListRequest();
		playListRequest.setChannel("3478495");
		playListRequest.setFrom("mainsite");
		playListRequest.setPb("64");
		playListRequest.setPt("94.2");
		playListRequest.setR("f33287a468");
		playListRequest.setSid("472846");
		playListRequest.setType("s");
		
		content = HttpUtils.getWithParams(playListRequest.toQueryMap(), UrlUtil.MINE_PLAY_LIST);

		playListResult = JSON.parseObject(content, PlayListResult.class);
		System.out.println(playListResult);
	}

}
