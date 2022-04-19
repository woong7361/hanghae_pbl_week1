package sparta.week1.entity.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.week1.entity.Board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BoardDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotBlank
        private String createdBy;

        public Request(Board board) {
            this.title = board.getTitle();
            this.createdBy = board.getCreateBy();
            this.content = board.getContent();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String title;
        private String createdBy;
        private String content;
        private LocalDateTime createdAt;

        public Response(Board board) {
            this.title = board.getTitle();
            this.createdBy = board.getCreateBy();
            this.content = board.getContent();
            this.createdAt = board.getCreatedAt();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseAll {
        private Long id;
        private String title;
        private String createdBy;
        private LocalDateTime createdAt;


        public ResponseAll(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.createdBy = board.getCreateBy();
            this.createdAt = board.getCreatedAt();
        }
    }
}
