package sparta.week1.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import sparta.week1.entity.dto.BoardDto;
import sparta.week1.entity.dto.CommentDto;
import sparta.week1.entity.timeSuper.Timestamped;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends Timestamped {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String createBy;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    //==============생성자================//

    public Board(BoardDto.Request boardReq){
        this.title = boardReq.getTitle();
        this.createBy = boardReq.getCreatedBy();
        this.content = boardReq.getContent();
    }

    //=================비지니스 로직====================//
    /**
     * request받은 값을 null로 검증해 수정 진행
     * dirty checking 을 통하여 수정진행
     * 검증로직이 너무나 중복됨 수정 가능성 농후 추후 리팩토링 필요
     * @param boardReq
     */
    public void patch(BoardDto.Request boardReq) {
        if (boardReq.getContent() != null) {
            this.content = boardReq.getContent();
        }
        if (boardReq.getTitle() != null) {
            this.title = boardReq.getTitle();
        }
        if (boardReq.getCreatedBy() != null) {
            this.createBy = boardReq.getCreatedBy();
        }

    }

    public Long addComment(CommentDto.Request commentReq) {
        Comment comment = new Comment(commentReq.getContent(), this);
        this.comments.add(comment);
        return comment.getId();
    }

}
