package com.datamining;

import com.datamining.entity.RecScorePair;

import java.util.ArrayList;

/**
 * Created by wanghuali1 on 2017/5/2.
 */
public class JavaTest {
    public static void main(String[] args) {
        ArrayList<RecScorePair> pairList = new ArrayList<RecScorePair>();
        RecScorePair recScorePair = new RecScorePair(1222, 1.0);
        RecScorePair recScorePair1 = new RecScorePair(2343, 2.0);
//        com.datamining.entity.RecScorePair recScorePair2 = new com.datamining.entity.RecScorePair(3655, 3.0);
//        com.datamining.entity.RecScorePair recScorePair3 = new com.datamining.entity.RecScorePair(4567, 4.0);
//        com.datamining.entity.RecScorePair recScorePair4 = new com.datamining.entity.RecScorePair(898795,5.0);
        pairList.add(recScorePair);
        pairList.add(recScorePair1);
//        pairList.add(recScorePair2);
//        pairList.add(recScorePair3);
//        pairList.add(recScorePair4);
        int num=0;
        StringBuffer stringBuffer = new StringBuffer();
        for (RecScorePair pair : pairList) {
//            if (num < pairList.size() - 1) {
                stringBuffer.append(pair.getRecId()).append(",");
//            } else {
//                stringBuffer.append(pair.getRecId());
//            }
//            num++;
        }
        stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
        System.out.println(stringBuffer.toString());
    }
}
