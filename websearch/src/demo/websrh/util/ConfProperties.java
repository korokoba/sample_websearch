package demo.websrh.util;

import java.util.MissingResourceException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * conf.properties情報管理
 * @author tsutomu.kobayashi
 *
 */
public class ConfProperties extends PropertiesBase {
	private static final String CONF_FILE_PATH = "conf.properties";

	private static String KEY_EXEC_PREFIX = "exec.";
	private static String KEY_TIME_OUT = "timeout";

	private static final Logger log = Logger.getLogger(ConfProperties.class);

	private ConfigBean bean = null;

	public void init() throws Exception {
		super.init(CONF_FILE_PATH);
	}

	public boolean hasTypeConfig(final String key) throws MissingResourceException {
		return hasProperty(KEY_EXEC_PREFIX + key);
	}

	/**
	 * 処理別Bean取得
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ConfigBean getType(final String key) throws Exception {
		if (this.bean != null)
			return this.bean;
		final Gson gson = new Gson();
		final String json = getString(KEY_EXEC_PREFIX + key);
		log.debug("conf json =" + json);
		this.bean = gson.fromJson(json, new TypeToken<ConfigBean>() {
		}.getType());
		if (this.bean == null)
			throw new Exception("conf key is  not found.");
		return this.bean;
	}

	/**
	 * 共通タイムアウト設定取得
	 * @return
	 * @throws MissingResourceException
	 * @throws NumberFormatException
	 */
	public int getTimeOut() throws MissingResourceException, NumberFormatException {
		return getInt(KEY_TIME_OUT);
	}

}
