package hyman.study.ssh.utils.operatefile;

import java.net.URL;

public class FileUtils {
	/**
	 * 获取文件的实际路径
	 * @param filePath 文件相对于根目录的路径
	 * @return
	 */
	public static String getRealPath(String filePath){
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
		URL url=classLoader.getResource(filePath);
		return url.getPath();
	}
}
