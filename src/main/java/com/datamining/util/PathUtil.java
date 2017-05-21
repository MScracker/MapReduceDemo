package com.datamining.util;

import org.apache.hadoop.fs.Path;

/**
 * Created by wanghuali1 on 2017/4/27.
 */
public class PathUtil {
    public static Path getLocalPath(Path absolutePath) {
        Path localPath = absolutePath;

        if (absolutePath.toString().startsWith("hdfs://")
                || absolutePath.toString().startsWith("file://")) {
            String localPathStr = absolutePath.toString();
            System.out.println("hdfs://".length() + 1);
            int startPos = localPathStr.indexOf("/", "hdfs://".length() + 1);
            System.out.println(startPos);
            localPathStr = localPathStr.substring(startPos);

            localPath = new Path(localPathStr);
        }
        return localPath;
    }

    public static void main(String[] args) {
        String str = "1hdfs://cluster9/user/hive/warehouse/pms.db/selected_city_pm_info";
        if (str.endsWith("selected_city_pm_info")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
