package com.example.healthsolutionsapplication.Model;

public class sanPhamDaMuaModel{
        private int image;
        private String name, status, details;

        public sanPhamDaMuaModel(int picture, String name, String status, String details) {
            this.image = picture;
            this.name = name;
            this.status = status;
            this.details = details;
        }

        public sanPhamDaMuaModel() {
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }
}

