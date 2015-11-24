package demo.websrh.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import demo.websrh.ClientLogic;
import demo.websrh.ToolArgData;
import demo.websrh.WebClient;
import demo.websrh.util.FileWriter;
import demo.websrh.util.Html;

/**
 * ニュース情報取得(Marvelous)
 * @author tsutomu.kobayashi
 *
 */
public class MarvelousLogic extends ClientLogic{
	private final WebClient client;

	/**
	 * コンストラクタ
	 * @param argData
	 */
	public MarvelousLogic(ToolArgData argData) {
		super(argData);
		this.client = new WebClient(argData.getTimeout());
	}

	private static final Logger log = Logger.getLogger( MarvelousLogic.class );

	@Override
	protected void execute() throws Exception {
		//ページ情報取得
		List<String> list = new ArrayList<String>();
		for(int p = 1;p <= getArgData().getMaxPage() ;p++){
			List<String> eList = getPageInfo(p);
			if(eList == null)
				break;
			list.addAll(eList);
		}

		//ファイル書き出し
		(new FileWriter(new File(getFileName())){
			@Override
			protected String edit(String text) {
				String date = (new Html(text))
						.doFilter("<td\\s*class=\"newsDate\">","</td>",true)
						.getElement();
				String title = (new Html(text))
						.doFilter("<a\\s*href(.*?)>","</a>",true)
						.getElement();
				log.info(date+","+title);
				return date+","+title;
			}
		}).write(list);
	}

	/**
	 * 1ページ分情報取得
	 * @param pageMo
	 * @return
	 * @throws Exception
	 */
	private List<String> getPageInfo(final int pageMo) throws Exception {
		log.info("execute page " + pageMo);
		List<String> rtnList = new ArrayList<String>();
		String page = this.client.getPage(String.format(getArgData().getURI(),Integer.valueOf(pageMo)));
		if(page == null)
			return null;
		List<String> list = (new Html(page))
				.doFilter("<div\\s*class=\"newsListContents\">", "</div>")
				.doFilter("<tr>", "</tr>").
				getElementsList();
		for(String e : list){
			if(e.indexOf(getArgData().getKeyWord()) > 0) {
				log.info(e);
				rtnList.add(e);
			}
		}
		return rtnList;
	}
}
