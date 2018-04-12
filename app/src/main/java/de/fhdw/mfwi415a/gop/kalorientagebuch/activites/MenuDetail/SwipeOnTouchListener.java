package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.HashMap;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Foodstuff;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.MenuItem;

public class SwipeOnTouchListener implements View.OnTouchListener
{
    AppLogic appLogic;

    Context mContext;

    //Swipecontrol
    private boolean mIsSwiping = false;
    private boolean mIsItemPressed = false;
    private static final int SwipeDuration = 250;
    private static final int MoveDuration = 150;
    private HashMap<Long, Integer> mItemIdToMap = new HashMap<>();

    ListView mListView;

    float mDownX;
    private int mSwipeSlop = -1;
    private boolean mSwiped;
    boolean deleteMark = false;

    public SwipeOnTouchListener(AppLogic applicationLogic, Context context, ListView listView)
    {
        appLogic = applicationLogic;
        mContext = context;
        mListView = listView;
    }

    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent) {

        if(mSwipeSlop < 0)
        {
            mSwipeSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        }

        switch (motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(mIsItemPressed)
                    return false;

                mIsItemPressed = true;
                mDownX = motionEvent.getX();
                mSwiped = false;
                break;

            case MotionEvent.ACTION_CANCEL:
                view.setTranslationX(0);
                mIsItemPressed = false;
                deleteMark = false;
                break;
            case MotionEvent.ACTION_MOVE:
                float x = motionEvent.getX() + view.getTranslationX();
                float deltaX = x - mDownX;
                float deltaXAbs = Math.abs(deltaX);

                if(!mIsSwiping)
                {
                    // try differentiating a swipe from an aggressive touching behavior of the user
                    if(deltaXAbs > mSwipeSlop)
                    {
                        mIsSwiping = true;
                        mListView.requestDisallowInterceptTouchEvent(true);
                    }
                }
                if(mIsSwiping && !mSwiped)
                {
                    view.setTranslationX(x - mDownX);

                    if(deltaX > view.getWidth() / 3) {
                        mDownX = x;
                        mSwiped = true;
                        mIsSwiping = false;
                        mIsItemPressed = false;

                        return true;
                    }
                    else if( deltaX < -2 * (view.getWidth() / 3)) // remove if swipe is more than 2/3
                    {
                        deleteMark = true;

                        /*mDownX = x;
                        mSwiped = true;
                        mIsSwiping = false;
                        mIsItemPressed = false;*/

                        return true;
                    }
                    else
                    {
                        deleteMark = false;

                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:

                if(mIsSwiping) //swipe
                {
                    if(deleteMark)
                    {
                        view.setEnabled(false);

                        view.animate().setDuration(300).translationX(-view.getWidth() / 3).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation)
                            {
                                super.onAnimationEnd(animation);

                                view.animate().setDuration(300).alpha(0).translationX(-view.getWidth()).setListener(new AnimatorListenerAdapter()
                                {

                                    @Override
                                    public void onAnimationEnd(Animator animation)
                                    {
                                        super.onAnimationEnd(animation);

                                        mIsSwiping = false;
                                        mIsItemPressed = false;
                                        //animateRemoval(mListView, view);
                                        appLogic.removeIngredient((MenuItem)mListView.getAdapter().getItem(mListView.getPositionForView(view)));
                                        appLogic.LoadMenu(appLogic.get_menuId());
                                    }
                                });
                            }
                        });

                        Toast.makeText(mContext, String.format(mContext.getResources().getString(R.string.str_ingredient_deleted), ((TextView)view.findViewById(R.id.lbl_ingredient_name)).getText()), Toast.LENGTH_LONG).show();

                        deleteMark = false;
                        mSwiped = true;
                    }
                    else {
                        view.animate().setDuration(300).alpha(1).translationX(0).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                mIsSwiping = false;
                                mIsItemPressed = false;
                                mListView.setEnabled(true);
                            }
                        });
                    }
                }
                else // click
                {
                    mIsItemPressed = false;
                    mListView.setEnabled(true);

                    return false;
                }
                break;
                default:
                    return false;
        }

        return true;
    }

    private void animateRemoval(final ListView lw, View viewToRemove)
    {
        final int firstVisiblePos = lw.getFirstVisiblePosition();
        final ArrayAdapter adapter = (ArrayAdapter)lw.getAdapter();

        for(int i = 0; i < lw.getChildCount(); ++i)
        {
            View child = lw.getChildAt(i);

            if(child != viewToRemove)
            {
                int position = firstVisiblePos + i;
                long itemId = lw.getAdapter().getItemId(position);
                mItemIdToMap.put(itemId, child.getTop());
            }
        }

        adapter.remove(adapter.getItem(lw.getPositionForView(viewToRemove)));

        final ViewTreeObserver observer = lw.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
        {
            @Override
            public boolean onPreDraw() {
                observer.removeOnPreDrawListener(this);
                boolean firstAnimation = true;

                int firstVisiblePos = lw.getFirstVisiblePosition();

                for(int i = 0; i < lw.getChildCount(); ++i)
                {
                    final View child = lw.getChildAt(i);
                    int position = firstVisiblePos + i;
                    long itemId = adapter.getItemId(position);

                    Integer startTop = mItemIdToMap.get(itemId);
                    int top = child.getTop();

                    if(startTop != null)
                    {

                        if (startTop != top) {
                            int deltaTop = startTop - top;
                            child.setTranslationY(deltaTop);
                            child.animate().setDuration(MoveDuration).translationY(0);

                            if (firstAnimation) {
                                child.animate().setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        mIsSwiping = false;
                                        lw.setEnabled(true);
                                    }
                                });

                                firstAnimation = false;
                            }
                        }
                    }
                    else
                    {
                        int heightOfChild = child.getHeight() + lw.getDividerHeight();
                        startTop = top + ( i > 0 ? heightOfChild : -heightOfChild);

                        int deltaHeight = startTop - top;
                        child.setTranslationY(deltaHeight);

                        child.animate().setDuration(MoveDuration).translationY(0);
                        if (firstAnimation) {
                            child.animate().setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    mIsSwiping = false;
                                    lw.setEnabled(true);
                                }
                            });

                            firstAnimation = false;
                        }
                    }
                }

                mItemIdToMap.clear();
                return true;
            }
        });
    }
}
