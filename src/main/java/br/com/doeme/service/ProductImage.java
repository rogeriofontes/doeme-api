package br.com.doeme.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tb_product_image", uniqueConstraints = @UniqueConstraint(columnNames = "imageUrl", name = "tb_product_image_uk"))
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode//(callSuper = true)
@ToString(callSuper = true, of = {"id", "imageUrl"})
@Builder
@Data
//@ApiModel(description = "Class representing a Product Image in the application.")
public class ProductImage implements Serializable {
    private static final long serialVersionUID = 2564098253712890631L; //extends IDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    //@ApiModelProperty(notes = "Unique identifier of the Product.", example = "1", required = true, position = 0)
    private Long id;

    //@ApiModelProperty(notes = "imageUrl.",
      //      example = "imageUrl", required = true, position = 1)
    @NotNull//(message = "{farm.uuid.notnull}")
    private String imageUrl;

}
