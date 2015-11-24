package demo.websrh;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * クライアント処理
 * @author tsutomu.kobayashi
 *
 */
public abstract class ClientLogic {
	private final ToolArgData argData;
	private final static String dateString;
	static {
		dateString = (new SimpleDateFormat("yyyyMMddHHmm")).format(new Date());
	}
	/**
	 * コンストラクタ
	 * @param argData
	 * @param input
	 * @param prop
	 */
	protected ClientLogic(final ToolArgData argData){
		this.argData = argData;
	}

	/**
	 * パラメータ取得
	 * @return
	 */
	public ToolArgData getArgData() {
		return argData;
	}

	/**
	 * 出力ファイル名取得
	 * @return
	 * @throws Exception
	 */
	public String getFileName() throws Exception {
		return this.argData.getConfigBean().getFilePath() + "_" + dateString;
	}

	/**
	 * 処理
	 * @throws Exception
	 */
	protected abstract void execute() throws Exception;

}
