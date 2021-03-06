package kr.co.buskers.repository.domain;

import org.springframework.web.multipart.MultipartFile;

public class MusicFile {
	private MultipartFile attach;
	private MultipartFile attach2;
	private int fileNo;
	private String name;
	private String title;
	private String writer;
	private String sysname;
	private String path;
	private String imgPath;
	private String time;
	private String buskerName;
	private int buskerNo;
	private Busker busker;
	
	public Busker getBusker() {
		return busker;
	}
	public void setBusker(Busker busker) {
		this.busker = busker;
	}
	public MultipartFile getAttach2() {
		return attach2;
	}
	public void setAttach2(MultipartFile attach2) {
		this.attach2 = attach2;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getBuskerName() {
		return buskerName;
	}
	public void setBuskerName(String buskerName) {
		this.buskerName = buskerName;
	}
	public MultipartFile getAttach() {
		return attach;
	}
	public void setAttach(MultipartFile attach) {
		this.attach = attach;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getBuskerNo() {
		return buskerNo;
	}
	public void setBuskerNo(int buskerNo) {
		this.buskerNo = buskerNo;
	}
}
