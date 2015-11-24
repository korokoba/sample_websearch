package demo.websrh;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HTTP Client共通ラッパー
 * @author tsutomu.kobayashi
 *
 */
public class WebClient {

	private static final Logger log = Logger.getLogger( WebClient.class );

	public static final int HTTP_STATUS_OK 				= 200;
	public static final int HTTP_STATUS_NOTFOUND 			= 404;


	protected final static String ENCODE 			= "UTF-8";

	protected final static String USER_AGENT = "Web Client";

	private final CloseableHttpClient httpClient;


	/**
	 * コンストラクタ
	 * @param config
	 * @param input
	 */
	public WebClient(final int timeout){
		// request configuration
		RequestConfig requestConfig = RequestConfig.custom()
		.setConnectTimeout(timeout)
		.setSocketTimeout(timeout)
		.build();
		// headers
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Accept-Charset",ENCODE));
		headers.add(new BasicHeader("Accept-Language","ja, en;q=0.8"));
		headers.add(new BasicHeader("User-Agent",USER_AGENT));
		// create client
		this.httpClient = HttpClientBuilder.create()
			.setDefaultRequestConfig(requestConfig)
			.setDefaultHeaders(headers).build();
	}

	/**
	 * ページ遷移
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getPage(final String url) throws Exception{
		log.info("get page="+url);
		final HttpGet httpGet = new HttpGet(url);
		final HttpResponse response = httpClient.execute(httpGet);
		final int responseStatus = response.getStatusLine().getStatusCode();
		showHttpStatusLog(url,responseStatus);
		if( responseStatus == HTTP_STATUS_NOTFOUND ){
			log.error("page not found");
			return null;
		}
		if( responseStatus != HTTP_STATUS_OK ){
			throw new Exception("http status error code="+responseStatus);
		}
		return EntityUtils.toString(response.getEntity(), ENCODE)
				.replaceAll("\\r", "")
				.replaceAll("\\n", "");
	}

	/**
	 * ログ表示
	 * @param url
	 * @param httpStatusCode
	 */
	protected void showHttpStatusLog(String url,final int httpStatusCode){
		if(httpStatusCode == HTTP_STATUS_OK){
			log.debug(url + ":http_status_code ="+httpStatusCode);
		} else {
			log.error(url + ":http_status_code ="+httpStatusCode);
		}
	}
}
