package kr.co.buskers.main.notice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.protocol.x.Notice;

import kr.co.buskers.repository.domain.NoticeBoard;
import kr.co.buskers.repository.domain.NoticePage;
import kr.co.buskers.repository.mapper.NoticeBoardMapper;


@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeBoardMapper mapper;
	
	public Map<String, Object> list(NoticePage noticePage) {
		Map<String, Object> map = new HashMap<>();
		map.put("list", mapper.selectBoard(noticePage));
		return map;
	}	

	public Map<String, Object> listAjax(NoticePage noticePage) {
		Map<String, Object> map = new HashMap<>();
		map.put("listAjax", mapper.selectBoard(noticePage));
		return map;
	}	
	
	public Map<String,Object> detail(int boardNo){
		Map<String,Object> map = new HashMap<>();
		map.put("board", mapper.selectBoardByNo(boardNo));
		return map;
	}

	@Override
	public void insert(NoticeBoard noticeboard) {
		mapper.insertBoard(noticeboard);
		
	}

	@Override
	public void update(NoticeBoard noticeBoard) {
		mapper.updateBoard(noticeBoard);
	}

	@Override
	public void delete(NoticeBoard noticeBoard) {
		mapper.deleteBoard(noticeBoard);
	}
}
