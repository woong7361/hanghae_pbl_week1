package sparta.week1.entity.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.week1.entity.Comment;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Request {
        @NotBlank
        private String content;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class Response {
        private Long commentId;
        private String msg;

        public Response(Long commentId) {
            this.commentId = commentId;
            this.msg = "success";
        }

        public Response(String msg) {
            this.commentId = null;
            this.msg = msg;
        }


    }
    @NoArgsConstructor
    @Getter
    public static class ResponseAll {
        private List<responseWithId> comments;

        public ResponseAll(List<Comment> comment) {
            this.comments = comment.stream()
                    .map(responseWithId::new)
                    .collect(Collectors.toList());
        }
    }

    @NoArgsConstructor
    @Getter
    public static class responseWithId {
        private String content;
        private Long id;

        public responseWithId(Comment comment) {
            this.content = comment.getContent();
            this.id = comment.getId();
        }
    }

}
