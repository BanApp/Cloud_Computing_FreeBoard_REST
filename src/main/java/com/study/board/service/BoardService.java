package com.study.board.service;

import com.study.board.dto.BoardRequestDto;
import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성
    public void write(BoardRequestDto board) {
        boardRepository.save(
                new Board(
                        0,
                        board.getTitle(),
                        board.getContent(),
                        board.getNickname(),
                        board.getCompany(),
                        board.getUser_id()
                )
        );
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).orElse(null);
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardRepository.deleteById(id);
    }

    // 게시글 업데이트
    public void boardUpdate(Board board) {
        Board existingBoard = boardRepository.findById(board.getId()).orElse(null);
        if (existingBoard != null) {
            existingBoard.setTitle(board.getTitle());
            existingBoard.setContent(board.getContent());
            boardRepository.save(existingBoard);
        }
    }
}