package com.justwayward.reader.ui.contract;

import com.justwayward.reader.bean.DiscussionList;

import java.util.List;

/**
 * @author lfh.
 * @date 16/9/8.
 */
public interface GirlBookDiscussionContract {

    interface View {
        void showGirlBookDisscussionList(List<DiscussionList.PostsBean> list, boolean isRefresh);

        void showError();
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getGirlBookDisscussionList(String sort, String distillate, int start, int limit);
    }

}
