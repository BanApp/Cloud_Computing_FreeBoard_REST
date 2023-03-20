package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardReposiotry boardReposiotry;

    //글 작성
    public void write(Board board, MultipartFile file) throws Exception
    {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);
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
