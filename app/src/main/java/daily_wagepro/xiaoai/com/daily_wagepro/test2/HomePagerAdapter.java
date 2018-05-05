package daily_wagepro.xiaoai.com.daily_wagepro.test2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 *mainactivity view pager adapter
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
               // fragment = (Fragment) ARouter.getInstance().build("/work/main/workfragment").navigation();
                fragment = Test1Fragme.newInstance();
                break;
//            case 1:
//                fragment = MyAppProvider.getGrowingUpFragment();
//                break;
//            case 2:
//              // fragment = MyWorkModuleService.getFriendFrgment();
//                 fragment = MyAppProvider.getMyLifeFragment();
//                break;
            case 1:
                fragment = Test1Fragme.newInstance();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }




}
