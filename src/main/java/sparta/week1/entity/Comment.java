package sparta.week1.entity;

import lombok.*;
import sparta.week1.entity.dto.CommentDto;
import sparta.week1.entity.timeSuper.Timestamped;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends Timestamped {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") //FK
    private Board board;

    //=========================생성자 메서드, 편의 메서드==========================//
    public Comment(String content, Board board){
        this.content = content;
        this.board = board;
    }


    public void patchContent(String content) {
        this.content = content;
    }
}
