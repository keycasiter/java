package com.github.java.learning.io;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.*;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * created by guanjian on 2021/1/6 17:42
 */
public class SequenceStreamTest {

    public static void main(String[] args) throws IOException {
        Vector<ByteArrayInputStream> inputStreams = new Vector<>();
        inputStreams.addElement(new ByteArrayInputStream("abc".getBytes()));
        inputStreams.addElement(new ByteArrayInputStream("def".getBytes()));
        inputStreams.addElement(new ByteArrayInputStream("ghi".getBytes()));

        SequenceInputStream sis = new SequenceInputStream(inputStreams.elements());

        int c = 0;
        while ((c = sis.read()) != -1) {
            System.out.print((char)c);
        }
    }
}
