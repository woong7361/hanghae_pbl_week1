package sparta.week1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.week1.entity.Board;
import sparta.week1.entity.Comment;
import sparta.week1.entity.dto.CommentDto;
import sparta.week1.repository.BoardRepository;
import sparta.week1.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    @Transactional
    public CommentDto.Response save(CommentDto.Request commentReq, Long boardId) {
        if(commentReq.getContent() == null){
            return new CommentDto.Response("fail");
        }
        Board board = getBoard(boardId);
        Long commentId = board.addComment(commentReq);
        return new CommentDto.Response(commentId);
    }

    public CommentDto.ResponseAll getComments(Long boardId) {
//        Board board = getBoard(boardId);
        List<Comment> comments = commentRepository
                .findByBoardId(boardId)
                .stream()
                .sorted((c1, c2) -> {
                    LocalDateTime l1 = (LocalDateTime) c1.getCreatedAt();
                    LocalDateTime l2 = (LocalDateTime) c2.getCreatedAt();
                    return -l1.compareTo(l2);
                })
                .collect(Collectors.toList());

        return new CommentDto.ResponseAll(comments);
    }

    private Board getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalStateException("not exist Board id"));
        return board;
    }

    @Transactional
    public void remove(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public Long patch(Long commentId, String content) {
        Comment comment = getCommentById(commentId);
        comment.patchContent(content);
        return comment.getId();
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("not exist Board id"));
    }
}
