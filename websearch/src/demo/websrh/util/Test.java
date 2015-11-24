package demo.websrh.util;



public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a="<tr><td class=\"newsDate\">2013年06月28日</td><td class=\"newsTitle\"><a href='http://www.tbs.co.jp/anime/oregairu/' target='_blank'>「やはり俺の青春ラブコメはまちがっている。」Blu-ray&amp;DVD第2巻発売決定！！</a></td></tr>";

		String date = (new Html(a))
				.doFilter("<td\\s*class=\"newsDate\">","</td>",true)
				.getElement();

		System.out.println(date);

////		String page="aaa<div class=\"newsListContents\"> aaaa</div>ggggggggggggggg";
//		String page="aaa<div class=\"newsListContents\"> <table class=\"stripeTable\"><tr><td class=\"newsDate\"></div>2015</div>ggggggggggggggg</div>aaaaaaaaa";
//		String reg = "<div\\s.*class=\"newsListContents\">(.*?)</div>";
//		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
//		Matcher m = p.matcher(page);
//		System.out.println(m.groupCount());
//		while(m.find()){
//			System.out.println(m.group());
//			}

	}

}
