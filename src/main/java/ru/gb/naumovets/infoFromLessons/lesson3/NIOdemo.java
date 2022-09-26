package ru.gb.naumovets.infoFromLessons.lesson3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NIOdemo {
    // Java <===== OS =====> resource

    //InputStream, OutputStream - переносят байты
    //Reader, Writer - переносит символы (умеют транслировать символы в байты)

    public static void main(String[] args) throws IOException {
//        File file = new File("files/file.txt");
        File file = new File("files/f.txt");
//        file.createNewFile();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        while (true) {
            bw.write(bufferedReader.readLine() + "\n");
            if (bufferedReader.readLine().equals("exit")) {
                bw.flush();
                break;
            }
        }

//        System.out.println(file.exists());

//        Path file_1 = Path.of("files","file.txt"); //files/file.txt


//        createDirectory(file_1);
//        deleteFile(file_1);
//        createFile(file_1);
//        writeTextToFile(file_1);
//        System.out.println(readTextFromFile(file_1));
//        writeDataToFile(file_1, "Hi, i am from file");
//        System.out.println(readDataFromFile(file_1));
    }

    private static void writeDataToFile(Path file, String data){
        try {
            OutputStream outputStream = Files.newOutputStream(file);
            outputStream.write(data.getBytes());

            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readDataFromFile(Path file){
        try {
            InputStream inputStream = Files.newInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);//Java ходит в файл не при каждом запросе на чтение символа, а один раз и кладет в свой кэш
            String result = new String();
            byte[] buffer = new byte[2];
            while (bufferedInputStream.read(buffer) != -1){
//                System.out.println(Arrays.toString(buffer)); //кладет символы в массив
                String s = new String(buffer);
                result += s;
            }
            inputStream.close();//закрываем файл, чтобы освободить его для других действий
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readTextFromFile(Path file) {
        try {
            return Files.readString(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void deleteFile(Path path){
        try{
            Files.delete(path);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static void createFile(Path path){
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDirectory(Path path){
        Path parent = path.getParent();
        if(!Files.exists(parent)){
            System.out.println("Родительской папки не существует. Создадим");
            try {
                Files.createDirectory(parent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void writeTextToFile(Path file){
        try {
            Files.writeString(file,"Text. I am text from Java");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
