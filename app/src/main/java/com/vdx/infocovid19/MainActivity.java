package com.vdx.infocovid19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.vdx.infocovid19.Adapters.StateAdapter;
import com.vdx.infocovid19.Models.ApiModel;
import com.vdx.infocovid19.Models.History;
import com.vdx.infocovid19.Models.HistoryData;
import com.vdx.infocovid19.Models.HistoryModel;
import com.vdx.infocovid19.Models.Statewise;
import com.vdx.infocovid19.Models.Total;
import com.vdx.infocovid19.Utils.Helper;
import com.vdx.infocovid19.Utils.URLS;
import com.vdx.infocovid19.Utils.VolleySingleton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements StateAdapter.setOnClickListener {

    private static String TAG = "MainActivity";
    private ArrayList<Statewise> dataArrayList;
    private RecyclerView recyclerView;
    private StateAdapter stateAdapter;
    private ApiModel apiModel;
    private HistoryModel historyModel;
    private Context context;
    private TextView country_confirmed_current, country_active_current, country_recovered_current, country_dead_current;
    private TextView country_confirmed_increased, country_recovered_increased, country_dead_increased;
    private SearchView searchView;
    private ArrayList<History> history;
    private ArrayList<Statewise> currentDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initViews();
        getHistoryResponse();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.main_recycler);
        country_confirmed_current = findViewById(R.id.country_confirmed_current);
        country_active_current = findViewById(R.id.country_active_current);
        country_recovered_current = findViewById(R.id.country_recovered_current);
        country_dead_current = findViewById(R.id.country_dead_current);
        searchView = findViewById(R.id.search);
        country_confirmed_increased = findViewById(R.id.country_confirmed_increased);
        country_recovered_increased = findViewById(R.id.country_recovered_increased);
        country_dead_increased = findViewById(R.id.country_dead_increased);
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        stateAdapter = new StateAdapter(currentDataList, getApplicationContext(), this);
        recyclerView.setAdapter(stateAdapter);
        search(searchView);
    }

    private void getResponse() {
        dataArrayList = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, URLS.UnOfficialApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();

                apiModel = gson.fromJson(response, ApiModel.class);

                dataArrayList = apiModel.getData().getStatewise();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }


    private void getHistoryResponse() {
        history = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, URLS.UnOfficialHistoryApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
//                History[] history = gson.fromJson(response, History[].class);
//                Log.e(TAG, "onResponse: " + history[history.length - 1].getDay());

                historyModel = gson.fromJson(response, HistoryModel.class);
                history = historyModel.getData().getHistory();
                setTotalCount();
                sortHistory();
                setRecyclerView();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: ", error);
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void sortHistory() {


        ArrayList<Statewise> s2 = new ArrayList<>();
        currentDataList = history.get(history.size() - 1).getStatewise();
        s2 = history.get(history.size() - 2).getStatewise();

        Collections.sort(currentDataList, new Comparator<Statewise>() {
            @Override
            public int compare(Statewise o1, Statewise o2) {
                return o1.getState().compareTo(o2.getState());
            }
        });

        Collections.sort(s2, new Comparator<Statewise>() {
            @Override
            public int compare(Statewise o1, Statewise o2) {
                return o1.getState().compareTo(o2.getState());
            }
        });
        Log.e(TAG, "sortHistory: " + currentDataList.get(0).getState());
        Log.e(TAG, "sortHistory: " + s2.get(0).getState());
        for (int i = 0; i < currentDataList.size(); i++) {
            long diff = currentDataList.get(i).getConfirmed() - s2.get(i).getConfirmed();
            currentDataList.get(i).setDiff(diff);
        }

        Collections.sort(currentDataList, new Comparator<Statewise>() {
            @Override
            public int compare(Statewise o1, Statewise o2) {
                if (o1.getConfirmed() > o2.getConfirmed()) {
                    return -1;
                } else if (o1.getConfirmed() < o2.getConfirmed()) {
                    return 1;
                }
                return 0;
            }
        });
    }


    private void setTotalCount() {
        setTextFont();
        Total totalCount = history.get(history.size() - 1).getTotal();
        Total diffCount = history.get(history.size() - 2).getTotal();
        long confirm = totalCount.getConfirmed() - diffCount.getConfirmed();
        long recover = totalCount.getRecovered() - diffCount.getRecovered();
        long death = totalCount.getDeaths() - diffCount.getDeaths();


        if (confirm > 0) {
            String s = "+ " + confirm;
            country_confirmed_increased.setText(s);
        } else if (confirm < 0) {
            String s = "- " + Math.abs(confirm);
            country_confirmed_increased.setText(s);
        }

        if (recover > 0) {
            String s = "+ " + Math.abs(recover);
            country_recovered_increased.setText(s);
        } else if (recover < 0) {
            String s = "- " + Math.abs(recover);
            country_recovered_increased.setText(s);
        }

        if (death > 0) {
            String s = "+ " + Math.abs(death);
            country_dead_increased.setText(s);
        } else if (death < 0) {
            String s = "- " + Math.abs(death);
            country_dead_increased.setText(s);
        }


        country_confirmed_current.setText(String.valueOf(totalCount.getConfirmed()));
        country_active_current.setText(String.valueOf(totalCount.getActive()));
        country_recovered_current.setText(String.valueOf(totalCount.getRecovered()));
        country_dead_current.setText(String.valueOf(totalCount.getDeaths()));


    }

    private void setTextFont() {
        TextView confirmed = findViewById(R.id.country_confirmed_status);
        TextView active = findViewById(R.id.country_active_status);
        TextView recovered = findViewById(R.id.country_recovered_status);
        TextView dead = findViewById(R.id.country_dead_status);

        confirmed.setTypeface(Helper.getFontSb(context));
        active.setTypeface(Helper.getFontSb(context));
        recovered.setTypeface(Helper.getFontSb(context));
        dead.setTypeface(Helper.getFontSb(context));
        country_confirmed_current.setTypeface(Helper.getFontSb(context));
        country_active_current.setTypeface(Helper.getFontSb(context));
        country_recovered_current.setTypeface(Helper.getFontSb(context));
        country_dead_current.setTypeface(Helper.getFontSb(context));

    }

    private void search(SearchView searchView) {
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (stateAdapter != null) {
//                    moto.setVisibility(View.GONE);
                    stateAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setQuery("", false);
            searchView.setIconified(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(int position, Statewise statewise, View view) {
        statewise.setExpanded(!statewise.isExpanded());

        if (!searchView.isIconified()) {
            hideKeyboard();
        }
        stateAdapter.notifyItemChanged(position);
        recyclerView.smoothScrollToPosition(position);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) Objects.requireNonNull(context).getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = Objects.requireNonNull(getCurrentFocus());
        if (focusedView != null) {
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    private String daysLeft(String date) {
        int day = 0;
        int hh = 0;
        int mm = 0;
        try {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zz yyyy");
            Date oldDate = dateFormat.parse(date);
            Date cDate = new Date();
            assert oldDate != null;
            long timeDiff = oldDate.getTime() - cDate.getTime();
            day = (int) TimeUnit.MILLISECONDS.toDays(timeDiff);
            hh = (int) (TimeUnit.MILLISECONDS.toHours(timeDiff) - TimeUnit.DAYS.toHours(day));
            mm = (int) (TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));


        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (mm <= 60 && hh != 0) {
            if (hh <= 60 && day != 0) {
                return day + " Days left";
            } else {
                return hh + " Hours left";
            }
        } else {
            return mm + " Minutes left";
        }
    }


}
