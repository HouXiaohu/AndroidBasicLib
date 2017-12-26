package com.hxh.component.basicore.mvp;

/**
 * 标题: Mapper.java
 * 作者: hxh
 * 日期: 2017/11/9 16:32
 * 描述: VO 转换基类 ，这个可以干嘛？
 * 举个栗子：
 * 1. 在接口文档没有出之前
 * 2. 后台返回数据变了
 * 3. 后台返回的数据太啰嗦，我界面只想要其中某些，以及我不想要后台强规定给我的字段名，如： uid,uname 这些
 *    我只想要  id,name 这些字段名，虽然有@SerializedName("Name")这样的注解，但我不想用！！！
 * 当你有以上这种情况，推荐你做一个 DTO -> VO  的转换
 *
 */
public interface Mapper<T> {
    T transform();
}
