package com.fuzhucheng.rxjavartrofitrecyclerview.bean;

import java.util.List;

/**
 * Created by 符柱成 on 2016/12/14.
 */
public class AbilityBean {

    public int image;
    public String place;
    public int like;
    public int price;


    public int count;
    public int start;
    public int total;
    public String title;
    public List<SubjectsEntity> subjects;

    public AbilityBean(int image, String place, int like, int price) {
        this.image = image;
        this.place = place;
        this.like = like;
        this.price = price;
    }


    public static class SubjectsEntity {

        public RatingEntity rating;
        public String title;
        public int collect_count;
        public String original_title;
        public String subtype;
        public String year;
        public RatingEntity.ImagesEntity images;
        public String alt;
        public String id;
        public List<String> genres;
        public List<RatingEntity.CastsEntity> casts;
        public List<RatingEntity.DirectorsEntity> directors;


        public static class RatingEntity {


            public int max;
            public double average;
            public String stars;
            public int min;


            public static class ImagesEntity {

                public String small;
                public String large;
                public String medium;


            }

            public static class CastsEntity {


                public String alt;
                public AvatarsEntity avatars;
                public String name;
                public String id;


                public static class AvatarsEntity {


                    public String small;
                    public String large;
                    public String medium;


                }
            }

            public static class DirectorsEntity {


                public String alt;
                public AvatarsEntityX avatars;
                public String name;
                public String id;


                public static class AvatarsEntityX {


                    public String small;
                    public String large;
                    public String medium;


                }
            }
        }
    }
}
