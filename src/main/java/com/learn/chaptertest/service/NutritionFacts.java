package com.learn.chaptertest.service;

/**
 * 建造者（Builder）模式 减少多构造参数的
 */
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int soduim;
    private final int carbohydrate;

    /**
     * 内部类构建器 提供外部类返回值方法 参数为自己本身
     */
    public static class Builder {

        private final int servingSize;
        private final int servings;

        private  int calories = 0;
        private  int fat = 0;
        private  int soduim = 0;
        private  int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
        this.servingSize = servingSize;
        this.servings = servings;
        }

        public Builder calories(int val){
            calories = val;
            return this;
        }
        public Builder fat(int val){
            fat = val;
            return this;
        }
        public Builder soduim(int val){
            soduim = val;
            return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }
        //可以加入验证用于增强功能
        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }

    /**
     * 构造器为内部类参数，内部类拥有必要的默认值，如果不设置为默认值 设置则代替默认值。避免再次修改。提高安全性
     * @param builder
     */
    public NutritionFacts(Builder builder) {
        this.servingSize = builder.servingSize;
        this.servings = builder.servings;
        this.calories = builder.calories;
        this.fat = builder.fat;
        this.soduim = builder.soduim;
        this.carbohydrate = builder.carbohydrate;
    }

    NutritionFacts nutritionFacts = new Builder(32,11).calories(123).carbohydrate(12).carbohydrate(34).fat(5).build();//可以定义默认值，可以直接构建
}
