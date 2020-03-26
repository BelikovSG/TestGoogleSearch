package com.example.testgooglesearch.main;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testgooglesearch.R;
import com.example.testgooglesearch.app.App;
import com.example.testgooglesearch.model.room.Data;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainInterface.view {


    private MainInterface.presenter presenter;
    private RVAdapter rvAdapter;
    private SharedPreferences myPreferences;
    private static final String QUERY = "query";

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.text_input)
    EditText editText;
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = App.getComponent().getMainPresenter();
        presenter.setView(this);
        initViews();

        myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        String lastQuery = myPreferences.getString(QUERY, "");

        if (!lastQuery.equals("")) {
            presenter.getFromDB(lastQuery);
        }

    }

    private void initViews() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        button.setOnClickListener(v -> {
            SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putString(QUERY, editText.getText().toString());
            myEditor.commit();
            presenter.search(editText.getText().toString());
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().equals("")) button.setEnabled(false);
                else button.setEnabled(true);
            }
        });
    }

    @Override
    public void showResult(List<Data> dataList) {
        rvAdapter = new RVAdapter(this, dataList);
        rv.setAdapter(rvAdapter);


    }

    @Override
    public void setSearchField(String lastQuery) {
        editText.setText(lastQuery);
        button.setEnabled(true);
    }


}
