package kr.co.buskers.common.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.buskers.common.file.service.FileService;

@RequestMapping("/file")
@Controller
public class FileController {
	
	@Autowired
	private FileService service;
	
	@RequestMapping("insert-file-ajax.do")
	@ResponseBody
	public int insertFile(kr.co.buskers.repository.domain.File file) throws Exception {
		return service.insertFile(file);
	}
	
	@RequestMapping("delete-file-ajax.do")
	@ResponseBody
	public void deleteFile(int groupNo) throws Exception {
		service.deleteFile(groupNo);
	}
	
	@RequestMapping("image-upload.do")
	@ResponseBody
	public kr.co.buskers.repository.domain.File FileUpload(MultipartFile file, String uriPath) throws Exception {
        return service.uploadImage(file, uriPath);
	}
	
	@RequestMapping("download.do")
	public void download(String path, HttpServletResponse response) throws IOException {

		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		OutputStream out = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		
		while(true) {
			int ch = bis.read();
			if (ch == -1) {
				break;
			}
			bos.write(ch);
		}
		bis.close();
		fis.close();
		bos.close();
		out.close();
	}
	
	@RequestMapping("download-file.do")
	public void fileDownload(kr.co.buskers.repository.domain.File file, HttpServletResponse response) throws IOException {
		
		String dName = file.getName();
		
		File f = new File(file.getPath(), file.getSystemName());
		
		if (file.getName() == null) {
			response.setHeader("Content-Type", "image/jpg");
		} else { 
			response.setHeader("Content-Type", "application/octet-stream");
			dName = new String(file.getName().getBytes("utf-8"), "8859_1");
			response.setHeader("Content-Disposition", "attachment;filename=" + dName);
		}
		
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		OutputStream out = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		while (true) {
			int ch = bis.read();
			if (ch == -1) {
				break;
			}
			bos.write(ch);
		}
		bis.close();
		bos.close();
		bis.close();
		out.close();
	}
}
