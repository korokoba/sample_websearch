package demo.websrh.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * ファイル書き込み
 * @author tsutomu.kobayashi
 *
 */
public abstract class FileWriter {
	private final File file;
	/**
	 * コンストラクタ
	 * @param file
	 */
	public FileWriter(final File file){
		this.file = file;
	}
	/**
	 * 書き込み
	 * @param text
	 * @throws Exception
	 */
	public void write (final List<String> textList) throws Exception {
		FileOutputStream outStream= null;
		OutputStreamWriter outStreamWriter = null;
		try{
			outStream = new FileOutputStream(this.file.getAbsolutePath());
			outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
			for(String text : textList){
				outStreamWriter.write(edit(text));
				outStreamWriter.write("\r\n");
			}
		}finally{
			if(outStreamWriter != null)
				outStreamWriter.close();
			if(outStream != null)
				outStream.close();
		}
	}

	abstract protected String edit(final String text);
}
