package com.si.inter;

import com.si.inter.dataModel.File;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

@Service
// We will use solid principle dependency inversion by spliting service in service and serviceImpl
public class FilesystemService {
    private ConcurrentHashMap<String, ConcurrentLinkedDeque<File>> inMemoryFileSystem = new ConcurrentHashMap<>();
    public static final int BLOCK_SIZE = 128;

    private static FilesystemService INSTANCE ;
    private FilesystemService(){

    }
    public static FilesystemService getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (FilesystemService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FilesystemService();
                }
            }
        }
        return INSTANCE;
    }

    public String fread(String name) {
        if(inMemoryFileSystem.get(name) == null) {
            //Exception
            return "File with given Filename Does not exist";
        }

        StringBuffer content =  new StringBuffer();

        ConcurrentLinkedDeque<File> fileContent = inMemoryFileSystem.get(name);

        for (File file : fileContent) {
            System.out.println(file.getContent());
        }

        return content.toString();
    }

    //handle concurrency here
    public String fwrite(String name, String content) {

        if(inMemoryFileSystem.get(name) == null) {
            //Exception
            return "First create a File using f create";
        }

        char[] contentArray = content.toCharArray();

        //logic to divide content in blocks ex 1, 2, 5
        AtomicInteger contentLength=   new AtomicInteger(contentArray.length);
        ConcurrentLinkedDeque<File> contentBlocks =  new ConcurrentLinkedDeque<>();
        AtomicInteger blockNum = new AtomicInteger(0);
        while(contentLength.get() > 0 ) {
            contentBlocks.add(new File(Arrays.copyOfRange(contentArray, blockNum.get(), BLOCK_SIZE)));
            contentLength.set(contentLength.get()-BLOCK_SIZE); ;
            blockNum.set(blockNum.get() +1);
        }

        // can be scaled to multiple blocks
        // partital data in block will use full block.

        ConcurrentLinkedDeque<File> fileContent = inMemoryFileSystem.get(name);
        fileContent.addAll(contentBlocks);
        //stale reads
        return this.fread(name);
    }

    public void fcreate(String name) {
        inMemoryFileSystem.putIfAbsent(name, new ConcurrentLinkedDeque<>());
    }

}
