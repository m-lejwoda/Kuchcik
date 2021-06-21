package pl.saxatachi.kuchcik.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String created_date;
}
