package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardReposiotry boardReposiotry;

    //글 작성
    public void write(Board board) {
        boardReposiotry.save(board);
    }

    // 게시글 리스트 처리
   public List<Board> boardList() {
        return boardReposiotry.findAll();
   }

   // 특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardReposiotry.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id) {
        boardReposiotry.deleteById(id);
    }
}
