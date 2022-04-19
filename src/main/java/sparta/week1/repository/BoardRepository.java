package sparta.week1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.week1.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    //rule spring data JPA or make native Query of sorting
    List<Board> findAllByOrderByCreatedAtDesc();

}
