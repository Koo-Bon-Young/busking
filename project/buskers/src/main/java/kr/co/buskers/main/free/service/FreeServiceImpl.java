package kr.co.buskers.main.free.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.buskers.common.page.FreePageResult;
import kr.co.buskers.repository.domain.FreeBoard;
import kr.co.buskers.repository.domain.FreeBoardComment;
import kr.co.buskers.repository.domain.FreePage;
import kr.co.buskers.repository.domain.Like;
import kr.co.buskers.repository.domain.Member;
import kr.co.buskers.repository.mapper.FreeBoardMapper;

@Service
public class FreeServiceImpl implements FreeService {
	
	@Autowired
	private FreeBoardMapper mapper;
	
	public Map<String, Object> list(FreePage freePage) {
		Map<String, Object> map = new HashMap<>();

		map.put("searchType", freePage.getSearchType());
		map.put("input", freePage.getInput());
		map.put("sortType", freePage.getSortType());
		map.put("notifyList", mapper.selectNoticeBoard());
		map.put("list", mapper.selectBoard(freePage));
		map.put("pageResult", new FreePageResult(freePage.getPageNo(), mapper.selectBoardCount(freePage)));
		return map;
	}	
	
	public Map<String, Object> detail(int boardNo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		
		if (session.getAttribute("user") != null) {
			Like like = new Like();
			Member member = (Member)session.getAttribute("user");
			like.setMemberNo(member.getMemberNo());
			like.setBoardNo(boardNo);
			map.put("like", mapper.selectBoardIsLike(like));
			
			if (mapper.selectBoardIsLike(like) == null) {
				mapper.insertLikeStatus(like);
			}
			
		}
		mapper.updateBoardViewCount(boardNo);
		if (mapper.selectGroupNo(boardNo) != null  ) {
			map.put("file", mapper.selectFile(mapper.selectGroupNo(boardNo).getGroupNo()));
		}
		map.put("reply", mapper.selectReplyList(boardNo));
		map.put("comment", mapper.selectCommentList(boardNo));
		map.put("board", mapper.selectBoardByNo(boardNo));
		
		return map;
	}
	
	public Map<String, Object> sortList(FreePage freePage) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("searchType", freePage.getSearchType());
		map.put("input", freePage.getInput());
		map.put("sortType", freePage.getSortType());
		map.put("notifyList", mapper.selectNoticeBoard());
		map.put("list", mapper.selectBoard(freePage));
		map.put("pageResult", new FreePageResult(freePage.getPageNo(), mapper.selectBoardCount(freePage)));
		
