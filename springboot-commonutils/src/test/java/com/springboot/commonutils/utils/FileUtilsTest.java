package com.springboot.commonutils.utils;

import com.springboot.utils.utils.FileUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileUtilsTest {

    @Test
    public void createFolder() {
        String path = this.getClass().getResource("/").getFile();

        System.out.println(path);

        boolean folderPath = FileUtils.createFolder(path + "\\test\\测试\\11\\xcf");

        System.out.println(folderPath);

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void createFile() {
        String path = this.getClass().getResource("/").getFile();

        File file = FileUtils.createFile(path + "\\test\\测试\\11\\xcf.txt");

        System.out.println(file.getName());

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void testCreateFile() {
        String path = this.getClass().getResource("/").getFile();

        File file = FileUtils.createFile(path + "\\test\\测试\\11\\xcf.txt", "tsetsetsetstsaetasetaset中文中文");

        System.out.println(file.getName());
    }

    @Test
    public void testCreateFile1() {
        String path = this.getClass().getResource("/").getFile();

        File file = FileUtils.createFile(path + "\\test\\测试\\11\\xcf.txt", "tsetsetsetstsaetasetaset中文中文", "GBK");

        System.out.println(file.getName());
    }

    @Test
    public void getFileWriter() {
        String path = this.getClass().getResource("/").getFile();

        Writer writer = FileUtils.getFileWriter(path + "\\testwriter.txt", true);

        try {
            writer.write("中文中文");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getReader() {
        String path = this.getClass().getResource("/").getFile();

        BufferedReader reader = FileUtils.getFileReader(path + "\\test.txt");

        while (true) {
            try {
                String line = reader.readLine();

                if (line == null) break;

                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    public void getTestReader() {
        String path = this.getClass().getResource("/").getFile();

        BufferedReader reader = FileUtils.getFileReader(path + "\\test.txt", "gbk");

        while (true) {
            try {
                String line = reader.readLine();

                if (line == null) break;

                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void getTestReader1() {
        String path = this.getClass().getResource("/").getFile();

        File file = new File(path + "\\test.txt");

        BufferedReader reader = FileUtils.getFileReader(file, "gbk");

        while (true) {
            try {
                String line = reader.readLine();

                if (line == null) break;

                System.out.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void getFileList() {
        String path = this.getClass().getResource("/").getFile();

        List<File> file_list = FileUtils.getFileList(path);

        System.out.println(file_list);
    }

    @Test
    public void testGetFileList() {
        String path = this.getClass().getResource("/").getFile();

        File file = new File(path);

        List<File> file_list = FileUtils.getFileList(file);

        System.out.println(file_list);
    }

    @Test
    public void delFile() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf1.txt", "tsetsetsetstsaetasetaset中文中文");

        boolean folderPath = FileUtils.deleteFile(path + "\\test\\测试\\11\\xcf1.txt");

        System.out.println(folderPath);

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void delFolder() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.createFolder(path + "\\test\\测试\\11\\xcf");

        boolean folderPath = FileUtils.deleteFolder(path + "\\test\\测试\\11");

        System.out.println(folderPath);

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void delAllFile() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf1.txt", "tsetsetsetstsaetasetaset中文中文");

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf2.txt", "tsetsetsetstsaetasetaset中文中文");

        boolean folderPath = FileUtils.deleteAllFile(path + "\\test");

        System.out.println(folderPath);

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void copyFile() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.copyFile(path + "\\copy.txt", path + "\\copy1.txt");

        FileUtils.deleteFile(path + "\\copy1.txt");
    }

    @Test
    public void copyFolder() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf1.txt", "tsetsetsetstsaetasetaset中文中文");

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf2.txt", "tsetsetsetstsaetasetaset中文中文");

        FileUtils.copyFolder(path + "\\test\\测试\\11", path + "\\test\\测试\\22");

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void moveFile() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf1.txt", "tsetsetsetstsaetasetaset中文中文");

        FileUtils.moveFile(path + "\\test\\测试\\11\\xcf1.txt", path + "\\test\\测试\\22\\xcf1.txt");

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void moveFolder() {
        String path = this.getClass().getResource("/").getFile();

        FileUtils.createFile(path + "\\test\\测试\\11\\xcf1.txt", "tsetsetsetstsaetasetaset中文中文");

        FileUtils.moveFolder(path + "\\test\\测试\\11", path + "\\test\\测试\\22");

        FileUtils.deleteFolder(path + "\\test");
    }

    @Test
    public void renameFile() {
        String path = this.getClass().getResource("/").getFile();

        boolean result = FileUtils.renameFile(path + "\\rename.txt", "rename1.txt");

        System.out.println(result);

        FileUtils.renameFile(path + "\\rename1.txt", "rename.txt");
    }


    @Test
    public void getRelativeRootPath() {
        String path = this.getClass().getResource("/").getFile();

        String result = FileUtils.getRelativeRootPath(path + "\\test\\测试\\11\\xcf1.txt", path);

        System.out.println(result);
    }

    @Test
    public void readBytes() {
        String path = this.getClass().getResource("/").getFile();

        byte[] data = FileUtils.getFileBytes(path + "\\test.txt");

        System.out.println(new String(data));
    }

    @Test
    public void getFileString() {
        String path = this.getClass().getResource("/").getFile();

        String data = FileUtils.getFileString(path + "\\test.txt");

        System.out.println(data);
    }

    @Test
    public void testGetFileString() {
        String path = this.getClass().getResource("/").getFile();

        String data = FileUtils.getFileString(path + "\\test.txt", "utf-8");

        System.out.println(data);
    }


    @Test
    public void getFilePrefix() {
        String result = FileUtils.getFilePrefix("123123.xcvxv.txt");

        System.out.println(result);
    }

    @Test
    public void getFilePostfix() {
        String result = FileUtils.getFilePostfix("123123.xcvxv.txt");

        System.out.println(result);
    }

    @Test
    public void removePrefix() {
        String result = FileUtils.removePrefix("123123.xcvxv.txt", "123");

        System.out.println(result);
    }

    @Test
    public void getSize() {
        System.out.println(FileUtils.getSize(13131234));
    }


    final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    /*
     * 生成多个测试文件数据
     * */
    @Test
    public void testFile() {
        int length = ALPHA.length();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            char ch = ALPHA.charAt(i);
            list.add(String.valueOf(ch));
        }
        Collections.shuffle(list);

        String path = this.getClass().getResource("/").getFile();

        System.out.println(path);
        boolean folderPath = FileUtils.createFolder(path + "\\test");
        System.out.println(folderPath);
        for (int i = 0; i < 26; i++) {
            try (PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path + "\\test\\" + (i + 1) + ".txt")))) {

                for (int j = 0; j < 100000; j++) {
                    String collect = list.stream().collect(Collectors.joining("\n"));
                    out.append(collect+"\r\n");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testGenerateBigFile() {

        String path = this.getClass().getResource("/").getFile();
        System.out.println(path);
        boolean folderPath = FileUtils.createFolder(path + "\\test1");
        System.out.println(folderPath);

        long beginTime = System.currentTimeMillis();
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path + "\\test1\\" + "big.txt")))) {
            //out.print(ALPHA);
            for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100000; j++) {
					out.append(ALPHA);
				}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

		System.out.println("经历时间："+(endTime-beginTime));
    }


    @Test
    public void testReadBigFile(){
        String path = this.getClass().getResource("/").getFile();

        long beginTime = System.currentTimeMillis();

        try {
           /* InputStream is = new FileInputStream(new File(path + "\\test1\\" + "\\big.txt"));
            byte[] buff = new byte[8192];
            long counts = 0;
            int offset = 0;
            while((offset = is.read(buff)) != -1) {
                counts = counts + offset;
            }
            System.out.println(counts);
            is.close();*/


           /* long counts = 0;
            int offset = 0;
            BufferedInputStream bos = new BufferedInputStream(new FileInputStream(new File(path + "\\test1\\" + "\\big.txt")));
            byte[] buff = new byte[8192];
            while((offset= bos.read(buff)) != -1) {
                counts = counts + offset;
            }
            System.out.println(counts);
            bos.close();*/

            long counts = 0;
            File file = new File(path + "\\test1\\" + "\\big.txt");
            FileInputStream fis = new FileInputStream(file);
            FileChannel fc = fis.getChannel();
            ByteBuffer bbuf = ByteBuffer.allocate(10240);
            int offset = 0;
            while((offset = fc.read(bbuf)) != -1) {
                counts = counts + offset;
                bbuf.clear();
            }
            System.out.println(counts);
            fc.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


        long endTime = System.currentTimeMillis();

        System.out.println("经历时间："+(endTime-beginTime));
    }
}