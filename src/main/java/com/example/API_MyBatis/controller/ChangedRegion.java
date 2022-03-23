package com.example.API_MyBatis.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;


//class for request to change data
@AllArgsConstructor
@Getter
public class ChangedRegion
{
    @Schema(description = "Старое сокращенное название региона", example = "SPB")
    private String oldName;
    @Schema(description = "Новое сокращенное название региона", example = "SPB")
    private String newName;
    @Schema(description = "Новое полное название региона", example = "Ленинград")
    private String newFullName;
}