package com.springboot.utils.testfile;

class Words implements Comparable<Words>{
    String word ;
    int counts;
    public Words() {
        // TODO Auto-generated constructor stub
    }
    public Words(String word,int counts) {
        // TODO Auto-generated constructor stub
        this.word = word;
        this.counts = counts ;
    }
    //重写compareTo方法，用于比较两个对象大小
    @Override 
    public int compareTo(Words w) {
        // TODO Auto-generated method stub
        if(this.counts > w.counts){
            return 1;
        }
        if(this.counts < w.counts){
            return -1;
        }        
        return 0;
    }
    //重写clone方法（深复制）
    @Override
    protected Words clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Words words = new Words(word,counts);
        return words;
    }
}