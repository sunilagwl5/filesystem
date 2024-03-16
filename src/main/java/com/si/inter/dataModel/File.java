package com.si.inter.dataModel;


// this class should be immuatble / effectivce
import lombok.Getter;

import static com.si.inter.FilesystemService.BLOCK_SIZE;

//File == memory Block
@Getter
public class File {
    // Block has size
    private char[] content = new char[BLOCK_SIZE];

    private File nextfile;

    //Revisit for concurrency
    public File(char[] content){
        this.content = content;
    }

}
