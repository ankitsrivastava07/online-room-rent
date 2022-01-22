package onlineroomrent.convertor;
import onlineroomrent.dao.entity.PropertyAdsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class DtoToEntityConvertor {
    private ModelMapper modelMapper = new ModelMapper();
    public <D> D convertToEntity(Object source, Class<D> destinationType){
        return modelMapper.map(source,destinationType);
    }

    public <D> List<D> convertToDto(List<PropertyAdsEntity> propertyAdsEntities, Class<D> destinationType){
        return propertyAdsEntities.stream().map(entity->modelMapper.map(entity,destinationType)).collect(Collectors.toList());
    }
}
