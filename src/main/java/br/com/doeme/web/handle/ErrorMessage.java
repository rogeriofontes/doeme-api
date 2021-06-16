package br.com.doeme.web.handle;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode //(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Builder
@Data
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
}
