package demo.websrh.util;

/**
 * Confファイル設定情報
 * ※Jsonでシリアライズ
 * @author tsutomu.kobayashi
 *
 */
public class ConfigBean {
	private String uri;
	private String max;
	private String logicClass;
	private String filePath;
	public String getLogicClass() {
		return logicClass;
	}
	public void setLogicClass(String logicClass) {
		this.logicClass = logicClass;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
}
