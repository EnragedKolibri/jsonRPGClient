package utils;

import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class AssetsManager {

    private Map<AssetsTypeHolder.AssetType,File> assets = new HashMap<>();

}
