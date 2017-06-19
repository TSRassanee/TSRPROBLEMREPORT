package com.tsr.tsrproblemreport.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tsr.tsrproblemreport.MusicActivity2;
import com.tsr.tsrproblemreport.R;
import com.tsr.tsrproblemreport.activity.ManageSharedPreferences;
import com.tsr.tsrproblemreport.other.CircleTransform;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String pictureurl="",name="";
    TextView txtposition_intro=null;
    private ImageView imgNavHeaderBg, imgProfile;
    TextView textView1,textView2,boss,namegroub;
    String urlNavHeaderBg = "",urlProfileImg = "";
    String gg;
    String name_intro="";

    private OnFragmentInteractionListener mListener;
    Button button7,button8,button9,button10;
    public HomeFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //util=new Utils();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_home, container, false);


        View view = inflater.inflate(R.layout.fragment_home,
                container, false);


        textView1 = (TextView) view.findViewById(R.id.txtname_intro);
        textView2= (TextView) view.findViewById(R.id.txtposition_intro);
        imgNavHeaderBg = (ImageView) view.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) view.findViewById(R.id.img_profile);
        boss= (TextView) view.findViewById(R.id.boss);
        namegroub= (TextView) view.findViewById(R.id.namegroub);


        // gg= ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity.class.getSimpleName(),"ggg");
        textView1.setText(ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity2.class.getSimpleName(), "namethai"));
        textView2.setText(ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity2.class.getSimpleName(),"position"));
        boss.setText(ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity2.class.getSimpleName(), "supervisor"));
        namegroub.setText(ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity2.class.getSimpleName(),"name_supervisor"));
        // textView1.setText("aaa");

        urlNavHeaderBg=ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity2.class.getSimpleName(),"backgroud");
        urlProfileImg=ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity2.class.getSimpleName(),"picture");

        //textView2=ManageSharedPreferences.LoadPreferences(this,MusicActivity, );


        loadNavHeader();
        return view;


    }



    private void loadNavHeader() {



        //  profileName.setText(namethai);
        //  positionprofile.setText(position);
        // String strurl=Utils.UPLOAD_URL+pictureurl;
        // loading header background image
        Glide.with(this).load("http://app.thiensurat.co.th/assanee/upload/"+urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load("http://app.thiensurat.co.th/assanee/upload/"+urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(getActivity()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        //navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }
    private  void sale(){




    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
