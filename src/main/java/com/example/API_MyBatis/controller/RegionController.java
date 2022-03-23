package com.example.API_MyBatis.controller;

import com.example.API_MyBatis.domain.Region;
import com.example.API_MyBatis.domain.RegionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("region")
@Tag(name = "Обработчик запросов")
public class RegionController
{
    @Autowired
    private RegionRepository regionRepository;


    @PostMapping("add")
    @Operation(
            summary = "Добавление региона",
            description = "API принимает данные (сокращенное название, полное название) и записывает в базу данных"
    )
    //@Cacheable(value = "regions", key = "#region.name")
    public ResponseEntity<String> add(@RequestBody Region region)
    {
        if(region.getName() == null || region.getFullname() == null)
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);

        if(regionRepository.isExists(region.getName()))
            return new ResponseEntity<>("Such region already exists", HttpStatus.FORBIDDEN);

        regionRepository.insert(region);
        return ResponseEntity.ok("Added new region");
    }

    @PostMapping("delete")
    @Operation(
            summary = "Удаление региона",
            description = "API принимает сокращенное название региона и удаляет регион из базы данных"
    )
    public ResponseEntity<String> deleteByName(@RequestParam("name") String name)
    {
        if(name == null)
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        if(!regionRepository.isExists(name))
            return new ResponseEntity<>("Region is not found", HttpStatus.NOT_FOUND);

        Region region = regionRepository.findByName(name);
        String response = "Region " + region.getFullname() + " (" + region.getName() + ") is deleted";
        regionRepository.deleteById(region.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("find")
    @Operation(
            summary = "Поиск региона",
            description = "API принимает сокращенное название региона и извлекает строку из базы данных"
    )
    public ResponseEntity<String> findByName(@RequestParam("name") String name)
    {
        if(name == null)
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        if(!regionRepository.isExists(name))
            return new ResponseEntity<>("Region is not found", HttpStatus.NOT_FOUND);

        Region region = regionRepository.findByName(name);
        String response = "Region " + region.getFullname() + " (" + region.getName() + ") found with id: " + region.getId();
        return ResponseEntity.ok(response);
    }




    @GetMapping("showAll")
    @Operation(
            summary = "Показать все",
            description = "API отправляет ответ, содержащий информацию обо всех регионах"
    )
    public ResponseEntity<String> showAll()
    {
        Iterable<Region> regions = regionRepository.showAll();
        StringBuilder response = new StringBuilder();
        regions.forEach((region) -> response.append(region.toString()));

        if(response.length() == 0)
            return new ResponseEntity<>("Regions not found", HttpStatus.NOT_FOUND);
        else
            return ResponseEntity.ok(response.toString());
    }

    @PostMapping("change")
    @Operation(
            summary = "Изменить запись",
            description = "API принимает сокращенное название региона, новое полное и сокращенное названия и изменяет запись"
    )
    public ResponseEntity<String> changeByName(@RequestBody ChangedRegion region)
    {
        if(region.getOldName() == null || region.getNewFullName() == null || region.getNewName()== null)
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);

        if(regionRepository.isExists(region.getOldName()))
        {
            regionRepository.update(region.getOldName(), new Region(region.getNewName(), region.getNewFullName()));
            return ResponseEntity.ok("Region changed");
        }
        else
            return new ResponseEntity<>("Region is not found", HttpStatus.NOT_FOUND);


    }


}
