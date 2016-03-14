package org.leadfar.egov.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;

public class UpLoadServlet extends HttpServlet {
	private String fileSavePath="e:\\";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String enterpriseId = "0";
		String fileName = "";
		String path = "";
		
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload();

			// Parse the request
			FileItemIterator iter = null;
			try {
				iter = upload.getItemIterator(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					String key = item.getFieldName();
					//String name = FilenameUtils.getName(item.getFieldName());
					InputStream stream = item.openStream();
					if (item.isFormField()) {
						enterpriseId = Streams.asString(stream);//TODO:这里获得ENTERPRISEID
					} else {
						fileName = FilenameUtils.getName(item.getName());
						
						path = fileSavePath+enterpriseId+"\\" + fileName;
						File f = new File(path);
						f.getParentFile().mkdirs();
						OutputStream out = new FileOutputStream(f);
						long length = Streams.copy(stream, out, true);
						
						String url = request.getContextPath()+"register/ep/EnterpriseServlet?m="+
						enterpriseId+"&fileName="+ URLEncoder.encode(fileName, "utf-8") + "&path" +
						URLEncoder.encode(path, "utf-8") + "&length" + length;
						response.sendRedirect(url);
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect(request.getContextPath() + "/fileUpload.jsp");

	}
}
