package com.example.extendedassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.extendedassignment.databinding.FragmentDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    FragmentDetailBinding binding;
    private DetailViewModel detailViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    // TODO: Rename and change types of parameters
    private Contact mParam1;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(Contact contact) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1,contact);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentDetailBinding.inflate(inflater,container,false);

        // creating an instance of detailViewModel, for interaction b/w fragment and repository
        detailViewModel=new ViewModelProvider(this).get(DetailViewModel.class);

        binding.ibGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email[]=new String[1];
                email[0]=mParam1.getEmail();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });
        binding.ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNo=mParam1.getPhoneNo();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + PhoneNo));
                startActivity(intent);
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment=InsertFragment.newInstance("updateMode",mParam1);
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.fragmentContainer,fragment).addToBackStack(null).commit();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  detailViewModel.deleteAll();
                  Toast.makeText(getContext(), "All Item deleted", Toast.LENGTH_SHORT).show();
                  getFragmentManager().popBackStackImmediate();


            }
        });

        // inflating data received from MainFragment
        binding.tvDetailName.setText(mParam1.getName());
        binding.tvDetailPhoneNo.setText(mParam1.getPhoneNo());
        binding.tvDetailEmail.setText(mParam1.getEmail());
        return binding.getRoot();
    }
}