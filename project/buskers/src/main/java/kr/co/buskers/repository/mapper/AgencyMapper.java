package kr.co.buskers.repository.mapper;

import java.util.List;
import java.util.Map;

import kr.co.buskers.repository.domain.Agency;
import kr.co.buskers.repository.domain.AgencyGenre;
import kr.co.buskers.repository.domain.AgencyInfo;
import kr.co.buskers.repository.domain.AgencyPage;
import kr.co.buskers.repository.domain.Genre;

public interface AgencyMapper {
	List<AgencyInfo> selectAgencyInfo(AgencyPage page);
	int selectAgencyInfoCount();
	AgencyInfo selectAgencyInfoByNo(int agencyInfoNo);
	List<AgencyGenre> selectAgencyGenreByNo(int agencyInfoNo);
	List<Genre> selectGenre();
	String selectAgencyCode(int agencyInfoNo);
	Agency selectAgencyByNo(int memberNo);
	Integer selectAgencyInfoNoByAgencyCode(String agencyCode);
	List<Integer> selectAgencyByAgencyInfoNo(int agencyInfo);
	
	int checkAgencyCode(String agencyCode);
	Agency checkAgency(Agency agency);
	
	void insertAgencyInfo(AgencyInfo agencyInfo);
	void insertAgencyGenre(AgencyGenre agencyGenre);
	void insertAgency(Agency agency);
	
	void deleteAgencyInfoByNo(int agencyInfoNo);
	void deleteAgencyGenreByNo(int agencyInfoNo);
	void deleteAgencyByNo(int agencyInfoNo);
	void deleteAgencyByMemberNo(int memberNo);
	void deleteAgency(int agencyNo);
	
	void updateAgencyInfo(AgencyInfo agencyInfo);
	void updateAgencyPermission(AgencyInfo AgencyInfo);
	void updateAgencyCode(AgencyInfo agencyInfo);
	void updateMemberAgency(AgencyInfo agencyInfo);
	void updateAgencyMembers(Map<String,Object> map);
}
