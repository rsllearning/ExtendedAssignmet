package com.example.extendedassignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.extendedassignment.databinding.FragmentInsertBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertFragment extends Fragment {
     FragmentInsertBinding binding;
     private Contact contact;
     private InsertViewModel insertViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Contact mParam2;

    public InsertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment InsertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertFragment newInstance(String param1,Contact contact) {
        InsertFragment fragment = new InsertFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putParcelable(ARG_PARAM2, contact);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getParcelable(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentInsertBinding.inflate(inflater,container,false);
        View view=inflater.inflate(R.layout.fragment_insert, container, false);
        insertViewModel = new ViewModelProvider(this).get(InsertViewModel.class);

        // we are using @mParam1 to identify whether it is updateMode or insertMode,
        // because we are using same fragment for updating the @Contact as well as inserting @Contact
        String type=mParam1;
        if(type.equals("updateMode")){
            //update data
            getActivity().setTitle("update contact");
            binding.evName.setText(mParam2.getName());
            binding.evEmail.setText(mParam2.getEmail());
            binding.evPhoneNo.setText(mParam2.getPhoneNo());
            binding.btnSubmit.setText("Update");
            binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String na=binding.evName.getText().toString();
                    String em=binding.evEmail.getText().toString();
                    String ph=binding.evPhoneNo.getText().toString();
                    mParam2.setName(na);
                    mParam2.setEmail(em);
                    mParam2.setPhoneNo(ph);
                    insertViewModel.update(mParam2);
                    Toast.makeText(getContext(),"Item updated",Toast.LENGTH_SHORT).show();
                    getFragmentManager().popBackStackImmediate();
                }
            });
        }
        else {
            getActivity().setTitle("Add contact");
            binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textName = binding.evName.getText().toString();
                    String textEmail = binding.evEmail.getText().toString();
                    String textPhone = binding.evPhoneNo.getText().toString();
                    if (!(textName.equals("") || textEmail.equals("") || textPhone.equals(""))) {
                        contact = new Contact(binding.evName.getText().toString(), binding.evEmail.getText().toString(), binding.evPhoneNo.getText().toString());
                        insertViewModel.insert(contact);
                        getFragmentManager().popBackStackImmediate();
                    } else
                        Toast.makeText(getContext(), "Empty entries! please fill it.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return binding.getRoot();
    }
}