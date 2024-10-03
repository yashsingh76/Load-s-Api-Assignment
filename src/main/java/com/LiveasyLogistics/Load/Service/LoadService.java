package com.LiveasyLogistics.Load.Service;

import com.LiveasyLogistics.Load.dto.CreateLoadDto;
import com.LiveasyLogistics.Load.dto.ResponseLoad;
import com.LiveasyLogistics.Load.dto.UpdateLoadDto;

import java.util.List;

public interface LoadService {


    ResponseLoad createLoad(CreateLoadDto createLoadDto);

    List<ResponseLoad> getLoadsByShipperId(String shipperId);

    ResponseLoad getLoadsById(Integer id);

    ResponseLoad updateLoads(UpdateLoadDto updateLoadDto, Integer id);

    void deleteLoads(Integer id);
}
