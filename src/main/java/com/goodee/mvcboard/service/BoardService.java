package com.goodee.mvcboard.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
@Transactional 
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardfileMapper boardfileMapper;
	
	
	//REST API 차트 호출...
	public List<Map<String, Object>> getLocalNameList(){
		return boardMapper.selectLocalNameList();
	}
	
	//insertBoard
	public int addBoard(Board board, String path) {
		int row = boardMapper.insertBoard(board);
		
		//첨부된 파일이 1개 이상이 있다면
		List<MultipartFile> fileList = board.getMultipartFile();  
		if(row == 1 && fileList != null && fileList.size() > 0) {
			int boardNo = board.getBoardNo();
			
			for(MultipartFile mf : fileList) {
			if(mf.getSize() > 0) {
				Boardfile bf = new Boardfile();
				bf.setBoardNo(boardNo); // 부모테이블 키값
				bf.setOriginFilename(mf.getOriginalFilename()); // 파일원본 이름
				bf.setFilesize(mf.getSize()); // 파일 사이즈
				bf.setFiletype(mf.getContentType());
				// 저장된 파일이름
				// 파일 확장자
				String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
				// 새로운 이름
				bf.setSaveFilename(UUID.randomUUID().toString().replace("-", "") + ext);
				
				// 테이블에 저장
				boardfileMapper.insertBoardfile(bf);
				// 파일 저장(저장위치필요)
				File f = new File(path+bf.getSaveFilename()); // path위치에 저장파일이름으로 빈파일을 생성
				// 빈파일에 첨부된 파일의 스트림을 주입한다
				try {
					mf.transferTo(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					// 트랜잭셔ㅕㄴ 작동을 위해 예외를 발생 시켜야됌
					throw new RuntimeException(); // 예외를 처리를 강요하지 않게 해주는 놈
					}
				}
			}
			// X, 1번, 반복
		}
		
		return row;
	}
	
	public int removeBoard( String path, Boardfile boardfile) {
		List<Boardfile> bf = boardfileMapper.selectSavename(boardfile);
		log.debug(""+bf);
		
		if(bf.size() > 0) {
			// row2 == 1이면 하나만 삭제된걸로 간주되서 2개이상의 테이블이 삭제되면 사진이 삭제안되므로row2 > 0으로 수
			int row2 = boardfileMapper.deleteBoardfile(boardfile);
			if(row2 > 0) {
				for(Boardfile i : bf) {
					File file = new File(path+i.getSaveFilename());
				    file.delete();
				    
				}
			}
		
		
		
		}
		int row = boardMapper.deleteBoard(boardfile);
		return row;
	}
	
	public int modify(Board board) {
		return boardMapper.updateBoard(board);
	}
	
	public Board getBoardOne(Board board){
		Board boardOne = boardMapper.selectBoardOne(board);
		return  boardOne;
	}
	
	
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName){
		// service layer 역할 1 :  controller 가 넘겨준 매개값을 DAO의 매개값을 맞게 가공
		int beginRow =(currentPage-1)*rowPerPage; 
		
		// 반환값 1
		List<Map<String,Object>> localNameList = boardMapper.selectLocalNameList();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("localName", localName);
		
		// 반환값 2
		List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
		
		
		int boardCount = boardMapper.selectBoardCount();
		// service layer 역할 2 : DAO에서 반환 값을 controller에 맞게 가공
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage += 1;
		}
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("localNameList", localNameList);
		resultMap.put("boardList", boardList);
		resultMap.put("lastPage", lastPage);

		return resultMap;
	}
	
	
}
