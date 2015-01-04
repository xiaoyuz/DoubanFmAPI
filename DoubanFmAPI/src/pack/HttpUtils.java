package pack;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpUtils {

	public final static String GAME_CENTER_HOST = "http://172.16.11.30:8091/platform-api";

	// public final static String GAME_CENTER_HOST =
	// OKBuildConfig.IS_TEST ? "http://cds.bj.oupeng.com/platform-api"
	// : "http://172.18.100.67:8084/platform-api";
	//
	// public final static String GAME_CENTER_HOST =
	// "http://172.16.11.197:8091/platform-api";

	// public final static String UPDATE_HOST =
	// "http://cds.bj.oupeng.com/platform-update";

	public final static String UPDATE_HOST = "http://update.okgame.im";

	private final static int TIMEOUT = 20000;

	private static final String TAG = "HttpUtils";

	public static String get(String url) {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);
			int rcode = response.getStatusLine().getStatusCode();
			if (rcode == 200) {
				String content = EntityUtils.toString(response.getEntity());
				return content;
			}
		} catch (ClientProtocolException e) {

		} catch (IOException e) {
		}
		return null;

	}
	
	public static String getWithParams(Map<String , String> paramMap, String url) {
		if (paramMap != null) {
			StringBuffer querySb = new StringBuffer("?");
			for (String key : paramMap.keySet()) {
				querySb.append(key).append("=").append(paramMap.get(key)).append("&");
			}
			querySb.deleteCharAt(querySb.length() - 1);
			
			url += querySb.toString();
		}
		
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		get.setHeader("Content-type", "application/x-www-form-urlencoded");
		
		try {		
			System.out.println("Excuting request : " + get.getURI());
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				int rcode = response.getStatusLine().getStatusCode();
				if (rcode == 200) {
					String content = EntityUtils.toString(response.getEntity(),
							"UTF-8");
					
					// 消除转移\
					content.replaceAll("\\\\", "");
					return content;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getBytes(String url) {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response;

		try {
			response = client.execute(get);
			int rcode = response.getStatusLine().getStatusCode();
			if (rcode == 200) {
				return EntityUtils.toByteArray(response.getEntity());
			}
		} catch (ClientProtocolException e) {

		} catch (IOException e) {

		}
		return null;
	}

	public static byte[] getImage(String path) {

		try {
			URL url = new URL(path);
			HttpURLConnection httpURLconnection = (HttpURLConnection) url
					.openConnection();
			httpURLconnection.setRequestMethod("GET");
			httpURLconnection.setReadTimeout(6 * 1000);
			InputStream in = null;
			if (httpURLconnection.getResponseCode() == 200) {
				in = httpURLconnection.getInputStream();
				byte[] result = readStream(in);
				in.close();
				return result;

			}
		} catch (Exception e) {

		}
		return null;
	}

	public static byte[] readStream(InputStream in) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = in.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
		in.close();
		return outputStream.toByteArray();
	}

	public static String postForm(List<NameValuePair> formParams, String url) {
		HttpClient client = HttpClients.createDefault();

		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/x-www-form-urlencoded");

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formParams);
			post.setEntity(uefEntity);
			System.out.println("Excuting request : " + post.getURI());

			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				int rcode = response.getStatusLine().getStatusCode();
				if (rcode == 200) {
					String content = EntityUtils.toString(response.getEntity(),
							"UTF-8");
					return content;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String postJson(String postText, String url) {

		HttpClient client = HttpClients.createDefault();

		HttpPost post = new HttpPost(url);
		post.setHeader("Content-type", "application/json");

		HttpResponse response;
		try {
			StringEntity entity = new StringEntity(postText, "UTF-8");
			post.setEntity(entity);
			response = client.execute(post);

			int rcode = response.getStatusLine().getStatusCode();
			if (rcode == 200) {
				String content = EntityUtils.toString(response.getEntity(),
						"UTF-8");
				return content;
			}
		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return null;
	}

}
