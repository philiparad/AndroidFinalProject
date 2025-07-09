package com.example.finalproject.ui;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.example.finalproject.R;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView profileImage = view.findViewById(R.id.profile_image);
        TextView username = view.findViewById(R.id.username_display);

        SharedPreferences prefs = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE);
        String name = prefs.getString("username", "משתמש");
        String imageUri = prefs.getString("image_uri", null);

        username.setText(name);
        if (imageUri != null) {
            profileImage.setImageURI(Uri.parse(imageUri));
        }
        return view;
    }
}