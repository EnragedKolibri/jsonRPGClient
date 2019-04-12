package com.jsonRPGClientFX.entities;

import javafx.scene.canvas.Canvas;
import lombok.Data;

@Data
public class MapEntity {
            private String title;

            
            private int[][] map;



            private int[][] collisionLayer;
            public void setMap(String title,int[][] map,int[][] collisionLayer)
            {
                this.title = title;
                this.map = map;
                this.collisionLayer = collisionLayer;
            }
}
