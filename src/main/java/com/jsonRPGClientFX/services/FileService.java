package com.jsonRPGClientFX.services;

import java.io.File;
import java.util.Arrays;

public class FileService {

    private File file = new File("assets");

    public void test()
    {
        String[] fil = file.list();
        System.out.println(Arrays.toString(fil));
    }

}
