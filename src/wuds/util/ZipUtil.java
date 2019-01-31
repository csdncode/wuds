package wuds.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ZipUtil {
	/**压缩文件夹
	 * 只适用于单层文件夹结构，及待压缩的文件夹下全部为文件
	 * @param dest 文件的目的地址，需要包含文件名和扩展名
	 * @param src 源文件夹的地址
	 */
	public static void compressFolder(String dest, String src) {
		try {
			//创建zip输出流
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(dest));
			
			//创建缓冲输出流
			BufferedOutputStream  bos = new BufferedOutputStream(out);
			
			File srcFolder = new File(src);
			
			//获取文件夹下的文件列表
			File[] files = srcFolder.listFiles();
			
			//在zip中写入目录进入点
			out.putNextEntry(new ZipEntry(srcFolder.getName() + File.separator));
			
			//在zip的对应目录下写入文件的进入点
			for(int i = 0; i < files.length; i++) {
				//写入文件进入点
				out.putNextEntry(new ZipEntry(srcFolder.getName() + File.separator + files[i].getName()));
				FileInputStream fis = new FileInputStream(files[i]);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				//将文件写入到zip中
				int tag;
				while( (tag=bis.read()) != -1) {
					out.write(tag);
				}
				bis.close();
				fis.close();
			}
			bos.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
