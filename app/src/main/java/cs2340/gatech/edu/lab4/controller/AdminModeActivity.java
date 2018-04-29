package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Account;
import cs2340.gatech.edu.lab4.model.Model;

public class AdminModeActivity extends AppCompatActivity {
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mode);

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.course_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);

        //this is only needed if you are doing an optional support for multiple display sizes
        if (findViewById(R.id.course_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,0,0, "Logout");
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return false;
    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new AdminModeActivity.SimpleViewHolder(SearchController.getInstance().getUserResult()));
        recyclerView.invalidate();
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the Course object to a text field.
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public class SimpleViewHolder
            extends RecyclerView.Adapter<SimpleViewHolder.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Account> mUsers;


        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleViewHolder(List<Account> items) {
            mUsers = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.admin_mode_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * Matches list elements with correct views
         * @param holder holds the view
         * @param position place within list
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

            final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mUser = mUsers.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            holder.mIdView.setText("" + mUsers.get(position).getKey());
            holder.mContentView.setText(mUsers.get(position).toString());
            holder.mSwitch.setChecked(mUsers.get(position).getBanState());


            holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    FirebaseController.updateBanState(mUsers.get(position), isChecked);
                    Model.updateBanState(mUsers.get(position).getUsername(), isChecked);

                }
            });
//            /*
//             * set up a listener to handle if the user clicks on this list item, what should happen?
//             */
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mTwoPane) {
//                        //if a two pane window, we change the contents on the main screen
//                        Bundle arguments = new Bundle();
//                        arguments.putInt(ShelterDetailFragment.ARG_COURSE_ID, holder.mShelter.getKey());
//
//                        ShelterDetailFragment fragment = new ShelterDetailFragment();
//                        fragment.setArguments(arguments);
//                        getSupportFragmentManager().beginTransaction()
//                                .replace(R.id.course_detail_container, fragment)
//                                .commit();
//                    } else {
//                        //on a phone, we need to change windows to the detail view
//                        Context context = v.getContext();
//                        //create our new intent with the new screen (activity)
//                        Intent intent = new Intent(getBaseContext(), ShelterDetailActivity.class);
//                        /*
//                            pass along the id of the course so we can retrieve the correct data in
//                            the next window
//                         */
//                        intent.putExtra(ShelterDetailFragment.ARG_COURSE_ID, holder.mShelter.getKey());
//
//                        model.setCurrentShelter(holder.mShelter);
//
//                        //now just display the new window
//                        context.startActivity(intent);
//                    }
//                }
//            });
        }

        /**
         * returns number of items in the list
         * @return list size
         */
        @Override
        public int getItemCount() {
            return mUsers.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public final Switch mSwitch;
            public Account mUser;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
                mSwitch = (Switch) view.findViewById(R.id.ban);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }

}
