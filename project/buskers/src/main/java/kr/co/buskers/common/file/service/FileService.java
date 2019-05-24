package kr.co.buskers.common.file.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.buskers.repository.domain.File;

public interface FileService {
	public File uploadImage(MultipartFile multipartFile, String uriPath) throws Exception; 
	public int insertFile(kr.co.buskers.repository.domain.File file) throws Exception;
	public void deleteFile(int groupNo) throws Exception;
}
