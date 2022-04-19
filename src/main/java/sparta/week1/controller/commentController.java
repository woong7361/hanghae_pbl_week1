package sparta.week1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sparta.week1.entity.dto.CommentDto;
import sparta.week1.service.CommentService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class commentController {

    private final CommentService commentService;

    /**
     * 코멘트 생성 API
     */
    @PostMapping("/api/board/{boardId}/comment")
    public CommentDto.Response newComment(
            @PathVariable("boardId") Long boardId,
            @RequestBody CommentDto.Request commentReq) {
        return commentService.save(commentReq, boardId);
    }

    /**
     * 코멘트 조회 API
     */
    @GetMapping("/api/board/{boardId}/comment")
    public CommentDto.ResponseAll getComments(
            @PathVariable("boardId") Long boardId) {
        return commentService.getComments(boardId);
    }

    /**
     * 코멘트 삭제 API
     */
    @PostMapping("/api/board/{boardId}/comment/{commentId}/delete")
    public void removeComment(@PathVariable("commentId") Long commentId) {
        commentService.remove(commentId);
    }

    /**
     * 코멘트 수정 API
     */
    @PostMapping("/api/board/{boardId}/comment/{commentId}/patch")
    public Long patchComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentDto.Request commentReq
    ) {
        return commentService.patch(commentId, commentReq.getContent());
    }
}
