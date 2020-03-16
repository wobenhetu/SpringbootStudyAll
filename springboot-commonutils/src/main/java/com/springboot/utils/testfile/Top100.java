package com.springboot.utils.testfile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.*;

@Slf4j(topic = "c.Top100")
public class Top100 {
    public static Map<String, Integer> hashMap = new ConcurrentHashMap<String, Integer>();
    public static void main(String[] args) {

        // TODO Auto-generated method stub
        try {
            //构建线程池
            ExecutorService service = Executors.newFixedThreadPool(26);
            CountDownLatch c = new CountDownLatch(26);

            //多个文本文件的根目录
            //这个目录是target下面的
            String path = "D:\\workspaces\\SpringbootStudyAll\\springboot-commonutils\\target\\test-classes\\test";
            File[] fileList = new File(path).listFiles();

            //这里是resourcces目录下面
//            File files = ResourceUtils.getFile("classpath:testfile/");
//            File[] fileList = files.listFiles();

            for (File file : fileList) {
                //遍历文件，使用子线程读取
                MyFileUtilTask f = new MyFileUtilTask(file.getPath(),c);
                service.execute(f);

            }
            c.await();
            log.debug("主线程开始执行");
            //获取频率最高的100个单词
            getTop100();
            //关闭线程池
            service.shutdown();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }
    }

    //获取频率最高的100个单词
    private static void getTop100() {
        // TODO Auto-generated method stub
        Words[] words = null;
        //单词个数少于100直接排序输出
        if (hashMap.size() <= 100) {
            log.debug("单词个数小于100");
            words = new Words[hashMap.size()];
            System.out.println(hashMap.size());
            int i = 0;
            for (Entry<String, Integer> entry : hashMap.entrySet()) {
                words[i++] = new Words(entry.getKey(), entry.getValue());
            }
            Arrays.sort(words);
            for (int k = words.length - 1; k >= 0; k--) {
                log.debug(words[k].word + " " + words[k].counts);
            }
        } else {
            log.debug("单词个数大于100");
            //否则，构建最小堆
            words = new Words[101];
            int i = 1;
            for (Entry<String, Integer> entry : hashMap.entrySet()) {
                if (i > 100) {
                    //将新元素与堆顶元素相比，大于堆顶元素则替换掉堆顶元素
                    if (entry.getValue() > words[1].counts) {
                        words[1].word = entry.getKey();
                        words[1].counts = entry.getValue();
                        //维护最大堆的性质
                        heapFy(words, 1, 100);
                    }
                } else {
                    words[i++] = new Words(entry.getKey(), entry.getValue());
                    if (i == 101) {
                        buildMinHeap(words);//构建最小堆
                    }
                }
            }
            //堆排序，将第一个元素与“最后一个元素”交换，然后维护最小堆的性质
            for (int k = 1; k < words.length - 1; k++) {
                exchange(words, 1, words.length - k);
                heapFy(words, 1, words.length - 1 - k);
            }
            for (int k = 1; k <= words.length - 1; k++) {
                System.out.println(words[k].word + " " + words[k].counts);
            }
        }

    }

    //构建最小堆
    private static void buildMinHeap(Words[] words) {
        // TODO Auto-generated method stub
        int length = words.length - 1;
        for (int i = length / 2; i >= 1; i--) {
            heapFy(words, i, length);
        }
    }

    //维护最小堆
    private static void heapFy(Words[] words, int i, int length) {
        // TODO Auto-generated method stub
        if (i > length)
            return;
        int left = left(i);
        int right = right(i);
        int minIndex = i;
        //找到左右节点中最小的节点
        if (left >= 1 && left <= length) {
            if (words[left].compareTo(words[minIndex]) < 0) {
                minIndex = left;
            }
        }
        if (right >= 1 && right <= length) {
            if (words[right].compareTo(words[minIndex]) < 0) {
                minIndex = right;
            }
        }
        if (minIndex == i) {
            return;
        } else {
            exchange(words, i, minIndex);
            heapFy(words, minIndex, length);
        }
    }

    //交换两个元素
    private static void exchange(Words[] words, int i, int maxindex) {
        // TODO Auto-generated method stub
        try {
            Words temp;
            temp = words[i].clone();
            words[i] = words[maxindex].clone();
            words[maxindex] = temp.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //返回左节点索引
    private static int left(int i) {
        // TODO Auto-generated method stub
        return 2 * i;
    }

    //返回右节点索引
    private static int right(int i) {
        // TODO Auto-generated method stub
        return 2 * i + 1;
    }


    //实现Runnable的工具类
    static class MyFileUtilTask implements Runnable {

        private String fileName;

        private CountDownLatch countDownLatch;

        public MyFileUtilTask(String fileName, CountDownLatch countDownLatch) {
            this.fileName = fileName;
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader(fileName);
                br = new BufferedReader(fr);
                log.debug(fileName);
                String line = "";
                String[] arrs = null;
                while ((line = br.readLine()) != null) {
                    arrs = line.split(" ");
                    for (String string : arrs) {
                        string = prePro(string);
                        if (string.equals("")) {
                            continue;
                        }

                        synchronized (hashMap) {
                            if (hashMap.containsKey(string.trim())) {
                                hashMap.put(string, hashMap.get(string) + 1);
                            } else {
                                hashMap.put(string, 1);
                            }
                        }
                    }
                }
                this.countDownLatch.countDown();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        //由于测试文件是英文小说原著，里面包含了大量的符号，这里进行预处理
        private String prePro(String str) {
            // TODO Auto-generated method stub
            StringBuilder sb = new StringBuilder();
            char[] ch = str.trim().toCharArray();
            for (char c : ch) {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

    }
}