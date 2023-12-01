package com.hello;

import com.hello.BoardBeans.BoardVO;
import com.hello.BoardService.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/board")
public class helloController {
    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {
        model.addAttribute("list", boardService.getBoardList());

        return "index";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String boardList(Model model) {

        model.addAttribute("list", boardService.getBoardList());
        return "posts";
    }



    @RequestMapping(value = "/addpostform", method = RequestMethod.GET)
    public String addPostform() {
        return "addpostform";
    }

    @RequestMapping(value = "addpost",method = RequestMethod.POST)
    public String addPost(BoardVO vo){
        if(boardService.insertBoard(vo)==0){
            System.out.println("데이터 추가 실패");
        }else{
            System.out.println("데이터 추가 성공");
        }
        return "redirect:posts";
    }




    @RequestMapping(value = "/editform/{id}", method = RequestMethod.GET)
    public String editPostForm(@PathVariable("id") int id, Model model){
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("u",boardVO);
        return "editform";
    }

    @RequestMapping(value = "/deletepost/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") int id){
        if(boardService.deleteBoard(id)==0){
            System.out.println("데이터 삭제 실패");
        }else{
            System.out.println("데이터 삭제 성공");
        }
        return "redirect:../posts";
    }

    @RequestMapping(value = "/editpost",  method = RequestMethod.POST)
    public String editPost(BoardVO vo){
        if(boardService.updateBoard(vo)==0){
            System.out.println("데이터 수정 실패");
        }else{
            System.out.println("데이터 수정 성공");
        }
        return "redirect:posts";
    }

//    이 위로는 문제 일으키는 거 없음 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewPage(@PathVariable("id") int id,Model model){
        BoardVO boardVO = boardService.getBoard(id);
        model.addAttribute("u",boardVO);
        return "view";
    }




    }