		return map;
	}
	
	public Map<String, Object> insertComment(FreeBoardComment freeBoardComment) {
		Map<String, Object> map = new HashMap<>();
		
		mapper.insertComment(freeBoardComment);
		map.put("comment", mapper.selectCommentList(freeBoardComment.getBoardNo()));
		map.put("reply", mapper.selectReplyList(freeBoardComment.getBoardNo()));
		
		return map;
	}
	
	public Map<String, Object> insertReply(FreeBoardComment freeBoardComment) {
		
		Map<String, Object> map = new HashMap<>();
		
		mapper.insertReply(freeBoardComment);
		map.put("comment", mapper.selectCommentList(freeBoardComment.getBoardNo()));
		map.put("reply", mapper.selectReplyList(freeBoardComment.getBoardNo()));
		
		return map;
	}
	
	public Map<String, Object> updateComment(FreeBoardComment freeBoardComment) {
		
		Map<String, Object> map = new HashMap<>();
		
		mapper.updateComment(freeBoardComment);
		map.put("comment", mapper.selectCommentList(freeBoardComment.getBoardNo()));
		map.put("reply", mapper.selectReplyList(freeBoardComment.getBoardNo()));
		
		return map;
	}
	
	public Map<String, Object> listIsLikedComment(int memberNo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("isLikedComment", mapper.selectCommentIsLiked(memberNo));
		
		return map;
	}
	
	public Map<String, Object> listIsDislikedComment(int memberNo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("isDislikedComment", mapper.selectCommentIsDisliked(memberNo));
		
		return map;
	}
	
	public Map<String, Object> updateForm(int boardNo) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("board", mapper.selectBoardByNo(boardNo));
		return map;
	}
	
	public void write(FreeBoard freeBoard) {
		if (freeBoard.getGroupNo() == 0) {
			mapper.insertBoard(freeBoard);
		} else {
			mapper.insertBoardFile(freeBoard);
		}
	}
	
	public void deleteComment(int commentNo) {
		
		if (mapper.selectCommentHasReply(commentNo) == 0) {
			mapper.deleteComment(commentNo);
		} else {
			mapper.updateDeleteComment(commentNo);
		}
	}
	
	public void update(FreeBoard freeBoard) {
		if (freeBoard.getGroupNo() == 0) {
			mapper.updateBoard(freeBoard);
		} else {
			mapper.updateBoardFile(freeBoard);
		}
	}
	
	public void delete(FreeBoard freeBoard) {
		
		mapper.deleteBoard(freeBoard);
	}
	
	public int updateLikeStatus(Like like) {
		System.out.println("좋아요 상태 : " + like.getLikeStatus());
		
		mapper.updateLikeStatus(like);
		if (like.getLikeStatus() == 'y') {
			mapper.updateAddLike(like.getBoardNo());
		} else {
			mapper.updateRemoveLike(like.getBoardNo());
		}
		
		return mapper.selectBoardLikeCount(like.getBoardNo());
	}
	
	public Map<String, String> insertCommentLike(Like like) {
		Map<String, String> map = new HashMap<>();
		
		if (mapper.selectCommentLike(like) == null) {
			mapper.insertCommentLike(like);
			mapper.updateCommentLikePlus(like.getBoardNo());
			map.put("likeCount", "1");
		} else {
			if (mapper.selectCommentLike(like).getLikeStatus() == 'n') {
				mapper.updateCommentLikePlus(like.getBoardNo());
				mapper.updateCommentLikeStatusY(like.getBoardNo());
				map.put("likeCount", String.valueOf(mapper.selectCommentLikeCount(like.getBoardNo())));
				map.put("likeStatus", String.valueOf(mapper.selectCommentLike(like).getLikeStatus()));
				
				return map;
				
			} else {
				mapper.updateCommentLikeMinus(like.getBoardNo());
				mapper.updateCommentLikeStatusN(like.getBoardNo());
				map.put("likeCount", String.valueOf(mapper.selectCommentLikeCount(like.getBoardNo())));
				map.put("likeStatus", String.valueOf(mapper.selectCommentLike(like).getLikeStatus()));
				
				return map;
			}
		}
		return map;
	}
	
	public Map<String, String> insertCommentDislike(Like like) {
		Map<String, String> map = new HashMap<>();
		
		if (mapper.selectCommentDislike(like) == null) {
			mapper.insertCommentDislike(like);
			mapper.updateCommentDislikePlus(like.getBoardNo());
			map.put("dislikeCount", "1");
		} else {
			if (mapper.selectCommentDislike(like).getDislikeStatus() == 'n') {
				mapper.updateCommentDislikePlus(like.getBoardNo());
				mapper.updateCommentDislikeStatusY(like.getBoardNo());
				map.put("dislikeCount", String.valueOf(mapper.selectCommentDislikeCount(like.getBoardNo())));
				map.put("dislikeStatus", String.valueOf(mapper.selectCommentDislike(like).getDislikeStatus()));
				
				return map;
				
			} else {
				mapper.updateCommentDislikeMinus(like.getBoardNo());
				mapper.updateCommentDislikeStatusN(like.getBoardNo());
				map.put("dislikeCount", String.valueOf(mapper.selectCommentDislikeCount(like.getBoardNo())));
				map.put("dislikeStatus", String.valueOf(mapper.selectCommentDislike(like).getDislikeStatus()));
				
				return map;
			}
		}
		return map;
	}
}
