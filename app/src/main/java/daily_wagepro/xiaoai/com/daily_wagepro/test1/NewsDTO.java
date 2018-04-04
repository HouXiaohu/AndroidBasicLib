package daily_wagepro.xiaoai.com.daily_wagepro.test1;

import com.hxh.component.basicore.ui.recycleview.mrecycleview.NetResultBean;

import java.util.List;

/**
 * Created by hxh on 2018/2/27.
 */

public class NewsDTO extends NetResultBean<NewsDTO.ResultBean.DataBean> {
    public NewsDTO() {
    }


    private String reason;
    private ResultBean result;
    private int error_code;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    @Override
    public int getTotal_count() {
        return result.data.size();
    }



    @Override
    public List<NewsDTO.ResultBean.DataBean> getItems() {
        return result.data;
    }

    @Override
    public Object getSource() {
        return this;
    }

    public static class ResultBean {


        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 7f5e189d596def25f606e5d935678881
             * title : 驻瑞典大使桂从友接受瑞典《可持续发展》杂志专访
             * date : 2018-02-27 14:43
             * category : 头条
             * author_name : 驻瑞典使馆
             * url : http://mini.eastday.com/mobile/180227144309095.html
             * thumbnail_pic_s : http://03.imgmini.eastday.com/mobile/20180227/20180227144309_bffb22f316fd3f4b9a5be0e414f81f19_2_mwpm_03200403.jpg
             * thumbnail_pic_s02 : http://03.imgmini.eastday.com/mobile/20180227/20180227144309_bffb22f316fd3f4b9a5be0e414f81f19_3_mwpm_03200403.jpg
             * thumbnail_pic_s03 : http://03.imgmini.eastday.com/mobile/20180227/20180227144309_bffb22f316fd3f4b9a5be0e414f81f19_1_mwpm_03200403.jpg
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;
            private String thumbnail_pic_s;
            private String thumbnail_pic_s02;
            private String thumbnail_pic_s03;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getThumbnail_pic_s() {
                return thumbnail_pic_s;
            }

            public void setThumbnail_pic_s(String thumbnail_pic_s) {
                this.thumbnail_pic_s = thumbnail_pic_s;
            }

            public String getThumbnail_pic_s02() {
                return thumbnail_pic_s02;
            }

            public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
                this.thumbnail_pic_s02 = thumbnail_pic_s02;
            }

            public String getThumbnail_pic_s03() {
                return thumbnail_pic_s03;
            }

            public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
                this.thumbnail_pic_s03 = thumbnail_pic_s03;
            }
        }
    }
}
