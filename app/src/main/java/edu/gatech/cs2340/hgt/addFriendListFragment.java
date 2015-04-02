package edu.gatech.cs2340.hgt;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class addFriendListFragment extends ListFragment {
    private List<User> allUsers;
    private String curUsername;
    private CallBack2 activity;

    public addFriendListFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_add_friend_list, container, false);
        Button returnBtn = (Button) view.findViewById(R.id.af_return);
        returnBtn.setOnClickListener(new returnBtnListener(activity));
        return view;
    }

    /**
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (CallBack2)activity;
        this.curUsername = activity
                .getApplicationContext()
                .getSharedPreferences("userSession", 0)
                .getString("curUsername", null);
        this.allUsers = new UserDB(getActivity()).getAllUsers();
        //FriendDB db = new FriendDB(activity.getApplicationContext());
//        returnBtn.setOnClickListener(new returnBtnListener((CallBack2)activity));
        addFriendArrayAdapter adapter = new addFriendArrayAdapter(getActivity(),
                allUsers);
        setListAdapter(adapter);

    }

    /**
     *
     */
    private class returnBtnListener implements View.OnClickListener {
        final CallBack2 activity;

        private returnBtnListener(CallBack2 activity) {
            this.activity = activity;
        }


        @Override
        public void onClick(View v) {
            activity.showFriendList();

        }
    }

    /**
     *
     * @param l
     * @param v
     * @param position
     * @param id
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        User target = allUsers.get(position);
        if (activity.addFriendship(curUsername, target.getUsername())) {
            ImageView b = (ImageView)v.findViewById(R.id.afCross);
            b.setVisibility(View.INVISIBLE);
        }

    }

    /**
     *
     */
    public interface CallBack2 {
        public boolean addFriendship(String username1, String username2);
        public void showFriendList();
    }
}
