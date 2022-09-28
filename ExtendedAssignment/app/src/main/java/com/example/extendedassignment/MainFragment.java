package com.example.extendedassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements ItemClick {
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private RVAdapter adapter;
    private FloatingActionButton button;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }
    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        mainViewModel=new ViewModelProvider(this).get(MainViewModel.class);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new RVAdapter(getContext(),this::onItemClick);
        recyclerView.setAdapter(adapter);
        button=view.findViewById(R.id.btnFloating);
        mainViewModel.contactList.observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.updateList(contacts);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Fragment insertFragment=InsertFragment.newInstance("addMode",new Contact("","",""));
                  FragmentTransaction ft=getFragmentManager().beginTransaction();
                  ft.replace(R.id.fragmentContainer,insertFragment)
                          .addToBackStack(null).commit();

            }
        });
        // we also delete and update fragment via swiping item,
        // on RIGHT swipe it will be deleted and on LEFT swipe it will be updated.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                if(direction==ItemTouchHelper.RIGHT){
                    //delete
                    mainViewModel.delete(adapter.contactList.get(viewHolder.getAdapterPosition()));
                    Toast.makeText(getContext(), "Item deleted!", Toast.LENGTH_SHORT).show();
                }
                else{

                    Contact contact=adapter.contactList.get(viewHolder.getAdapterPosition());
                    Fragment fragment=InsertFragment.newInstance("updateMode",contact);
                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                    ft.add(R.id.fragmentContainer,fragment)
                            .addToBackStack(null).commit();
                }
            }
        }).attachToRecyclerView(recyclerView);
        return view;
    }

    // ItemClick listener function
    @Override
    public void onItemClick(Contact contact) {
        // detail fragment code
        Fragment detailFragment=DetailFragment.newInstance(contact);
        FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,detailFragment)
                .addToBackStack(null).commit();
    }

}