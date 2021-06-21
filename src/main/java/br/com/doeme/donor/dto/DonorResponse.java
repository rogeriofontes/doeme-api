package br.com.doeme.donor.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Data
public class DonorResponse implements Serializable {

    private Long id;
    private String name;
    private String local;
    private String email;
    private String code;
}
