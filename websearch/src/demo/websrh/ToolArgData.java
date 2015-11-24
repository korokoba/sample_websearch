package demo.websrh;

import org.apache.log4j.Logger;

import demo.websrh.util.ConfProperties;
import demo.websrh.util.ConfigBean;

/**
 * 実行パラメータ情報
 * @author tsutomu.kobayashi
 *
 */
public class ToolArgData {

	enum Index{
		TYPE(0,"type","target site"),
		KEYWORD(1,"keyword","search keyword"),
		;
		private final int index;
		private final String name;
		private final String comment;
		Index(final int index,final String name, final String comment){
			this.index = index;
			this.name = name;
			this.comment = comment;
		}
		int getIndex() {
			return index;
		}
		String getName() {
			return name;
		}
		String getComment() {
			return comment;
		}
		static String getInfo(){
			StringBuilder sb = new StringBuilder();
			sb.append("arg");
			for(Index i : values()){
				sb.append("\r\n");
				sb.append("arg["+i.getIndex() + "] : " + i.getName() + " " + i.getComment());
			}
			return sb.toString();
		}
		static String getInfo(final String[] args){
			StringBuilder sb = new StringBuilder();
			sb.append("arg");
			for(Index i : values()){
				String val = args.length > i.getIndex() ? args[i.getIndex()] : "";
				sb.append("\r\n");
				sb.append("arg["+i.getIndex() + "](" + i.getName() + ") = " + val);
			}
			return sb.toString();
		}
	}

	private static final Logger log = Logger.getLogger( ToolArgData.class );
	private final String[] args;
	private final ConfProperties prop;

	/**
	 * コンストラクタ
	 * @param args
	 * @param prop
	 */
	ToolArgData(final String[] args,final ConfProperties prop){
		this.args = args;
		this.prop = prop;
	}

	/**
	 * 入力チェック
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	boolean validate() throws Exception {
		for(String a: args)
			log.debug("arg"+a);
		if(this.args == null || this.args.length < 2) {
			log.error("parameter is invalid.");
			log.error(Index.getInfo());
			return false;
		}

		if(!this.prop.hasTypeConfig(this.args[Index.TYPE.getIndex()])){
			log.error("type is invalid. "+this.args[Index.TYPE.getIndex()]);
			return false;
		}

		return true;
	}

	public String getKeyWord(){
		return this.args[Index.KEYWORD.getIndex()];
	}

	public String getType(){
		return this.args[Index.TYPE.getIndex()];
	}

	ConfigBean getConfigBean() throws  Exception{
		return this.prop.getType(getType());
	}

	/**
	 * タイムアウト設定
	 * @return
	 * @throws Exception
	 */
	public int getTimeout(){
		int timeout = 5000;
		try{
			timeout = this.prop.getTimeOut();
		} catch(Exception e){
			log.error(e, e);
		}
		return timeout;
	}

	/**
	 * URI取得
	 * @return
	 */
	public String getURI(){
		String url = "";
		try{
			url = getConfigBean().getUri();
		} catch(Exception e){
			log.error(e, e);
		}
		return url;
	}

	/**
	 * タイムアウト設定
	 * @return
	 * @throws Exception
	 */
	public int getMaxPage(){
		int max = 0;
		try{
			max = Integer.valueOf(getConfigBean().getMax());
		} catch(Exception e){
			log.error(e, e);
		}
		return max;
	}

	/**
	 * 実行クラス名取得
	 * @return
	 */
	public String getLogicClassName(){
		String clsName = null;
		try{
			clsName = getConfigBean().getLogicClass();
		} catch(Exception e){
			log.error(e, e);
		}
		return clsName;
	}

	/**
	 * パラメータ情報
	 */
	void showParamInfo(){
		log.info(Index.getInfo(this.args));
	}
}
