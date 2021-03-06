package kr.co.buskers.artist.board.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.buskers.repository.domain.Alarm;
import kr.co.buskers.repository.domain.ArtistShow;
import kr.co.buskers.repository.domain.Genre;
import kr.co.buskers.repository.domain.Member;
import kr.co.buskers.repository.domain.Video;
import kr.co.buskers.repository.domain.VideoPage;

public interface ArtistBoardService {
	
	List<ArtistShow> selectArtistShow(int buskerNo);
	ArtistShow selectArtistShowByNo(int showNo);
	List<Genre> selectGenre();
	
	void insertArtistShow(ArtistShow artistShow);
	void insertShowAlarmIsReadStatus(Alarm alarm, HttpSession session);
	
	void updateArtistShowByNo(ArtistShow artistShow);
	void updateWeather(ArtistShow artistShow);
	
	void insertVideo(Video video);
	
	void deleteArtistShowByNo(int showNo);
	HashMap<String,Object> selectVideo(int buskerNo, VideoPage videoPage);
	void deleteVideo(int videoNo);
	
	List<Member> selectFollowMembers(int buskerNo);
	
}
