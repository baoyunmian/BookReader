package com.justwayward.reader.ui.presenter;

import android.content.Context;

import com.justwayward.reader.api.BookApi;
import com.justwayward.reader.bean.BooksByCats;
import com.justwayward.reader.ui.contract.SubCategoryFragmentContract;
import com.justwayward.reader.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/8/31.
 */
public class SubCategoryFragmentPresenter implements SubCategoryFragmentContract.Presenter<SubCategoryFragmentContract.View> {

    private SubCategoryFragmentContract.View view;

    private Context context;
    private BookApi bookApi;

    @Inject
    public SubCategoryFragmentPresenter(Context context, BookApi bookApi) {
        this.context = context;
        this.bookApi = bookApi;
    }

    @Override
    public void attachView(SubCategoryFragmentContract.View view) {
        this.view = view;
    }

    @Override
    public void getCategoryList(String gender, String major, String minor, String type, final int start, int limit) {
        bookApi.getBooksByCats(gender, type, major, minor, start, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BooksByCats>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getCategoryList:" + e.toString());
                        view.showError();
                    }

                    @Override
                    public void onNext(BooksByCats booksByCats) {
                        view.showCategoryList(booksByCats, start == 0 ? true : false);
                    }
                });
    }


}
