package com.LiveasyLogistics.Load.Service.ServiceImpl;

import com.LiveasyLogistics.Load.Entity.Load;
import com.LiveasyLogistics.Load.Repository.LoadRepositoy;
import com.LiveasyLogistics.Load.Service.LoadService;
import com.LiveasyLogistics.Load.dto.CreateLoadDto;
import com.LiveasyLogistics.Load.dto.ResponseLoad;
import com.LiveasyLogistics.Load.dto.UpdateLoadDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoadServiceImpl implements LoadService {

    @Autowired
    private LoadRepositoy loadRepositoy;

    @Autowired
    private ModelMapper modelMapper;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");

    @Override
    public ResponseLoad createLoad(CreateLoadDto createLoadDto) {
        Load load = modelMapper.map(createLoadDto,Load.class);
        if (createLoadDto.getDate() != null && !createLoadDto.getDate().isEmpty()) {
            try {
                load.setDate(String.valueOf(LocalDate.parse(createLoadDto.getDate(), formatter)));
            } catch (DateTimeParseException e) {
                throw new RuntimeException("Invalid date format. Please use 'dd-MM-yyyy'");
            }
        } else {
            load.setDate(String.valueOf(LocalDate.now()));
        }
        load.setShipperId(createLoadDto.getShipperId() != null ? createLoadDto.getShipperId() : "shipper:" + UUID.randomUUID());
        Load save = loadRepositoy.save(load);
        return modelMapper.map(save, ResponseLoad.class);
    }

    @Override
    public List<ResponseLoad> getLoadsByShipperId(String shipperId) {
        List<Load> load = loadRepositoy.findByShipperId(shipperId);
        if(load.isEmpty()){
            throw  new RuntimeException("loads not found with this shipper id");
        }
        return load.stream().map(loads -> modelMapper.map(loads, ResponseLoad.class)).collect(Collectors.toList());
    }

    @Override
    public ResponseLoad getLoadsById(Integer id) {
        Load load = loadRepositoy.findById(id).orElseThrow(()->new RuntimeException("Load Data not Found"));
        return modelMapper.map(load, ResponseLoad.class);
    }

    @Override
    public ResponseLoad updateLoads(UpdateLoadDto updateLoadDto, Integer id) {
        Load load = loadRepositoy.findById(id).orElseThrow(() -> new RuntimeException("Load Data not Found"));
        load.setLoadingPoint(updateLoadDto.getLoadingPoint());
        load.setUnloadingPoint(updateLoadDto.getUnloadingPoint());
        load.setProductType(updateLoadDto.getProductType());
        load.setTruckType(updateLoadDto.getTruckType());
        load.setNoOfTrucks(updateLoadDto.getNoOfTrucks());
        load.setWeight(updateLoadDto.getWeight());
        load.setComment(updateLoadDto.getComment());
        load.setDate(String.valueOf(LocalDate.parse(updateLoadDto.getDate(),formatter)));
        loadRepositoy.save(load);
        return modelMapper.map(load, ResponseLoad.class);
    }

    @Override
    public void deleteLoads(Integer id) {
        Load load = loadRepositoy.findById(id).orElseThrow(()->new RuntimeException("Load Data not Found"));
        loadRepositoy.delete(load);
    }
}
