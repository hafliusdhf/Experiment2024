package com.example.casper.Experiment2024.data;

public class ShopLocation {
        private String name;
        private String latitude;
        private String longitude;
        private String memo;

        // 构造函数
        public ShopLocation(String name, String latitude, String longitude, String memo) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
            this.memo = memo;
        }

        // Getter方法
        public String getName() { return name; }
        public Double getLatitude() { return  Double.parseDouble(latitude) ; }
        public Double getLongitude() { return Double.parseDouble(longitude); }
        public String getMemo() { return memo; }
    }