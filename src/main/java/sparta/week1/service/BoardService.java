package sparta.week1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.week1.entity.Board;
import sparta.week1.entity.dto.BoardDto;
import sparta.week1.repository.BoardRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long saveBoard(BoardDto.Request boardReq){
        Board board = new Board(boardReq);
        Board save = boardRepository.save(board);
        return save.getId();
    }

    public BoardDto.Response findBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("no Board"));
        return new BoardDto.Response(board);

    }

    public List<BoardDto.ResponseAll> findAllBoard() {
        List<Board> all = boardRepository.findAllByOrderByCreatedAtDesc();
        // 비어있을 때 검증 로직 추가하기

        return all.stream().map(BoardDto.ResponseAll::new).collect(Collectors.toList());
    }

    @Transactional
    public void patch(Long id, BoardDto.Request boardReq) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("no Board"));

        board.patch(boardReq);
    }

    @Transactional
    public void remove(Long id) {
        boardRepository.deleteById(id);        //entity가 비어있을경우 IllegalStatementException이 발생한다고 한다.
    }
}
