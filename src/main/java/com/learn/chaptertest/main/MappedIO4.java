package com.learn.chaptertest.main;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
//io 与nio 性能对比
public class MappedIO4 {
    private static int numOfInts = 4000000;
    private static int numOfBuffInts = 200000;

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest(){
            System.out.print(name +" : ");

            try {
                long start = System.nanoTime();
                test();
                double dresult = System.nanoTime() - start;
                System.out.format("%.2f\n",dresult/1.0e9);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        //模板方法
        protected abstract void test() throws IOException;
    }
    //匿名内部类数组
    private static Tester[] tests = {
            //io写操作
        new Tester("Stream Write") {
            @Override
            protected void test() throws IOException {

                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream( new File("temp.tmp"))));
                for (int i = 0 ; i < numOfInts ; i ++){
                    dos.writeInt(i);
                }
                dos.close();
            }
        },
            //nio写操作
        new Tester("Mapped Write"){
            @Override
            protected void test() throws IOException {
                FileChannel fc = new RandomAccessFile("temp.tmp","rw").getChannel();
                IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_WRITE,0 , fc.size()).asIntBuffer();

                for (int i = 0 ; i < numOfInts ; i ++){
                    intBuffer.put(i);
                }
                fc.close();
            }
        },
            //io读操作
        new Tester("Stream read") {
            @Override
            protected void test() throws IOException {
                DataInputStream dis = new DataInputStream(new BufferedInputStream(
                        new FileInputStream("temp.tmp")));
                for (int i = 0 ; i < numOfInts ; i ++){
                    dis.readInt();
                }
                dis.close();
            }
        },
            //nio读操作
        new Tester("Mapped read") {
            @Override
            protected void test() throws IOException {
                FileChannel fc = new FileInputStream(new File("temp.tmp")).getChannel();
                IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_ONLY,0 , fc.size()).asIntBuffer();
                while (intBuffer.hasRemaining()){
                    intBuffer.get();
                }
                fc.close();
            }
        },
            //io 读写
        new Tester("Stream read/write") {
            @Override
            protected void test() throws IOException {
                RandomAccessFile raf = new RandomAccessFile(new File("temp.tmp"),"rw");
                raf.writeInt(1);
                for (int i = 0; i < numOfBuffInts; i++) {
                    raf.seek(raf.length() - 4);
                    raf.writeInt( raf.readInt());
                }
                raf.close();
            }
        },
            //nio 读写
        new Tester("Mapped read/write") {
            @Override
            protected void test() throws IOException {
                FileChannel fc = new RandomAccessFile(new File("temp.tmp"),"rw").getChannel();
                IntBuffer intBuffer = fc.map(FileChannel.MapMode.READ_WRITE, 0 , fc.size()).asIntBuffer();
                intBuffer.put(0);
                for (int i = 1; i < numOfBuffInts; i++) {
                    intBuffer.put(intBuffer.get(i - 1));

                }
                fc.close();
            }
        }
    };
    public static void main (String [] args){
        for (Tester testes: tests
             ) {
            testes.runTest();

        }

    }
    //result
    //Stream Write : 0.42
    //Mapped Write : 0.03
    //Stream read : 0.60
    //Mapped read : 0.01
    //Stream read/write : 4.06
    //Mapped read/write : 0.01
};
