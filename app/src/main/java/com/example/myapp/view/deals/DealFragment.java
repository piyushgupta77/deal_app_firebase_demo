package com.example.myapp.view.deals;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapp.R;
import com.example.myapp.base.BaseFragment;
import com.example.myapp.di.AppDI;
import com.example.myapp.framework.di.FrameworkDI;
import com.example.myapp.framework.model.Deal;
import com.example.myapp.framework.presenter.deals.DealPresenter;
import com.example.myapp.framework.presenter.deals.DealView;
import com.example.myapp.network_library.network.client.Constants;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DealFragment extends BaseFragment implements DealView {

    private static final String TAG = DealFragment.class.getCanonicalName();
    private RecyclerView recyclerView;

    private OnListFragmentInteractionListener mListener;

    @Inject
    DealPresenter dealPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DealFragment() {
    }

    public static DealFragment newInstance(String dealCategory) {
        DealFragment fragment = new DealFragment();
        Bundle args = new Bundle();
        args.putString(Constants.BUNDLE_KEYS.CATEGORY_TYPE, dealCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deal_list, container, false);

        // Set the adapter
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppDI.getFragmentComponent(this).inject(this);
        dealPresenter.bind(this);

        if(null != getArguments()) {
            String category = getArguments().getString(Constants.BUNDLE_KEYS.CATEGORY_TYPE);
            dealPresenter.getDeals(category);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setDeals(List<Deal> dealList) {
        recyclerView.setAdapter(new MyDealRecyclerViewAdapter(dealList, mListener));
    }

    @Override
    public void showError() {
        Log.d(TAG, "error in getting deals");
    }

    @Override
    public void finish() {

    }

    @Override
    public void showProgress(boolean toShow) {

    }

    @Override
    public void showToast(String msg) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Deal item);
    }
}
