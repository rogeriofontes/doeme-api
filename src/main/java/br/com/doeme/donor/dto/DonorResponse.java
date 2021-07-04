package br.com.doeme.donor.dto;

import br.com.doeme.user.entiry.User;
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
    private User user;
}
