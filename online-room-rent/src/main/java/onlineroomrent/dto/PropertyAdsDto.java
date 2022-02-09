package onlineroomrent.dto;
import lombok.Getter;
import lombok.Setter;
import onlineroomrent.dao.entity.PropertyImages;
import java.util.List;
@Getter
@Setter
public class PropertyAdsDto {
    private Long id;
    private String propertyType;
    private String slugName;
    private Boolean isPopular=Boolean.TRUE;
    private String description;
    private String sqrtFit;
    private String furnished;
    private String title;
    private String roomSet;
    private String floor;
    private List<PropertyImages> images;
    private AddressDto address;
}
