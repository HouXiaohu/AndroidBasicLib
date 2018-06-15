package com.hxh.component.emvp.model;

import android.widget.EditText;
import android.widget.TextView;

import com.hxh.component.basicore.component.net.IApiError;

import java.util.List;

import retrofit2.Response;
import rx.Subscription;

/**
 * Presenter 相关辅助方法
 */
public interface IModelRelated {
    //region 管理订阅(sub) 进入
    void unSubscription();
    void addSubscription(Subscription sub);
    void newCompositeSubscription();
    //endregion

    //region 非空
    boolean isEmpty(List list);
    boolean isEmpty(String msg);
    boolean isEmpty(CharSequence str);
    boolean isEmpty(String... args) ;
    boolean isEmpty(EditText text);
    boolean isEmpty(EditText text, String tipmsg);
    boolean isEmpty(TextView tv);
    boolean isEmptyJson(String text);
    //endregion

    int getErrorCode();
    String getErrorMessage();

    int getErrorCode(Throwable msg);
    String getErrorMessage(Throwable msg);
    /**
     * 处理网络错误
     * @param msg
     */
    void handleApiError(Throwable msg);

    /**
     * 将一个Throwable 转换成IApierror
     * @param msg
     * @return
     */
    IApiError getApiError(Throwable msg);

    /**
     * 检查相应体是否有错误
     * 通常情况用于你的返回结果为 Response<Void> 之类的
     * @param body
     * @return
     */
    boolean checkResponseBodyContainErrorBody(Response body);
    String checkResponseBodyContainErrorBodyReturnErrorMessage(Response body);
    IApiError checkResponseBodyContainErrorBodyReturnErrorBody(Response body);

}
