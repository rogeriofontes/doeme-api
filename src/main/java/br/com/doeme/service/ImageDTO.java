package br.com.doeme.service;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class ImageDTO {
    private String description;
    private MultipartFile file;
}
