package com.spring.mvc.chap05.entity;

/*
    create table tbl_reply
    (
    reply_no     INT(10) auto_increment,
    reply_text   VARCHAR(1000) not null,
    reply_writer VARCHAR(100)  not null,
    reply_date   DATETIME default current_timestamp,
    board_no     INT(10),
    constraint pk_reply primary key (reply_no),
    constraint fk_reply
        foreign key (board_no)
            references tbl_board (board_no)
            on delete cascade
    );
*/

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    private long replyNo;
    @Setter
    private String replyText;
    @Setter
    private String replyWriter;
    private LocalDateTime replyDate;
    private long boardNo;
    @Setter
    private String account;
    private String profileImage;

}
