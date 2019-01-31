package wuds.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import wuds.entity.User;
import wuds.util.ZipUtil;


@WebServlet("/manage/download")
public class WorkDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String workPath = "/work";
    public WorkDownload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		User user = (User) request.getSession().getAttribute("user");
		String course = request.getParameter("course");
		String expNo = request.getParameter("expNo");
		
		String workFilePath = request.getServletContext().getRealPath(workPath) +
				File.separator + user.getGrade() + course + expNo;//作业文件路径
		
		//打包压缩作业
		ZipUtil.compressFolder(workFilePath + ".zip", workFilePath);
		
		//设置下载文件名以及编码
		String filename = workFilePath.substring(workFilePath.lastIndexOf(File.separator) + 1) + ".zip";
		String encoderFilename = filename;
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {    
			encoderFilename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//firefox浏览器    
		} else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {    
			encoderFilename = URLEncoder.encode(filename, "UTF-8");//IE浏览器    
		}else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0) {
			encoderFilename = new String(filename.getBytes("UTF-8"), "ISO8859-1");//谷歌    
		}
		//设置浏览器下载响应
		response.addHeader("Content-Type", "application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=" + encoderFilename);
		//设置文件输入流
		InputStream inputStream = new FileInputStream(workFilePath + ".zip");
		//设置输出流
		OutputStream outputStream = response.getOutputStream();
		//将输入流拷贝到输出流
		IOUtils.copy(inputStream, outputStream);
		inputStream.close();
		outputStream.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
