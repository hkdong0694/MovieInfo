package com.example.movieinfo_mvp.View;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.movieinfo_mvp.Adapter.DailyOfficeAdapter;
import com.example.movieinfo_mvp.Network.Model.DailyOffice.BoxOfficeResult;
import com.example.movieinfo_mvp.Network.Model.DailyOffice.DailyBoxOfficeList;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.MovieDetail;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.Network.Model.DailyOffice.Result;
import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDetailRepository;
import com.example.movieinfo_mvp.Repository.MovieListRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.text.StringEscapeUtils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    /**
     * A simple {@link Fragment} subclass.
     */
    public class DailyMovieFragment extends Fragment {

        private int mYear, mMonth, mDay;
        private String dateSet;
        private RecyclerView recyclerView;
        private MovieInfoOpenApiService movieInfoOpenApiService;
        private ProgressBar progressBar;
        private DailyOfficeAdapter dailyOfficeAdapter;
        private LinkedHashMap<String, RecyclerViewModel> hashMap;
        private LinearLayoutManager linearLayoutManager;
        private SharedPreferences sf;
        private int index = 0;
        private Gson gson = new GsonBuilder().create();
        private View view;
        private List<RecyclerViewModel> listData;

        public DailyMovieFragment() {
            hashMap = new LinkedHashMap<>();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_daily_movie, container, false);
            dailyOfficeAdapter = new DailyOfficeAdapter(view.getContext());
            progressBar = view.findViewById(R.id.prog);
            recyclerView = view.findViewById(R.id.dailyrecyclerview);
            Log.e("Start","DailyMovie Fragment  불림");
            calendatinit();
            hashMap.clear();
            dailyOfficeAdapter.clear();
            MovieListRepository movieListRepository = new MovieListRepository();
            movieInfoOpenApiService = movieListRepository.initBuild();
            sf = getContext().getSharedPreferences("Movielike", Context.MODE_PRIVATE);
            boxofficesearch();

            //imagebutton 클릭 할때 발생하는 리스너
            dailyOfficeAdapter.setOnclickListener(new DailyOfficeAdapter.OnclickListener() {
                @Override
                public void onclick(View v, int pos, ImageButton imageButton) {
                    SharedPreferences.Editor editor = sf.edit();
                    RecyclerViewModel recyclerViewModel = dailyOfficeAdapter.get(pos);
                    listData = new ArrayList<>();
                    listData = onSearchData();
                    if(listData.size() == 0){
                        imageButton.setImageResource(R.drawable.ic_favorite_blacks_24dp);
                        onStoreData(listData,recyclerViewModel);
                    } else {
                        boolean check = true;
                        for(RecyclerViewModel arrayModel : listData){
                            if(arrayModel.getMovieNm().equals(recyclerViewModel.getMovieNm())){
                                check = false;
                                break;
                            }
                        }
                        if(!check) {
                            imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
                            onRemoveData(listData,recyclerViewModel);
                        } else {
                            imageButton.setImageResource(R.drawable.ic_favorite_blacks_24dp);
                            onStoreData(listData,recyclerViewModel);
                        }
                    }
                }
            });
            return view;
        }

        public void onRemoveData(List<RecyclerViewModel> listData, RecyclerViewModel recyclerViewModel){
            int index = 0;
            for(RecyclerViewModel k : listData){
                if(k.getMovieNm().equals(recyclerViewModel.getMovieNm())){
                    listData.remove(index);
                    break;
                }
                index++;
            }
            gson = new GsonBuilder().create();
            Type listT = new TypeToken<ArrayList<RecyclerViewModel>>() {}.getType();
            String json = gson.toJson(listData,listT);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("like",json);
            editor.commit();
        }

        public void onStoreData(List<RecyclerViewModel> listData, RecyclerViewModel recyclerViewModel){
            List<RecyclerViewModel> a;
            if(listData.size() == 0) a = new ArrayList<>();
            else a = listData;
            a.add(recyclerViewModel);
            gson = new GsonBuilder().create();
            Type listT = new TypeToken<ArrayList<RecyclerViewModel>>() {}.getType();
            String json = gson.toJson(a,listT);
            SharedPreferences.Editor editor = sf.edit();
            editor.putString("like",json);
            editor.commit();
        }

        public List<RecyclerViewModel> onSearchData(){
            String strModel = sf.getString("like",null);
            List<RecyclerViewModel> datas = new ArrayList<>();
            if(strModel != null){
                Type listType = new TypeToken<ArrayList<RecyclerViewModel>>() {}.getType();
                datas = gson.fromJson(strModel,listType);
            }
            return datas;
        }

        //날짜와 함꼐 영화진흥원 api 접근하여 날짜에 맞는 영화 제목 뽑아오기 (Presenter)
        public void boxofficesearch() {
            //final long start = System.currentTimeMillis();
            movieInfoOpenApiService.getBoxOffice(MovieConst.key,dateSet).enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    //Log.e("Start",call.request().url().toString() +" 9");
                    if (response.isSuccessful()) {
                        //Log.e("Start",System.currentTimeMillis() - start + " l");
                        Result result = response.body();
                        BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                        List<DailyBoxOfficeList> dailyBoxOfficeLists = boxOfficeResult.getDailyBoxOfficeLists();
                        for (DailyBoxOfficeList dailyBoxOfficeList : dailyBoxOfficeLists) {
                            String dY = dateSet.substring(0, 4);
                            hashMap.put(dailyBoxOfficeList.getMovieNm(), new RecyclerViewModel(dailyBoxOfficeList.getRank(),dailyBoxOfficeList.getMovieNm(), dailyBoxOfficeList.getOpenDt(), dailyBoxOfficeList.getAudiAcc(), dY, dY));
                        }
                        MovieDetailRepository movieDetailRepository = new MovieDetailRepository();
                        movieInfoOpenApiService = movieDetailRepository.initBuild();
                        naverSearchApi();
                    } else {
                        Log.e("Start",call.request().url().toString() + " ");
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    //Log.e("Start",call.request().url().toString());
                }
            });
        }

        //네이버 검색 api를 통하여 recyclerview adapter에 붙이기
        public void naverSearchApi() {
            index = 0;
            progressBar.setVisibility(View.VISIBLE);
            linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(dailyOfficeAdapter);
            try {
                for (final String title : hashMap.keySet()) {
                    movieInfoOpenApiService.getMovies(title, 100, Integer.parseInt(hashMap.get(title).getEndYear()) - 100, Integer.parseInt(hashMap.get(title).getEndYear())).enqueue(new Callback<MovieDetail>() {
                        @Override
                        public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                            recyclerView.setNestedScrollingEnabled(false);
                            if (response.isSuccessful()) {
                                MovieDetail movieDetail = response.body();
                                List<Item> items = movieDetail.getItems();
                                for (Item items1 : items) {
                                    String naverTitle = StringEscapeUtils.unescapeHtml4(items1.getTitle());
                                    if ((naverTitle.length() == title.length() + 7)) {
                                        index++;
                                        RecyclerViewModel recyclerViewModel = hashMap.get(title);
                                        recyclerViewModel.setImage(items1.getImage());
                                        recyclerViewModel.setSubtitle(items1.getSubtitle());
                                        recyclerViewModel.setLink(items1.getLink());
                                        recyclerViewModel.setDirector(items1.getDirector());
                                        recyclerViewModel.setActor(items1.getActor());
                                        recyclerViewModel.setUserRating(items1.getUserRating());
                                        recyclerViewModel.setPubDate(items1.getPubDate());
                                        hashMap.put(title, recyclerViewModel);
                                        if (hashMap.size() == index) {
                                            for (String key : hashMap.keySet())
                                                dailyOfficeAdapter.add(hashMap.get(key));
                                            progressBar.setVisibility(View.GONE);
                                        }
                                        break;
                                    }
                                }
                            }
                            recyclerView.setNestedScrollingEnabled(true);
                        }
                        @Override
                        public void onFailure(Call<MovieDetail> call, Throwable t) {

                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //현재 년, 월, 일을 최신화 해주는 함수 (Presenter)
        public void calendatinit() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long mNow = System.currentTimeMillis();
            Date mDate = new Date(mNow);
            dateFormat.format(mDate);
            mYear = dateFormat.getCalendar().get(Calendar.YEAR);
            mMonth = dateFormat.getCalendar().get(Calendar.MONTH) + 1;
            mDay = dateFormat.getCalendar().get(Calendar.DAY_OF_MONTH) - 1;
            dateSet = dateFormat(mYear, mMonth, mDay);
        }

        //날짜를 형식에 맞게 바꿔주는 함수 (Presenter)
        public String dateFormat(int year, int month, int day) {
            String result = "";
            if (month <= 9 && day <= 9) result = String.valueOf(year) + "0" + month + "0" + day;
            else if (day <= 9) result = String.valueOf(year) + month + "0" + day;
            else if (month <= 9) result = String.valueOf(year) + "0" + month + day;
            else result = String.valueOf(year) + month + day;
            return result;
        }

}
