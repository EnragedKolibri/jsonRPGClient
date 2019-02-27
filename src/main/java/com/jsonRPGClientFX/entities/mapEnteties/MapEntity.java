package com.jsonRPGClientFX.entities.mapEnteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapEntity {
    String title;
    Map<String,Layer> layers;
}
