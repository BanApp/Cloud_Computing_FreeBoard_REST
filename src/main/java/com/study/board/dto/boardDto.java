package com.study.board.dto;

import lombok.Data;

@Data
public class boardDto
{
    private int content_id;
    private String title;
    private String content;
    private String nickname;
    private String company;
    private long user_id;
}
