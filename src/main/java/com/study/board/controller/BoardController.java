package com.study.board.controller;
import com.study.board.entity.Board;

import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm()
    {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, Integer id, MultipartFile file) throws Exception
    {
        boardService.write(board, file);

        model.addAttribute("message","글작성이 완료되었습니다!");
        model.addAttribute("searchUrl","/board/view?id=" + board.getId());
        //model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    @GetMapping("board/list")
    public String boardlist(Model model)
    {
        model.addAttribute("list",boardService.boardList());
        return "boardlist";
    }

    @GetMapping("board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model,Integer id){
        model.addAttribute("board",boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("board/delete") //localhost:8080/board/view?id=1
    public String boardDelete(Integer id,Model model){
        boardService.boardDelete(id);

        model.addAttribute("message","삭제완료!");
        model.addAttribute("searchUrl","/board/list");


        return "message";
    }

    @GetMapping("board/modify/{id}") //localhost:8080/board/view?id=1
    public String boardModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("board",boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("board/update/{id}") //localhost:8080/board/view?id=1
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception
    {

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp,file);

        return "redirect:/board/list";
    }
}
