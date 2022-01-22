package onlineroomrent.dto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class PostProperty {

    @NotNull(message = "Please enter property type")
    private String propertyType;
    @NotNull(message = "Please enter Property category id")
    private Long productCategory;
    @NotNull(message = "Please enter city id")
    private Long city;
    @NotNull(message = "Please enter state id ")
    private Long state;
    @NotNull(message = "Please enter country id")
    private Long country;
    @NotNull(message = "Please enter property title")
    private String propertyTitle;
    @NotNull(message = "Please enter property description")
    private String propertyDescription;
    @NotNull(message = "Please enter furnished")
    private String furnished;
    @NotNull(message = "Please enter room set")
    private String roomSet;
    @NotNull(message = "Please enter alternate mobile number")
    private String alternateMobile;
    @NotNull(message = "Please enter address")
    private String address;
    @NotNull(message = "Please enter floor")
    private String floor;
    @NotNull(message = "Please enter out of floor")
    private String outOf;
    @NotNull(message = "Please enter rent price")
    private String rentPrice;
    private MultipartFile image1;
    private MultipartFile image2;
}
