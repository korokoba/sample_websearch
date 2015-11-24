package demo.websrh.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTML操作
 * @author koba
 *
 */
public class Html {
	private final List<String> elementsList;
	/**
	 * コンストラクタ
	 * @param tag
	 */
	public Html(String tag){
		this.elementsList = new ArrayList<String>();
		this.elementsList.add(tag);
	}
	/**
	 * コンストラクタ
	 * @param elements
	 */
	public Html(final List<String> elements){
		this.elementsList = elements;
	}

	/**
	 * 結果取得
	 * @return
	 */
	public List<String> getElementsList() {
		return elementsList;
	}

	/**
	 * 結果取得
	 * @return
	 */
	public String getElement() {
		return elementsList.size() > 0 ? elementsList.get(0) : "";
	}

	/**
	 * 要素抽出
	 * @param startTag
	 * @param endTag
	 * @return
	 */
	public Html doFilter(final String startTag,final String endTag){
		return doFilter(startTag,endTag,false);
	}

	/**
	 * タグで囲まれた部分抽出
	 * @param startTag
	 * @param endTag
	 * @param removetag
	 * @return
	 */
	public Html doFilter(final String startTag,final String endTag,boolean removetag){
		List<String> list = new ArrayList<String>();

		Pattern p = Pattern.compile(startTag+"(.*?)"+endTag, Pattern.CASE_INSENSITIVE);
		for(String e : this.elementsList){
			Matcher m = p.matcher(e);
			while(m.find()){
				String s = m.group();
				if(removetag){
					s = m.group().replaceAll(startTag, "").replaceAll(endTag, "");
				}
				list.add(s);
			}
		}
		return new Html(list);
	}
}
