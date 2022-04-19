package sparta.week1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.week1.entity.dto.BoardDto;
import sparta.week1.repository.BoardRepository;
import sparta.week1.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시판 전체 조회 API
     */
    @GetMapping("/api/board/boards")
    public List<BoardDto.ResponseAll> getAllBoard(){
        return boardService.findAllBoard();
    }

    /**
     * 게시글 조회 API
     */
    @GetMapping("/api/board/{id}")
    public BoardDto.Response getBoard(@PathVariable("id") Long id) {
        return boardService.findBoard(id);
    }

    /**
     * 게시글 작성 API
     * 요청받은 파라미터가 부족할경우?
     * valid 추가
     */
    @PostMapping("/api/board")
    public Long createBoard(@RequestBody @Valid BoardDto.Request board){
        if(true) {
            throw new RuntimeException("runtime EX");
        }
        return boardService.saveBoard(board);
    }

    /**
     * 게시글 수정 API
     * 검증시 BoardDto.Reqeust 생성과 동일한 Dto 사용중 문제가 생길 가능성 농후
     * 해결 방법 찾아보기
     */
    @PostMapping("/api/board/{id}/patch")
    public void patchBoard(
            @PathVariable("id") Long id,
            @RequestBody BoardDto.Request board) {
        boardService.patch(id, board);
    }

    /**
     * 게시글 삭제 API
     * @param id
     */
    @PostMapping("/api/board/{id}/delete")
    public void deleteBoard(@PathVariable("id") Long id){
        boardService.remove(id);
    }

}
