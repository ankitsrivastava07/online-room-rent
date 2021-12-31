package onlineroomrent.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityConvertor {
    private ModelMapper modelMapper = new ModelMapper();

    public <D> D convertToEntity(Object source, Class<D> destinationType){
        return modelMapper.map(source,destinationType);
    }
}
