package demo.websrh;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

import demo.websrh.util.ConfProperties;

/**
 * 起動
 * @author tsutomu.kobayashi
 *
 */
public class Main {
	private static final Logger log = Logger.getLogger( Main.class );

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("ver." + Constant.VERSION);

		try{
			log.info("load properties start ******************");
			//設定ファイル読み込み
			ConfProperties prop = new ConfProperties();
			prop.init();
			log.info("load properties end ******************");

			//パラメータチェック
			ToolArgData argData = new ToolArgData(args,prop);
			if(!argData.validate())
				return;
			argData.showParamInfo();

			final ClientLogic clientLogic = getClientLogic(argData);
			log.debug("client logic" + clientLogic.getClass().getSimpleName());
			log.info("logic start ******************");
			clientLogic.execute();
			log.info("logic end ******************");
			log.info("finish.");
		}catch(Exception e){
			log.error(e,e);
		}
	}

	/**
	 * ロジックインスタンス取得
	 * @param argData
	 * @param prop
	 * @return
	 * @throws Exception
	 */
	private static ClientLogic getClientLogic(final ToolArgData argData) throws Exception{
		Class<?>[] types = { ToolArgData.class };
		Class<?> clazz = Class.forName("demo.websrh.impl." + argData.getLogicClassName());
		Constructor<?> cons = clazz.getConstructor(types);
		Object[] args = {argData};
		return (ClientLogic)cons.newInstance(args);
	}
}
