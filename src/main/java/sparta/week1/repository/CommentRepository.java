package sparta.week1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.week1.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByBoardId(Long boardId);
}
