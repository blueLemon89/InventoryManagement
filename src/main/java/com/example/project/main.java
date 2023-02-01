package com.example.project;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        System.out.println(now);            // 2019-06-14T15:50:36.068076300
        System.out.println(timestamp);      // 2019-06-14 15:50:36.0680763

        String timestamp1 = now.toString();

        System.out.println(timestamp1);  // 2019-06-14T15:50:36.068076300
    }
}
