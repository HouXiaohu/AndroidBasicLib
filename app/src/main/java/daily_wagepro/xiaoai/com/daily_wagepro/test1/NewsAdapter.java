package daily_wagepro.xiaoai.com.daily_wagepro.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hxh.component.basicore.Base.adapter.BaseRecyAdapter;

import daily_wagepro.xiaoai.com.daily_wagepro.R;

public class NewsAdapter extends BaseRecyAdapter<NewsDTO.ResultBean.DataBean, NewsAdapter.VH> {
    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public VH newViewHolder(View view) {
        return new VH(view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_news;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        NewsDTO.ResultBean.DataBean item = getmDatas().get(position);
        holder.tv_1.setText(item.getTitle());
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView tv_1;
        public VH(View itemView) {
            super(itemView);
            tv_1 = (TextView) itemView.findViewById(R.id.tv_1);
        }
    }
}