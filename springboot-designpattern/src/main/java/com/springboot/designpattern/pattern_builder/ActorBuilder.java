package com.springboot.designpattern.pattern_builder;

//角色建造器：抽象建造者
abstract class ActorBuilder {

    protected static Actor actor = new Actor();

    public abstract void buildType();

    public abstract void buildSex();

    public abstract void buildFace();

    public abstract void buildCostume();

    public abstract void buildHairstyle();


    public boolean isBareHeaded(){
        return false;
    }

    //工厂方法，返回一个完整的游戏角色对象
    public static Actor construct(ActorBuilder ab) {

        ab.buildType();

        ab.buildSex();

        ab.buildFace();

        ab.buildCostume();

        if(!ab.isBareHeaded()){
            ab.buildHairstyle();
        }

        return actor;

    }


}