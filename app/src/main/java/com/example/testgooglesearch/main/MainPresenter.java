package com.example.testgooglesearch.main;

import android.util.Log;

import com.example.testgooglesearch.app.App;
import com.example.testgooglesearch.model.room.Data;
import com.example.testgooglesearch.model.response.Item;
import com.example.testgooglesearch.model.response.ResponseBody;
import com.example.testgooglesearch.model.response.Website;
import com.example.testgooglesearch.model.room.DataDao;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainInterface.presenter {

    private MainInterface.view view;
    private List<Data> dataList = new ArrayList<>();
    private DataDao dataDao = App.getAppDatabase().getDataDao();
    private static final String TAG = "Presenter";
    private static final String KEY = "AIzaSyC4l3vMs-VwJ96vsEkYy5VDkMw38sH-5xc";
    final String SID = "018152255601381891864:plcshivihve";

    @Override
    public void search(final String q) {
        App.getApi().getData(KEY, SID, q).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    makeData(q, response.body().getItems());
                    saveData(dataList);
                    view.showResult(dataList);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "response is failure");
            }
        });
    }

    @Override
    public void getFromDB(String lastQuery) {
        view.setSearchField(lastQuery);
        Disposable disposable = getData(lastQuery).observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    view.showResult(data);
                }, throwable -> {

                });
    }

    private Single<List<Data>> getData(String lastQuery) {
        return Single.create((SingleOnSubscribe<List<Data>>) emitter -> {
            dataList = dataDao.getAllDataWithQuery(lastQuery);
            emitter.onSuccess(dataList);
        }).subscribeOn(Schedulers.io());
    }

    private void saveData(List<Data> dataList) {
        Disposable disposable = saveDataList(dataList).observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    Log.d(TAG, "save data");
                }, throwable -> {
                    Log.d(TAG, "save data " + throwable);
                });
    }

    private Single<List<Data>> saveDataList(final List<Data> dataList) {
        return Single.create((SingleOnSubscribe<List<Data>>) emitter -> {
            dataDao.insertAll(dataList);
            emitter.onSuccess(dataList);
            Log.e(TAG, "added to bd");
        }).subscribeOn(Schedulers.io());

    }

    private void makeData(String q, List<Item> items) {
        if (dataList != null) dataList.clear();
        int i = 1;
        String prefix;
        if (items != null) {
            for (Item item : items) {
                prefix = "" + i + ". ";
                if (item.getPagemap() == null) {
                    dataList.add(new Data(q, prefix + item.getTitle(),
                            item.getLink(), item.getSnippet()));
                    return;
                }
                List<Website> w = item.getPagemap().getWebsite();
                if (w != null) {
                    dataList.add(new Data(q, prefix + w.get(0).getName(),
                            item.getLink(), w.get(0).getDescription()));
                } else {
                    dataList.add(new Data(q, prefix + item.getTitle(),
                            item.getLink(), item.getSnippet()));
                }
                i++;
            }
        } else {
            dataList.add(new Data(q, "По Вашему запросу ни чего не найдено", "", ""));
        }
    }

    @Override
    public void setView(MainInterface.view view) {
        this.view = view;
    }
}
