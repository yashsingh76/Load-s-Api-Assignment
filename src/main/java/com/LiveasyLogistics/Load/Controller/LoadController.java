package com.LiveasyLogistics.Load.Controller;

import com.LiveasyLogistics.Load.Service.LoadService;
import com.LiveasyLogistics.Load.dto.CreateLoadDto;
import com.LiveasyLogistics.Load.dto.ResponseLoad;
import com.LiveasyLogistics.Load.dto.UpdateLoadDto;
import com.LiveasyLogistics.Load.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping("")
    public ResponseEntity<Object> addLoad(@RequestBody CreateLoadDto createLoadDto){
        ResponseLoad load = loadService.createLoad(createLoadDto);
        return ResponseHandler.generateResponse("loads details added successfully", HttpStatus.CREATED,load);
    }

    @GetMapping("")
    public ResponseEntity<Object> getLoadsShipper(@RequestParam String shipperId){
        List<ResponseLoad> load = loadService.getLoadsByShipperId(shipperId);
        return ResponseHandler.generateResponse("Load Data fetched successfully",HttpStatus.FOUND,load);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoads(@PathVariable Integer id){
        ResponseLoad load = loadService.getLoadsById(id);
        return ResponseHandler.generateResponse("Load Data fetched successfully",HttpStatus.FOUND,load);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLoad(@RequestBody UpdateLoadDto updateLoadDto,@PathVariable Integer id){
        ResponseLoad load = loadService.updateLoads(updateLoadDto,id);
        return ResponseHandler.generateResponse("Load Data updated successfully",HttpStatus.OK,load);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoad(@PathVariable Integer id){
        loadService.deleteLoads(id);
        return ResponseHandler.generateResponse("Load Data deleted successfully",HttpStatus.OK,null);
    }
}
