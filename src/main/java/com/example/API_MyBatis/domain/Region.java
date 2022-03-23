package com.example.API_MyBatis.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "region")
//@Schema(name = "Структура записи региона")
public class Region
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Уникальный идентификатор региона",
            //example = "23",
            accessMode = Schema.AccessMode.READ_ONLY)
    private int id;
    @Schema(description = "Сокращенное название региона", example = "SPB")
    private String name;
    @Schema(description = "Полное название региона", example = "Санкт-Петербург")
    private String fullname;

    public Region(String name, String fullname)
    {
        this.name = name;
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name " + this.name + ", fullname: " + this.fullname + "\n";
    }
}
